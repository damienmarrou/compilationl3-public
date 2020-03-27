import c3a.*;
import nasm.*;
import ts.Ts;

public class C3a2nasm implements C3aVisitor<NasmOperand> {
    private NasmOperand ebp = new NasmRegister(Nasm.REG_EBP, Nasm.REG_EBP);
    private NasmOperand esp = new NasmRegister(Nasm.REG_ESP, Nasm.REG_ESP);
    private Nasm nasm;
    private Ts tableGlobale;
    private Ts localScope;

    public C3a2nasm(C3a c3a, Ts table) {
        this.tableGlobale = table;
        this.nasm = new Nasm(table);
        nasm.setTempCounter(c3a.getTempCounter());
        //Création des registres
        NasmRegister eax = nasm.newRegister();
        NasmRegister ebx = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
        ebx.colorRegister(Nasm.REG_EBX);

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

    private NasmOperand getLabel(C3aInst inst) {
        return inst.label != null
                ? inst.label.accept(this)
                : null;
    }

    @Override
    public NasmOperand visit(C3aInstFBegin inst) {
        localScope = inst.val.getTable();
        NasmOperand label = new NasmLabel(inst.val.identif);
        nasm.ajouteInst(new NasmPush(label, ebp, "sauvegarde la valeur de ebp"));
        nasm.ajouteInst(new NasmMov(null, ebp, esp, "nouvelle valeur de ebp"));
        nasm.ajouteInst(new NasmSub(null, esp, new NasmConstant(localScope.nbVar() * 4), "allocation des variables locales"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFEnd inst) {
        nasm.ajouteInst(new NasmAdd(getLabel(inst), esp, new NasmConstant(localScope.nbVar() * 4), "désallocation des variables locales"));
        nasm.ajouteInst(new NasmPop(null, ebp, "restaure la valeur de ebp"));
        nasm.ajouteInst(new NasmRet(null, ""));

        return null;
    }

    @Override
    public NasmOperand visit(C3aInstWrite inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
        nasm.ajouteInst(new NasmMov(getLabel(inst), eax, op1, "Write 1"));
        NasmLabel print = new NasmLabel("iprintLF");
        nasm.ajouteInst(new NasmCall(null, print, "Write 2"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstRead inst) {
        NasmOperand dest = inst.result.accept(this);
        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);

        nasm.ajouteInst(new NasmMov(getLabel(inst), eax, new NasmConstant(2), ""));
        nasm.ajouteInst(new NasmCall(null, new NasmLabel("readline"), ""));
        nasm.ajouteInst(new NasmCall(null, new NasmLabel("atoi"), ""));
        nasm.ajouteInst(new NasmMov(null, dest, eax, ""));

        return null;
    }

    @Override
    public NasmOperand visit(C3aInstCall inst) {
        NasmConstant valRet = new NasmConstant(4);
        nasm.ajouteInst(new NasmSub(getLabel(inst), esp, valRet, "allocation mémoire pour la valeur de retour"));
        nasm.ajouteInst(new NasmCall(null, inst.op1.accept(this), ""));
        nasm.ajouteInst(new NasmPop(null, inst.result.accept(this), "récupération de la valeur de retour"));
        if (inst.op1.val.nbArgs > 0)
            nasm.ajouteInst(new NasmAdd(null, esp, new NasmConstant(inst.op1.val.nbArgs * 4), "désallocation des arguments"));
        return null;
    }

    public NasmOperand visit(C3aInstAffect inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand result = inst.result.accept(this);
        nasm.ajouteInst(new NasmMov(getLabel(inst), result, op1, "Affect"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstParam inst) {
        NasmOperand op1 = inst.op1.accept(this);
        nasm.ajouteInst(new NasmPush(getLabel(inst), op1, "Param"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstReturn inst) {
        NasmAddress address = new NasmAddress(ebp, '+', new NasmConstant(2));
        nasm.ajouteInst(new NasmMov(getLabel(inst), address, inst.op1.accept(this), "ecriture de la valeur de retour"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJump inst) {
        NasmOperand adresse = inst.result.accept(this);
        nasm.ajouteInst(new NasmJmp(getLabel(inst), adresse, "Jump"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfEqual inst) {
        nasm.ajouteInst(new NasmCmp(getLabel(inst), inst.op1.accept(this), inst.op2.accept(this), "JumpIfEqual 1"));
        nasm.ajouteInst(new NasmJe(null, inst.result.accept(this), "JumpIfEqual 2"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
        nasm.ajouteInst(new NasmCmp(getLabel(inst), inst.op1.accept(this), inst.op2.accept(this), "jumpIfNotEqual 1"));
        nasm.ajouteInst(new NasmJne(null, inst.result.accept(this), "jumpIfNotEqual 2"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfLess inst) {
        nasm.ajouteInst(new NasmCmp(null, inst.op1.accept(this), inst.op2.accept(this), "JumpIfLess 1"));
        nasm.ajouteInst(new NasmJl(getLabel(inst), inst.result.accept(this), "JumpIfLess 2"));
        return null;
    }


    public NasmOperand visit(C3aInstAdd inst) {
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(getLabel(inst), dest, oper1, ""));
        nasm.ajouteInst(new NasmAdd(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstSub inst) {
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(getLabel(inst), dest, oper1, ""));
        nasm.ajouteInst(new NasmSub(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstMult inst) {
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(getLabel(inst), dest, oper1, ""));
        nasm.ajouteInst(new NasmMul(null, dest, oper2, ""));
        return null;
    }
    @Override
    public NasmOperand visit(C3aInstDiv inst) {
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);
        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);

        var register = oper2 instanceof NasmConstant
                ? nasm.newRegister()
                : oper2;
        nasm.ajouteInst(new NasmMov(getLabel(inst), eax, oper1, ""));
        if (oper2 instanceof NasmConstant) {
            nasm.ajouteInst(new NasmMov(getLabel(inst), register, oper2, ""));
            nasm.ajouteInst(new NasmDiv(null, register, ""));
        }
        else {
            nasm.ajouteInst(new NasmDiv(null, oper2, ""));
        }
        nasm.ajouteInst(new NasmMov(null, dest, eax, ""));

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
        var base = oper.item.portee == tableGlobale
                ? new NasmLabel(oper.item.identif)
                : ebp;

        if (oper.item.portee == tableGlobale && oper.item.getTaille() == 1)
            return new NasmAddress(base);

        var direction = oper.item.isParam || oper.item.portee == tableGlobale ? '+' : '-';
        NasmConstant offset;

        if (oper.item.getTaille() > 1)
            offset = new NasmConstant(((C3aConstant) oper.index).val);
        else
            offset = new NasmConstant(oper.item.isParam
                    ? 2 + (oper.item.portee.nbArg() - oper.item.getAdresse())
                    : 1 + oper.item.getAdresse());

        return new NasmAddress(base, direction, offset);
    }

    @Override
    public NasmOperand visit(C3aFunction oper) {
        return new NasmLabel(oper.toString());
    }

    @Override
    public NasmOperand visit(C3aInst inst) {
        return null;
    }
}