/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AListListinstr extends PListinstr
{
    private PInstr _instr_;
    private PListinstr _listinstr_;

    public AListListinstr()
    {
        // Constructor
    }

    public AListListinstr(
        @SuppressWarnings("hiding") PInstr _instr_,
        @SuppressWarnings("hiding") PListinstr _listinstr_)
    {
        // Constructor
        setInstr(_instr_);

        setListinstr(_listinstr_);

    }

    @Override
    public Object clone()
    {
        return new AListListinstr(
            cloneNode(this._instr_),
            cloneNode(this._listinstr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAListListinstr(this);
    }

    public PInstr getInstr()
    {
        return this._instr_;
    }

    public void setInstr(PInstr node)
    {
        if(this._instr_ != null)
        {
            this._instr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._instr_ = node;
    }

    public PListinstr getListinstr()
    {
        return this._listinstr_;
    }

    public void setListinstr(PListinstr node)
    {
        if(this._listinstr_ != null)
        {
            this._listinstr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listinstr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._instr_)
            + toString(this._listinstr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._instr_ == child)
        {
            this._instr_ = null;
            return;
        }

        if(this._listinstr_ == child)
        {
            this._listinstr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._instr_ == oldChild)
        {
            setInstr((PInstr) newChild);
            return;
        }

        if(this._listinstr_ == oldChild)
        {
            setListinstr((PListinstr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
