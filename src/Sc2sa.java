import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {

    private SaNode returnValue;

    public SaNode getRoot() {
        return returnValue;
    }

    private SaNode apply(Switchable sw) {
        sw.apply(this);
        return returnValue;
    }

    @Override
    public void defaultIn(Node node) { //Marche 100%
        super.defaultIn(node);
    }

    @Override
    public void defaultOut(Node node) { //Marche 100%
        super.defaultOut(node);
    }

    @Override
    public void caseStart(Start node) { //Marche 100%
        apply(node.getPProgramme());
    }

    @Override
    public void caseAProgramme(AProgramme node) { //Marche 100%
        SaLDec variable = (SaLDec) apply(node.getDecvar2());
        SaLDec fonctions = (SaLDec) apply(node.getDeffonction2());
        this.returnValue = new SaProg(variable, fonctions);
    }
    //Jusque là c'est bon

    @Override
    public void caseAMultipleDecvar(AMultipleDecvar node) {//TODO à vérifier mais ok
        SaDec varSimple = (SaDec) apply(node.getVarsimple());
        SaLDec saLDec = (SaLDec) apply(node.getVarmultiple());
        this.returnValue = new SaLDec(varSimple,saLDec);
    }

    @Override
    public void caseAEntierVarsimple(AEntierVarsimple node) { //ok
        SaDecVar var = (SaDecVar) apply(node.getVarent());
        this.returnValue = new SaDecVar(var.getNom());
    }

    @Override
    public void caseATabVarsimple(ATabVarsimple node) {//ok
        SaDecTab var = (SaDecTab) apply(node.getVartab());
        this.returnValue = new SaDecTab(var.getNom(),var.getTaille());
    }

    @Override
    public void caseAVarent(AVarent node) {//ok
        String nom = node.getNom().getText();
        returnValue = new SaDecVar(nom);
    }

    @Override
    public void caseAVartab(AVartab node) { //ok
        String nom = node.getNom().getText();
        int taille = Integer.parseInt(node.getNombre().getText());
        returnValue = new SaDecTab(nom,taille);
    }

    @Override
    public void caseAVarmultiple(AVarmultiple node) {//ok
        SaDec saDec = (SaDec) apply(node.getVarsimple());
        SaLDec saLDec = (SaLDec) apply((node.getVarmultiple()));
        this.returnValue = new SaLDec(saDec,saLDec);
    }


    @Override
    public void caseARienVarmultiple(ARienVarmultiple node) {//OK
        this.returnValue = null;
    }

    @Override
    public void caseAMultipleFctdecvar(AMultipleFctdecvar node) { //pas sur
        //Je pense que c'est bon
        SaDec varSimple = (SaDec) apply(node.getVarsimple());
        SaLDec var = (SaLDec) apply(node.getVarmultiple());
        this.returnValue= new SaLDec(varSimple,var);
    }

    @Override
    public void caseAFctvarmultiple(AFctvarmultiple node) {//pas sur
        //Je pense que c'est bon
        SaDec varSimple = (SaDec) apply(node.getVarsimple());
        this.returnValue = new SaDecVar(varSimple.getNom());
    }

    @Override
    public void caseARienFctvarmultiple(ARienFctvarmultiple node) {//OK
        this.returnValue = null;
    }

    @Override
    public void caseAFoncDeffonction2(AFoncDeffonction2 node) {//todo vérif bien car vraiment pas sur
        //???? Pas compris ????
        SaDecFonc tete = (SaDecFonc)apply(node.getDeffonction());
        SaDecFonc queue = (SaDecFonc) apply(node.getDeffonction2());
        this.returnValue = new SaDecFonc(tete.getNom(),tete.getParametres(),queue.getVariable(),tete.getCorps());
    }

    @Override
    public void caseARienDeffonction2(ARienDeffonction2 node) {//OK
        this.returnValue=null;
    }

    @Override
    public void caseAArgsDeffonction(AArgsDeffonction node) { //Marche
        String nom = node.getNom().getText();
        SaLDec parametre = (SaLDec) apply(node.getFctdecvar());
        SaLDec variables = (SaLDec) apply(node.getDecvar2());
        SaInst bloc = (SaInst) apply(node.getBloc());
        this.returnValue = new SaDecFonc(nom, parametre,variables,bloc);
    }

    @Override
    public void caseASansargDeffonction(ASansargDeffonction node) { //Marche
        String nom = node.getNom().getText();
        SaLDec variables = (SaLDec) apply(node.getDecvar2());
        SaInst bloc = (SaInst) apply(node.getBloc());
        this.returnValue = new SaDecFonc(nom, new SaLDec(null,null),variables,bloc);
    }

    @Override
    public void caseAListListinstr(AListListinstr node) {//todo à corriger
        //corrigé
        SaInst tete = (SaInst) apply(node.getInstr());
        SaLInst queue = (SaLInst) apply(node.getListinstr());
        this.returnValue = new SaLInst(tete,queue);
    }

    @Override
    public void caseARienListinstr(ARienListinstr node) {
        this.returnValue = null;
    }

    @Override
    public void caseAAffectationInstr(AAffectationInstr node) {
        apply(node.getAffect());
    }

    @Override
    public void caseABlocInstr(ABlocInstr node) {
        apply(node.getBloc());
    }

    @Override
    public void caseATantqueInstr(ATantqueInstr node) {
        apply(node.getInstrtantque());

    }

    @Override
    public void caseASiInstr(ASiInstr node) {
        apply(node.getInstrsi());
    }

    @Override
    public void caseARetourInstr(ARetourInstr node) {
        apply(node.getInstrretour());
    }

    @Override
    public void caseAEcrireInstr(AEcrireInstr node) {
        apply(node.getFonctionecrire());
    }

    @Override
    public void caseARienDecvar2(ARienDecvar2 node) {
        this.returnValue = null;
    }

    @Override
    public void caseAVarDecvar2(AVarDecvar2 node) {
        apply(node.getDecvar());
    }

    @Override
    public void caseALireInstr(ALireInstr node) {
        apply(node.getFonctionlire());
    }

    @Override
    public void caseAInstrtantque(AInstrtantque node) {
        SaExp expTest = (SaExp) apply(node.getExpr());
        SaLInst faire = (SaLInst) apply(node.getFaire());
        this.returnValue = new SaInstTantQue(expTest, faire.getTete());
    }

    @Override
    public void caseAInstrretour(AInstrretour node) {
        SaExp inst = (SaExp) apply(node.getExpr());
        this.returnValue = new SaInstRetour(inst);
    }

    @Override
    public void caseASisinonInstrsi(ASisinonInstrsi node) {
        SaExp expTest = (SaExp) apply(node.getExpr());
        SaInst alors = (SaInst) apply(node.getAlors());
        SaInst sinon = (SaInst) apply(node.getSi());
        this.returnValue = new SaInstSi(expTest, alors, sinon);
    }

    @Override
    public void caseASiInstrsi(ASiInstrsi node) { //Pas sur de l'avoir
        SaExp exp = (SaExp) apply(node.getExpr());
        SaInst alors = (SaInst) apply(node.getAlors());
        SaInst sinon = (SaInst) apply(node.getSi());
        this.returnValue = new SaInstSi(exp, alors, sinon);
    }

    @Override
    public void caseAInstrsinon(AInstrsinon node) { //Pas sur de l'avoir
        SaInst saInst = (SaInst) apply(node.getSinon());
        SaLInst saInst1 = (SaLInst) apply(node.getBloc());
        this.returnValue = new SaInstBloc(new SaLInst(saInst,saInst1));
    }

    @Override
    public void caseABloc(ABloc node) { //Marche 100%
        apply(node.getListinstr());
    }

    @Override
    public void caseAAffect(AAffect node) { //Marche 100% //todo à corriger
        SaVar var = (SaVar) apply(node.getVariable());
        SaExp expr = (SaExp) apply(node.getExpr());
        this.returnValue = new SaInstAffect(var, expr);
    }

    @Override
    public void caseASimpleVariable(ASimpleVariable node) {//Censé marcher
        String nom = node.getNom().getText();
        this.returnValue = new SaVarSimple(nom);
    }

    @Override
    public void caseATabVariable(ATabVariable node) { //Pas sur
        String nom = node.getNom().getText();
        node.getExpr().apply(this);
        SaExp exp = (SaExp) this.returnValue;
        this.returnValue = new SaVarIndicee(nom, exp);

    }

    @Override
    public void caseAOuExpr(AOuExpr node) { //Marche 100%
        SaExp op1 = (SaExp) apply(node.getExpr());
        SaExp op2 = (SaExp) apply(node.getExpr2());
        this.returnValue = new SaExpOr(op1, op2);
    }

    @Override
    public void caseAExpr2Expr(AExpr2Expr node) { //Marche 100%
        apply(node.getExpr2());
    }

    @Override
    public void caseAEtExpr2(AEtExpr2 node) { //Marche 100%
        SaExp op1 = (SaExp) apply(node.getExpr2());
        SaExp op2 = (SaExp)apply(node.getExpr3());
        this.returnValue = new SaExpAnd(op1, op2);
    }



    @Override
    public void caseAExpr3Expr2(AExpr3Expr2 node) { //Marche 100%
        apply(node.getExpr3());
    }

    @Override
    public void caseAEgalExpr3(AEgalExpr3 node) { //Marche 100%
        SaExp op1 = (SaExp) apply(node.getExpr3());
        SaExp op2 = (SaExp) apply(node.getExpr4());
        this.returnValue = new SaExpEqual(op1, op2);

    }

    @Override
    public void caseAInfExpr3(AInfExpr3 node) { //Marche 100%
        SaExp op1 = (SaExp) apply(node.getExpr3());
        SaExp op2 = (SaExp) apply(node.getExpr4());
        this.returnValue = new SaExpInf(op1, op2);
    }

    @Override
    public void caseAExpr4Expr3(AExpr4Expr3 node) {
        apply(node.getExpr4());
    }

    @Override
    public void caseAPlusExpr4(APlusExpr4 node) { //ok
        SaExp op1 = (SaExp) apply(node.getExpr4());
        SaExp op2 = (SaExp) apply(node.getExpr5());
        this.returnValue = new SaExpAdd(op1, op2);
    }

    @Override
    public void caseAMoinsExpr4(AMoinsExpr4 node) { //ok
        SaExp op1 = (SaExp) apply(node.getExpr4());
        SaExp op2 = (SaExp) apply(node.getExpr5());
        this.returnValue = new SaExpSub(op1, op2);
    }

    @Override
    public void caseAExpr5Expr4(AExpr5Expr4 node) { //todo à vérifier
       apply(node.getExpr5());
    }

    @Override
    public void caseAMultiExpr5(AMultiExpr5 node) { //ok
        SaExp op1 = (SaExp) apply(node.getExpr5());
        SaExp op2 = (SaExp) apply(node.getExpr6());
        this.returnValue = new SaExpMult(op1, op2);
    }

    @Override
    public void caseADivExpr5(ADivExpr5 node) { //ok
        SaExp op1 = (SaExp) apply(node.getExpr5());
        SaExp op2 = (SaExp) apply(node.getExpr6());
        this.returnValue = new SaExpDiv(op1, op2);
    }

    @Override
    public void caseAExpr6Expr5(AExpr6Expr5 node) { //todo à vérifier
        //Normalement bon
        apply(node.getExpr6());
    }

    @Override
    public void caseANonExpr6(ANonExpr6 node) { //ok
        SaExp exp = (SaExp) apply(node.getExpr7());
        this.returnValue = new SaExpNot(exp);
    }

    @Override
    public void caseAPart7Expr6(APart7Expr6 node) {//todo à corriger
        /*SaLExp exp = (SaLExp) apply(node.getExpr7());
        this.returnValue = new SaLExp(exp.getTete(),exp.getQueue());*/
        apply(node.getExpr7());
    }

    @Override
    public void caseAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node) {//todo à vérifier
        //Quasi sur
        apply(node.getExpr());
    }

    @Override
    public void caseANomvarExpr7(ANomvarExpr7 node) {//ok
        String nom = node.getNom().getText();
        this.returnValue = new SaDecVar(nom);
        //todo : pourquoi une déclaration de var ?
    }

    @Override
    public void caseANombreExpr7(ANombreExpr7 node) {//ok
        int nombre = Integer.parseInt(node.getNombre().getText());
        this.returnValue = new SaExpInt(nombre);
    }

    @Override
    public void caseALireExpr7(ALireExpr7 node) { //todo à vérifier
        apply(node.getFonctionlire());
        //this.returnValue = new SaExpLire(); ??
    }

    @Override
    public void caseATabExpr7(ATabExpr7 node) { //Marche normalement
        SaExp op1 = (SaExp) apply(node.getNombre());
        String nom = node.getNom().getText();
        this.returnValue = new SaVarIndicee(nom, op1);
    }

    @Override
    public void caseAFonctionExpr7(AFonctionExpr7 node) { //pas sur
        apply(node.getDeffonction());
    }

    @Override
    public void caseAAppelExpr7(AAppelExpr7 node) { //Marche 100%
        SaAppel op1 = (SaAppel) apply(node.getFonctionappel());
        this.returnValue = new SaExpAppel(op1);
    }

    @Override
    public void caseAFonctionecrire(AFonctionecrire node) { //Marche 100%
        SaExp op1 = (SaExp) apply(node.getExpr());
        this.returnValue = new SaInstEcriture(op1);
    }

    @Override
    public void caseAFonctionappel(AFonctionappel node) { //Marche 100%
        String nom = node.getNom().getText();
        SaLExp op1 = (SaLExp) apply(node.getAppelexpr());
        this.returnValue = new SaAppel(nom, op1);
    }

    @Override
    public void caseAAppelexpr(AAppelexpr node) {
        SaAppel op1 = (SaAppel) apply(node.getExpr());
        this.returnValue = new SaExpAppel(op1);
    }

    @Override
    public void caseAListeexpr(AListeexpr node) {
        SaLExp op1 = (SaLExp)apply(node.getExpr());
        this.returnValue = new SaLExp(op1.getTete(),op1.getQueue());
    }

    @Override
    public void caseAVideFonctionlire(AVideFonctionlire node) {//todo à vérifier + le todo d'en dessous
       apply(node.getLire());
       //this.returnValue = null  peut-être
    }

    @Override
    public void caseASimpleFonctionlire(ASimpleFonctionlire node) {
        //TODO à finir et modifier la grammaire pour plus de logique
        this.returnValue = new SaExpLire();
        //pourquoi pas apply(node.getExpr()); ??

    }


}
