import sa.SaAppel;
import sa.SaDecFonc;
import sa.SaDecTab;
import sa.SaDecVar;
import sa.SaDepthFirstVisitor;
import sa.SaNode;
import sa.SaProg;
import sa.SaVarIndicee;
import sa.SaVarSimple;
import ts.Ts;
import ts.TsItemVar;

public class Sa2ts extends SaDepthFirstVisitor<Void> {

    Ts tableGlobale = new Ts();
    Ts tableLocale = null;
    Location location;
    int argsLength = 0;

    public Sa2ts(SaNode saRoot) {
        location = Location.Global;
        visit((SaProg) saRoot);
        checkMainExists();
    }

    //TODO : faire une méthode qui renvoie la localisation
    //TODO : vérifier si addParam et add.Var sont bien utilisé comme il faut


    public Ts getTableGlobale() {
        return tableGlobale;
    }

    @Override
    public Void visit(SaDecVar node) {
        //Location loc = location;
        if (tableGlobale.variables.containsKey(node.getNom()))
            throw new Sa2ts.TsException("Variable avec le même nom");
       // tableGlobale.addVar(node.getNom(), 1);
        if (tableLocale != null) {
            // location = Location.Local;
            if (tableLocale.variables.containsKey(node.getNom()))
                throw new Sa2ts.TsException("variable locale avec le même nom");
            if (tableLocale.variables.size() < argsLength) {
                tableLocale.addParam(node.getNom());
            } else {
                tableLocale.addVar(node.getNom(), 1);
            }
        } else {
            tableGlobale.addVar(node.getNom(), 1);
        }
        //location = loc;
        return super.visit(node);
    }

    @Override
    public Void visit(SaDecTab node) {
        //location = Location.Global;
        if (tableLocale != null) throw new TsException("tableau non globale");
        if (tableGlobale.variables.containsKey(node.getNom()))
            throw new TsException("Variable globale tableau déjà déclaré");
        tableGlobale.addVar(node.getNom(), node.getTaille());
        return super.visit(node);
    }

    @Override
    public Void visit(SaDecFonc node) {

        if (tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("fonction déjà déclaré");
        if (tableGlobale.variables.containsKey(node.getNom()))
            throw new TsException("Fonction avec le même nom qu'une variable globale");
        if (node.getParametres() == null) {
            argsLength = 0;
        } else argsLength = node.getParametres().length();
        //Ajout de fonction dans table globale
        tableLocale = new Ts();
        tableGlobale.addFct(node.getNom(), argsLength, tableLocale, node);
        if (!tableGlobale.fonctions.containsKey("main") && tableGlobale.fonctions.size() > 1)
            throw new TsException("pas de main sans arguments" + tableGlobale.fonctions.size());
        if (tableGlobale.fonctions.containsKey("main") && !(tableGlobale.fonctions.get("main").getNbArgs() == 0))
            throw new TsException("Arguments dans main pas bon");
        return super.visit(node);
    }

    @Override
    public Void visit(SaVarSimple node) {
        TsItemVar var;
        /*  -- Toute variable utilisée est déclarée en tant que (recherche dans l’ordre)
        : — (1) Variable locale ou (2) argument de fonction ou (3) variable globale.*/
        if (tableGlobale.variables.containsKey(node.getNom())) {
            var = tableGlobale.variables.get(node.nom);
        } else if (tableLocale != null) {
            var = tableLocale.variables.get(node.getNom());
            //location = Location.Local;
        } else {
            var = tableGlobale.variables.get(node.getNom());
        }
        if (var == null)
            throw new TsException("Variable non définie\n local.var " + tableLocale.variables + "\nglob.var " + tableGlobale.variables);

        //-- Les entiers ne peuvent jamais être indicés
        if (var.getTaille() > 1) throw new TsException("Entier indicé alors qu'il ne faut pas");
        return super.visit(node);
    }

    @Override
    public Void visit(SaVarIndicee node) {
        TsItemVar var = null;
        if (tableLocale != null) {
            var = tableLocale.variables.get(node.getNom());
        }
        if (var == null) {
            var = tableGlobale.variables.get(node.getNom());
        }
        if (var == null) throw new TsException("Variable glob non définie");

        if (var.getTaille() < 1) throw new TsException("tableau sans indice");
        return super.visit(node);
    }

    @Override
    public Void visit(SaAppel node) {
        if (!tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("Fonction non declarée");
        int length = 0;
        if (node.getArguments() != null) {
            length = node.getArguments().length();
        }
        if (length != tableGlobale.fonctions.get(node.getNom()).getNbArgs()) {

            throw new TsException("Pas le bon nombre d'argument pour la fonction");
        }
        return super.visit(node);
    }

    static class TsException extends RuntimeException {
        TsException(String message) {
            super(message);
        }
    }


    private void checkMainExists() {
        if (!tableGlobale.fonctions.containsKey("main"))
            throw new TsException("The main function does nit exist.");
        if (tableGlobale.fonctions.get("main").getNbArgs() != 0) throw new TsException("pas de main sans argument");
    }
}
