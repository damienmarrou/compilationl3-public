/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AVarent extends PVarent
{
    private TTypeentier _typeentier_;
    private TNom _nom_;

    public AVarent()
    {
        // Constructor
    }

    public AVarent(
        @SuppressWarnings("hiding") TTypeentier _typeentier_,
        @SuppressWarnings("hiding") TNom _nom_)
    {
        // Constructor
        setTypeentier(_typeentier_);

        setNom(_nom_);

    }

    @Override
    public Object clone()
    {
        return new AVarent(
            cloneNode(this._typeentier_),
            cloneNode(this._nom_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVarent(this);
    }

    public TTypeentier getTypeentier()
    {
        return this._typeentier_;
    }

    public void setTypeentier(TTypeentier node)
    {
        if(this._typeentier_ != null)
        {
            this._typeentier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._typeentier_ = node;
    }

    public TNom getNom()
    {
        return this._nom_;
    }

    public void setNom(TNom node)
    {
        if(this._nom_ != null)
        {
            this._nom_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._nom_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._typeentier_)
            + toString(this._nom_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._typeentier_ == child)
        {
            this._typeentier_ = null;
            return;
        }

        if(this._nom_ == child)
        {
            this._nom_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._typeentier_ == oldChild)
        {
            setTypeentier((TTypeentier) newChild);
            return;
        }

        if(this._nom_ == oldChild)
        {
            setNom((TNom) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}