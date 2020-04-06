import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;


public class Sc2sa extends DepthFirstAdapter {

    private SaNode returnValue;

    SaNode getRoot() {
        return returnValue;
    }

    /**
     * Applies the search on sw
     *
     * @param sw the node to visit
     */
    private SaNode apply(Switchable sw) {
        sw.apply(this);
        return returnValue;
    }

    @Override
    public void defaultIn(Node node) {
        super.defaultIn(node);
    }

    @Override
    public void defaultOut(Node node) {
        super.defaultOut(node);
    }

    /**
     * Applies the search for the startinf node
     *
     * @param node the node to visit
     */
    @Override
    public void caseStart(Start node) {
        apply(node.getPProgramme());
    }

    /**
     * Create the node for the case of a program
     *
     * @param node the node to visit
     */
    @Override
    public void caseAProgramme(AProgramme node) {
        SaLDec var = (SaLDec) apply(node.getDecvar2());
        SaLDec fonct = (SaLDec) apply(node.getDeffonction2());
        this.returnValue = new SaProg(var, fonct);
    }

    /**
     * Create the node for the case of a variable declaration
     *
     * @param node the node to visit
     */
    @Override
    public void caseADecvar(ADecvar node) {
        SaDec dec = (SaDec) apply(node.getVarsimple());
        SaLDec listeDec = (SaLDec) apply(node.getVarmultiple());
        this.returnValue = new SaLDec(dec, listeDec);
    }

    /**
     * Create the node for the case of an integer simple variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseAEntierVarsimple(AEntierVarsimple node) {
        SaDecVar var = (SaDecVar) apply(node.getVarent());
        this.returnValue = new SaDecVar(var.getNom());
    }

    /**
     * Create the node for the case of an indexed simple variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseATabVarsimple(ATabVarsimple node) {
        SaDecTab tab = (SaDecTab) apply(node.getVartab());
        String nom = tab.getNom();
        int taille = tab.getTaille();
        this.returnValue = new SaDecTab(nom, taille);
    }

    /**
     * Create the node for the case of an integer variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseAVarent(AVarent node) {
        String nom = node.getNom().getText();
        returnValue = new SaDecVar(nom);
    }

    /**
     * Create the node for the case of an indexed variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseAVartab(AVartab node) {
        String nom = node.getNom().getText();
        int taille = Integer.parseInt(node.getNombre().getText());
        returnValue = new SaDecTab(nom, taille);
    }

    /**
     * Create the node for the case of multiple variables
     *
     * @param node the node to visit
     */
    @Override
    public void caseAVarmultiple(AVarmultiple node) {
        SaDec var = (SaDec) apply(node.getVarsimple());
        SaLDec listeVar = (SaLDec) apply((node.getVarmultiple()));
        this.returnValue = new SaLDec(var, listeVar);
    }

    /**
     * Create the node for the case of a null multiple variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseARienVarmultiple(ARienVarmultiple node) {
        this.returnValue = null;
    }

    /**
     * Create the node for the case of a function definition function
     *
     * @param node the node to visit
     */
    @Override
    public void caseAFoncDeffonction2(AFoncDeffonction2 node) {
        SaDec fonct = (SaDec) apply(node.getDeffonction());
        SaLDec listeFonct = (SaLDec) apply(node.getDeffonction2());
        this.returnValue = new SaLDec(fonct, listeFonct);
    }

    /**
     * Create the node for the case of a null definition function
     *
     * @param node the node to visit
     */
    @Override
    public void caseARienDeffonction2(ARienDeffonction2 node) {
        this.returnValue = null;
    }

    /**
     * Create the node for the case of a multiple function declaration variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseAMultipleFctdecvar(AMultipleFctdecvar node) {
        SaDec dec = (SaDec) apply(node.getVarsimple());
        SaLDec listeDec = (SaLDec) apply(node.getVarmultiple());
        this.returnValue = new SaLDec(dec, listeDec);
    }

    /**
     * Create the node for the case of a function multiple variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseAFctvarmultiple(AFctvarmultiple node) {
        SaDec varSimple = (SaDec) apply(node.getVarsimple());
        SaLDec varMultiple = (SaLDec) apply(node.getFctvarmultiple());
        this.returnValue = new SaLDec(varSimple, varMultiple);
    }

    /**
     * Create the node for the case of a null function multiple variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseARienFctvarmultiple(ARienFctvarmultiple node) {
        this.returnValue = null;
    }

    /**
     * Create the node for the case of arguments definiton function
     *
     * @param node the node to visit
     */
    @Override
    public void caseAArgsDeffonction(AArgsDeffonction node) {
        String nom = node.getNom().getText();
        SaLDec param = (SaLDec) apply(node.getFctdecvar());
        SaLDec var = (SaLDec) apply(node.getDecvar2());
        SaInst bloc = (SaInst) apply(node.getBloc());
        this.returnValue = new SaDecFonc(nom, param, var, bloc);
    }

