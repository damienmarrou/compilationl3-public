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
    }

    //TODO : ajouter dans les 6 methodes la localisation correspondante
    //TODO : implementer toutes les conditions du TP


    public Ts getTableGlobale() {
        return tableGlobale;
    }

    @Override
    public Void visit(SaDecVar node) {
        if (tableLocale != null) {
            location = Location.Local;
            //Var locale déjà déclarée
            // -- Il n’y a pas deux variables identiques déclarées dans une même portée
            // -- Il n’est pas possible de déclarer une variable locale qui a le même nom qu’un argument.
            if (tableLocale.variables.containsKey(node.getNom()))
                throw new TsException("Variable/Arg loc déjà déclarée");
            if (tableLocale.variables.size() < argsLength)
                tableLocale.addParam(node.getNom());
            else tableLocale.addVar(node.getNom(), 1);
        } else {
            //Var globale déjà déclarée
            // -- Il n’y a pas deux variables identiques déclarées dans une même portée
            location = Location.Global;
            if (tableGlobale.variables.containsKey(node.getNom())) throw new TsException("Variable glob déjà déclarée");
            tableGlobale.addVar(node.getNom(), 1);
        }
        return super.visit(node);
    }

    @Override
    public Void visit(SaDecTab node) {
        if (location != Location.Global) throw new TsException("Tab non global");
        //Var déjà déclarée
        if (tableGlobale.variables.containsKey(node.getNom())) throw new TsException("Variable globale tableau déjà déclaré");
        // -- Un tableau est toujours une variable globale
        tableGlobale.addVar(node.getNom(), node.getTaille());
        return super.visit(node);
    }

    //TODO :  ajouter les arguments et les variables de la fonction dans la table locale
    @Override
    public Void visit(SaDecFonc node) {
        if (location != Location.Global) throw new TsException("fonction non global");
        location = Location.Local;
        //Fonction déjà déclarée
        if (!tableGlobale.fonctions.containsKey("main") && tableGlobale.fonctions.size() > 0)
            throw new TsException("pas de main");
        // --  Il n’y a pas deux fonctions identiques déclarées à des endroits diﬀérents
        if (tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("Fonction déjà déclarée");
        if (tableGlobale.variables.containsKey(node.getNom()))
            throw new TsException("Fonction avec le même nom qu'une variable globale");
        if (node.getParametres() == null) {
            argsLength = 0;
        } else argsLength = node.getParametres().length();
        //Ajout de fonction dans table globale
        tableLocale = new Ts();
        tableGlobale.addFct(node.getNom(), argsLength, tableLocale, node);
        return super.visit(node);
    }

    @Override
    public Void visit(SaVarSimple node) {
        TsItemVar var = null;
        /*  -- Toute variable utilisée est déclarée en tant que (recherche dans l’ordre)
        : — (1) Variable locale ou (2) argument de fonction ou (3) variable globale.*/
        if(tableLocale != null){
            var = tableLocale.variables.get(node.getNom());
            location = Location.Local;
        }
        if (var == null) {
            var = tableGlobale.variables.get(node.getNom());
        }
        if (var == null) throw new TsException("Variable non définie");
        //-- Les entiers ne peuvent jamais être indicés
        if (var.getTaille() > 1) throw new TsException("Entier indicé alors qu'il ne faut pas");
        return super.visit(node);
    }

    @Override //Peut pas faire : Les tableaux ne peuvent jamais être utilisés sans indice
    public Void visit(SaVarIndicee node) {
        TsItemVar var = null;
        if(tableLocale != null) {
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
        location = Location.Global;
        // -- Toute fonction appelée doit être déclarée avant dans le programme.
        if (!tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("Fonction non declarée");
        // --  Il existe une fonction sans arguments qui s’appelle main
        // if (!tableGlobale.fonctions.containsKey("main")) throw new TsException("Il n'existe aucune fonction main");
        if (tableGlobale.fonctions.containsKey("main") && tableLocale.fonctions.get("main").getTaille() > 0)
            throw new TsException("Trop d'argument pour main");


        // -- Le nombre d’arguments réels passés à la fonction appelée est identique au nombre d’arguments formels dans la déclaration
        if (tableLocale.fonctions.get(node.getNom()).nbArgs != node.getArguments().length())
            throw new TsException("Le nombre d'arguments passés est différents du nombre attendu");

        return super.visit(node);
    }

    static class TsException extends RuntimeException {

        TsException(String message) {
            super(message);
        }
    }
}
