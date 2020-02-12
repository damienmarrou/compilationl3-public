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
            if (tableLocale.variables.containsKey(node.getNom()))
                throw new TsException("Variable/Arg loc déjà déclarée");
            if (tableLocale.variables.size() < argsLength)
                tableLocale.addParam(node.getNom());
            else tableLocale.addVar(node.getNom(), 1);
        } else {
            location = Location.Global;
            if (tableGlobale.variables.containsKey(node.getNom())) throw new TsException("Variable glob déjà déclarée");
            tableGlobale.addVar(node.getNom(), 1);
        }
        return super.visit(node);
    }

    @Override
    public Void visit(SaDecTab node) {
        if (location != Location.Global) throw new TsException("Tab non global");
        if (tableGlobale.variables.containsKey(node.getNom())) throw new TsException("Variable déjà déclaré");
        tableGlobale.addVar(node.getNom(), node.getTaille());
        return super.visit(node);
    }

    @Override
    public Void visit(SaDecFonc node) {
        if (tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("Fonction déjà déclarée");
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
        if (var == null) throw new TsException("Variable glob non définie");
        if (var.getTaille() > 1) throw new TsException("Entier indicé");
        return super.visit(node);
    }

    @Override //Peut pas faire : Les tableaux ne peuvent jamais être utilisés sans indice
    public Void visit(SaVarIndicee node) {TsItemVar var = null;
        if(tableLocale != null) var = tableLocale.variables.get(node.getNom());
        if (var == null) var = tableGlobale.variables.get(node.getNom());
        if (var == null) throw new TsException("Variable glob non définie");
        return super.visit(node);
    }

    @Override
    public Void visit(SaAppel node) {
        if (!tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("Fonction non declarée");
        if (!tableGlobale.fonctions.containsKey("main")) throw new TsException("Il n'existe aucune fonction main");
        if (tableGlobale.fonctions.containsKey("main") && tableLocale.variables.get("main").getTaille() != 0)
            throw new TsException("Trop d'argument pour main");

        return super.visit(node);
    }


    public class TsException extends RuntimeException {

        public TsException(String message) {
            super(message);
        }
    }
}
