/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ALireExpr7 extends PExpr7
{
    private PFonctionlire _fonctionlire_;

    public ALireExpr7()
    {
        // Constructor
    }

    public ALireExpr7(
        @SuppressWarnings("hiding") PFonctionlire _fonctionlire_)
    {
        // Constructor
        setFonctionlire(_fonctionlire_);

    }

    @Override
    public Object clone()
    {
        return new ALireExpr7(
            cloneNode(this._fonctionlire_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALireExpr7(this);
    }

    public PFonctionlire getFonctionlire()
    {
        return this._fonctionlire_;
    }

    public void setFonctionlire(PFonctionlire node)
    {
        if(this._fonctionlire_ != null)
        {
            this._fonctionlire_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fonctionlire_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._fonctionlire_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._fonctionlire_ == child)
        {
            this._fonctionlire_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._fonctionlire_ == oldChild)
        {
            setFonctionlire((PFonctionlire) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
