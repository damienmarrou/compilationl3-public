import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {

    private SaNode returnValue;

    SaNode getRoot() {
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

    @Override
    public void caseADecvar(ADecvar node) {
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
        String nom = var.getNom();
        int taille = var.getTaille();
        this.returnValue = new SaDecTab(nom,taille);
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
    //Jusque là c'est bon


    @Override
    public void caseAFoncDeffonction2(AFoncDeffonction2 node) {//todo vérif bien car vraiment pas sur
            SaDec saLDec = (SaDec)apply(node.getDeffonction());
            SaLDec saLDec1 = (SaLDec)apply(node.getDeffonction2());
            this.returnValue = new SaLDec(saLDec,saLDec1);
    }

    @Override
    public void caseARienDeffonction2(ARienDeffonction2 node) {//OK
        this.returnValue=null;
    }

    @Override
    public void caseAMultipleFctdecvar(AMultipleFctdecvar node) {
        SaDec varSimple = (SaDec) apply(node.getVarsimple());
        SaLDec var = (SaLDec) apply(node.getVarmultiple());
        this.returnValue= new SaLDec(varSimple,var);
    }

    @Override
    public void caseAFctvarmultiple(AFctvarmultiple node) {
        SaDec varSimple = (SaDec) apply(node.getVarsimple());
        SaLDec varMultiple = (SaLDec) apply(node.getFctvarmultiple());
        this.returnValue = new SaLDec(varSimple,varMultiple);
    }

    @Override
    public void caseARienFctvarmultiple(ARienFctvarmultiple node) {//OK
        this.returnValue = null;
    }

    @Override
    public void caseAArgsDeffonction(AArgsDeffonction node) { //Marche pas
        String nom = node.getNom().getText();
        SaLDec parametre = (SaLDec) apply(node.getFctdecvar());
        SaLDec variables = (SaLDec) apply(node.getDecvar2());
        SaInst bloc = (SaInst) apply(node.getBloc());
        this.returnValue = new SaDecFonc(nom, parametre,variables,bloc);
    }

    @Override
    public void caseASansargDeffonction(ASansargDeffonction node) { //Marche pas
        String nom = node.getNom().getText();
        SaLDec variables = (SaLDec) apply(node.getDecvar2());
        SaInstBloc  bloc = (SaInstBloc) apply(node.getBloc());
        this.returnValue = new SaDecFonc(nom, null,variables,bloc);
    }

    @Override
    public void caseAListListinstr(AListListinstr node) {//todo à corriger
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
        SaLInst bloc = (SaLInst) apply(node.getBloc());
        if (bloc == null)
            bloc = new SaLInst(null, null);
        this.returnValue =new SaInstBloc(bloc);
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
        SaInstBloc faire = (SaInstBloc) apply(node.getBloc());
        this.returnValue = new SaInstTantQue(expTest, faire);
    }

    @Override
    public void caseAInstrretour(AInstrretour node) {
        SaExp inst = (SaExp) apply(node.getExpr());
        this.returnValue = new SaInstRetour(inst);
    }

    @Override
    public void caseASisinonInstrsi(ASisinonInstrsi node) {
        SaExp expTest = (SaExp) apply(node.getExpr());
        SaInstBloc alors = (SaInstBloc) apply(node.getBloc());
        SaLInst sinon = (SaLInst) apply(node.getInstrsinon());
        this.returnValue = new SaInstSi(expTest, alors,sinon.getTete());
    }

    @Override
    public void caseASiInstrsi(ASiInstrsi node) {
        SaExp exp = (SaExp) apply(node.getExpr());
        SaInstBloc bloc1 = (SaInstBloc)apply(node.getBloc());
        this.returnValue = new SaInstSi(exp,bloc1,null);
    }

    @Override
    public void caseAInstrsinon(AInstrsinon node) { //Pas sur de l'avoir
        SaInstBloc bloc1 = (SaInstBloc)apply(node.getBloc());
        this.returnValue = new SaLInst (bloc1,null);
    }

    @Override
    public void caseABloc(ABloc node) {
        SaLInst inst = (SaLInst) apply(node.getListinstr());
        this.returnValue = new SaInstBloc(inst);
    }

    @Override
    public void caseAAffect(AAffect node) { //Marche 100% //todo à corriger le cas du tableau
        SaVarSimple var = (SaVarSimple) apply(node.getVariable());
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
        int taille = Integer.parseInt(node.getNombre().getText());
        this.returnValue = new SaDecTab(nom,taille);
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
       this.returnValue = null;
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
        this.returnValue = null;
    }

    @Override
    public void caseANonExpr6(ANonExpr6 node) { //ok
        SaExp exp = (SaExp) apply(node.getExpr7());
        this.returnValue = new SaExpNot(exp);
    }

    @Override
    public void caseAPart7Expr6(APart7Expr6 node) {//todo à corriger
        this.returnValue = null;
    }

    @Override
    public void caseAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node) {//todo à vérifier
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
        this.returnValue = new SaExpLire();
    }

    @Override
    public void caseATabExpr7(ATabExpr7 node) { //Marche normalement
        String nom = node.getNom().getText();
        int taille = Integer.parseInt(node.getNombre().getText());
        this.returnValue = new SaDecTab(nom,taille);
    }

    @Override
    public void caseAFonctionExpr7(AFonctionExpr7 node) { //pas sur
        SaDecFonc fonc = (SaDecFonc) apply(node.getDeffonction());
        this.returnValue = new SaDecFonc(fonc.getNom(),fonc.getParametres(),fonc.getVariable(),fonc.getCorps());
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
        this.returnValue = new SaAppel(nom,op1);
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
        SaExp exp = (SaExp) apply(node.getExpr());
        this.returnValue = new SaLExp(exp,null);
       /* this.returnValue = new SaExpLire();
        //pourquoi pas apply(node.getExpr()); ??
*/
    }


}