    /**
     * Create the node for the case without arguments definition function
     *
     * @param node the node to visit
     */
    @Override
    public void caseASansargDeffonction(ASansargDeffonction node) {
        String nom = node.getNom().getText();
        SaLDec var = (SaLDec) apply(node.getDecvar2());
        SaInstBloc bloc = (SaInstBloc) apply(node.getBloc());
        this.returnValue = new SaDecFonc(nom, null, var, bloc);
    }

    /**
     * Create the node for the case of a list of list of instructions
     *
     * @param node the node to visit
     */
    @Override
    public void caseAListListinstr(AListListinstr node) {
        SaInst tete = (SaInst) apply(node.getInstr());
        SaLInst queue = (SaLInst) apply(node.getListinstr());
        this.returnValue = new SaLInst(tete, queue);
    }

    /**
     * Create the node for the case of null list of list of instructions
     *
     * @param node the node to visit
     */
    @Override
    public void caseARienListinstr(ARienListinstr node) {
        this.returnValue = null;
    }

    /**
     * Create the node for the case of an affectation instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseAAffectationInstr(AAffectationInstr node) {
        apply(node.getAffect());
    }

    /**
     * Create the node for the case of a bloc of instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseABlocInstr(ABlocInstr node) {
        apply(node.getBloc());
    }

    /**
     * Create the node for the case of a while instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseATantqueInstr(ATantqueInstr node) {
        apply(node.getInstrtantque());
    }

    /**
     * Create the node for the case of a if instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseASiInstr(ASiInstr node) {
        apply(node.getInstrsi());
    }

    /**
     * Create the node for the case of a return instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseARetourInstr(ARetourInstr node) {
        apply(node.getInstrretour());
    }

    /**
     * Create the node for the case of a write instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseAEcrireInstr(AEcrireInstr node) {
        apply(node.getFonctionecrire());
    }

    /***
     * Create the node for the case of null declaration variable
     * @param node the node to visit
     */
    @Override
    public void caseARienDecvar2(ARienDecvar2 node) {
        this.returnValue = null;
    }

    /**
     * Create the node for the case of variable declaration variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseAVarDecvar2(AVarDecvar2 node) {
        apply(node.getDecvar());
    }

    /**
     * Create the node for the case of a read instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseALireInstr(ALireInstr node) {
        apply(node.getFonctionlire());
    }

    /**
     * Create the node for the case of a while instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseAInstrtantque(AInstrtantque node) {
        SaExp testExpression = (SaExp) apply(node.getExpr());
        SaInstBloc blocInst = (SaInstBloc) apply(node.getBloc());
        this.returnValue = new SaInstTantQue(testExpression, blocInst);
    }

    /**
     * Create the node for the case of a return instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseAInstrretour(AInstrretour node) {
        SaExp inst = (SaExp) apply(node.getExpr());
        this.returnValue = new SaInstRetour(inst);
    }

    /**
     * Create the node for the case of if else if instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseASisinonInstrsi(ASisinonInstrsi node) {
        SaExp testExpression = (SaExp) apply(node.getExpr());
        SaInstBloc alorsBloc = (SaInstBloc) apply(node.getBloc());
        SaLInst sinonInst = (SaLInst) apply(node.getInstrsinon());
        this.returnValue = new SaInstSi(testExpression, alorsBloc, sinonInst.getTete());
    }

    /**
     * Create the node for the case of if instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseASiInstrsi(ASiInstrsi node) {
        SaExp siExpression = (SaExp) apply(node.getExpr());
        SaInstBloc blocInst = (SaInstBloc) apply(node.getBloc());
        this.returnValue = new SaInstSi(siExpression, blocInst, null);
    }

    /**
     * Create the node for the case of else instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseAInstrsinon(AInstrsinon node) {
        SaInstBloc blocInst = (SaInstBloc) apply(node.getBloc());
        this.returnValue = new SaLInst(blocInst, null);
    }

    /**
     * Create the node for the case of a bloc of instructions
     *
     * @param node the node to visit
     */
    @Override
    public void caseABloc(ABloc node) {
        SaLInst listeInstr = (SaLInst) apply(node.getListinstr());
        if (listeInstr == null) {
            this.returnValue = null;
        } else {
            this.returnValue = new SaInstBloc(listeInstr);
        }
    }

