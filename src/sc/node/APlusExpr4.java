/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class APlusExpr4 extends PExpr4
{
    private PExpr4 _expr4_;
    private TPlus _plus_;
    private PExpr5 _expr5_;

    public APlusExpr4()
    {
        // Constructor
    }

    public APlusExpr4(
        @SuppressWarnings("hiding") PExpr4 _expr4_,
        @SuppressWarnings("hiding") TPlus _plus_,
        @SuppressWarnings("hiding") PExpr5 _expr5_)
    {
        // Constructor
        setExpr4(_expr4_);

        setPlus(_plus_);

        setExpr5(_expr5_);

    }

    @Override
    public Object clone()
    {
        return new APlusExpr4(
            cloneNode(this._expr4_),
            cloneNode(this._plus_),
            cloneNode(this._expr5_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPlusExpr4(this);
    }

    public PExpr4 getExpr4()
    {
        return this._expr4_;
    }

    public void setExpr4(PExpr4 node)
    {
        if(this._expr4_ != null)
        {
            this._expr4_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expr4_ = node;
    }

    public TPlus getPlus()
    {
        return this._plus_;
    }

    public void setPlus(TPlus node)
    {
        if(this._plus_ != null)
        {
            this._plus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._plus_ = node;
    }

    public PExpr5 getExpr5()
    {
        return this._expr5_;
    }

    public void setExpr5(PExpr5 node)
    {
        if(this._expr5_ != null)
        {
            this._expr5_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expr5_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._expr4_)
            + toString(this._plus_)
            + toString(this._expr5_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._expr4_ == child)
        {
            this._expr4_ = null;
            return;
        }

        if(this._plus_ == child)
        {
            this._plus_ = null;
            return;
        }

        if(this._expr5_ == child)
        {
            this._expr5_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._expr4_ == oldChild)
        {
            setExpr4((PExpr4) newChild);
            return;
        }

        if(this._plus_ == oldChild)
        {
            setPlus((TPlus) newChild);
            return;
        }

        if(this._expr5_ == oldChild)
        {
            setExpr5((PExpr5) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}