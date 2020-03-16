import c3a.*;
import nasm.*;
import ts.Ts;
import ts.TsItemVar;

import java.util.Map;

//TODO : Revoir pour les noms de registres différents

public class C3a2nasm implements C3aVisitor<NasmOperand> {
    private C3a c3a;
    private NasmRegister eax;
    private NasmRegister ebp;
    private NasmRegister esp;
    private Nasm nasm;
    private Ts tableGlobale;
    Map<String, TsItemVar> localVar;

    //Pour calcul de cas précis
    private int nbArgs = -1;
    private int argSize = -1;
    private int jmp_eq_offset = 0;
    private int jmp_l_offset = 0;
    //private TsItemFct currentFct;

    public C3a2nasm(C3a c3a, Ts table) {
        this.c3a = c3a;
        this.tableGlobale = table;
        this.nasm = new Nasm(table);

        //Création des registres
        NasmRegister ebx = new NasmRegister(Nasm.REG_EBX);
        ebx.colorRegister(Nasm.REG_EBX);
        this.eax = new NasmRegister(Nasm.REG_EAX);
        eax.colorRegister(Nasm.REG_EAX);
        this.ebp = new NasmRegister(Nasm.REG_EBP);
        ebp.colorRegister(Nasm.REG_EBP);
        this.esp = new NasmRegister(Nasm.REG_ESP);
        esp.colorRegister(Nasm.REG_ESP);

        nasm.ajouteInst(new NasmCall(null, new NasmLabel("main"), ""));
        nasm.ajouteInst(new NasmMov(null, ebx, new NasmConstant(0), " valeur de retour du programme"));
        nasm.ajouteInst(new NasmMov(null, eax, new NasmConstant(1), ""));
        nasm.ajouteInst(new NasmInt(null, ""));

        for (C3aInst inst : c3a.listeInst) {
            inst.accept(this);
        }
    }

    public Nasm getNasm() {
        return nasm;
    }

