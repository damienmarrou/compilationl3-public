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
        //super.caseStart(node);
        apply(node.getPProgramme());
    }

    @Override
    public void caseAProgramme(AProgramme node) { //Marche 100%
        //super.caseAProgramme(node);
        SaLDec variable = (SaLDec) apply(node.getDecvar2());
        SaLDec fonctions = (SaLDec) apply(node.getDeffonction2());
        this.returnValue = new SaProg(variable, fonctions);
    }
    //Jusque là c'est bon
    //TODO : Revoir les cas des varsimple, varent, vartab
/*
    @Override
    public void caseAMultipleDecvar(AMultipleDecvar node) { //Pas bon à vérifier l'appel en SaDecVar
        //super.caseAMultipleDecvar(node);
        SaDec opt1 = (SaDec) apply(node.getVarsimple());
        SaLDec opt2 = (SaLDec) apply(node.getVarmultiple());
        this.returnValue = new SaLDec(opt2.getTete(), opt2.getQueue());
    }

    @Override
    public void caseAEntierVarsimple(AEntierVarsimple node) { //Marche 100%
        //super.caseAEntierVarsimple(node);
        apply(node.getVarent());
    }

    @Override
    public void caseATabVarsimple(ATabVarsimple node) { //Marche 100%
        //super.caseATabVarsimple(node);
        apply(node.getVartab());
    }

    @Override
    public void caseAVarent(AVarent node) { //A REVOIR mais j'pense que c'est ok
        //super.caseAVarent(node);
        apply(node.getNom());
    }

    @Override
    public void caseAVartab(AVartab node) { //A REVOIR
        //super.caseAVartab(node);
        apply(node.getNom());

    }

    @Override
    public void caseAVarmultiple(AVarmultiple node) {//pas sur d'avoir cette fonction
        //super.caseAVarmultiple(node);
        node.getVarsimple().apply(this);
        SaLDec exp1 = (SaLDec) this.returnValue;
        node.getVarmultiple().apply(this);
        SaLDec exp2 = (SaLDec)this.returnValue;
    }

    @Override
    public void caseARienVarmultiple(ARienVarmultiple node) {//pas sur mais semble bon
        super.caseARienVarmultiple(node);
        //node.apply(this);
    }

    @Override
    public void caseAMultipleFctdecvar(AMultipleFctdecvar node) { //pas sur
        //super.caseAMultipleFctdecvar(node);
    }

    @Override
    public void caseAFctvarmultiple(AFctvarmultiple node) {//pas sur
        //super.caseAFctvarmultiple(node);
    }

    @Override
    public void caseARienFctvarmultiple(ARienFctvarmultiple node) {//pas sur mais normalement c'est bon
        //super.caseARienFctvarmultiple(node);
    }*/

    @Override
    public void caseAFoncDeffonction2(AFoncDeffonction2 node) {
        //super.caseAFoncDeffonction2(node);
        if(node.getDeffonction2()==null){
            node.getDeffonction().apply(this);
            SaDecFonc saDecFonc = (SaDecFonc) this.returnValue;
            this.returnValue = new SaDecFonc(saDecFonc.getNom(),saDecFonc.getParametres(),saDecFonc.getVariable(),saDecFonc.getCorps());
        }
        else{
            node.getDeffonction().apply(this);
            SaDecFonc saDecFonc = (SaDecFonc) this.returnValue;
            this.returnValue = new SaDecFonc(saDecFonc.getNom(),saDecFonc.getParametres(),saDecFonc.getVariable(),saDecFonc.getCorps());
            node.getDeffonction2().apply(this);
            SaDecFonc saDecFonc1 = (SaDecFonc)this.returnValue;
            this.returnValue = new SaDecFonc(saDecFonc1.getNom(),saDecFonc1.getParametres(),saDecFonc1.getVariable(),saDecFonc1.getCorps());
        }
        //node.getDeffonction2();

        /*SaDec exp = (SaDec) this.returnValue;
        node.getDeffonction2().apply(this);
        SaLDec exp1 = (SaLDec) this.returnValue;*/
        //todo : a finir
    }

