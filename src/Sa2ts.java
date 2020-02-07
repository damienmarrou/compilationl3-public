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
    public Void visit(SaDecVar node){

        if(tableLocale !=null){
            if(tableLocale.variables.size() < argsLength)
                tableLocale.addParam(node.getNom());
            else tableLocale.addVar(node.getNom(),1);
        }
        else{
            tableGlobale.addVar(node.getNom(),1);
        }
        return super.visit(node);
    }

    @Override
    public Void visit(SaDecTab node){

        tableGlobale.addVar(node.getNom(),node.getTaille());
        return super.visit(node);
    }
    @Override
    public Void visit(SaDecFonc node){
        if(node.getParametres()== null){
            argsLength = 0;
        }
        else argsLength = node.getParametres().length();
        tableLocale = new Ts();
        tableGlobale.addFct(node.getNom(),argsLength,tableLocale,node);
        return super.visit(node);
    }
    @Override
    public Void visit(SaVarSimple node){

        return super.visit(node);
    }
    @Override
    public Void visit(SaVarIndicee node){
        return super.visit(node);
    }

    @Override
    public Void visit(SaAppel node){
        return super.visit(node);
    }





}
