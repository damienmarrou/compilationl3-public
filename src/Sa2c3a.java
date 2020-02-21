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
        return null;
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
    public C3aOperand visit(SaInstEcriture node) { //OK
        c3a.ajouteInst(new C3aInstWrite(node.getArg().accept(this), "Write"));
        return null;
    }

    @Override
    public C3aOperand visit(SaInstTantQue node) {

        C3aLabel test = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();
        C3aConstant False = new C3aConstant(0);

        c3a.ajouteInst(new C3aInstJumpIfEqual(test,False,suite,""));

        c3a.ajouteInst(new C3aInstJump(test,""));
        c3a.addLabelToNextInst(suite);






        /*int test = node.getTest().accept(this);
         while (test != 0){
          node.getFaire().accept(this);
          test = node.getTest().accept(this);
          }
          return 1;*/


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
    public C3aOperand visit(SaInstAffect node) { //OK
        c3a.ajouteInst(new C3aInstAffect(node.getLhs().accept(this), node.getRhs().accept(this), "Affect"));
        return null;
    }

    @Override
    public C3aOperand visit(SaLDec node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaVarSimple node) {
        /*if (node.tsItem.portee == this.tableGlobale) {

            val = varGlob[node.tsItem.adresse];
        }*/


        return super.visit(node);

    }

    @Override
    public C3aOperand visit(SaAppel node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpAppel node) { //todo verif
        var tx = c3a.newTemp();
        C3aFunction callFunct = new C3aFunction(node.getVal().tsItem);
        c3a.ajouteInst(new C3aInstCall(callFunct, tx, "ExpAppel"));
        return tx;
        //return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpAdd node) { //OK
        var tempRes = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstAdd(op1, op2, tempRes, "Add"));
        return tempRes;
    }

    @Override
    public C3aOperand visit(SaExpSub node) { //OK
        var tempRes = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstSub(op1, op2, tempRes, "Sub"));
        return tempRes;
    }

    @Override
    public C3aOperand visit(SaExpMult node) { //OK
        var tempRes = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstMult(op1, op2, tempRes, "Mult"));
        return tempRes;
    }

    @Override
    public C3aOperand visit(SaExpDiv node) { //OK
        var tempRes = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstDiv(op1, op2, tempRes, "Div"));
        return tempRes;
    }

    @Override
    public C3aOperand visit(SaExpInf node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfLess(op1,op2,e1,""));
        //gen(E.t =0)
        // tempTest =  0;
        c3a.ajouteInst(new C3aInstJump(e2,""));
        //gen(e1 : E.t =1)
        //gen(e2:)
        c3a.addLabelToNextInst(e2);


        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpEqual node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpAnd node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();

        //c3a.ajouteInst(new C3aInstJumpIfEqual());

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
    public C3aOperand visit(SaExpLire node) { //todo verif
        var tx = c3a.newTemp();
        //C3aFunction readFunct = new C3aFunction(node);
        c3a.ajouteInst(new C3aInstRead(tx,"ExpRead"));
        return tx;
        //return super.visit(node);
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
    public C3aOperand visit(SaInstRetour node) { //todo verif
        c3a.ajouteInst(new C3aInstReturn(node.getVal().accept(this), "Return"));

        return null;
    }

    @Override
    public C3aOperand visit(SaLExp node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaVarIndicee node) { //todo revoir/ vérif
        new C3aVar(node.tsItem,node.getIndice().accept(this));
        return super.visit(node);
    }
}
