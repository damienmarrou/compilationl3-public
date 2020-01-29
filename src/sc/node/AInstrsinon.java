/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AInstrsinon extends PInstrsinon
{
    private TSinon _sinon_;
    private PBloc _bloc_;

    public AInstrsinon()
    {
        // Constructor
    }

    public AInstrsinon(
        @SuppressWarnings("hiding") TSinon _sinon_,
        @SuppressWarnings("hiding") PBloc _bloc_)
    {
        // Constructor
        setSinon(_sinon_);

        setBloc(_bloc_);

    }

    @Override
    public Object clone()
    {
        return new AInstrsinon(
            cloneNode(this._sinon_),
            cloneNode(this._bloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstrsinon(this);
    }

    public TSinon getSinon()
    {
        return this._sinon_;
    }

    public void setSinon(TSinon node)
    {
        if(this._sinon_ != null)
        {
            this._sinon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._sinon_ = node;
    }

    public PBloc getBloc()
    {
        return this._bloc_;
    }

    public void setBloc(PBloc node)
    {
        if(this._bloc_ != null)
        {
            this._bloc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._bloc_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._sinon_)
            + toString(this._bloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._sinon_ == child)
        {
            this._sinon_ = null;
            return;
        }

        if(this._bloc_ == child)
        {
            this._bloc_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._sinon_ == oldChild)
        {
            setSinon((TSinon) newChild);
            return;
        }

        if(this._bloc_ == oldChild)
        {
            setBloc((PBloc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}