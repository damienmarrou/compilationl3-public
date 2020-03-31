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

    public Ts getTableGlobale() {
        return tableGlobale;
    }

    private boolean isLocalTable() {
        return location != tableGlobale;
    }

    /**
     * Vérifie si la déclaration d'une variable simple est unique
     * @param node le noeud à visiter
     */
    @Override
    public Void visit(SaDecVar node) {
        TsItemVar itemVar = location.getVar(node.getNom());
        if (itemVar != null)
            if (isLocalTable())
                throw new TsException(node.getNom() + "Variable déjà définie en local");
            else
                throw  new TsException(node.getNom() + "Variable déjà définie en global");

        node.tsItem = isParam
                ? location.addParam(node.getNom())
                : location.addVar(node.getNom(), 1);
        return null;
    }

    /**
     * Vérifie si la déclaration d'un tableau est au bon endroit et unique
     * @param node le noeud à visiter
     */
    @Override
    public Void visit(SaDecTab node) {
        if (isLocalTable()) throw new TsException("Ne pas définir un tableau en local");
        if (tableGlobale.getVar(node.getNom()) != null) throw new RuntimeException("Déjà défini dans la table globale");
        node.tsItem = location.addVar(node.getNom(), node.getTaille());
        return null;
    }

    /**
     * Vérifie si la déclaration de fonction est unique
     * @param node le noeud à visiter
     */
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
        return null;
    }

    /**
     * Vérifie si la variable simple est bien définie et qu'elle n'est pas indicée
     * @param node le noeud à visiter
     */
    @Override
    public Void visit(SaVarSimple node) {
        if(isLocalTable() && location.variables.containsKey(node.getNom())) node.tsItem = location.variables.get(node.getNom());
        else node.tsItem = tableGlobale.getVar(node.getNom());
        if(node.tsItem == null) throw new TsException("Variable non définie");
        if(node.tsItem.getTaille() > 1) throw  new TsException("Entier indicé alors qu'il ne faut pas");
        return null;
    }

    /**
     * Vérifie si la variable tableau est bien définie et que le tableau est bien indicé
     * @param node le noeud à visiter
     */
    @Override
    public Void visit(SaVarIndicee node) {
       if(isLocalTable() && location.variables.containsKey(node.getNom())) node.tsItem = location.variables.get(node.getNom());
       else node.tsItem = tableGlobale.getVar(node.getNom());
       if(node.tsItem == null) throw  new TsException("Variable indicée non définie");
       if(node.tsItem.getTaille() < 1) throw  new TsException("Tableau sans indice");
       return null;
    }

    /**
     * Vérifie que la fonction appelée est bien déclarée et qu'elle est appelée avec le bon nombre d'arguments
     * @param node le noeud à visiter
     */
    @Override
    public Void visit(SaAppel node) {
        if (!tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("Fonction non declarée");
        else node.tsItem = tableGlobale.getFct(node.getNom());
        int length;
        if (node.getArguments() != null) {
            length = node.getArguments().length();
        }
        else length = 0;
        if (argsLength > 0) {
            assert node.getArguments() != null;
            node.getArguments().accept(this);
        }
        if (length != node.tsItem.nbArgs) {

            throw new TsException("Pas le bon nombre d'argument pour la fonction");
        }
        return null;
    }

    /**
     * Définition d'une exception spéciale pour cette classe là
     */
    static class TsException extends RuntimeException {
        TsException(String message) {
            super(message);
        }
    }

    /**
     * Vérifie que le main existe et qu'il est défini correctement (sans arguments)
     */
    private void checkMainExists() {
        if (!tableGlobale.fonctions.containsKey("main"))
            throw new TsException("Absence de la fonction main");
        if (tableGlobale.fonctions.get("main").getNbArgs() != 0) throw new TsException("La fonction main doit avoir aucun argument");
    }
}
