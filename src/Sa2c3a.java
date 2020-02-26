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

    //TODO : Vérifier comment on utilise SaDecVar et SaDecTab et SaExp
    @Override
    public C3aOperand visit(SaDecVar node) {
        return null;
    }

    public C3aOperand visit(SaDecTab node) {
        return null;
    }

    @Override
    public C3aOperand visit(SaExp node) {
        return super.visit(node);
    }
    //Le reste est bon


    public C3aOperand visit(SaDecFonc node) { //OK
        c3a.ajouteInst(new C3aInstFBegin(node.tsItem,"Begin"));

        if(node.getParametres() != null)
            node.getParametres().accept(this);

        if(node.getVariable() != null)
            node.getVariable().accept(this);

        node.getCorps().accept(this);

        c3a.ajouteInst(new C3aInstFEnd("End"));
        return null;
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
        c3a.ajouteInst(new C3aInstWrite(op, "Write"));
        return null;
    }

    @Override
    public C3aOperand visit(SaInstRetour node) { //OK
        c3a.ajouteInst(new C3aInstReturn(node.getVal().accept(this), "Return"));
        return null;
    }

    @Override
    public C3aOperand visit(SaExpLire node) { //OK
        var temp = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(temp, "ExpRead"));
        return temp;
    }

    @Override
    public C3aOperand visit(SaInstTantQue node) { //OK

        C3aLabel testLabel = c3a.newAutoLabel();
        C3aLabel suiteLabel = c3a.newAutoLabel();
        C3aOperand test = node.getTest().accept(this);

        c3a.addLabelToNextInst(testLabel);
        c3a.ajouteInst(new C3aInstJumpIfEqual(test, c3a.False, suiteLabel, "si test = 0 , goto suite"));

        node.getFaire().accept(this);
        c3a.ajouteInst(new C3aInstJump(testLabel, "goto test"));
        c3a.addLabelToNextInst(suiteLabel);

        return null;
    }



    @Override
    public C3aOperand visit(SaInstAffect node) { //OK
        C3aOperand op1 = node.getRhs().accept(this);
        C3aOperand op2 = node.getLhs().accept(this);

        c3a.ajouteInst(new C3aInstAffect(op1, op2, "Affect"));
        return null;
    }



    @Override
    public C3aOperand visit(SaVarSimple node) {//OK
        return new C3aVar(node.tsItem, null);
    }

    @Override
    public C3aOperand visit(SaVarIndicee node) { //ok
        return new C3aVar(node.tsItem, node.getIndice().accept(this));
    }


    //TODO : Vérifier les fonctions d'appel SaAppel et SaExpAppel mais normalement OK

    @Override
    public C3aOperand visit(SaAppel node) {
        C3aFunction c3aFunction = new C3aFunction(node.tsItem);
        if(node.getArguments() != null) node.getArguments().accept(this);
        c3a.ajouteInst(new C3aInstCall(c3aFunction, null,""));
        return c3aFunction;
    }

    @Override
    public C3aOperand visit(SaExpAppel node) {
        C3aTemp temp = c3a.newTemp();

        C3aFunction c3aFunction = new C3aFunction(node.getVal().tsItem);
        if(node.getVal().getArguments() != null)
            node.getVal().getArguments().accept(this);
        c3a.ajouteInst(new C3aInstCall(c3aFunction, temp,""));

        return temp;
    }

    //Le reste est bon

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
    public C3aOperand visit(SaExpInf node) { //OK
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
        C3aTemp temp = c3a.newTemp();
        C3aLabel e1 = c3a.newAutoLabel();

        c3a.ajouteInst(new C3aInstAffect(c3a.True,temp,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(node.getOp1().accept(this),c3a.False,e1,""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False,temp,""));
        c3a.addLabelToNextInst(e1);

        return temp;

    }


    @Override
    public C3aOperand visit(SaInstSi node) { //OK

        C3aOperand test = node.getTest().accept(this);
        C3aLabel faux = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();
        C3aLabel jump = c3a.newAutoLabel();

        if(node.getSinon() != null){
            jump =  faux;
        }else jump = suite;

        c3a.ajouteInst(new C3aInstJumpIfEqual(test,c3a.False,jump, "si test = 0 , goto faux"));
        node.getAlors().accept(this);

        if(node.getSinon() != null) {
            c3a.ajouteInst(new C3aInstJump(suite, "goto suite"));
            c3a.addLabelToNextInst(faux);
            node.getSinon().accept(this);
        }
        c3a.addLabelToNextInst(suite);

        return null;
    }

    @Override
    public C3aOperand visit(SaLExp node) { //OK
        c3a.ajouteInst(new C3aInstParam(node.getTete().accept(this),""));

        if (node.getQueue() != null) return node.getQueue().accept(this);
        else return null;
    }


    //TODO : Verif si on en a besoin ou pas pour les fonctions SaLInst, SaLDec, SaInstBloc
    @Override
    public C3aOperand visit(SaLInst node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaLDec node) {
        return super.visit(node);
    }


    @Override
    public C3aOperand visit(SaInstBloc node) {
        return super.visit(node);
    }

}