    @Override
    public NasmOperand visit(C3aInstCall inst) {
        NasmConstant valRet = new NasmConstant(4);
        nasm.ajouteInst(new NasmSub(null, esp,valRet, "allocation mémoire pour la valeur de retour"));

        NasmLabel nasmlabel = new NasmLabel(inst.op1.val.identif);
        nasm.ajouteInst(new NasmCall(null, nasmlabel, ""));

        NasmRegister regRet = new NasmRegister(1);
        nasm.ajouteInst(new NasmPop(null, regRet, "récupération de la valeur de retour"));

        nbArgs = inst.op1.val.getNbArgs();
        if (nbArgs > 0)
            nasm.ajouteInst(new NasmAdd(null, esp, new NasmConstant(8), "désallocation des arguments"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFBegin inst) {
        NasmOperand label = new NasmLabel(inst.val.identif);

        localVar = inst.val.getTable().variables;
        nbArgs = inst.val.getNbArgs();
        argSize = inst.val.getTable().nbVar();

        nasm.ajouteInst(new NasmPush(label, ebp, "sauvegarde la valeur de ebp"));
        nasm.ajouteInst(new NasmMov(null, ebp, esp, "nouvelle valeur de ebp"));
        int sizeVar = 4*argSize;
        nasm.ajouteInst(new NasmSub(null, esp, new NasmConstant(sizeVar), "allocation des variables locales"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInst inst) {
        return null;
    }


    @Override
    public NasmOperand visit(C3aInstRead inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand op = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(label, eax, op, ""));
        return null;
    }

    public NasmOperand visit(C3aInstAffect inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand result = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(label, result, op1, "Affect"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFEnd inst) { //OK
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        int sizeVar = 4*argSize;
        NasmConstant sizeVarLoc = new NasmConstant(sizeVar);
        nasm.ajouteInst(new NasmAdd(label, esp, sizeVarLoc, "désallocation des variables locales"));
        nasm.ajouteInst(new NasmPop(null, ebp, "restaure la valeur de ebp"));
        nasm.ajouteInst(new NasmRet(null, ""));

        argSize = -1;
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfEqual inst) {
        C3aOperand op1 = inst.op1;
        C3aOperand op2 = inst.op2;
        if (!(op1 instanceof C3aTemp)) {
            NasmRegister register = new NasmRegister(2 + jmp_eq_offset);
            nasm.ajouteInst(new NasmMov(null, register, op1.accept(this), "JumpIfEqual 1"));
            nasm.ajouteInst(new NasmCmp(null, register, op2.accept(this), "on passe par un registre temporaire"));
            jmp_eq_offset++;
        } else {
            NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
            NasmRegister register = new NasmRegister(1 + jmp_eq_offset);
            nasm.ajouteInst(new NasmCmp(label, register, op2.accept(this), "JumpIfEqual 1"));
            jmp_eq_offset += 3;
        }
        NasmOperand address = inst.result.accept(this);
        nasm.ajouteInst(new NasmJe(null, address, "JumpIfEqual 2"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
        C3aOperand op1 = inst.op1;
        C3aOperand op2 = inst.op2;
        if (!(op1 instanceof C3aTemp)) {
            NasmOperand address = inst.result.accept(this);
            NasmRegister register = new NasmRegister(3 + jmp_eq_offset);
            jmp_eq_offset++;
            nasm.ajouteInst(new NasmMov(null, register, op1.accept(this), "jumpIfNotEqual 1"));
            nasm.ajouteInst(new NasmCmp(null, register, op2.accept(this), "on passe par un registre temporaire"));
            nasm.ajouteInst(new NasmJne(null, address, "jumpIfNotEqual 2"));
        } else {
            NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
            NasmOperand address = inst.result.accept(this);
            NasmRegister register = new NasmRegister(3 + jmp_eq_offset);
            jmp_eq_offset++;
            nasm.ajouteInst(new NasmCmp(label, register, op1.accept(this), "jumpIfNotEqual 1"));
            nasm.ajouteInst(new NasmJne(null, address, "jumpIfNotEqual 2"));
        }
        return null;

    }

    @Override
    public NasmOperand visit(C3aInstJumpIfLess inst) {
        C3aOperand op1 = inst.op1;
        C3aOperand op2 = inst.op2;
        if (!(op1 instanceof C3aTemp)) {
            NasmRegister register = new NasmRegister(3);
            nasm.ajouteInst(new NasmMov(null, register, op1.accept(this), "JumpIfLess 1"));
            nasm.ajouteInst(new NasmCmp(null, register, op2.accept(this), "on passe par un registre temporaire"));
            NasmOperand address = inst.result.accept(this);
            nasm.ajouteInst(new NasmJl(null, address, "JumpIfLess 2"));
        } else {
            NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
            NasmOperand address = inst.result.accept(this);
            NasmRegister register = new NasmRegister(3 + jmp_l_offset);
            jmp_l_offset += 2;
            nasm.ajouteInst(new NasmCmp(label, register, op2.accept(this), "JumpIfLess 1"));
            nasm.ajouteInst(new NasmJl(null, address, "JumpIfLess 2"));
        }
        return null;
    }


    @Override
    public NasmOperand visit(C3aInstJump inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand adresse = inst.result.accept(this);

        nasm.ajouteInst(new NasmJmp(label, adresse, "Jump"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstParam inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand op1 = inst.op1.accept(this);

        nasm.ajouteInst(new NasmPush(label, op1, "Param"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstReturn inst) {
        NasmRegister register = new NasmRegister(0);
        NasmConstant size = new NasmConstant(localVar.size());
        NasmAddress address =  new NasmAddress(ebp, '+', size);

        nasm.ajouteInst(new NasmMov(null, address, register, "ecriture de la valeur de retour"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstWrite inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand op1 = inst.op1.accept(this);
        nasm.ajouteInst(new NasmMov(label, eax, op1, "Write 1"));

        NasmLabel print = new NasmLabel("iprintLF");
        nasm.ajouteInst(new NasmCall(null, print , "Write 2"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aConstant oper) {
        return new NasmConstant(oper.val);
    }

    @Override
    public NasmOperand visit(C3aLabel oper) {
        return new NasmLabel(oper.toString());
    }

    @Override
    public NasmOperand visit(C3aTemp oper) {
        return new NasmRegister(oper.num);
    }

    @Override
    public NasmOperand visit(C3aVar oper) {
        NasmOperand base;
        if(oper.item.portee == tableGlobale) {
            base = new NasmLabel(oper.item.identif);
        }else {
            base = ebp;
        }

        if (oper.item.portee == tableGlobale && oper.item.getTaille() == 1)
            return new NasmAddress(base);

        var direction = oper.item.isParam || oper.item.portee == tableGlobale ? '+' : '-';
        NasmConstant offset;
        if (oper.item.getTaille() > 1)
            offset = new NasmConstant(((C3aConstant) oper.index).val);
        else
            offset = new NasmConstant(oper.item.isParam ? 2 + (oper.item.portee.nbArg() - oper.item.getAdresse())
                    : 1 + oper.item.getAdresse());
        return new NasmAddress(base, direction, offset);
    }

    @Override
    public NasmOperand visit(C3aFunction oper) {
        return new NasmLabel(oper.toString());
    }

    public NasmOperand visit(C3aInstAdd inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmAdd(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstSub inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmSub(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstMult inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmMul(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstDiv inst) {
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);

        nasm.ajouteInst(new NasmMov(null, eax, oper1, ""));

        NasmRegister register = nasm.newRegister();
        nasm.ajouteInst(new NasmMov(label, register, oper2, ""));
        nasm.ajouteInst(new NasmDiv(null, register, ""));

        NasmRegister newRegister = nasm.newRegister();
        nasm.ajouteInst(new NasmMov(label, newRegister, eax, ""));

        return null;
    }

}
