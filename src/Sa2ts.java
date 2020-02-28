import sa.*;
import ts.Ts;
import ts.TsItemVar;

public class Sa2ts extends SaDepthFirstVisitor<Void> {

    Ts tableGlobale = new Ts();
    Ts tableLocale = null;
    Ts location = tableGlobale;
    int argsLength = 0;
    boolean isParam = false;

    public Sa2ts(SaNode saRoot) {
        visit((SaProg) saRoot);
        checkMainExists();
    }

    //TODO : faire une méthode qui renvoie la localisation
    //TODO : vérifier si addParam et add.Var sont bien utilisé comme il faut


    public Ts getTableGlobale() {
        return tableGlobale;
    }

    private boolean isLocalTable() {
        return location != tableGlobale;
    }

    @Override
    public Void visit(SaDecVar node) {
        /*if (tableGlobale.variables.containsKey(node.getNom()))
            throw new Sa2ts.TsException("Variable avec le même nom");
        if (tableLocale != null) {
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

        node.tsItem = isParam
                ? location.addParam(node.getNom())
                : location.addVar(node.getNom(), 1);
        return null;*/

        TsItemVar itemVar = location.getVar(node.getNom());
        if (itemVar != null)
            if (isLocalTable())
                throw new TsException(node.getNom() + " Déjà définie en local");
            else
                throw  new TsException(node.getNom() + " Déjà définie en global");

        node.tsItem = isParam
                ? location.addParam(node.getNom())
                : location.addVar(node.getNom(), 1);

        return null;
    }

    @Override
    public Void visit(SaDecTab node) {
        //location = Location.Global;
        /*if (isLocalTable()) throw new TsException("tableau non global");

        if (tableGlobale.variables.containsKey(node.getNom()))
            throw new TsException("Variable globale tableau déjà déclaré");
        node.tsItem = location.addVar(node.getNom(), node.getTaille());
        return null;*/

        if (isLocalTable()) throw new TsException("Pas de déclaration de tableau en lcoal");
        if (tableGlobale.getVar(node.getNom()) != null) throw new RuntimeException("Dans table global, déja definie");

        node.tsItem = location.addVar(node.getNom(), node.getTaille());

        return null;
    }

    @Override
    public Void visit(SaDecFonc node) {

        if (tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("Fonction déjà déclaré");
        node.tsItem = tableGlobale.addFct(
                node.getNom(),
                node.getParametres() != null ? node.getParametres().length() : 0,
                new Ts(),
                node);

        location = node.tsItem.getTable();
        isParam = true;
        if(node.getParametres() != null) node.getParametres().accept(this);
        isParam = false;
        if(node.getVariable() != null) node.getVariable().accept(this);
        node.getCorps().accept(this);
        location = tableGlobale;


       /* if (tableGlobale.variables.containsKey(node.getNom()))
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
        return super.visit(node);*/
        return null;
    }

    @Override
    public Void visit(SaVarSimple node) {
        /*TsItemVar var;
        /*  -- Toute variable utilisée est déclarée en tant que (recherche dans l’ordre)
        : — (1) Variable locale ou (2) argument de fonction ou (3) variable globale.


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
        return null;*/


        if(isLocalTable() && location.variables.containsKey(node.getNom())) node.tsItem = location.variables.get(node.getNom());
        else node.tsItem = tableGlobale.getVar(node.getNom());
        if(node.tsItem == null) throw new TsException("Pas définie");
        if(node.tsItem.getTaille() > 1) throw  new TsException("Entier indicé alors qu'il ne faut pas");
        return null;
    }

    @Override
    public Void visit(SaVarIndicee node) {
       /* TsItemVar var = null;
        if (tableLocale != null) {
            var = tableLocale.variables.get(node.getNom());
        }
        if (var == null) {
            var = tableGlobale.variables.get(node.getNom());
        }
        if (var == null) throw new TsException("Variable glob non définie");

        if (var.getTaille() < 1) throw new TsException("tableau sans indice");
        return super.visit(node);*/

       if(isLocalTable() && location.variables.containsKey(node.getNom())) node.tsItem = location.variables.get(node.getNom());
       else node.tsItem = tableGlobale.getVar(node.getNom());
       if(node.tsItem == null) throw  new TsException("Pas définie");
       if(node.tsItem.getTaille() < 1) throw  new TsException("Tableau sans indice");

        return null;
    }

    @Override
    public Void visit(SaAppel node) {
        if (!tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("Fonction non declarée");
        else node.tsItem = tableGlobale.getFct(node.getNom());
        int length;
        if (node.getArguments() != null) {
            length = node.getArguments().length();
        }
        else length = 0;
        if (argsLength > 0) node.getArguments().accept(this);
        if (length != node.tsItem.nbArgs) {

            throw new TsException("Pas le bon nombre d'argument pour la fonction");
        }
        return null;
    }

    static class TsException extends RuntimeException {
        TsException(String message) {
            super(message);
        }
    }

    private void checkMainExists() {
        if (!tableGlobale.fonctions.containsKey("main"))
            throw new TsException("Il n'y a pas de fonction main");
        if (tableGlobale.fonctions.get("main").getNbArgs() != 0) throw new TsException("La fonction main doit avoir aucun argument");
    }
}
