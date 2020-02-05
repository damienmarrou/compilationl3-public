/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import java.util.*;
import sc.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    @Override
    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    @Override
    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    @Override
    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    @Override
    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    @Override
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAProgramme(AProgramme node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADecvar(ADecvar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEntierVarsimple(AEntierVarsimple node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATabVarsimple(ATabVarsimple node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarent(AVarent node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVartab(AVartab node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarmultiple(AVarmultiple node)
    {
        defaultCase(node);
    }

    @Override
    public void caseARienVarmultiple(ARienVarmultiple node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMultipleFctdecvar(AMultipleFctdecvar node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFctvarmultiple(AFctvarmultiple node)
    {
        defaultCase(node);
    }

    @Override
    public void caseARienFctvarmultiple(ARienFctvarmultiple node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFoncDeffonction2(AFoncDeffonction2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseARienDeffonction2(ARienDeffonction2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAArgsDeffonction(AArgsDeffonction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASansargDeffonction(ASansargDeffonction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseARienDecvar2(ARienDecvar2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarDecvar2(AVarDecvar2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListListinstr(AListListinstr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseARienListinstr(ARienListinstr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAffectationInstr(AAffectationInstr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABlocInstr(ABlocInstr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATantqueInstr(ATantqueInstr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASiInstr(ASiInstr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseARetourInstr(ARetourInstr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEcrireInstr(AEcrireInstr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALireInstr(ALireInstr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstrtantque(AInstrtantque node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstrretour(AInstrretour node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASisinonInstrsi(ASisinonInstrsi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASiInstrsi(ASiInstrsi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInstrsinon(AInstrsinon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseABloc(ABloc node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAOuExpr(AOuExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExpr2Expr(AExpr2Expr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEtExpr2(AEtExpr2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExpr3Expr2(AExpr3Expr2 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAEgalExpr3(AEgalExpr3 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAInfExpr3(AInfExpr3 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExpr4Expr3(AExpr4Expr3 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPlusExpr4(APlusExpr4 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMoinsExpr4(AMoinsExpr4 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExpr5Expr4(AExpr5Expr4 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMultiExpr5(AMultiExpr5 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADivExpr5(ADivExpr5 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExpr6Expr5(AExpr6Expr5 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANonExpr6(ANonExpr6 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAPart7Expr6(APart7Expr6 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExprentreparenthesesExpr7(AExprentreparenthesesExpr7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAVarExpr7(AVarExpr7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANombreExpr7(ANombreExpr7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseALireExpr7(ALireExpr7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFonctionExpr7(AFonctionExpr7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAppelExpr7(AAppelExpr7 node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAffect(AAffect node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASimpleVariable(ASimpleVariable node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATabVariable(ATabVariable node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFonctionecrire(AFonctionecrire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFonctionappel(AFonctionappel node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAAppelexpr(AAppelexpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAListeexpr(AListeexpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFonctionlire(AFonctionlire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTypeentier(TTypeentier node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPlus(TPlus node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMoins(TMoins node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMulti(TMulti node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTDiv(TDiv node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTInf(TInf node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEgal(TEgal node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEt(TEt node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTOu(TOu node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNon(TNon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTCrocheto(TCrocheto node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTCrochetf(TCrochetf node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAccoladeo(TAccoladeo node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAccoladef(TAccoladef node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTParentheseo(TParentheseo node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTParenthesef(TParenthesef node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTPointvirgule(TPointvirgule node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTVirgule(TVirgule node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEspaces(TEspaces node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSi(TSi node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTAlors(TAlors node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSinon(TSinon node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTRetour(TRetour node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTTantque(TTantque node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFaire(TFaire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTLire(TLire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTEcrire(TEcrire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTFonction(TFonction node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTCommentaire(TCommentaire node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNombre(TNombre node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNom(TNom node)
    {
        defaultCase(node);
    }

    @Override
    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    @Override
    public void caseInvalidToken(InvalidToken node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
