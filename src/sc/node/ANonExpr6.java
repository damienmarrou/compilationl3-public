/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ANonExpr6 extends PExpr6
{
    private TNon _non_;
    private PExpr7 _expr7_;

    public ANonExpr6()
    {
        // Constructor
    }

    public ANonExpr6(
        @SuppressWarnings("hiding") TNon _non_,
        @SuppressWarnings("hiding") PExpr7 _expr7_)
    {
        // Constructor
        setNon(_non_);

        setExpr7(_expr7_);

    }

    @Override
    public Object clone()
    {
        return new ANonExpr6(
            cloneNode(this._non_),
            cloneNode(this._expr7_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANonExpr6(this);
    }

    public TNon getNon()
    {
        return this._non_;
    }

    public void setNon(TNon node)
    {
        if(this._non_ != null)
        {
            this._non_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._non_ = node;
    }

    public PExpr7 getExpr7()
    {
        return this._expr7_;
    }

    public void setExpr7(PExpr7 node)
    {
        if(this._expr7_ != null)
        {
            this._expr7_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expr7_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._non_)
            + toString(this._expr7_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._non_ == child)
        {
            this._non_ = null;
            return;
        }

        if(this._expr7_ == child)
        {
            this._expr7_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._non_ == oldChild)
        {
            setNon((TNon) newChild);
            return;
        }

        if(this._expr7_ == oldChild)
        {
            setExpr7((PExpr7) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
