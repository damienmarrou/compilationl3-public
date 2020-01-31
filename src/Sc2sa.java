import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {

        private SaNode returnValue;

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
                super.caseStart(node);
                apply(node.getPProgramme());
        }

        @Override
        public void caseAProgramme(AProgramme node) { //Marche 100%
                super.caseAProgramme(node);
                SaLDec variable = (SaLDec) apply(node.getDecvar2());
                SaLDec fonctions = (SaLDec) apply(node.getDeffonction2());
                this.returnValue = new SaProg(variable,fonctions);
        }

        @Override
        public void caseAMultipleDecvar(AMultipleDecvar node) { //A REVOIR
                super.caseAMultipleDecvar(node);
                SaDec opt1 = (SaDec) apply(node.getVarsimple());
                SaLDec opt2 = (SaLDec) apply(node.getVarmultiple());
                this.returnValue = new SaLDec(opt1,opt2);
        }

        @Override
        public void caseAEntierVarsimple(AEntierVarsimple node) { //Marche 100%
                super.caseAEntierVarsimple(node);
                apply(node.getVarent());
        }


        @Override
        public void caseATabVarsimple(ATabVarsimple node) { //Marche 100%
                super.caseATabVarsimple(node);
                apply(node.getVartab());
        }


        @Override
        public void caseAVarent(AVarent node) { //Marche 100%
                super.caseAVarent(node);
                apply(node.getNom());
        }

        @Override
        public void caseAVartab(AVartab node) { //A REVOIR
                super.caseAVartab(node);
                apply(node.getNom());
        }

        @Override
        public void caseAVarmultiple(AVarmultiple node) {
                super.caseAVarmultiple(node);
                apply(node.getVarmultiple());
        }

        @Override
        public void caseARienVarmultiple(ARienVarmultiple node) {
                super.caseARienVarmultiple(node);

        }



        @Override
        public void caseAMultipleFctdecvar(AMultipleFctdecvar node) {
                super.caseAMultipleFctdecvar(node);
        }



        @Override
        public void caseAFctvarmultiple(AFctvarmultiple node) {
                super.caseAFctvarmultiple(node);
        }



        @Override
        public void caseARienFctvarmultiple(ARienFctvarmultiple node) {//pas sur mais normalement c'est bon
                super.caseARienFctvarmultiple(node);
        }

        @Override
        public void caseAFoncDeffonction2(AFoncDeffonction2 node) {
                super.caseAFoncDeffonction2(node);
        }

        @Override
        public void caseARienDeffonction2(ARienDeffonction2 node) {//normalement c'est bon
                super.caseARienDeffonction2(node);
        }

        @Override
        public void caseAArgsDeffonction(AArgsDeffonction node) {
                super.caseAArgsDeffonction(node);
        }

        @Override
        public void caseASansargDeffonction(ASansargDeffonction node) {
                super.caseASansargDeffonction(node);
        }

        @Override
        public void caseAListListinstr(AListListinstr node) {
                super.caseAListListinstr(node);

        }

        @Override
        public void caseARienListinstr(ARienListinstr node) {//pas sur
                super.caseARienListinstr(node);
        }

        @Override
        public void caseAAffectationInstr(AAffectationInstr node) {
                super.caseAAffectationInstr(node);
        }

        @Override
        public void caseABlocInstr(ABlocInstr node) {
                super.caseABlocInstr(node);
        }

        @Override
        public void caseATantqueInstr(ATantqueInstr node) {
                super.caseATantqueInstr(node);
        }

        @Override
        public void caseASiInstr(ASiInstr node) {
                super.caseASiInstr(node);
        }

        @Override
        public void caseARetourInstr(ARetourInstr node) {
                super.caseARetourInstr(node);
        }

        @Override
        public void caseAEcrireInstr(AEcrireInstr node) {
                super.caseAEcrireInstr(node);
        }

        @Override
        public void caseAInstrtantque(AInstrtantque node) {
                super.caseAInstrtantque(node);
        }

        @Override
        public void caseAInstrretour(AInstrretour node) {
                super.caseAInstrretour(node);
        }

        @Override
        public void caseASisinonInstrsi(ASisinonInstrsi node) {
                super.caseASisinonInstrsi(node);
        }

        @Override
        public void caseASiInstrsi(ASiInstrsi node) {
                super.caseASiInstrsi(node);
        }

        @Override
        public void caseAInstrsinon(AInstrsinon node) {
                super.caseAInstrsinon(node);
        }

        @Override
        public void caseABloc(ABloc node) {
                super.caseABloc(node);
        }

        @Override
        public void caseAAffect(AAffect node) {
                super.caseAAffect(node);
        }

        @Override
        public void caseASimpleVariable(ASimpleVariable node) {//vraiment pas sur
                super.caseASimpleVariable(node);
                apply(node.getNom());
        }

        @Override
        public void caseATabVariable(ATabVariable node) {
                super.caseATabVariable(node);

        }

        @Override
        public void caseAOuExpr(AOuExpr node) { //Marche
                super.caseAOuExpr(node);
                node.getExpr().apply(this);
                SaExp op1 = (SaExp) this.returnValue;
                node.getExpr2().apply(this);
                SaExp op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpOr(op1,op2);
        }

        @Override
        public void caseAExpr2Expr(AExpr2Expr node) { //Marche
                super.caseAExpr2Expr(node);
                apply(node.getExpr2());
        }

        @Override
        public void caseAEtExpr2(AEtExpr2 node) { //Marche
                super.caseAEtExpr2(node);
                node.getExpr2().apply(this);
                SaExp op1 = (SaExp) this.returnValue;
                node.getExpr3().apply(this);
                SaExp op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpAnd(op1,op2);

        }

        @Override
        public void caseAExpr3Expr2(AExpr3Expr2 node) { //Marche
                super.caseAExpr3Expr2(node);
                apply(node.getExpr3());
        }

        @Override
        public void caseAEgalExpr3(AEgalExpr3 node) { //Marche
                super.caseAEgalExpr3(node);
                node.getExpr3().apply(this);
                SaExp op1 = (SaExp) this.returnValue;
                node.getExpr4().apply(this);
                SaExp op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpEqual(op1,op2);

        }

        @Override
        public void caseAInfExpr3(AInfExpr3 node) { //Marche
                super.caseAInfExpr3(node);
                node.getExpr3().apply(this);
                SaExp op1 = (SaExp) this.returnValue;
                node.getExpr4().apply(this);
                SaExp op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpInf(op1,op2);
        }

        @Override
        public void caseAExpr4Expr3(AExpr4Expr3 node) { //Marche
                super.caseAExpr4Expr3(node);
                apply(node.getExpr4());
        }

        @Override
        public void caseAPlusExpr4(APlusExpr4 node) { //Marche
                super.caseAPlusExpr4(node);
                node.getExpr4().apply(this);
                SaExp op1 = (SaExp) this.returnValue;
                node.getExpr5().apply(this);
                SaExp op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpAdd(op1,op2);
        }

        @Override
        public void caseAMoinsExpr4(AMoinsExpr4 node) { //Marche
                super.caseAMoinsExpr4(node);
                node.getExpr4().apply(this);
                SaExp op1 = (SaExp) this.returnValue;
                node.getExpr5().apply(this);
                SaExp op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpSub(op1,op2);
        }

        @Override
        public void caseAExpr5Expr4(AExpr5Expr4 node) { //Marche
                super.caseAExpr5Expr4(node);
                apply(node.getExpr5());
        }

        @Override
        public void caseAMultiExpr5(AMultiExpr5 node) { //Marche
                super.caseAMultiExpr5(node);
                node.getExpr5().apply(this);
                SaExp op1 = (SaExp) this.returnValue;
                node.getExpr6().apply(this);
                SaExp op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpMult(op1,op2);
        }

        @Override
        public void caseADivExpr5(ADivExpr5 node) { //Marche
                super.caseADivExpr5(node);
                node.getExpr5().apply(this);
                SaExp op1 = (SaExp) this.returnValue;
                node.getExpr6().apply(this);
                SaExp op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpDiv(op1,op2);
        }

        @Override
        public void caseAExpr6Expr5(AExpr6Expr5 node) { //Marche
                super.caseAExpr6Expr5(node);
                apply(node.getExpr6());
        }

        @Override
        public void caseANonExpr6(ANonExpr6 node) { //Marche
                super.caseANonExpr6(node);
                apply(node.getNon());
        }

        /*@Override
        public void caseAPart2Expr7(APart2Expr6 node) {
                super.caseAPart2Expr7(node);
                apply(node.getExpr7());
        }*/

        @Override
        public void caseAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node) {//pas sur
                super.caseAExprentreparenthesesExpr7(node);
                this.returnValue = (SaNode) node.getExpr();
        }

        @Override
        public void caseANomvarExpr7(ANomvarExpr7 node) {//pas sur
                super.caseANomvarExpr7(node);
                apply(node.getNom());
        }

        @Override
        public void caseANombreExpr7(ANombreExpr7 node) {//pas sur
                super.caseANombreExpr7(node);
                apply(node.getNombre());
        }

        @Override
        public void caseALireExpr7(ALireExpr7 node) { //Marche 100%
                super.caseALireExpr7(node);
                this.returnValue = new SaExpLire();
        }

        @Override
        public void caseATabExpr7(ATabExpr7 node) {
                super.caseATabExpr7(node);
        }

        @Override
        //TODO ?
        public void caseAFonctionExpr7(AFonctionExpr7 node) {//pas sur
                super.caseAFonctionExpr7(node);
                apply(node.getDeffonction());
        }

        @Override
        public void caseAAppelExpr7(AAppelExpr7 node) { //Marche 100%
                super.caseAAppelExpr7(node);
                node.getFonctionappel().apply(this);
                SaAppel op1 = (SaAppel) this.returnValue;
                this.returnValue = new SaExpAppel(op1);
        }

        @Override
        public void caseAFonctionecrire(AFonctionecrire node) { //Marche 100%
                super.caseAFonctionecrire(node);
                node.getEcrire().apply(this);
                SaExp op1 = (SaExp) this.returnValue;
                this.returnValue = new SaInstEcriture(op1);
        }

        @Override
        public void caseAFonctionappel(AFonctionappel node) { //Marche 100%
                super.caseAFonctionappel(node);
                String nom =node.getNom().getText();
                node.getAppelexpr().apply(this);
                SaLExp op1 = (SaLExp) this.returnValue;
                this.returnValue = new SaAppel(nom,op1);
        }

        @Override
        public void caseAAppelexpr(AAppelexpr node) { //Marche 100%
                super.caseAAppelexpr(node);
                node.getExpr().apply(this);
                SaAppel op1 = (SaAppel) this.returnValue;
                this.returnValue = new SaExpAppel(op1);
        }

        @Override
        public void caseAListeexpr(AListeexpr node) { //Marche
                super.caseAListeexpr(node);
                node.getVirgule().apply(this);
                SaExp op1 = (SaExp) this.returnValue;
                node.getExpr().apply(this);
                SaLExp op2 = (SaLExp) this.returnValue;
                this.returnValue = new SaLExp(op1,op2);
        }

        @Override
        public void caseAVideFonctionlire(AVideFonctionlire node) {//pas sur
                super.caseAVideFonctionlire(node);
                apply(node.getLire());
        }

        @Override
        public void caseASimpleFonctionlire(ASimpleFonctionlire node) {//pas sur
                super.caseASimpleFonctionlire(node);
                apply(node.getExpr());
        }
}
