/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AAppelexpr extends PAppelexpr
{
    private PExpr _expr_;
    private PListeexpr _listeexpr_;

    public AAppelexpr()
    {
        // Constructor
    }

    public AAppelexpr(
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") PListeexpr _listeexpr_)
    {
        // Constructor
        setExpr(_expr_);

        setListeexpr(_listeexpr_);

    }

    @Override
    public Object clone()
    {
        return new AAppelexpr(
            cloneNode(this._expr_),
            cloneNode(this._listeexpr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAppelexpr(this);
    }

    public PExpr getExpr()
    {
        return this._expr_;
    }

    public void setExpr(PExpr node)
    {
        if(this._expr_ != null)
        {
            this._expr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expr_ = node;
    }

    public PListeexpr getListeexpr()
    {
        return this._listeexpr_;
    }

    public void setListeexpr(PListeexpr node)
    {
        if(this._listeexpr_ != null)
        {
            this._listeexpr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listeexpr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._expr_)
            + toString(this._listeexpr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        if(this._listeexpr_ == child)
        {
            this._listeexpr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        if(this._listeexpr_ == oldChild)
        {
            setListeexpr((PListeexpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
