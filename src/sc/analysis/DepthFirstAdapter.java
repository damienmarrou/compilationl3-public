/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import sc.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProgramme().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAProgramme(AProgramme node)
    {
        defaultIn(node);
    }

    public void outAProgramme(AProgramme node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAProgramme(AProgramme node)
    {
        inAProgramme(node);
        if(node.getDecvar2() != null)
        {
            node.getDecvar2().apply(this);
        }
        if(node.getDeffonction2() != null)
        {
            node.getDeffonction2().apply(this);
        }
        outAProgramme(node);
    }

    public void inADecvar(ADecvar node)
    {
        defaultIn(node);
    }

    public void outADecvar(ADecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecvar(ADecvar node)
    {
        inADecvar(node);
        if(node.getVarsimple() != null)
        {
            node.getVarsimple().apply(this);
        }
        if(node.getVarmultiple() != null)
        {
            node.getVarmultiple().apply(this);
        }
        if(node.getPointvirgule() != null)
        {
            node.getPointvirgule().apply(this);
        }
        outADecvar(node);
    }

    public void inAEntierVarsimple(AEntierVarsimple node)
    {
        defaultIn(node);
    }

    public void outAEntierVarsimple(AEntierVarsimple node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEntierVarsimple(AEntierVarsimple node)
    {
        inAEntierVarsimple(node);
        if(node.getVarent() != null)
        {
            node.getVarent().apply(this);
        }
        outAEntierVarsimple(node);
    }

    public void inATabVarsimple(ATabVarsimple node)
    {
        defaultIn(node);
    }

    public void outATabVarsimple(ATabVarsimple node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATabVarsimple(ATabVarsimple node)
    {
        inATabVarsimple(node);
        if(node.getVartab() != null)
        {
            node.getVartab().apply(this);
        }
        outATabVarsimple(node);
    }

    public void inAVarent(AVarent node)
    {
        defaultIn(node);
    }

    public void outAVarent(AVarent node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarent(AVarent node)
    {
        inAVarent(node);
        if(node.getTypeentier() != null)
        {
            node.getTypeentier().apply(this);
        }
        if(node.getNom() != null)
        {
            node.getNom().apply(this);
        }
        outAVarent(node);
    }

    public void inAVartab(AVartab node)
    {
        defaultIn(node);
    }

    public void outAVartab(AVartab node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVartab(AVartab node)
    {
        inAVartab(node);
        if(node.getTypeentier() != null)
        {
            node.getTypeentier().apply(this);
        }
        if(node.getNom() != null)
        {
            node.getNom().apply(this);
        }
        if(node.getCrocheto() != null)
        {
            node.getCrocheto().apply(this);
        }
        if(node.getNombre() != null)
        {
            node.getNombre().apply(this);
        }
        if(node.getCrochetf() != null)
        {
            node.getCrochetf().apply(this);
        }
        outAVartab(node);
    }

    public void inAVarmultiple(AVarmultiple node)
    {
        defaultIn(node);
    }

    public void outAVarmultiple(AVarmultiple node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarmultiple(AVarmultiple node)
    {
        inAVarmultiple(node);
        if(node.getVirgule() != null)
        {
            node.getVirgule().apply(this);
        }
        if(node.getVarsimple() != null)
        {
            node.getVarsimple().apply(this);
        }
        if(node.getVarmultiple() != null)
        {
            node.getVarmultiple().apply(this);
        }
        outAVarmultiple(node);
    }

    public void inARienVarmultiple(ARienVarmultiple node)
    {
        defaultIn(node);
    }

    public void outARienVarmultiple(ARienVarmultiple node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARienVarmultiple(ARienVarmultiple node)
    {
        inARienVarmultiple(node);
        outARienVarmultiple(node);
    }

    public void inAMultipleFctdecvar(AMultipleFctdecvar node)
    {
        defaultIn(node);
    }

    public void outAMultipleFctdecvar(AMultipleFctdecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultipleFctdecvar(AMultipleFctdecvar node)
    {
        inAMultipleFctdecvar(node);
        if(node.getVarsimple() != null)
        {
            node.getVarsimple().apply(this);
        }
        if(node.getVarmultiple() != null)
        {
            node.getVarmultiple().apply(this);
        }
        outAMultipleFctdecvar(node);
    }

    public void inAFctvarmultiple(AFctvarmultiple node)
    {
        defaultIn(node);
    }

    public void outAFctvarmultiple(AFctvarmultiple node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFctvarmultiple(AFctvarmultiple node)
    {
        inAFctvarmultiple(node);
        if(node.getVirgule() != null)
        {
            node.getVirgule().apply(this);
        }
        if(node.getVarsimple() != null)
        {
            node.getVarsimple().apply(this);
        }
        if(node.getFctvarmultiple() != null)
        {
            node.getFctvarmultiple().apply(this);
        }
        outAFctvarmultiple(node);
    }

    public void inARienFctvarmultiple(ARienFctvarmultiple node)
    {
        defaultIn(node);
    }

    public void outARienFctvarmultiple(ARienFctvarmultiple node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARienFctvarmultiple(ARienFctvarmultiple node)
    {
        inARienFctvarmultiple(node);
        outARienFctvarmultiple(node);
    }

    public void inAFoncDeffonction2(AFoncDeffonction2 node)
    {
        defaultIn(node);
    }

    public void outAFoncDeffonction2(AFoncDeffonction2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFoncDeffonction2(AFoncDeffonction2 node)
    {
        inAFoncDeffonction2(node);
        if(node.getDeffonction() != null)
        {
            node.getDeffonction().apply(this);
        }
        if(node.getDeffonction2() != null)
        {
            node.getDeffonction2().apply(this);
        }
        outAFoncDeffonction2(node);
    }

    public void inARienDeffonction2(ARienDeffonction2 node)
    {
        defaultIn(node);
    }

    public void outARienDeffonction2(ARienDeffonction2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARienDeffonction2(ARienDeffonction2 node)
    {
        inARienDeffonction2(node);
        outARienDeffonction2(node);
    }

    public void inAArgsDeffonction(AArgsDeffonction node)
    {
        defaultIn(node);
    }

    public void outAArgsDeffonction(AArgsDeffonction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArgsDeffonction(AArgsDeffonction node)
    {
        inAArgsDeffonction(node);
        if(node.getNom() != null)
        {
            node.getNom().apply(this);
        }
        if(node.getParentheseo() != null)
        {
            node.getParentheseo().apply(this);
        }
        if(node.getFctdecvar() != null)
        {
            node.getFctdecvar().apply(this);
        }
        if(node.getParenthesef() != null)
        {
            node.getParenthesef().apply(this);
        }
        if(node.getDecvar2() != null)
        {
            node.getDecvar2().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outAArgsDeffonction(node);
    }

    public void inASansargDeffonction(ASansargDeffonction node)
    {
        defaultIn(node);
    }

    public void outASansargDeffonction(ASansargDeffonction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASansargDeffonction(ASansargDeffonction node)
    {
        inASansargDeffonction(node);
        if(node.getNom() != null)
        {
            node.getNom().apply(this);
        }
        if(node.getParentheseo() != null)
        {
            node.getParentheseo().apply(this);
        }
        if(node.getParenthesef() != null)
        {
            node.getParenthesef().apply(this);
        }
        if(node.getDecvar2() != null)
        {
            node.getDecvar2().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outASansargDeffonction(node);
    }

    public void inARienDecvar2(ARienDecvar2 node)
    {
        defaultIn(node);
    }

    public void outARienDecvar2(ARienDecvar2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARienDecvar2(ARienDecvar2 node)
    {
        inARienDecvar2(node);
        outARienDecvar2(node);
    }

    public void inAVarDecvar2(AVarDecvar2 node)
    {
        defaultIn(node);
    }

    public void outAVarDecvar2(AVarDecvar2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarDecvar2(AVarDecvar2 node)
    {
        inAVarDecvar2(node);
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
        }
        outAVarDecvar2(node);
    }

    public void inAListListinstr(AListListinstr node)
    {
        defaultIn(node);
    }

    public void outAListListinstr(AListListinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListListinstr(AListListinstr node)
    {
        inAListListinstr(node);
        if(node.getInstr() != null)
        {
            node.getInstr().apply(this);
        }
        if(node.getListinstr() != null)
        {
            node.getListinstr().apply(this);
        }
        outAListListinstr(node);
    }

    public void inARienListinstr(ARienListinstr node)
    {
        defaultIn(node);
    }

    public void outARienListinstr(ARienListinstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARienListinstr(ARienListinstr node)
    {
        inARienListinstr(node);
        outARienListinstr(node);
    }

    public void inAAffectationInstr(AAffectationInstr node)
    {
        defaultIn(node);
    }

    public void outAAffectationInstr(AAffectationInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAffectationInstr(AAffectationInstr node)
    {
        inAAffectationInstr(node);
        if(node.getAffect() != null)
        {
            node.getAffect().apply(this);
        }
        outAAffectationInstr(node);
    }

    public void inABlocInstr(ABlocInstr node)
    {
        defaultIn(node);
    }

    public void outABlocInstr(ABlocInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABlocInstr(ABlocInstr node)
    {
        inABlocInstr(node);
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outABlocInstr(node);
    }

    public void inATantqueInstr(ATantqueInstr node)
    {
        defaultIn(node);
    }

    public void outATantqueInstr(ATantqueInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATantqueInstr(ATantqueInstr node)
    {
        inATantqueInstr(node);
        if(node.getInstrtantque() != null)
        {
            node.getInstrtantque().apply(this);
        }
        outATantqueInstr(node);
    }

    public void inASiInstr(ASiInstr node)
    {
        defaultIn(node);
    }

    public void outASiInstr(ASiInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASiInstr(ASiInstr node)
    {
        inASiInstr(node);
        if(node.getInstrsi() != null)
        {
            node.getInstrsi().apply(this);
        }
        outASiInstr(node);
    }

    public void inARetourInstr(ARetourInstr node)
    {
        defaultIn(node);
    }

    public void outARetourInstr(ARetourInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARetourInstr(ARetourInstr node)
    {
        inARetourInstr(node);
        if(node.getInstrretour() != null)
        {
            node.getInstrretour().apply(this);
        }
        outARetourInstr(node);
    }

    public void inAEcrireInstr(AEcrireInstr node)
    {
        defaultIn(node);
    }

    public void outAEcrireInstr(AEcrireInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEcrireInstr(AEcrireInstr node)
    {
        inAEcrireInstr(node);
        if(node.getFonctionecrire() != null)
        {
            node.getFonctionecrire().apply(this);
        }
        outAEcrireInstr(node);
    }

    public void inALireInstr(ALireInstr node)
    {
        defaultIn(node);
    }

    public void outALireInstr(ALireInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALireInstr(ALireInstr node)
    {
        inALireInstr(node);
        if(node.getFonctionlire() != null)
        {
            node.getFonctionlire().apply(this);
        }
        if(node.getPointvirgule() != null)
        {
            node.getPointvirgule().apply(this);
        }
        outALireInstr(node);
    }

    public void inAAppelInstr(AAppelInstr node)
    {
        defaultIn(node);
    }

    public void outAAppelInstr(AAppelInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAppelInstr(AAppelInstr node)
    {
        inAAppelInstr(node);
        if(node.getFonctionappel() != null)
        {
            node.getFonctionappel().apply(this);
        }
        if (node.getPointvirgule() != null) {
            node.getPointvirgule().apply(this);
        }
        outAAppelInstr(node);
    }

    public void inAInstrtantque(AInstrtantque node)
    {
        defaultIn(node);
    }

    public void outAInstrtantque(AInstrtantque node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrtantque(AInstrtantque node)
    {
        inAInstrtantque(node);
        if(node.getTantque() != null)
        {
            node.getTantque().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getFaire() != null)
        {
            node.getFaire().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outAInstrtantque(node);
    }

    public void inAInstrretour(AInstrretour node)
    {
        defaultIn(node);
    }

    public void outAInstrretour(AInstrretour node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrretour(AInstrretour node)
    {
        inAInstrretour(node);
        if(node.getRetour() != null)
        {
            node.getRetour().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getPointvirgule() != null)
        {
            node.getPointvirgule().apply(this);
        }
        outAInstrretour(node);
    }

    public void inASisinonInstrsi(ASisinonInstrsi node)
    {
        defaultIn(node);
    }

    public void outASisinonInstrsi(ASisinonInstrsi node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASisinonInstrsi(ASisinonInstrsi node)
    {
        inASisinonInstrsi(node);
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        if(node.getInstrsinon() != null)
        {
            node.getInstrsinon().apply(this);
        }
        outASisinonInstrsi(node);
    }

    public void inASiInstrsi(ASiInstrsi node)
    {
        defaultIn(node);
    }

    public void outASiInstrsi(ASiInstrsi node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASiInstrsi(ASiInstrsi node)
    {
        inASiInstrsi(node);
        if(node.getSi() != null)
        {
            node.getSi().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getAlors() != null)
        {
            node.getAlors().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outASiInstrsi(node);
    }

    public void inAInstrsinon(AInstrsinon node)
    {
        defaultIn(node);
    }

    public void outAInstrsinon(AInstrsinon node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrsinon(AInstrsinon node)
    {
        inAInstrsinon(node);
        if(node.getSinon() != null)
        {
            node.getSinon().apply(this);
        }
        if(node.getBloc() != null)
        {
            node.getBloc().apply(this);
        }
        outAInstrsinon(node);
    }

    public void inABloc(ABloc node)
    {
        defaultIn(node);
    }

    public void outABloc(ABloc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABloc(ABloc node)
    {
        inABloc(node);
        if(node.getAccoladeo() != null)
        {
            node.getAccoladeo().apply(this);
        }
        if(node.getListinstr() != null)
        {
            node.getListinstr().apply(this);
        }
        if(node.getAccoladef() != null)
        {
            node.getAccoladef().apply(this);
        }
        outABloc(node);
    }

    public void inAOuExpr(AOuExpr node)
    {
        defaultIn(node);
    }

    public void outAOuExpr(AOuExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOuExpr(AOuExpr node)
    {
        inAOuExpr(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getOu() != null)
        {
            node.getOu().apply(this);
        }
        if(node.getExpr2() != null)
        {
            node.getExpr2().apply(this);
        }
        outAOuExpr(node);
    }

    public void inAExpr2Expr(AExpr2Expr node)
    {
        defaultIn(node);
    }

    public void outAExpr2Expr(AExpr2Expr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExpr2Expr(AExpr2Expr node)
    {
        inAExpr2Expr(node);
        if(node.getExpr2() != null)
        {
            node.getExpr2().apply(this);
        }
        outAExpr2Expr(node);
    }

    public void inAEtExpr2(AEtExpr2 node)
    {
        defaultIn(node);
    }

    public void outAEtExpr2(AEtExpr2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEtExpr2(AEtExpr2 node)
    {
        inAEtExpr2(node);
        if(node.getExpr2() != null)
        {
            node.getExpr2().apply(this);
        }
        if(node.getEt() != null)
        {
            node.getEt().apply(this);
        }
        if(node.getExpr3() != null)
        {
            node.getExpr3().apply(this);
        }
        outAEtExpr2(node);
    }

    public void inAExpr3Expr2(AExpr3Expr2 node)
    {
        defaultIn(node);
    }

    public void outAExpr3Expr2(AExpr3Expr2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExpr3Expr2(AExpr3Expr2 node)
    {
        inAExpr3Expr2(node);
        if(node.getExpr3() != null)
        {
            node.getExpr3().apply(this);
        }
        outAExpr3Expr2(node);
    }

    public void inAEgalExpr3(AEgalExpr3 node)
    {
        defaultIn(node);
    }

    public void outAEgalExpr3(AEgalExpr3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEgalExpr3(AEgalExpr3 node)
    {
        inAEgalExpr3(node);
        if(node.getExpr3() != null)
        {
            node.getExpr3().apply(this);
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getExpr4() != null)
        {
            node.getExpr4().apply(this);
        }
        outAEgalExpr3(node);
    }

    public void inAInfExpr3(AInfExpr3 node)
    {
        defaultIn(node);
    }

    public void outAInfExpr3(AInfExpr3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInfExpr3(AInfExpr3 node)
    {
        inAInfExpr3(node);
        if(node.getExpr3() != null)
        {
            node.getExpr3().apply(this);
        }
        if(node.getInf() != null)
        {
            node.getInf().apply(this);
        }
        if(node.getExpr4() != null)
        {
            node.getExpr4().apply(this);
        }
        outAInfExpr3(node);
    }

    public void inAExpr4Expr3(AExpr4Expr3 node)
    {
        defaultIn(node);
    }

    public void outAExpr4Expr3(AExpr4Expr3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExpr4Expr3(AExpr4Expr3 node)
    {
        inAExpr4Expr3(node);
        if(node.getExpr4() != null)
        {
            node.getExpr4().apply(this);
        }
        outAExpr4Expr3(node);
    }

    public void inAPlusExpr4(APlusExpr4 node)
    {
        defaultIn(node);
    }

    public void outAPlusExpr4(APlusExpr4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExpr4(APlusExpr4 node)
    {
        inAPlusExpr4(node);
        if(node.getExpr4() != null)
        {
            node.getExpr4().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getExpr5() != null)
        {
            node.getExpr5().apply(this);
        }
        outAPlusExpr4(node);
    }

    public void inAMoinsExpr4(AMoinsExpr4 node)
    {
        defaultIn(node);
    }

    public void outAMoinsExpr4(AMoinsExpr4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMoinsExpr4(AMoinsExpr4 node)
    {
        inAMoinsExpr4(node);
        if(node.getExpr4() != null)
        {
            node.getExpr4().apply(this);
        }
        if(node.getMoins() != null)
        {
            node.getMoins().apply(this);
        }
        if(node.getExpr5() != null)
        {
            node.getExpr5().apply(this);
        }
        outAMoinsExpr4(node);
    }

    public void inAExpr5Expr4(AExpr5Expr4 node)
    {
        defaultIn(node);
    }

    public void outAExpr5Expr4(AExpr5Expr4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExpr5Expr4(AExpr5Expr4 node)
    {
        inAExpr5Expr4(node);
        if(node.getExpr5() != null)
        {
            node.getExpr5().apply(this);
        }
        outAExpr5Expr4(node);
    }

    public void inAMultiExpr5(AMultiExpr5 node)
    {
        defaultIn(node);
    }

    public void outAMultiExpr5(AMultiExpr5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultiExpr5(AMultiExpr5 node)
    {
        inAMultiExpr5(node);
        if(node.getExpr5() != null)
        {
            node.getExpr5().apply(this);
        }
        if(node.getMulti() != null)
        {
            node.getMulti().apply(this);
        }
        if(node.getExpr6() != null)
        {
            node.getExpr6().apply(this);
        }
        outAMultiExpr5(node);
    }

    public void inADivExpr5(ADivExpr5 node)
    {
        defaultIn(node);
    }

    public void outADivExpr5(ADivExpr5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivExpr5(ADivExpr5 node)
    {
        inADivExpr5(node);
        if(node.getExpr5() != null)
        {
            node.getExpr5().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getExpr6() != null)
        {
            node.getExpr6().apply(this);
        }
        outADivExpr5(node);
    }

    public void inAExpr6Expr5(AExpr6Expr5 node)
    {
        defaultIn(node);
    }

    public void outAExpr6Expr5(AExpr6Expr5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExpr6Expr5(AExpr6Expr5 node)
    {
        inAExpr6Expr5(node);
        if(node.getExpr6() != null)
        {
            node.getExpr6().apply(this);
        }
        outAExpr6Expr5(node);
    }

    public void inANonExpr6(ANonExpr6 node)
    {
        defaultIn(node);
    }

    public void outANonExpr6(ANonExpr6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANonExpr6(ANonExpr6 node)
    {
        inANonExpr6(node);
        if(node.getNon() != null)
        {
            node.getNon().apply(this);
        }
        if(node.getExpr7() != null)
        {
            node.getExpr7().apply(this);
        }
        outANonExpr6(node);
    }

    public void inAPart7Expr6(APart7Expr6 node)
    {
        defaultIn(node);
    }

    public void outAPart7Expr6(APart7Expr6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPart7Expr6(APart7Expr6 node)
    {
        inAPart7Expr6(node);
        if(node.getExpr7() != null)
        {
            node.getExpr7().apply(this);
        }
        outAPart7Expr6(node);
    }

    public void inAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node)
    {
        defaultIn(node);
    }

    public void outAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node)
    {
        inAExprentreparenthesesExpr7(node);
        if(node.getParentheseo() != null)
        {
            node.getParentheseo().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getParenthesef() != null)
        {
            node.getParenthesef().apply(this);
        }
        outAExprentreparenthesesExpr7(node);
    }

    public void inAVarExpr7(AVarExpr7 node)
    {
        defaultIn(node);
    }

    public void outAVarExpr7(AVarExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarExpr7(AVarExpr7 node)
    {
        inAVarExpr7(node);
        if(node.getVariable() != null)
        {
            node.getVariable().apply(this);
        }
        outAVarExpr7(node);
    }

    public void inANombreExpr7(ANombreExpr7 node)
    {
        defaultIn(node);
    }

    public void outANombreExpr7(ANombreExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANombreExpr7(ANombreExpr7 node)
    {
        inANombreExpr7(node);
        if(node.getNombre() != null)
        {
            node.getNombre().apply(this);
        }
        outANombreExpr7(node);
    }

    public void inALireExpr7(ALireExpr7 node)
    {
        defaultIn(node);
    }

    public void outALireExpr7(ALireExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALireExpr7(ALireExpr7 node)
    {
        inALireExpr7(node);
        if(node.getFonctionlire() != null)
        {
            node.getFonctionlire().apply(this);
        }
        outALireExpr7(node);
    }

    public void inAFonctionExpr7(AFonctionExpr7 node)
    {
        defaultIn(node);
    }

    public void outAFonctionExpr7(AFonctionExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFonctionExpr7(AFonctionExpr7 node)
    {
        inAFonctionExpr7(node);
        if(node.getDeffonction() != null)
        {
            node.getDeffonction().apply(this);
        }
        outAFonctionExpr7(node);
    }

    public void inAAppelExpr7(AAppelExpr7 node)
    {
        defaultIn(node);
    }

    public void outAAppelExpr7(AAppelExpr7 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAppelExpr7(AAppelExpr7 node)
    {
        inAAppelExpr7(node);
        if(node.getFonctionappel() != null)
        {
            node.getFonctionappel().apply(this);
        }
        outAAppelExpr7(node);
    }

    public void inAAffect(AAffect node)
    {
        defaultIn(node);
    }

    public void outAAffect(AAffect node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAffect(AAffect node)
    {
        inAAffect(node);
        if(node.getVariable() != null)
        {
            node.getVariable().apply(this);
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getPointvirgule() != null)
        {
            node.getPointvirgule().apply(this);
        }
        outAAffect(node);
    }

    public void inASimpleVariable(ASimpleVariable node)
    {
        defaultIn(node);
    }

    public void outASimpleVariable(ASimpleVariable node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleVariable(ASimpleVariable node)
    {
        inASimpleVariable(node);
        if(node.getNom() != null)
        {
            node.getNom().apply(this);
        }
        outASimpleVariable(node);
    }

    public void inATabVariable(ATabVariable node)
    {
        defaultIn(node);
    }

    public void outATabVariable(ATabVariable node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATabVariable(ATabVariable node)
    {
        inATabVariable(node);
        if(node.getNom() != null)
        {
            node.getNom().apply(this);
        }
        if(node.getCrocheto() != null)
        {
            node.getCrocheto().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getCrochetf() != null)
        {
            node.getCrochetf().apply(this);
        }
        outATabVariable(node);
    }

    public void inAFonctionecrire(AFonctionecrire node)
    {
        defaultIn(node);
    }

    public void outAFonctionecrire(AFonctionecrire node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFonctionecrire(AFonctionecrire node)
    {
        inAFonctionecrire(node);
        if(node.getEcrire() != null)
        {
            node.getEcrire().apply(this);
        }
        if(node.getParentheseo() != null)
        {
            node.getParentheseo().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getParenthesef() != null)
        {
            node.getParenthesef().apply(this);
        }
        if(node.getPointvirgule() != null)
        {
            node.getPointvirgule().apply(this);
        }
        outAFonctionecrire(node);
    }

    public void inAAvecargsFonctionappel(AAvecargsFonctionappel node)
    {
        defaultIn(node);
    }

    public void outAAvecargsFonctionappel(AAvecargsFonctionappel node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAvecargsFonctionappel(AAvecargsFonctionappel node)
    {
        inAAvecargsFonctionappel(node);
        if(node.getNom() != null)
        {
            node.getNom().apply(this);
        }
        if(node.getParentheseo() != null)
        {
            node.getParentheseo().apply(this);
        }
        if(node.getAppelexpr() != null)
        {
            node.getAppelexpr().apply(this);
        }
        if(node.getParenthesef() != null)
        {
            node.getParenthesef().apply(this);
        }
        outAAvecargsFonctionappel(node);
    }

    public void inASansargFonctionappel(ASansargFonctionappel node)
    {
        defaultIn(node);
    }

    public void outASansargFonctionappel(ASansargFonctionappel node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASansargFonctionappel(ASansargFonctionappel node)
    {
        inASansargFonctionappel(node);
        if(node.getNom() != null)
        {
            node.getNom().apply(this);
        }
        if(node.getParentheseo() != null)
        {
            node.getParentheseo().apply(this);
        }
        if(node.getParenthesef() != null)
        {
            node.getParenthesef().apply(this);
        }
        outASansargFonctionappel(node);
    }

    public void inAAppelexpr(AAppelexpr node)
    {
        defaultIn(node);
    }

    public void outAAppelexpr(AAppelexpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAppelexpr(AAppelexpr node)
    {
        inAAppelexpr(node);
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getListeexpr() != null)
        {
            node.getListeexpr().apply(this);
        }
        outAAppelexpr(node);
    }

    public void inAListeexpr(AListeexpr node)
    {
        defaultIn(node);
    }

    public void outAListeexpr(AListeexpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeexpr(AListeexpr node)
    {
        inAListeexpr(node);
        if(node.getVirgule() != null)
        {
            node.getVirgule().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getListeexpr() != null)
        {
            node.getListeexpr().apply(this);
        }
        outAListeexpr(node);
    }

    public void inARienListeexpr(ARienListeexpr node)
    {
        defaultIn(node);
    }

    public void outARienListeexpr(ARienListeexpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARienListeexpr(ARienListeexpr node)
    {
        inARienListeexpr(node);
        outARienListeexpr(node);
    }

    public void inAFonctionlire(AFonctionlire node)
    {
        defaultIn(node);
    }

    public void outAFonctionlire(AFonctionlire node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFonctionlire(AFonctionlire node)
    {
        inAFonctionlire(node);
        if(node.getLire() != null)
        {
            node.getLire().apply(this);
        }
        if(node.getParentheseo() != null)
        {
            node.getParentheseo().apply(this);
        }
        if(node.getParenthesef() != null)
        {
            node.getParenthesef().apply(this);
        }
        outAFonctionlire(node);
    }
}
