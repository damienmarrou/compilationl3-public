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


    @Override
    public void defaultIn(SaNode node) {
        super.defaultIn(node);
    }

    @Override
    public void defaultOut(SaNode node) {
        super.defaultOut(node);
    }


    public C3aOperand visit(SaProg node) {
        return null;
    }

    public C3aOperand visit(SaDecTab node) {
        return null;
    }

    public C3aOperand visit(SaDecFonc node) { //todo
        //ajouter inst begin
        // ajouter inst end
        return null;
    }


    @Override
    public C3aOperand visit(SaExp node) { //todo
        return super.visit(node);

    }

    @Override
    public C3aOperand visit(SaExpInt node) { // OK
        return new C3aConstant(node.getVal());
    }

    @Override
    public C3aOperand visit(SaExpVar node) { // OK
        return node.getVar().accept(this);
    }

    @Override
    public C3aOperand visit(SaInstEcriture node) { //OK
        C3aOperand op = node.getArg().accept(this);
        c3a.ajouteInst(new C3aInstWrite(op, "InstWrite"));
        return null;
    }

    @Override
    public C3aOperand visit(SaInstTantQue node) { //OK

        C3aLabel testLabel = c3a.newAutoLabel();
        C3aLabel suiteLabel = c3a.newAutoLabel();

        c3a.addLabelToNextInst(testLabel);

        //E.code
        C3aOperand test = node.getTest().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfEqual(test, c3a.False, suiteLabel, "si test = 0 , goto suite"));

        //LI.code
        node.getFaire().accept(this);
        c3a.ajouteInst(new C3aInstJump(testLabel, "goto test"));

        c3a.addLabelToNextInst(suiteLabel);

        return null;
    }

    @Override
    public C3aOperand visit(SaLInst node) { //todo
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaDecVar node) { //todo verif
        return new C3aVar(node.tsItem,null);
    }

    @Override
    public C3aOperand visit(SaInstAffect node) { //OK
        C3aOperand op = node.getRhs().accept(this);
        C3aTemp temp = c3a.newTemp();

        c3a.ajouteInst(new C3aInstAffect(op, temp, "InstAffect"));
        return temp;
    }

    @Override
    public C3aOperand visit(SaLDec node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaVarSimple node) {//OK
        return new C3aVar(node.tsItem, null);
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
    }

    @Override
    public C3aOperand visit(SaExpAdd node) { //OK
        var tempRes = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstAdd(op1, op2, tempRes, "ExpAdd"));
        return tempRes;
    }

    @Override
    public C3aOperand visit(SaExpSub node) { //OK
        var tempRes = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstSub(op1, op2, tempRes, "ExpSub"));
        return tempRes;
    }

    @Override
    public C3aOperand visit(SaExpMult node) { //OK
        var tempRes = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstMult(op1, op2, tempRes, "ExpMult"));

        return tempRes;
    }

    @Override
    public C3aOperand visit(SaExpDiv node) { //OK
        var tempRes = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstDiv(op1, op2, tempRes, "ExpDiv"));

        return tempRes;
    }

    @Override
    public C3aOperand visit(SaExpInf node) { //ok
        C3aLabel label = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstAffect(c3a.True,tempTest,""));
        c3a.ajouteInst(new C3aInstJumpIfLess(op1, op2, label, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False,tempTest,""));
        c3a.addLabelToNextInst(label);

        return tempTest;
    }

    @Override
    public C3aOperand visit(SaExpEqual node) { //OK
        C3aLabel label = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstAffect(c3a.True, tempTest,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1,op2, label, "si op1=op2 , goto e1"));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest,""));
        c3a.addLabelToNextInst(label);
        return tempTest;
    }

    @Override
    public C3aOperand visit(SaExpAnd node) { //ok
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfEqual(op1,c3a.False , e1, "si op1=0 , goto e1"));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op2, c3a.False, e1, "si op2=0 , goto e1"));
        c3a.ajouteInst(new C3aInstAffect(c3a.True, tempTest,"E.t = 1"));
        c3a.ajouteInst(new C3aInstJump(e2, "jump e2"));
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest,"E.t = 0"));
        c3a.addLabelToNextInst(e2);

        return tempTest;
    }

    @Override
    public C3aOperand visit(SaExpOr node) { //Ok
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);


        c3a.ajouteInst(new C3aInstJumpIfEqual(op1,c3a.False , e1, "si op1=0 , goto e1"));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op2,c3a.False , e1, "si op2 !=0 , goto e1"));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest,"E.t = 1"));

        c3a.ajouteInst(new C3aInstJump(e2, "jump e2"));
        c3a.addLabelToNextInst(e1);
        c3a.ajouteInst(new C3aInstAffect(c3a.True,tempTest,""));
        c3a.addLabelToNextInst(e2);

        return tempTest;
    }

    @Override
    public C3aOperand visit(SaExpNot node) { //OK
        //temp a vrai
        //test si equal a false : jump to next
        C3aTemp temp = c3a.newTemp();
        C3aLabel e1 = c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect(c3a.True,temp,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(node.getOp1().accept(this),c3a.False,e1,""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False,temp,""));
        c3a.addLabelToNextInst(e1);
        // sinon je met à faux

        return temp;

    }

    @Override
    public C3aOperand visit(SaExpLire node) { //ok
        var temp = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(temp, "ExpRead"));
        return temp;
    }

    @Override
    public C3aOperand visit(SaInstBloc node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstSi node) { //TODO QUESTION résolue : LI1 juste pour parcourir l'arbre
        //todo : faire un node.get.accep(this)
        //E.code

        C3aOperand op1 = node.getTest().accept(this);
        C3aLabel faux = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();


        c3a.ajouteInst(new C3aInstJumpIfEqual(op1,c3a.False,faux, "si test = 0 , goto faux"));
        //LI1.code
        node.getAlors().accept(this);

        c3a.ajouteInst(new C3aInstJump(suite,"goto suite"));
        c3a.addLabelToNextInst(faux);


        //LI2.code
        c3a.addLabelToNextInst(suite);

        return null;
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
    public C3aOperand visit(SaVarIndicee node) { //ok
        return new C3aVar(node.tsItem, node.getIndice().accept(this));
    }
}