/*
    @Override
    public void caseARienDeffonction2(ARienDeffonction2 node) {//normalement c'est bon
        super.caseARienDeffonction2(node);
    }
/*
    @Override
    public void caseAArgsDeffonction(AArgsDeffonction node) { //Marche
        //super.caseAArgsDeffonction(node);
        String nom = node.getNom().getText();
        node.getFctdecvar().apply(this);
        SaLDec parametre = (SaLDec) this.returnValue;
        node.getDecvar2().apply(this);
        SaLDec variables = (SaLDec) this.returnValue;
        node.getBloc().apply(this);
        SaInst bloc = (SaInst) this.returnValue;
        this.returnValue = new SaDecFonc(nom, parametre,variables,bloc);
    }

    @Override
    public void caseASansargDeffonction(ASansargDeffonction node) { //Marche peut-être
        //super.caseASansargDeffonction(node);
        String nom = node.getNom().getText();
        node.getDecvar2().apply(this);
        SaLDec variables = (SaLDec) this.returnValue;
        node.getBloc().apply(this);
        SaInst bloc = (SaInst) this.returnValue;
        this.returnValue = new SaDecFonc(nom, null,variables,bloc);
    }
/*
    @Override
    public void caseAListListinstr(AListListinstr node) { //Marche
        //super.caseAListListinstr(node);
        node.getInstr().apply(this);
        SaInst instr = (SaInst) this.returnValue;
        node.getListinstr().apply(this);
        SaLInst listInstr = (SaLInst) this.returnValue;
        this.returnValue = new SaLInst(instr, listInstr);

    }

    @Override
    public void caseARienListinstr(ARienListinstr node) {//pas sur mais semble bon
        //super.caseARienListinstr(node);
    }

    @Override
    public void caseAAffectationInstr(AAffectationInstr node) { //normalement ok
        //super.caseAAffectationInstr(node);
        apply(node.getAffect());
    }

    @Override
    public void caseABlocInstr(ABlocInstr node) { //Normalement Marche
        //super.caseABlocInstr(node);
        apply(node.getBloc());
    }

    @Override
    public void caseATantqueInstr(ATantqueInstr node) { //pas sur
        //super.caseATantqueInstr(node);
        apply(node.getInstrtantque());
    }

    @Override
    public void caseASiInstr(ASiInstr node) { //pas sur
        //super.caseASiInstr(node);
        apply(node.getInstrsi());
    }

    @Override
    public void caseARetourInstr(ARetourInstr node) { //pas sur
        //super.caseARetourInstr(node);
        node.getInstrretour().apply(this);
        SaExp expr = (SaExp) this.returnValue;
        this.returnValue = new SaInstRetour(expr);
    }

    @Override
    public void caseAEcrireInstr(AEcrireInstr node) {//pas sur
        //super.caseAEcrireInstr(node);
        node.getFonctionecrire().apply(this);
        SaExp expr = (SaExp) this.returnValue;
        this.returnValue = new SaInstEcriture(expr);
    }

    @Override
    public void caseARienDecvar2(ARienDecvar2 node) {
        super.caseARienDecvar2(node);
    }

    @Override
    public void caseAVarDecvar2(AVarDecvar2 node) {
        super.caseAVarDecvar2(node);
    }

    @Override
    public void caseALireInstr(ALireInstr node) {
        super.caseALireInstr(node);
    }

    @Override
    public void caseAInstrtantque(AInstrtantque node) { //Marche
        //super.caseAInstrtantque(node);
        node.getExpr().apply(this);
        SaExp expTest = (SaExp) this.returnValue;
        node.getFaire().apply(this);
        SaInst faire = (SaInst) this.returnValue;
        this.returnValue = new SaInstTantQue(expTest, faire);
    }

    @Override
    public void caseAInstrretour(AInstrretour node) { //Marche 100%
        //super.caseAInstrretour(node);
        apply(node.getExpr());
    }

    @Override
    public void caseASisinonInstrsi(ASisinonInstrsi node) { //REVOIR
        //super.caseASisinonInstrsi(node);
        node.getSi().apply(this);
        SaExp expTest = (SaExp) this.returnValue;
        node.getAlors().apply(this);
        SaInst alors = (SaInst) this.returnValue;
        node.getInstrsinon().apply(this);
        SaInst sinon = (SaInst) this.returnValue;
        this.returnValue = new SaInstSi(expTest, alors, sinon);
    }

    @Override
    public void caseASiInstrsi(ASiInstrsi node) { //Pas sur de l'avoir
        //super.caseASiInstrsi(node);
    }

    @Override
    public void caseAInstrsinon(AInstrsinon node) { //Pas sur de l'avoir
        //super.caseAInstrsinon(node);
    }

    @Override
    public void caseABloc(ABloc node) { //Marche 100%
        //super.caseABloc(node);
        apply(node.getListinstr());
    }

    @Override
    public void caseAAffect(AAffect node) { //Marche 100%
        //super.caseAAffect(node);
        node.getVariable().apply(this);
        SaVar op1 = (SaVar) this.returnValue;
        node.getExpr().apply(this);
        SaExp op2 = (SaExp) this.returnValue;
        this.returnValue = new SaInstAffect(op1, op2);
    }

    @Override
    public void caseASimpleVariable(ASimpleVariable node) {//Censé marcher
        //super.caseASimpleVariable(node);
        String nom = node.getNom().getText();
        this.returnValue = new SaVarSimple(nom);
    }

    @Override
    public void caseATabVariable(ATabVariable node) { //Pas sur
        //super.caseATabVariable(node);
        String nom = node.getNom().getText();
        node.getExpr().apply(this);
        SaExp exp = (SaExp) this.returnValue;
        this.returnValue = new SaVarIndicee(nom, exp);

    }

    @Override
    public void caseAOuExpr(AOuExpr node) { //Marche 100%
        //super.caseAOuExpr(node);
        node.getExpr().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        node.getExpr2().apply(this);
        SaExp op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpOr(op1, op2);
    }

    @Override
    public void caseAExpr2Expr(AExpr2Expr node) { //Marche 100%
        //super.caseAExpr2Expr(node);
        apply(node.getExpr2());
    }

    @Override
    public void caseAEtExpr2(AEtExpr2 node) { //Marche 100%
        //super.caseAEtExpr2(node);
        node.getExpr2().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        node.getExpr3().apply(this);
        SaExp op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAnd(op1, op2);

    }

    @Override
    public void caseAExpr3Expr2(AExpr3Expr2 node) { //Marche 100%
        //super.caseAExpr3Expr2(node);
        apply(node.getExpr3());
    }

    @Override
    public void caseAEgalExpr3(AEgalExpr3 node) { //Marche 100%
        //super.caseAEgalExpr3(node);
        node.getExpr3().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        node.getExpr4().apply(this);
        SaExp op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpEqual(op1, op2);

    }

    @Override
    public void caseAInfExpr3(AInfExpr3 node) { //Marche 100%
        //super.caseAInfExpr3(node);
        node.getExpr3().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        node.getExpr4().apply(this);
        SaExp op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpInf(op1, op2);
    }

    @Override
    public void caseAExpr4Expr3(AExpr4Expr3 node) { //Marche 100%
        //super.caseAExpr4Expr3(node);
        apply(node.getExpr4());
    }

    @Override
    public void caseAPlusExpr4(APlusExpr4 node) { //Marche 100%
        //super.caseAPlusExpr4(node);
        node.getExpr4().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        node.getExpr5().apply(this);
        SaExp op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAdd(op1, op2);
    }

    @Override
    public void caseAMoinsExpr4(AMoinsExpr4 node) { //Marche 100%
        //super.caseAMoinsExpr4(node);
        node.getExpr4().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        node.getExpr5().apply(this);
        SaExp op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpSub(op1, op2);
    }

    @Override
    public void caseAExpr5Expr4(AExpr5Expr4 node) { //Marche 100%
        //super.caseAExpr5Expr4(node);
        apply(node.getExpr5());
    }

    @Override
    public void caseAMultiExpr5(AMultiExpr5 node) { //Marche 100%
        //super.caseAMultiExpr5(node);
        node.getExpr5().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        node.getExpr6().apply(this);
        SaExp op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpMult(op1, op2);
    }

    @Override
    public void caseADivExpr5(ADivExpr5 node) { //Marche 100%
        //super.caseADivExpr5(node);
        node.getExpr5().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        node.getExpr6().apply(this);
        SaExp op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpDiv(op1, op2);
    }

    @Override
    public void caseAExpr6Expr5(AExpr6Expr5 node) { //Marche 100%
        //super.caseAExpr6Expr5(node);
        apply(node.getExpr6());
    }

    @Override
    public void caseANonExpr6(ANonExpr6 node) { //Marche 100%
        //super.caseANonExpr6(node);
        node.getExpr7().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        this.returnValue = new SaExpNot(op1);
    }

    @Override
    public void caseAPart7Expr6(APart7Expr6 node) {//pas sur
        //Sûr
        //super.caseAPart7Expr6(node);
        apply(node.getExpr7());
    }

    @Override
    public void caseAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node) {//pas sur
        //Quasi sûr à 100%
        //super.caseAExprentreparenthesesExpr7(node);
        apply(node.getExpr());
    }

    @Override
    public void caseANomvarExpr7(ANomvarExpr7 node) {//pas sur
        //Sur à 100 %
        //super.caseANomvarExpr7(node);
        apply(node.getNom());
    }

    @Override
    public void caseANombreExpr7(ANombreExpr7 node) {//pas sur
        //On a qu'un seul choix pour node.method donc sûr que ça marche
        //super.caseANombreExpr7(node);
        apply(node.getNombre());
    }

    @Override
    public void caseALireExpr7(ALireExpr7 node) { //Marche 100%
        //super.caseALireExpr7(node);
        this.returnValue = new SaExpLire();
    }

    @Override
    public void caseATabExpr7(ATabExpr7 node) { //Marche normalement
        //super.caseATabExpr7(node);
        node.getNombre().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        String nom = node.getNom().getText();
        this.returnValue = new SaVarIndicee(nom, op1);
    }

    @Override
    //TODO ? alors oui c'est à TODO
    public void caseAFonctionExpr7(AFonctionExpr7 node) { //pas sur
        //super.caseAFonctionExpr7(node);
        apply(node.getDeffonction());
    }

    @Override
    public void caseAAppelExpr7(AAppelExpr7 node) { //Marche 100%
        //super.caseAAppelExpr7(node);
        node.getFonctionappel().apply(this);
        SaAppel op1 = (SaAppel) this.returnValue;
        this.returnValue = new SaExpAppel(op1);
    }

    @Override
    public void caseAFonctionecrire(AFonctionecrire node) { //Marche 100%
        //super.caseAFonctionecrire(node);
        node.getEcrire().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        this.returnValue = new SaInstEcriture(op1);
    }

    @Override
    public void caseAFonctionappel(AFonctionappel node) { //Marche 100%
        //super.caseAFonctionappel(node);
        String nom = node.getNom().getText();
        node.getAppelexpr().apply(this);
        SaLExp op1 = (SaLExp) this.returnValue;
        this.returnValue = new SaAppel(nom, op1);
    }

    @Override
    public void caseAAppelexpr(AAppelexpr node) { //Marche 100%
        //super.caseAAppelexpr(node);
        node.getExpr().apply(this);
        SaAppel op1 = (SaAppel) this.returnValue;
        this.returnValue = new SaExpAppel(op1);
    }

    @Override
    public void caseAListeexpr(AListeexpr node) { //Marche 100%
        //super.caseAListeexpr(node);
        node.getVirgule().apply(this);
        SaExp op1 = (SaExp) this.returnValue;
        node.getExpr().apply(this);
        SaLExp op2 = (SaLExp) this.returnValue;
        this.returnValue = new SaLExp(op1, op2);
    }

    @Override
    public void caseAVideFonctionlire(AVideFonctionlire node) {//pas sur
        //a revoir
        //super.caseAVideFonctionlire(node);
        apply(node.getLire());
    }

    @Override
    public void caseASimpleFonctionlire(ASimpleFonctionlire node) {//pas sur
        //Mais ça me parait bon
        //super.caseASimpleFonctionlire(node);
        apply(node.getExpr());
    }
    */

}
