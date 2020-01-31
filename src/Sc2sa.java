import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {

        private SaNode returnValue;

        @Override
        public void defaultIn(Node node) {
                super.defaultIn(node);
        }

        @Override
        public void defaultOut(Node node) {
                super.defaultOut(node);
        }

        SaNode apply(Switchable sw) {
                sw.apply(this);
                return returnValue;
        }

        @Override
        public void caseStart(Start node) {
                super.caseStart(node);
                apply(node.getPProgramme());
        }

        @Override
        public void caseAProgramme(AProgramme node) {
                super.caseAProgramme(node);
                SaDec variable = (SaDec) node.getDecvar2();
                SaDec fonctions = (SaDec) node.getDeffonction2();
                //this.returnValue = new SaProg(variable,fonctions);
        }

        @Override
        public void caseAMultipleDecvar(AMultipleDecvar node) {
                super.caseAMultipleDecvar(node);
        }

        @Override
        public void caseAEntierVarsimple(AEntierVarsimple node) {
                super.caseAEntierVarsimple(node);
        }

        @Override
        public void caseATabVarsimple(ATabVarsimple node) {
                super.caseATabVarsimple(node);
        }

        @Override
        public void caseAVarent(AVarent node) {
                super.caseAVarent(node);
        }

        @Override
        public void caseAVartab(AVartab node) {
                super.caseAVartab(node);
        }

        @Override
        public void caseAVarmultiple(AVarmultiple node) {
                super.caseAVarmultiple(node);
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
        public void caseARienFctvarmultiple(ARienFctvarmultiple node) {
                super.caseARienFctvarmultiple(node);
        }

        @Override
        public void caseAFoncDeffonction2(AFoncDeffonction2 node) {
                super.caseAFoncDeffonction2(node);
        }

        @Override
        public void caseARienDeffonction2(ARienDeffonction2 node) {
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
        public void caseARienListinstr(ARienListinstr node) {
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
        public void caseASimpleVariable(ASimpleVariable node) {
                super.caseASimpleVariable(node);
        }

        @Override
        public void caseATabVariable(ATabVariable node) {
                super.caseATabVariable(node);
        }

        @Override
        public void caseAOuExpr(AOuExpr node) {
                super.caseAOuExpr(node);
        }

        @Override
        public void caseAExpr2Expr(AExpr2Expr node) {
                super.caseAExpr2Expr(node);
        }

        @Override
        public void caseAEtExpr2(AEtExpr2 node) {
                super.caseAEtExpr2(node);
        }

        @Override
        public void caseAExpr3Expr2(AExpr3Expr2 node) {
                super.caseAExpr3Expr2(node);
        }

        @Override
        public void caseAEgalExpr3(AEgalExpr3 node) {
                super.caseAEgalExpr3(node);
                SaExp op1 = null;
                SaExp op2 = null;
                node.getExpr3().apply(this);
                op1 = (SaExp) this.returnValue;
                node.getExpr4().apply(this);
                op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpEqual(op1,op2);

        }

        @Override
        public void caseAInfExpr3(AInfExpr3 node) {
                super.caseAInfExpr3(node);
                SaExp op1 = null;
                SaExp op2 = null;
                node.getExpr3().apply(this);
                op1 = (SaExp) this.returnValue;
                node.getExpr4().apply(this);
                op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpInf(op1,op2);
        }

        @Override
        public void caseAExpr4Expr3(AExpr4Expr3 node) {
                super.caseAExpr4Expr3(node);
        }

        @Override
        public void caseAPlusExpr4(APlusExpr4 node) {
                super.caseAPlusExpr4(node);
                SaExp op1 = null;
                SaExp op2 = null;
                node.getExpr4().apply(this);
                op1 = (SaExp) this.returnValue;
                node.getExpr5().apply(this);
                op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpAdd(op1,op2);
        }

        @Override
        public void caseAMoinsExpr4(AMoinsExpr4 node) {
                super.caseAMoinsExpr4(node);
                SaExp op1 = null;
                SaExp op2 = null;
                node.getExpr4().apply(this);
                op1 = (SaExp) this.returnValue;
                node.getExpr5().apply(this);
                op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpSub(op1,op2);
        }

        @Override
        public void caseAExpr5Expr4(AExpr5Expr4 node) {
                super.caseAExpr5Expr4(node);
        }

        @Override
        public void caseAMultiExpr5(AMultiExpr5 node) {
                super.caseAMultiExpr5(node);
                SaExp op1 = null;
                SaExp op2 = null;
                node.getExpr5().apply(this);
                op1 = (SaExp) this.returnValue;
                node.getExpr6().apply(this);
                op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpMult(op1,op2);
        }

        @Override
        public void caseADivExpr5(ADivExpr5 node) {
                super.caseADivExpr5(node);
                SaExp op1 = null;
                SaExp op2 = null;
                node.getExpr5().apply(this);
                op1 = (SaExp) this.returnValue;
                node.getExpr6().apply(this);
                op2 = (SaExp) this.returnValue;
                this.returnValue = new SaExpDiv(op1,op2);
        }

        @Override
        public void caseAExpr6Expr5(AExpr6Expr5 node) {
                super.caseAExpr6Expr5(node);
        }

        @Override
        public void caseANonExpr6(ANonExpr6 node) {
                super.caseANonExpr6(node);
        }

        @Override
        public void caseAPart2Expr6(APart2Expr6 node) {
                super.caseAPart2Expr6(node);
        }

        @Override
        public void caseAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node) {
                super.caseAExprentreparenthesesExpr7(node);
        }

        @Override
        public void caseANomvarExpr7(ANomvarExpr7 node) {
                super.caseANomvarExpr7(node);
        }

        @Override
        public void caseANombreExpr7(ANombreExpr7 node) {
                super.caseANombreExpr7(node);
        }

        @Override
        public void caseALireExpr7(ALireExpr7 node) {
                super.caseALireExpr7(node);
        }

        @Override
        public void caseATabExpr7(ATabExpr7 node) {
                super.caseATabExpr7(node);
        }

        @Override
        public void caseAFonctionExpr7(AFonctionExpr7 node) {
                super.caseAFonctionExpr7(node);
        }

        @Override
        public void caseAAppelExpr7(AAppelExpr7 node) {
                super.caseAAppelExpr7(node);
        }

        @Override
        public void caseAFonctionecrire(AFonctionecrire node) {
                super.caseAFonctionecrire(node);
        }

        @Override
        public void caseAFonctionappel(AFonctionappel node) {
                super.caseAFonctionappel(node);
        }

        @Override
        public void caseAAppelexpr(AAppelexpr node) {
                super.caseAAppelexpr(node);
        }

        @Override
        public void caseAListeexpr(AListeexpr node) {
                super.caseAListeexpr(node);
        }


        @Override
        public void caseAFonctionlire(AFonctionlire node) {
                super.caseAFonctionlire(node);
        }
}
