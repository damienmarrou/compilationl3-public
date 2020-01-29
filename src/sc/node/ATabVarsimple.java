/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ATabVarsimple extends PVarsimple
{
    private PVartab _vartab_;

    public ATabVarsimple()
    {
        // Constructor
    }

    public ATabVarsimple(
        @SuppressWarnings("hiding") PVartab _vartab_)
    {
        // Constructor
        setVartab(_vartab_);

    }

    @Override
    public Object clone()
    {
        return new ATabVarsimple(
            cloneNode(this._vartab_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATabVarsimple(this);
    }

    public PVartab getVartab()
    {
        return this._vartab_;
    }

    public void setVartab(PVartab node)
    {
        if(this._vartab_ != null)
        {
            this._vartab_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._vartab_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._vartab_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._vartab_ == child)
        {
            this._vartab_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._vartab_ == oldChild)
        {
            setVartab((PVartab) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
