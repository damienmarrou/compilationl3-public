import c3a.*;
import sa.*;
import ts.Ts;

public class Sa2c3a extends SaDepthFirstVisitor<C3aOperand> {
    private C3a c3a;
    private Ts tableGlobale;
    private Ts tableLocaleCourante;

    public C3a getC3a() {
        return this.c3a;
    }

    public Sa2c3a(SaNode root, Ts tableGlobale) {
        this.c3a = new C3a();
        this.tableGlobale = tableGlobale;
        this.tableLocaleCourante = null;
        root.accept(this);
    }

    public C3aOperand visit(SaProg node) {
        return null;
    }

    public C3aOperand visit(SaDecTab node) {

        return  null;
    }

    public C3aOperand visit(SaDecFonc node) {
        return null;
    }

    @Override
    public void defaultIn(SaNode node) {
        super.defaultIn(node);
    }

    @Override
    public void defaultOut(SaNode node) {
        super.defaultOut(node);
    }

    @Override
    public C3aOperand visit(SaExp node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpInt node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpVar node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstEcriture node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstTantQue node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaLInst node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaDecVar node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstAffect node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaLDec node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaVarSimple node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaAppel node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpAppel node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpAdd node) {
        C3aConstant op1 = (C3aConstant) node.getOp1().accept(this);
        C3aConstant op2 =  (C3aConstant) node.getOp2().accept(this);
        C3aConstant result =  new C3aConstant(op1.val + op2.val);
        C3aInstAdd add = new C3aInstAdd(op1,op2,result,"SaExpAdd");
        return  add.result;
        //return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpSub node) {
        C3aConstant op1 = (C3aConstant) node.getOp1().accept(this);
        C3aConstant op2 =  (C3aConstant) node.getOp2().accept(this);
        C3aConstant result =  new C3aConstant(op1.val - op2.val);
        C3aInstSub sub = new C3aInstSub(op1,op2,result,"SaExpSub");
        return  sub.result;
        //return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpMult node) {
        C3aConstant op1 = (C3aConstant) node.getOp1().accept(this);
        C3aConstant op2 =  (C3aConstant) node.getOp2().accept(this);
        C3aConstant result =  new C3aConstant(op1.val * op2.val);
        C3aInstMult mult = new C3aInstMult(op1,op2,result,"SaExpMult");
        return  mult.result;
        //return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpDiv node) {
        C3aConstant op1 = (C3aConstant) node.getOp1().accept(this);
        C3aConstant op2 =  (C3aConstant) node.getOp2().accept(this);
        C3aConstant result =  new C3aConstant(op1.val / op2.val);
        C3aInstDiv div = new C3aInstDiv(op1,op2,result,"SaExpDiv");
        return  div.result;
        //return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpInf node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpEqual node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpAnd node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpOr node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpNot node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpLire node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstBloc node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstSi node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstRetour node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaLExp node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaVarIndicee node) {
        return super.visit(node);
    }
}