    /**
     * Create the node for the case of affectation
     *
     * @param node the node to visit
     */
    @Override
    public void caseAAffect(AAffect node) {
        SaVar var = (SaVar) apply(node.getVariable());
        SaExp expr = (SaExp) apply(node.getExpr());
        this.returnValue = new SaInstAffect(var, expr);
    }

    /**
     * Create the node for the case of variable expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAVarExpr7(AVarExpr7 node) {
        SaVar var = (SaVar) apply(node.getVariable());
        this.returnValue = new SaExpVar(var);
    }

    /**
     * Create the node for the case of a simple variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseASimpleVariable(ASimpleVariable node) {
        String nom = node.getNom().getText();
        this.returnValue = new SaVarSimple(nom);
    }

    /**
     * Create the node for the case of a indexed variable
     *
     * @param node the node to visit
     */
    @Override
    public void caseATabVariable(ATabVariable node) {
        String nom = node.getNom().getText();
        SaExp exp = (SaExp) apply(node.getExpr());
        this.returnValue = new SaVarIndicee(nom, exp);
    }

    /**
     * Create the node for the case of or expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAOuExpr(AOuExpr node) {
        SaExp operand1 = (SaExp) apply(node.getExpr());
        SaExp operand2 = (SaExp) apply(node.getExpr2());
        this.returnValue = new SaExpOr(operand1, operand2);
    }

    /**
     * Create the node for the case of a expression expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAExpr2Expr(AExpr2Expr node) {
        apply(node.getExpr2());
    }

    /**
     * Create the node for the case of and expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAEtExpr2(AEtExpr2 node) {
        SaExp operand1 = (SaExp) apply(node.getExpr2());
        SaExp operand2 = (SaExp) apply(node.getExpr3());
        this.returnValue = new SaExpAnd(operand1, operand2);
    }

    /**
     * Create the node for the case of a expression expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAExpr3Expr2(AExpr3Expr2 node) {
        apply(node.getExpr3());
    }

    /**
     * Create the node for the case of equal expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAEgalExpr3(AEgalExpr3 node) {
        SaExp operand1 = (SaExp) apply(node.getExpr3());
        SaExp operand2 = (SaExp) apply(node.getExpr4());
        this.returnValue = new SaExpEqual(operand1, operand2);
    }

    /**
     * Create the node for the case of less expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAInfExpr3(AInfExpr3 node) {
        SaExp operand1 = (SaExp) apply(node.getExpr3());
        SaExp operand2 = (SaExp) apply(node.getExpr4());
        this.returnValue = new SaExpInf(operand1, operand2);
    }

    /**
     * Create the node for the case of a expression expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAExpr4Expr3(AExpr4Expr3 node) {
        apply(node.getExpr4());
    }

    /**
     * Create the node for the case of add expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAPlusExpr4(APlusExpr4 node) {
        SaExp operand1 = (SaExp) apply(node.getExpr4());
        SaExp operand2 = (SaExp) apply(node.getExpr5());
        this.returnValue = new SaExpAdd(operand1, operand2);
    }

    /**
     * Create the node for the case of sub expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAMoinsExpr4(AMoinsExpr4 node) {
        SaExp operand1 = (SaExp) apply(node.getExpr4());
        SaExp operand2 = (SaExp) apply(node.getExpr5());
        this.returnValue = new SaExpSub(operand1, operand2);
    }

    /**
     * Create the node for the case of a expression expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAExpr5Expr4(AExpr5Expr4 node) {
        apply(node.getExpr5());
    }

    /**
     * Create the node for the case of mult expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAMultiExpr5(AMultiExpr5 node) {
        SaExp operand1 = (SaExp) apply(node.getExpr5());
        SaExp operand2 = (SaExp) apply(node.getExpr6());
        this.returnValue = new SaExpMult(operand1, operand2);
    }

    /**
     * Create the node for the case of div expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseADivExpr5(ADivExpr5 node) {
        SaExp operand1 = (SaExp) apply(node.getExpr5());
        SaExp operand2 = (SaExp) apply(node.getExpr6());
        this.returnValue = new SaExpDiv(operand1, operand2);
    }

    /**
     * Create the node for the case of a expression expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAExpr6Expr5(AExpr6Expr5 node) {
        apply(node.getExpr6());
    }

    /**
     * Create the node for the case of not expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseANonExpr6(ANonExpr6 node) {
        SaExp exp = (SaExp) apply(node.getExpr7());
        this.returnValue = new SaExpNot(exp);
    }

    /**
     * Create the node for the case of a expression expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAPart7Expr6(APart7Expr6 node) {
        apply(node.getExpr7());
    }

    /**
     * Create the node for the case of parenthesis expression parenthesis
     *
     * @param node the node to visit
     */
    @Override
    public void caseAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node) {
        apply(node.getExpr());
    }

    /**
     * Create the node for the case of a int
     *
     * @param node the node to visit
     */
    @Override
    public void caseANombreExpr7(ANombreExpr7 node) {
        int nombre = Integer.parseInt(node.getNombre().getText());
        this.returnValue = new SaExpInt(nombre);
    }

    /**
     * Create the node for the case of read expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseALireExpr7(ALireExpr7 node) {
        apply(node.getFonctionlire());
    }

    /**
     * Create the node for the case of function expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAFonctionExpr7(AFonctionExpr7 node) {
        SaDecFonc fonc = (SaDecFonc) apply(node.getDeffonction());
        this.returnValue = new SaDecFonc(fonc.getNom(), fonc.getParametres(), fonc.getVariable(), fonc.getCorps());
    }

    /**
     * Create the node for the case of write function
     *
     * @param node the node to visit
     */
    @Override
    public void caseAFonctionecrire(AFonctionecrire node) {
        SaExp operand1 = (SaExp) apply(node.getExpr());
        this.returnValue = new SaInstEcriture(operand1);
    }

    /**
     * Create the node for the case of call expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAAppelExpr7(AAppelExpr7 node) {
        apply(node.getFonctionappel());
    }

    /**
     * Create the node for the case of call function with arguments
     *
     * @param node the node to visit
     */
    @Override
    public void caseAAvecargsFonctionappel(AAvecargsFonctionappel node) {
        String nom = node.getNom().getText();
        SaLExp appel = (SaLExp) apply(node.getAppelexpr());
        this.returnValue = new SaExpAppel(new SaAppel(nom, appel));
    }

    /**
     * Create the node for the case of call function without arguments
     *
     * @param node the node to visit
     */
    @Override
    public void caseASansargFonctionappel(ASansargFonctionappel node) {
        String nom = node.getNom().getText();
        this.returnValue = new SaExpAppel(new SaAppel(nom, null));
    }

    /**
     * Create the node for the case of expression list of expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAAppelexpr(AAppelexpr node) {
        SaExp exp = (SaExp) apply(node.getExpr());
        SaLExp listeExp = (SaLExp) apply(node.getListeexpr());
        this.returnValue = new SaLExp(exp, listeExp);
    }

    /**
     * Create the node for the case of null list of expressions
     *
     * @param node the node to visit
     */
    @Override
    public void caseARienListeexpr(ARienListeexpr node) {
        this.returnValue = null;
    }

    /**
     * Create the node for the case of list of expression
     *
     * @param node the node to visit
     */
    @Override
    public void caseAListeexpr(AListeexpr node) {
        SaExp exp = (SaExp) apply(node.getExpr());
        SaLExp listeExp = (SaLExp) apply(node.getListeexpr());
        this.returnValue = new SaLExp(exp, listeExp);
    }

    /**
     * Create the node for the case of a call instruction
     *
     * @param node the node to visit
     */
    @Override
    public void caseAAppelInstr(AAppelInstr node) {
        SaExpAppel inst = (SaExpAppel) apply(node.getFonctionappel());
        this.returnValue = new SaAppel(inst.getVal().getNom(), inst.getVal().getArguments());
    }

    /**
     * Create the node for the case of read function
     *
     * @param node the node to visit
     */
    @Override
    public void caseAFonctionlire(AFonctionlire node) {
        apply(node.getLire());
        this.returnValue = new SaExpLire();
    }
}
