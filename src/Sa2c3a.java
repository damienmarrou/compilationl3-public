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

    /**
     * Visit all nodes
     * @param node the node to visit
     */
    public C3aOperand visit(SaProg node) {
        return super.visit(node);
    }


    @Override
    public C3aOperand visit(SaDecVar node) {
        return null;
    }

    public C3aOperand visit(SaDecTab node) {
        return null;
    }

    @Override
    public C3aOperand visit(SaExp node) {
        return null;
    }


    /**
     * Create the c3a for the function declaration
     * @param node the node to visit
     */
    public C3aOperand visit(SaDecFonc node) {
        c3a.ajouteInst(new C3aInstFBegin(node.tsItem, "entree fonction"));

        if (node.getParametres() != null)
            node.getParametres().accept(this);

        if (node.getVariable() != null)
            node.getVariable().accept(this);

        node.getCorps().accept(this);

        c3a.ajouteInst(new C3aInstFEnd(""));
        return null;
    }

    /**
     * Create the c3a for int expression
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpInt node) {
        return new C3aConstant(node.getVal());
    }

    /**
     * Create the c3a for variable expression
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpVar node) {
        return node.getVar().accept(this);
    }

    /**
     * Create the c3a for the writing instruction
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaInstEcriture node) {
        C3aOperand operand = node.getArg().accept(this);

        c3a.ajouteInst(new C3aInstWrite(operand, ""));
        return null;
    }

    /**
     * Create the c3a for the return instruction
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaInstRetour node) {
        c3a.ajouteInst(new C3aInstReturn(node.getVal().accept(this), ""));
        return null;
    }

    /**
     * Create the c3a to read expression
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpLire node) {
        C3aTemp temp = c3a.newTemp();

        c3a.ajouteInst(new C3aInstRead(temp, ""));
        return temp;
    }

    /**
     * Create the c3a for the while instruction
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaInstTantQue node) {
        C3aLabel testLabel = c3a.newAutoLabel();
        C3aLabel testQuitterTq = c3a.newAutoLabel();

        c3a.addLabelToNextInst(testLabel);
        c3a.ajouteInst(new C3aInstJumpIfEqual(node.getTest().accept(this), c3a.False, testQuitterTq, ""));
        node.getFaire().accept(this);
        c3a.ajouteInst(new C3aInstJump(testLabel, ""));
        c3a.addLabelToNextInst(testQuitterTq);
        return null;
    }

    /**
     * Create the c3a for the affectation instruction
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaInstAffect node) {
        C3aOperand operand1 = node.getRhs().accept(this);
        C3aOperand operand2 = node.getLhs().accept(this);

        c3a.ajouteInst(new C3aInstAffect(operand1, operand2, ""));
        return null;
    }

    /**
     * Create the c3a for the simple variable
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaVarSimple node) {
        return new C3aVar(node.tsItem, null);
    }

    /**
     * Create the c3a for the indexed variable
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaVarIndicee node) {
        return new C3aVar(node.tsItem, node.getIndice().accept(this));
    }

    /**
     * Create the c3a for the call instruction
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaAppel node) {
        C3aFunction c3aFunction = new C3aFunction(node.tsItem);

        if (node.getArguments() != null)
            node.getArguments().accept(this);
        c3a.ajouteInst(new C3aInstCall(c3aFunction, null, ""));
        return c3aFunction;
    }

    /**
     * Create the c3a for the expression call
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpAppel node) {
        C3aTemp temp = c3a.newTemp();
        C3aFunction c3aFunction = new C3aFunction(node.getVal().tsItem);

        if (node.getVal().getArguments() != null)
            node.getVal().getArguments().accept(this);
        c3a.ajouteInst(new C3aInstCall(c3aFunction, temp, ""));
        return temp;
    }

    /**
     * Create the c3a to add expression
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpAdd node) {
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand2 = node.getOp2().accept(this);
        C3aTemp tempRes = c3a.newTemp();

        c3a.ajouteInst(new C3aInstAdd(operand1, operand2, tempRes, ""));
        return tempRes;
    }

    /**
     * Create the c3a to sub expression
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpSub node) {
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand2 = node.getOp2().accept(this);
        C3aTemp tempRes = c3a.newTemp();

        c3a.ajouteInst(new C3aInstSub(operand1, operand2, tempRes, ""));
        return tempRes;
    }

    /**
     * Create the c3a to mult expression
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpMult node) {
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand2 = node.getOp2().accept(this);
        C3aTemp tempRes = c3a.newTemp();

        c3a.ajouteInst(new C3aInstMult(operand1, operand2, tempRes, ""));
        return tempRes;
    }

    /**
     * Create the c3a to div expression
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpDiv node) {
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand2 = node.getOp2().accept(this);
        C3aTemp tempRes = c3a.newTemp();

        c3a.ajouteInst(new C3aInstDiv(operand1, operand2, tempRes, ""));
        return tempRes;
    }

    /**
     * Create the c3a for the inf condition
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpInf node) {
        C3aLabel label = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstAffect(c3a.True, tempTest, ""));
        c3a.ajouteInst(new C3aInstJumpIfLess(operand1, operand2, label, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest, ""));
        c3a.addLabelToNextInst(label);
        return tempTest;
    }

    /**
     * Create the c3a for the equal condition
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpEqual node) {
        C3aLabel label = c3a.newAutoLabel();
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand2 = node.getOp2().accept(this);
        C3aTemp tempTest = c3a.newTemp();

        c3a.ajouteInst(new C3aInstAffect(c3a.True, tempTest, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(operand1, operand2, label, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest, ""));
        c3a.addLabelToNextInst(label);
        return tempTest;
    }

    /**
     * Create the c3a for the and condition
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpAnd node) {
        C3aLabel label1 = c3a.newAutoLabel();
        C3aLabel label2 = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfEqual(operand1, c3a.False, label2, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(operand2, c3a.False, label2, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.True, tempTest, ""));
        c3a.ajouteInst(new C3aInstJump(label1, ""));
        c3a.addLabelToNextInst(label2);
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest, ""));
        c3a.addLabelToNextInst(label1);
        return tempTest;
    }

    /**
     * Create the c3a for the or condition
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpOr node) {
        C3aLabel label1 = c3a.newAutoLabel();
        C3aLabel label2 = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfNotEqual(operand1, c3a.False, label2, ""));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(operand2, c3a.False, label2, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest, ""));
        c3a.ajouteInst(new C3aInstJump(label1, ""));
        c3a.addLabelToNextInst(label2);
        c3a.ajouteInst(new C3aInstAffect(c3a.True, tempTest, ""));
        c3a.addLabelToNextInst(label1);
        return tempTest;
    }

    /**
     * Create the c3a for the not condition
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaExpNot node) {
        C3aTemp temp = c3a.newTemp();
        C3aLabel label = c3a.newAutoLabel();

        c3a.ajouteInst(new C3aInstAffect(c3a.True, temp, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(node.getOp1().accept(this), c3a.False, label, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, temp, ""));
        c3a.addLabelToNextInst(label);
        return temp;

    }

    /**
     * Create the c3a for the if condition
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaInstSi node) {
        C3aOperand test = node.getTest().accept(this);
        C3aLabel faux = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();
        C3aLabel jump = c3a.newAutoLabel();

        if (node.getSinon() != null) {
            jump = faux;
        } else jump = suite;

        c3a.ajouteInst(new C3aInstJumpIfEqual(test, c3a.False, jump, ""));
        node.getAlors().accept(this);

        if (node.getSinon() != null) {
            c3a.ajouteInst(new C3aInstJump(suite, ""));
            c3a.addLabelToNextInst(faux);
            node.getSinon().accept(this);
        }

        c3a.addLabelToNextInst(suite);
        return null;
    }

    /**
     * Create the c3a for the expression list
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaLExp node) {
        c3a.ajouteInst(new C3aInstParam(node.getTete().accept(this), ""));

        if (node.getQueue() != null)
            return node.getQueue().accept(this);
        else return null;
    }

    /**
     * Create the c3a for the instruction list
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaLInst node) {
        return super.visit(node);
    }

    /**
     * Create the c3a for the declaration list
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaLDec node) {
        return super.visit(node);
    }

    /**
     * Create the c3a for the bloc instruction
     * @param node the node to visit
     */
    @Override
    public C3aOperand visit(SaInstBloc node) {
        return super.visit(node);
    }

}
