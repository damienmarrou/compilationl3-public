/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AFctvarmultiple extends PFctvarmultiple
{
    private TVirgule _virgule_;
    private PVarsimple _varsimple_;
    private PFctvarmultiple _fctvarmultiple_;

    public AFctvarmultiple()
    {
        // Constructor
    }

    public AFctvarmultiple(
        @SuppressWarnings("hiding") TVirgule _virgule_,
        @SuppressWarnings("hiding") PVarsimple _varsimple_,
        @SuppressWarnings("hiding") PFctvarmultiple _fctvarmultiple_)
    {
        // Constructor
        setVirgule(_virgule_);

        setVarsimple(_varsimple_);

        setFctvarmultiple(_fctvarmultiple_);

    }

    @Override
    public Object clone()
    {
        return new AFctvarmultiple(
            cloneNode(this._virgule_),
            cloneNode(this._varsimple_),
            cloneNode(this._fctvarmultiple_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFctvarmultiple(this);
    }

    public TVirgule getVirgule()
    {
        return this._virgule_;
    }

    public void setVirgule(TVirgule node)
    {
        if(this._virgule_ != null)
        {
            this._virgule_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._virgule_ = node;
    }

    public PVarsimple getVarsimple()
    {
        return this._varsimple_;
    }

    public void setVarsimple(PVarsimple node)
    {
        if(this._varsimple_ != null)
        {
            this._varsimple_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._varsimple_ = node;
    }

    public PFctvarmultiple getFctvarmultiple()
    {
        return this._fctvarmultiple_;
    }

    public void setFctvarmultiple(PFctvarmultiple node)
    {
        if(this._fctvarmultiple_ != null)
        {
            this._fctvarmultiple_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fctvarmultiple_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._virgule_)
            + toString(this._varsimple_)
            + toString(this._fctvarmultiple_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._virgule_ == child)
        {
            this._virgule_ = null;
            return;
        }

        if(this._varsimple_ == child)
        {
            this._varsimple_ = null;
            return;
        }

        if(this._fctvarmultiple_ == child)
        {
            this._fctvarmultiple_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._virgule_ == oldChild)
        {
            setVirgule((TVirgule) newChild);
            return;
        }

        if(this._varsimple_ == oldChild)
        {
            setVarsimple((PVarsimple) newChild);
            return;
        }

        if(this._fctvarmultiple_ == oldChild)
        {
            setFctvarmultiple((PFctvarmultiple) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
