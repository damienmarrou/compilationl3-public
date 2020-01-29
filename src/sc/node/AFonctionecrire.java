/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AFonctionecrire extends PFonctionecrire
{
    private TEcrire _ecrire_;
    private TParentheseo _parentheseo_;
    private PExpr _expr_;
    private TParenthesef _parenthesef_;
    private TPointvirgule _pointvirgule_;

    public AFonctionecrire()
    {
        // Constructor
    }

    public AFonctionecrire(
        @SuppressWarnings("hiding") TEcrire _ecrire_,
        @SuppressWarnings("hiding") TParentheseo _parentheseo_,
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") TParenthesef _parenthesef_,
        @SuppressWarnings("hiding") TPointvirgule _pointvirgule_)
    {
        // Constructor
        setEcrire(_ecrire_);

        setParentheseo(_parentheseo_);

        setExpr(_expr_);

        setParenthesef(_parenthesef_);

        setPointvirgule(_pointvirgule_);

    }

    @Override
    public Object clone()
    {
        return new AFonctionecrire(
            cloneNode(this._ecrire_),
            cloneNode(this._parentheseo_),
            cloneNode(this._expr_),
            cloneNode(this._parenthesef_),
            cloneNode(this._pointvirgule_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFonctionecrire(this);
    }

    public TEcrire getEcrire()
    {
        return this._ecrire_;
    }

    public void setEcrire(TEcrire node)
    {
        if(this._ecrire_ != null)
        {
            this._ecrire_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ecrire_ = node;
    }

    public TParentheseo getParentheseo()
    {
        return this._parentheseo_;
    }

    public void setParentheseo(TParentheseo node)
    {
        if(this._parentheseo_ != null)
        {
            this._parentheseo_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._parentheseo_ = node;
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

    public TParenthesef getParenthesef()
    {
        return this._parenthesef_;
    }

    public void setParenthesef(TParenthesef node)
    {
        if(this._parenthesef_ != null)
        {
            this._parenthesef_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._parenthesef_ = node;
    }

    public TPointvirgule getPointvirgule()
    {
        return this._pointvirgule_;
    }

    public void setPointvirgule(TPointvirgule node)
    {
        if(this._pointvirgule_ != null)
        {
            this._pointvirgule_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pointvirgule_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._ecrire_)
            + toString(this._parentheseo_)
            + toString(this._expr_)
            + toString(this._parenthesef_)
            + toString(this._pointvirgule_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._ecrire_ == child)
        {
            this._ecrire_ = null;
            return;
        }

        if(this._parentheseo_ == child)
        {
            this._parentheseo_ = null;
            return;
        }

        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        if(this._parenthesef_ == child)
        {
            this._parenthesef_ = null;
            return;
        }

        if(this._pointvirgule_ == child)
        {
            this._pointvirgule_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._ecrire_ == oldChild)
        {
            setEcrire((TEcrire) newChild);
            return;
        }

        if(this._parentheseo_ == oldChild)
        {
            setParentheseo((TParentheseo) newChild);
            return;
        }

        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        if(this._parenthesef_ == oldChild)
        {
            setParenthesef((TParenthesef) newChild);
            return;
        }

        if(this._pointvirgule_ == oldChild)
        {
            setPointvirgule((TPointvirgule) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
