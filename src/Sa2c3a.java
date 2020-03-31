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
     * Permet de visiter tout le code
     * @param node le noeud à visiter
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
     * Créer le code 3 adresses pour la délaration de fonction
     * @param node le noeud à visiter
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
     * Créer le code 3 adresses pour l'expression d'entier
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpInt node) {
        return new C3aConstant(node.getVal());
    }

    /**
     * Créer le code 3 adresses pour l'expression d'une variable
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpVar node) {
        return node.getVar().accept(this);
    }

    /**
     * Créer le code 3 adresses pour l'instruction d'écriture
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaInstEcriture node) {
        C3aOperand op = node.getArg().accept(this);
        c3a.ajouteInst(new C3aInstWrite(op, ""));
        return null;
    }

    /**
     * Créer le code 3 adresses pour l'instruction de retour
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaInstRetour node) {
        c3a.ajouteInst(new C3aInstReturn(node.getVal().accept(this), ""));
        return null;
    }

    /**
     * Créer le code 3 adresses pour l'instruction de lecture
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpLire node) {
        var temp = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(temp, ""));
        return temp;
    }

    /**
     * Créer le code 3 adresses pour l'instruction tant que
     * @param node le noeud à visiter
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
     * Créer le code 3 adresses pour l'instruction d'affectation
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaInstAffect node) {
        C3aOperand op1 = node.getRhs().accept(this);
        C3aOperand op2 = node.getLhs().accept(this);

        c3a.ajouteInst(new C3aInstAffect(op1, op2, ""));
        return null;
    }

    /**
     * Créer le code 3 adresses pour la déclaration de variable simple
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaVarSimple node) {
        return new C3aVar(node.tsItem, null);
    }

    /**
     * Créer le code 3 adresses pour la déclaration de variable indicée
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaVarIndicee node) {
        return new C3aVar(node.tsItem, node.getIndice().accept(this));
    }

    /**
     * Créer le code 3 adresses pour l'instruction d'appel
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaAppel node) {
        C3aFunction c3aFunction = new C3aFunction(node.tsItem);
        if (node.getArguments() != null) node.getArguments().accept(this);
        c3a.ajouteInst(new C3aInstCall(c3aFunction, null, ""));
        return c3aFunction;
    }

    /**
     * Créer le code 3 adresses pour l'appel d'expression
     * @param node le noeud à visiter
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
     * Créer le code 3 adresses pour l'addition d'expression
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpAdd node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        var tempRes = c3a.newTemp();

        c3a.ajouteInst(new C3aInstAdd(op1, op2, tempRes, ""));
        return tempRes;
    }

    /**
     * Créer le code 3 adresses pour la soustraction d'expression
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpSub node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        var tempRes = c3a.newTemp();
        c3a.ajouteInst(new C3aInstSub(op1, op2, tempRes, ""));
        return tempRes;
    }

    /**
     * Créer le code 3 adresses pour la multiplication d'expression
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpMult node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        var tempRes = c3a.newTemp();
        c3a.ajouteInst(new C3aInstMult(op1, op2, tempRes, ""));

        return tempRes;
    }

    /**
     * Créer le code 3 adresses pour la division d'expression
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpDiv node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        var tempRes = c3a.newTemp();
        c3a.ajouteInst(new C3aInstDiv(op1, op2, tempRes, ""));

        return tempRes;
    }

    /**
     * Créer le code 3 adresses pour la condition d'infériorité
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpInf node) {
        C3aLabel label = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstAffect(c3a.True, tempTest, ""));
        c3a.ajouteInst(new C3aInstJumpIfLess(op1, op2, label, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest, ""));
        c3a.addLabelToNextInst(label);

        return tempTest;
    }

    /**
     * Créer le code 3 adresses pour la condition d'égalité
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpEqual node) {
        C3aLabel label = c3a.newAutoLabel();


        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        C3aTemp tempTest = c3a.newTemp();
        c3a.ajouteInst(new C3aInstAffect(c3a.True, tempTest, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, op2, label, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest, ""));
        c3a.addLabelToNextInst(label);
        return tempTest;
    }

    /**
     * Créer le code 3 adresses pour la condition "et"
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpAnd node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, c3a.False, e2, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op2, c3a.False, e2, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.True, tempTest, ""));
        c3a.ajouteInst(new C3aInstJump(e1, ""));
        c3a.addLabelToNextInst(e2);
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest, ""));
        c3a.addLabelToNextInst(e1);

        return tempTest;
    }

    /**
     * Créer le code 3 adresses pour la condition "ou"
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaExpOr node) {
        C3aLabel e1 = c3a.newAutoLabel();
        C3aLabel e2 = c3a.newAutoLabel();
        C3aTemp tempTest = c3a.newTemp();

        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);


        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op1, c3a.False, e2, ""));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op2, c3a.False, e2, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, tempTest, ""));

        c3a.ajouteInst(new C3aInstJump(e1, ""));
        c3a.addLabelToNextInst(e2);
        c3a.ajouteInst(new C3aInstAffect(c3a.True, tempTest, ""));
        c3a.addLabelToNextInst(e1);

        return tempTest;
    }

    /**
     * Créer le code 3 adresses pour la condition "non"
     * @param node le noeud à visiter
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
     * Créer le code 3 adresses pour la condition "si"
     * @param node le noeud à visiter
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
     * Créer le code 3 adresses pour les listes d'expression et continue le parcours
     * @param node le noeud à visiter
     */
    @Override
    public C3aOperand visit(SaLExp node) {
        c3a.ajouteInst(new C3aInstParam(node.getTete().accept(this), ""));

        if (node.getQueue() != null) return node.getQueue().accept(this);
        else return null;
    }

    /**
     * Permet de visiter les noeuds de la liste d'instruction
     * @param node le noeud à visiteretourr
     */
    @Override
    public C3aOperand visit(SaLInst node) {
        return super.visit(node);
    }

    /**
     * Permet de visiter les noeuds de la liste de déclaration
     * @param node le noeud à visiteretourr
     */
    @Override
    public C3aOperand visit(SaLDec node) {
        return super.visit(node);
    }

    /**
     * Permet de visiter les noeuds des instructions du bloc
     * @param node le noeud à visiteretourr
     */
    @Override
    public C3aOperand visit(SaInstBloc node) {
        return super.visit(node);
    }

}
