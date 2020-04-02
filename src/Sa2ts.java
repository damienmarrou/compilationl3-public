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
     * Check if the declaration of a simple variable is unique
     * @param node the node to visit
     */
    @Override
    public Void visit(SaDecVar node) {
        TsItemVar itemVar = location.getVar(node.getNom());
        if (itemVar != null)
            if (isLocalTable())
                throw new TsException(node.getNom() + "Simple variable already defined in local");
            else
                throw  new TsException(node.getNom() + "Simple variable already defined in global");

        node.tsItem = isParam
                ? location.addParam(node.getNom())
                : location.addVar(node.getNom(), 1);
        return null;
    }

    /**
     * Check if the declaration of an array is in the right place and unique
     * @param node the node to visit
     */
    @Override
    public Void visit(SaDecTab node) {
        if (isLocalTable()) throw new TsException("You can't defined an array in local");
        if (tableGlobale.getVar(node.getNom()) != null) throw new RuntimeException("Array already defined in global");
        node.tsItem = location.addVar(node.getNom(), node.getTaille());
        return null;
    }

    /**
     * Check if the function declaration is unique
     * @param node the node to visit
     */
    @Override
    public Void visit(SaDecFonc node) {
        if (tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("Function already defined");
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
     * Check if the simple variable is well defined and that it is not an array
     * @param node the node to visit
     */
    @Override
    public Void visit(SaVarSimple node) {
        if(isLocalTable() && location.variables.containsKey(node.getNom())) node.tsItem = location.variables.get(node.getNom());
        else node.tsItem = tableGlobale.getVar(node.getNom());
        if(node.tsItem == null) throw new TsException("Simple variable not defined");
        if(node.tsItem.getTaille() > 1) throw  new TsException("You can't have an indexed simple variable");
        return null;
    }

    /**
     * Check if the array variable is well defined and the array is well indexed
     * @param node the node to visit
     */
    @Override
    public Void visit(SaVarIndicee node) {
       if(isLocalTable() && location.variables.containsKey(node.getNom())) node.tsItem = location.variables.get(node.getNom());
       else node.tsItem = tableGlobale.getVar(node.getNom());
       if(node.tsItem == null) throw  new TsException("Indexed variable not defined");
       if(node.tsItem.getTaille() < 1) throw  new TsException("Array without index");
       return null;
    }

    /**
     * Check that the called function is declared and that it is called with the right number of arguments
     * @param node the node to visit
     */
    @Override
    public Void visit(SaAppel node) {
        if (!tableGlobale.fonctions.containsKey(node.getNom())) throw new TsException("Function not defined");
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

            throw new TsException("You don't have the right number of arguments for this function");
        }
        return null;
    }

    /**
     * Definition of a special exception for this class
     */
    static class TsException extends RuntimeException {
        TsException(String message) {
            super(message);
        }
    }

    /**
     * Check that the main function exists and that it is defined correctly (without arguments)
     */
    private void checkMainExists() {
        if (!tableGlobale.fonctions.containsKey("main"))
            throw new TsException("Missing function main");
        if (tableGlobale.fonctions.get("main").getNbArgs() != 0) throw new TsException("Function main should not have arguments");
    }
}
