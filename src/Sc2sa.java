import sa.SaExp;
import sa.SaExpAdd;
import sa.SaExpSub;
import sa.SaNode;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {

        private SaNode returnValue;

        @Override
        public void inStart(Start node) {
                super.inStart(node);
        }//non

        @Override
        public void outStart(Start node) {
                super.outStart(node);

        }//non

        @Override
        public void defaultIn(Node node) {
                super.defaultIn(node);
        }//non

        @Override
        public void defaultOut(Node node) {
                super.defaultOut(node);
        }//non

        @Override
        public void caseStart(Start node) {
                super.caseStart(node);
        }

        @Override
        public void inAProgramme(AProgramme node) {
                super.inAProgramme(node);
        }//non

        @Override
        public void outAProgramme(AProgramme node) {
                super.outAProgramme(node);
        }//non

        @Override
        public void caseAProgramme(AProgramme node) {
                super.caseAProgramme(node);
        }

        @Override
        public void inAMultipleDecvar(AMultipleDecvar node) {
                super.inAMultipleDecvar(node);
        }//non

        @Override
        public void outAMultipleDecvar(AMultipleDecvar node) {
                super.outAMultipleDecvar(node);
        }//non

        @Override
        public void caseAMultipleDecvar(AMultipleDecvar node) {
                super.caseAMultipleDecvar(node);
        }

        @Override
        public void inAEntierVarsimple(AEntierVarsimple node) {
                super.inAEntierVarsimple(node);
        }//non

        @Override
        public void outAEntierVarsimple(AEntierVarsimple node) {
                super.outAEntierVarsimple(node);
        }//non

        @Override
        public void caseAEntierVarsimple(AEntierVarsimple node) {
                super.caseAEntierVarsimple(node);
        }

        @Override
        public void inATabVarsimple(ATabVarsimple node) {
                super.inATabVarsimple(node);
        }//non

        @Override
        public void outATabVarsimple(ATabVarsimple node) {
                super.outATabVarsimple(node);
        }//non

        @Override
        public void caseATabVarsimple(ATabVarsimple node) {
                super.caseATabVarsimple(node);
        }

        @Override
        public void inAVarent(AVarent node) {
                super.inAVarent(node);
        }//non

        @Override
        public void outAVarent(AVarent node) {
                super.outAVarent(node);
        }//non

        @Override
        public void caseAVarent(AVarent node) {
                super.caseAVarent(node);
        }

        @Override
        public void inAVartab(AVartab node) {
                super.inAVartab(node);
        }//non

        @Override
        public void outAVartab(AVartab node) {
                super.outAVartab(node);
        }//non

        @Override
        public void caseAVartab(AVartab node) {
                super.caseAVartab(node);
        }

        @Override
        public void inAVarmultiple(AVarmultiple node) {
                super.inAVarmultiple(node);
        }//non

        @Override
        public void outAVarmultiple(AVarmultiple node) {
                super.outAVarmultiple(node);
        }//non

        @Override
        public void caseAVarmultiple(AVarmultiple node) {
                super.caseAVarmultiple(node);
        }

        @Override
        public void inARienVarmultiple(ARienVarmultiple node) {
                super.inARienVarmultiple(node);
        }//non

        @Override
        public void outARienVarmultiple(ARienVarmultiple node) {
                super.outARienVarmultiple(node);
        }//non

        @Override
        public void caseARienVarmultiple(ARienVarmultiple node) {
                super.caseARienVarmultiple(node);
        }

        @Override
        public void inAMultipleFctdecvar(AMultipleFctdecvar node) {
                super.inAMultipleFctdecvar(node);
        }//non

        @Override
        public void outAMultipleFctdecvar(AMultipleFctdecvar node) {
                super.outAMultipleFctdecvar(node);
        }//non

        @Override
        public void caseAMultipleFctdecvar(AMultipleFctdecvar node) {
                super.caseAMultipleFctdecvar(node);
        }

        @Override
        public void inAFctvarmultiple(AFctvarmultiple node) {
                super.inAFctvarmultiple(node);
        }//non

        @Override
        public void outAFctvarmultiple(AFctvarmultiple node) {
                super.outAFctvarmultiple(node);
        }//non

        @Override
        public void caseAFctvarmultiple(AFctvarmultiple node) {
                super.caseAFctvarmultiple(node);
        }

        @Override
        public void inARienFctvarmultiple(ARienFctvarmultiple node) {
                super.inARienFctvarmultiple(node);
        }//non

        @Override
        public void outARienFctvarmultiple(ARienFctvarmultiple node) {
                super.outARienFctvarmultiple(node);
        }//non

        @Override
        public void caseARienFctvarmultiple(ARienFctvarmultiple node) {
                super.caseARienFctvarmultiple(node);
        }

        @Override
        public void inAFoncDeffonction2(AFoncDeffonction2 node) {
                super.inAFoncDeffonction2(node);
        }//non

        @Override
        public void outAFoncDeffonction2(AFoncDeffonction2 node) {
                super.outAFoncDeffonction2(node);
        }//non

        @Override
        public void caseAFoncDeffonction2(AFoncDeffonction2 node) {
                super.caseAFoncDeffonction2(node);
        }

        @Override
        public void inARienDeffonction2(ARienDeffonction2 node) {
                super.inARienDeffonction2(node);
        }//non

        @Override
        public void outARienDeffonction2(ARienDeffonction2 node) {
                super.outARienDeffonction2(node);
        }//non

        @Override
        public void caseARienDeffonction2(ARienDeffonction2 node) {
                super.caseARienDeffonction2(node);
        }

        @Override
        public void inAArgsDeffonction(AArgsDeffonction node) {
                super.inAArgsDeffonction(node);
        }//non

        @Override
        public void outAArgsDeffonction(AArgsDeffonction node) {
                super.outAArgsDeffonction(node);
        }//non

        @Override
        public void caseAArgsDeffonction(AArgsDeffonction node) {
                super.caseAArgsDeffonction(node);
        }

        @Override
        public void inASansargDeffonction(ASansargDeffonction node) {
                super.inASansargDeffonction(node);
        }//non

        @Override
        public void outASansargDeffonction(ASansargDeffonction node) {
                super.outASansargDeffonction(node);
        }//non

        @Override
        public void caseASansargDeffonction(ASansargDeffonction node) {
                super.caseASansargDeffonction(node);
        }

        @Override
        public void inARienDecvar2(ARienDecvar2 node) {
                super.inARienDecvar2(node);
        }//non

        @Override
        public void outARienDecvar2(ARienDecvar2 node) {
                super.outARienDecvar2(node);
        }//non

        @Override
        public void caseARienDecvar2(ARienDecvar2 node) {
                super.caseARienDecvar2(node);
        }

        @Override
        public void inAVarDecvar2(AVarDecvar2 node) {
                super.inAVarDecvar2(node);
        }//non

        @Override
        public void outAVarDecvar2(AVarDecvar2 node) {
                super.outAVarDecvar2(node);
        }//non

        @Override
        public void caseAVarDecvar2(AVarDecvar2 node) {
                super.caseAVarDecvar2(node);
        }

        @Override
        public void inAListListinstr(AListListinstr node) {
                super.inAListListinstr(node);
        }//non

        @Override
        public void outAListListinstr(AListListinstr node) {
                super.outAListListinstr(node);
        }//non

        @Override
        public void caseAListListinstr(AListListinstr node) {
                super.caseAListListinstr(node);
        }

        @Override
        public void inARienListinstr(ARienListinstr node) {
                super.inARienListinstr(node);
        }//non

        @Override
        public void outARienListinstr(ARienListinstr node) {
                super.outARienListinstr(node);
        }//non

        @Override
        public void caseARienListinstr(ARienListinstr node) {
                super.caseARienListinstr(node);
        }

        @Override
        public void inAAffectationInstr(AAffectationInstr node) {
                super.inAAffectationInstr(node);
        }//non

        @Override
        public void outAAffectationInstr(AAffectationInstr node) {
                super.outAAffectationInstr(node);
        }//non

        @Override
        public void caseAAffectationInstr(AAffectationInstr node) {
                super.caseAAffectationInstr(node);
        }

        @Override
        public void inABlocInstr(ABlocInstr node) {
                super.inABlocInstr(node);
        }//non

        @Override
        public void outABlocInstr(ABlocInstr node) {
                super.outABlocInstr(node);
        }//non

        @Override
        public void caseABlocInstr(ABlocInstr node) {
                super.caseABlocInstr(node);
        }

        @Override
        public void inATantqueInstr(ATantqueInstr node) {
                super.inATantqueInstr(node);
        }//non

        @Override
        public void outATantqueInstr(ATantqueInstr node) {
                super.outATantqueInstr(node);
        }//non

        @Override
        public void caseATantqueInstr(ATantqueInstr node) {
                super.caseATantqueInstr(node);
        }

        @Override
        public void inASiInstr(ASiInstr node) {
                super.inASiInstr(node);
        }//non

        @Override
        public void outASiInstr(ASiInstr node) {
                super.outASiInstr(node);
        }//non

        @Override
        public void caseASiInstr(ASiInstr node) {
                super.caseASiInstr(node);
        }

        @Override
        public void inARetourInstr(ARetourInstr node) {
                super.inARetourInstr(node);
        }//non

        @Override
        public void outARetourInstr(ARetourInstr node) {
                super.outARetourInstr(node);
        }//non

        @Override
        public void caseARetourInstr(ARetourInstr node) {
                super.caseARetourInstr(node);
        }

        @Override
        public void inAEcrireInstr(AEcrireInstr node) {
                super.inAEcrireInstr(node);
        }//non

        @Override
        public void outAEcrireInstr(AEcrireInstr node) {
                super.outAEcrireInstr(node);
        }//non

        @Override
        public void caseAEcrireInstr(AEcrireInstr node) {
                super.caseAEcrireInstr(node);
        }

        @Override
        public void inAInstrtantque(AInstrtantque node) {
                super.inAInstrtantque(node);
        }//non

        @Override
        public void outAInstrtantque(AInstrtantque node) {
                super.outAInstrtantque(node);
        }//non

        @Override
        public void caseAInstrtantque(AInstrtantque node) {
                super.caseAInstrtantque(node);
        }

        @Override
        public void inAInstrretour(AInstrretour node) {
                super.inAInstrretour(node);
        }//non

        @Override
        public void outAInstrretour(AInstrretour node) {
                super.outAInstrretour(node);
        }//non

        @Override
        public void caseAInstrretour(AInstrretour node) {
                super.caseAInstrretour(node);
        }

        @Override
        public void inASisinonInstrsi(ASisinonInstrsi node) {
                super.inASisinonInstrsi(node);
        }//non

        @Override
        public void outASisinonInstrsi(ASisinonInstrsi node) {
                super.outASisinonInstrsi(node);
        }//non

        @Override
        public void caseASisinonInstrsi(ASisinonInstrsi node) {
                super.caseASisinonInstrsi(node);
        }

        @Override
        public void inASiInstrsi(ASiInstrsi node) {
                super.inASiInstrsi(node);
        }//non

        @Override
        public void outASiInstrsi(ASiInstrsi node) {
                super.outASiInstrsi(node);
        }//non

        @Override
        public void caseASiInstrsi(ASiInstrsi node) {
                super.caseASiInstrsi(node);
        }

        @Override
        public void inAInstrsinon(AInstrsinon node) {
                super.inAInstrsinon(node);
        }//non

        @Override
        public void outAInstrsinon(AInstrsinon node) {
                super.outAInstrsinon(node);
        }//non

        @Override
        public void caseAInstrsinon(AInstrsinon node) {
                super.caseAInstrsinon(node);
        }

        @Override
        public void inABloc(ABloc node) {
                super.inABloc(node);
        }//non

        @Override
        public void outABloc(ABloc node) {
                super.outABloc(node);
        }//non

        @Override
        public void caseABloc(ABloc node) {
                super.caseABloc(node);
        }

        @Override
        public void inAAffect(AAffect node) {
                super.inAAffect(node);
        }//non

        @Override
        public void outAAffect(AAffect node) {
                super.outAAffect(node);
        }//non

        @Override
        public void caseAAffect(AAffect node) {
                super.caseAAffect(node);
        }

        @Override
        public void inASimpleVariable(ASimpleVariable node) {
                super.inASimpleVariable(node);
        }//non

        @Override
        public void outASimpleVariable(ASimpleVariable node) {
                super.outASimpleVariable(node);
        }//non

        @Override
        public void caseASimpleVariable(ASimpleVariable node) {
                super.caseASimpleVariable(node);
        }

        @Override
        public void inATabVariable(ATabVariable node) {
                super.inATabVariable(node);
        }//non

        @Override
        public void outATabVariable(ATabVariable node) {
                super.outATabVariable(node);
        }//non

        @Override
        public void caseATabVariable(ATabVariable node) {
                super.caseATabVariable(node);
        }

        @Override
        public void inAOuExpr(AOuExpr node) {
                super.inAOuExpr(node);
        }//non

        @Override
        public void outAOuExpr(AOuExpr node) {
                super.outAOuExpr(node);
        }//non

        @Override
        public void caseAOuExpr(AOuExpr node) {
                super.caseAOuExpr(node);
        }

        @Override
        public void inAExpr2Expr(AExpr2Expr node) {
                super.inAExpr2Expr(node);
        }//non

        @Override
        public void outAExpr2Expr(AExpr2Expr node) {
                super.outAExpr2Expr(node);
        }//non

        @Override
        public void caseAExpr2Expr(AExpr2Expr node) {
                super.caseAExpr2Expr(node);
        }

        @Override
        public void inAEtExpr2(AEtExpr2 node) {
                super.inAEtExpr2(node);
        }//non

        @Override
        public void outAEtExpr2(AEtExpr2 node) {
                super.outAEtExpr2(node);
        }//non

        @Override
        public void caseAEtExpr2(AEtExpr2 node) {
                super.caseAEtExpr2(node);
        }

        @Override
        public void inAExpr3Expr2(AExpr3Expr2 node) {
                super.inAExpr3Expr2(node);
        }//non

        @Override
        public void outAExpr3Expr2(AExpr3Expr2 node) {
                super.outAExpr3Expr2(node);
        }//non

        @Override
        public void caseAExpr3Expr2(AExpr3Expr2 node) {
                super.caseAExpr3Expr2(node);
        }

        @Override
        public void inAEgalExpr3(AEgalExpr3 node) {
                super.inAEgalExpr3(node);
        }//non

        @Override
        public void outAEgalExpr3(AEgalExpr3 node) {
                super.outAEgalExpr3(node);
        }//non

        @Override
        public void caseAEgalExpr3(AEgalExpr3 node) {
                super.caseAEgalExpr3(node);
        }

        @Override
        public void inAInfExpr3(AInfExpr3 node) {
                super.inAInfExpr3(node);
        }//non

        @Override
        public void outAInfExpr3(AInfExpr3 node) {
                super.outAInfExpr3(node);
        }//non

        @Override
        public void caseAInfExpr3(AInfExpr3 node) {
                super.caseAInfExpr3(node);
        }

        @Override
        public void inAExpr4Expr3(AExpr4Expr3 node) {
                super.inAExpr4Expr3(node);
        }//non

        @Override
        public void outAExpr4Expr3(AExpr4Expr3 node) {
                super.outAExpr4Expr3(node);
        }//non

        @Override
        public void caseAExpr4Expr3(AExpr4Expr3 node) {
                super.caseAExpr4Expr3(node);
        }

        @Override
        public void inAPlusExpr4(APlusExpr4 node) {
                super.inAPlusExpr4(node);
        }//non

        @Override
        public void outAPlusExpr4(APlusExpr4 node) {
                super.outAPlusExpr4(node);
        }//non

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
        public void inAMoinsExpr4(AMoinsExpr4 node) {
                super.inAMoinsExpr4(node);
        }//non

        @Override
        public void outAMoinsExpr4(AMoinsExpr4 node) {
                super.outAMoinsExpr4(node);
        }//non

        @Override
        public void caseAMoinsExpr4(AMoinsExpr4 node) {
                super.caseAMoinsExpr4(node);
        }

        @Override
        public void inAExpr5Expr4(AExpr5Expr4 node) {
                super.inAExpr5Expr4(node);
        }//non

        @Override
        public void outAExpr5Expr4(AExpr5Expr4 node) {
                super.outAExpr5Expr4(node);
        }//non

        @Override
        public void caseAExpr5Expr4(AExpr5Expr4 node) {
                super.caseAExpr5Expr4(node);
        }

        @Override
        public void inAMultiExpr5(AMultiExpr5 node) {
                super.inAMultiExpr5(node);
        }//non

        @Override
        public void outAMultiExpr5(AMultiExpr5 node) {
                super.outAMultiExpr5(node);
        }//non

        @Override
        public void caseAMultiExpr5(AMultiExpr5 node) {
                super.caseAMultiExpr5(node);
        }

        @Override
        public void inADivExpr5(ADivExpr5 node) {
                super.inADivExpr5(node);
        }//non

        @Override
        public void outADivExpr5(ADivExpr5 node) {
                super.outADivExpr5(node);
        }//non

        @Override
        public void caseADivExpr5(ADivExpr5 node) {
                super.caseADivExpr5(node);
        }

        @Override
        public void inAExpr6Expr5(AExpr6Expr5 node) {
                super.inAExpr6Expr5(node);
        }//non

        @Override
        public void outAExpr6Expr5(AExpr6Expr5 node) {
                super.outAExpr6Expr5(node);
        }//non

        @Override
        public void caseAExpr6Expr5(AExpr6Expr5 node) {
                super.caseAExpr6Expr5(node);
        }

        @Override
        public void inANonExpr6(ANonExpr6 node) {
                super.inANonExpr6(node);
        }//non

        @Override
        public void outANonExpr6(ANonExpr6 node) {
                super.outANonExpr6(node);
        }//non

        @Override
        public void caseANonExpr6(ANonExpr6 node) {
                super.caseANonExpr6(node);
        }

        @Override
        public void inAPart2Expr6(APart2Expr6 node) {
                super.inAPart2Expr6(node);
        }//non

        @Override
        public void outAPart2Expr6(APart2Expr6 node) {
                super.outAPart2Expr6(node);
        }//non

        @Override
        public void caseAPart2Expr6(APart2Expr6 node) {
                super.caseAPart2Expr6(node);
        }

        @Override
        public void inAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node) {//non
                super.inAExprentreparenthesesExpr7(node);
        }

        @Override
        public void outAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node) {//non
                super.outAExprentreparenthesesExpr7(node);
        }

        @Override
        public void caseAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node) {
                super.caseAExprentreparenthesesExpr7(node);
        }

        @Override
        public void inANomvarExpr7(ANomvarExpr7 node) {
                super.inANomvarExpr7(node);
        }//non

        @Override
        public void outANomvarExpr7(ANomvarExpr7 node) {
                super.outANomvarExpr7(node);
        }//non

        @Override
        public void caseANomvarExpr7(ANomvarExpr7 node) {
                super.caseANomvarExpr7(node);
        }

        @Override
        public void inANombreExpr7(ANombreExpr7 node) {
                super.inANombreExpr7(node);
        }//non

        @Override
        public void outANombreExpr7(ANombreExpr7 node) {
                super.outANombreExpr7(node);
        }//non

        @Override
        public void caseANombreExpr7(ANombreExpr7 node) {
                super.caseANombreExpr7(node);
        }

        @Override
        public void inALireExpr7(ALireExpr7 node) {
                super.inALireExpr7(node);
        }//non

        @Override
        public void outALireExpr7(ALireExpr7 node) {
                super.outALireExpr7(node);
        }//non

        @Override
        public void caseALireExpr7(ALireExpr7 node) {
                super.caseALireExpr7(node);
        }

        @Override
        public void inATabExpr7(ATabExpr7 node) {
                super.inATabExpr7(node);
        }//non

        @Override
        public void outATabExpr7(ATabExpr7 node) {
                super.outATabExpr7(node);
        }//non

        @Override
        public void caseATabExpr7(ATabExpr7 node) {
                super.caseATabExpr7(node);
        }

        @Override
        public void inAFonctionExpr7(AFonctionExpr7 node) {
                super.inAFonctionExpr7(node);
        }//non

        @Override
        public void outAFonctionExpr7(AFonctionExpr7 node) {
                super.outAFonctionExpr7(node);
        }//non

        @Override
        public void caseAFonctionExpr7(AFonctionExpr7 node) {
                super.caseAFonctionExpr7(node);
        }

        @Override
        public void inAAppelExpr7(AAppelExpr7 node) {
                super.inAAppelExpr7(node);
        }//non

        @Override
        public void outAAppelExpr7(AAppelExpr7 node) {
                super.outAAppelExpr7(node);
        }//non

        @Override
        public void caseAAppelExpr7(AAppelExpr7 node) {
                super.caseAAppelExpr7(node);
        }

        @Override
        public void inAFonctionecrire(AFonctionecrire node) {
                super.inAFonctionecrire(node);
        }//non

        @Override
        public void outAFonctionecrire(AFonctionecrire node) {
                super.outAFonctionecrire(node);
        }//non

        @Override
        public void caseAFonctionecrire(AFonctionecrire node) {
                super.caseAFonctionecrire(node);
        }

        @Override
        public void inAFonctionappel(AFonctionappel node) {
                super.inAFonctionappel(node);
        }//non

        @Override
        public void outAFonctionappel(AFonctionappel node) {
                super.outAFonctionappel(node);
        }//non

        @Override
        public void caseAFonctionappel(AFonctionappel node) {
                super.caseAFonctionappel(node);
        }

        @Override
        public void inAAppelexpr(AAppelexpr node) {
                super.inAAppelexpr(node);
        }//non

        @Override
        public void outAAppelexpr(AAppelexpr node) {
                super.outAAppelexpr(node);
        }//non

        @Override
        public void caseAAppelexpr(AAppelexpr node) {
                super.caseAAppelexpr(node);
        }

        @Override
        public void inAListeexpr(AListeexpr node) {
                super.inAListeexpr(node);
        }//non

        @Override
        public void outAListeexpr(AListeexpr node) {
                super.outAListeexpr(node);
        }//non

        @Override
        public void caseAListeexpr(AListeexpr node) {
                super.caseAListeexpr(node);
        }

        @Override
        public void inAFonctionlire(AFonctionlire node) {//non
                super.inAFonctionlire(node);
        }

        @Override
        public void outAFonctionlire(AFonctionlire node) {//non
                super.outAFonctionlire(node);
        }

        @Override
        public void caseAFonctionlire(AFonctionlire node) {
                super.caseAFonctionlire(node);
        }
}
