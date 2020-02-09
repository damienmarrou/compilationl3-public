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
    int argsLength = 0;

    public Sa2ts(SaNode saRoot) {
        visit((SaProg) saRoot);
    }

    public Ts getTableGlobale() {
        return tableGlobale;
    }

    @Override
    public Void visit(SaDecVar node) {

        if (tableLocale != null) {
            if(tableLocale.variables.containsKey(node.getNom())) throw new RuntimeException("Variable/Arg loc déjà déclarée");
            if (tableLocale.variables.size() < argsLength)
                tableLocale.addParam(node.getNom());
            else tableLocale.addVar(node.getNom(), 1);
        } else {
            if(tableGlobale.variables.containsKey(node.getNom())) throw new RuntimeException("Variable glob déjà déclarée");
            tableGlobale.addVar(node.getNom(), 1);
        }
        return super.visit(node);
    }

    @Override
    public Void visit(SaDecTab node) {
        if(tableLocale != null) throw new RuntimeException("Tab non global");
        tableGlobale.addVar(node.getNom(), node.getTaille());
        return super.visit(node);
    }

    @Override
    public Void visit(SaDecFonc node) {
        if(tableGlobale.fonctions.containsKey(node.getNom())) throw new RuntimeException("Fonction déjà déclarée");
        if (node.getParametres() == null) {
            argsLength = 0;
        } else argsLength = node.getParametres().length();
        tableLocale = new Ts();
        tableGlobale.addFct(node.getNom(), argsLength, tableLocale, node);
        return super.visit(node);
    }

    @Override
    public Void visit(SaVarSimple node) {
        TsItemVar var = null;
        if(tableLocale != null) var = tableLocale.variables.get(node.getNom());
        if (var == null) var = tableGlobale.variables.get(node.getNom());
        if(var == null) throw new RuntimeException("Variable glob non définie");
        if(var.getTaille() > 1) throw new RuntimeException("Entier indicé");
        return super.visit(node);
    }

    @Override //Peut pas faire : Les tableaux ne peuvent jamais être utilisés sans indice
    public Void visit(SaVarIndicee node) {TsItemVar var = null;
        if(tableLocale != null) var = tableLocale.variables.get(node.getNom());
        if (var == null) var = tableGlobale.variables.get(node.getNom());
        if(var == null) throw new RuntimeException("Variable glob non définie");


        return super.visit(node);
    }

    /*  — Le nombre d’arguments réels passés à la fonction appelée est identique au nombre d’arguments formels dans la déclaration todo : verifier ça
        — Il existe une fonction sans arguments qui s’appelle main todo : verifier ça*/

    @Override
    public Void visit(SaAppel node) {
        if (!tableGlobale.fonctions.containsKey(node.getNom())) throw new RuntimeException("Fonction non declarée");
        if (!tableGlobale.fonctions.containsKey("main")) throw new RuntimeException("Il n'existe aucune fonction main");
        if(tableGlobale.fonctions.containsKey("main") && tableLocale.variables.get("main").getTaille() != 0) throw new RuntimeException("Trop d'argument pour main");

        return super.visit(node);
    }


    public class TsException extends Exception {

        public TsException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
