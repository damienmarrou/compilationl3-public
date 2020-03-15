import c3a.*;
import nasm.*;
import ts.Ts;
import ts.TsItemFct;
import ts.TsItemVar;


public class C3a2nasm implements C3aVisitor<NasmOperand> {
    private C3a c3a;
    private Nasm nasm;
    private Ts tableGlobale;
    private TsItemFct currentFct;
    private int nbArgs = -1;

    public C3a2nasm(C3a c3a, Ts table) {
        this.c3a = c3a;
        this.tableGlobale = table;
        this.nasm = new Nasm(table);

        nasm.ajouteInst(new NasmCall(null, new NasmLabel("main"),""));

        NasmRegister ebx = new NasmRegister(Nasm.REG_EBX);
        ebx.colorRegister(Nasm.REG_EBX);
        nasm.ajouteInst(new NasmMov(null,ebx,new NasmConstant(0)," valeur de retour du programme"));


        NasmRegister eax = new NasmRegister(Nasm.REG_EAX);
        eax.colorRegister(Nasm.REG_EAX);
        nasm.ajouteInst(new NasmMov(null,eax,new NasmConstant(1),""));
        nasm.ajouteInst(new NasmInt(null,""));
        for(C3aInst inst : c3a.listeInst){
            inst.accept(this);
        }
    }

    public Nasm getNasm() {
        return nasm;
    }

    @Override
    public NasmOperand visit(C3aInstCall inst) {
        NasmOperand op1 = inst.op1.accept(this);

        nasm.ajouteInst(new NasmCall(null, op1,""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFBegin inst) { //OK
        NasmOperand label = new NasmLabel(inst.val.identif);

        NasmRegister ebp = new NasmRegister(Nasm.REG_EBP);
        ebp.colorRegister(Nasm.REG_EBP);
        nasm.ajouteInst(new NasmPush(label, ebp,"sauvegarde la valeur de ebp"));

        NasmRegister esp = new NasmRegister(Nasm.REG_ESP);
        esp.colorRegister(Nasm.REG_ESP);
        nasm.ajouteInst(new NasmMov(null,ebp,esp,"nouvelle valeur de ebp"));
        nbArgs = inst.val.getNbArgs();
        nasm.ajouteInst(new NasmSub(null,esp,new NasmConstant(4*nbArgs),"allocation des variables locales"));

        nbArgs = inst.val.nbArgs;

        return null;
    }

    @Override
    public NasmOperand visit(C3aInst inst) {
        return null;
    }



    @Override
    public NasmOperand visit(C3aInstRead inst) {
        NasmOperand label = (inst.label != null) ?
                inst.label.accept(this) :
                null;
        NasmOperand op = inst.result.accept(this);

        NasmRegister eax = new NasmRegister(Nasm.REG_EAX);
        eax.colorRegister(Nasm.REG_EAX);
        nasm.ajouteInst(new NasmMov(label,eax,op,""));
        return null;
    }


    @Override
    public NasmOperand visit(C3aInstAffect inst) {
       NasmOperand label = (inst.label != null) ?
                inst.label.accept(this) :
                null;
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand result = inst.result.accept(this);
        String comment = inst.comment;
        nasm.ajouteInst(new NasmMov(label, result, op1, "Affect"));
        return null;
    }


    @Override
    public NasmOperand visit(C3aInstFEnd inst) { //OK
        NasmOperand label = (inst.label != null) ?
                inst.label.accept(this) :
                null;

        NasmRegister ebp = nasm.newRegister();
        ebp.colorRegister(Nasm.REG_EBP);
        NasmRegister esp = new NasmRegister(Nasm.REG_ESP);
        esp.colorRegister(Nasm.REG_ESP);

        nasm.ajouteInst(new NasmAdd(label,esp,new NasmConstant(4*nbArgs),"désallocation des variables locales"));
        nasm.ajouteInst(new NasmPop(null,ebp,"restaure la valeur de ebp"));
        nasm.ajouteInst(new NasmRet(null,""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfEqual inst) {
        /*NasmOperand label = (inst.label != null) ?
                inst.label.accept(this) :
                null;
        NasmOperand address = inst.result.accept(this);
        nasm.ajouteInst(new NasmJe(label,address, inst.comment));
        return null;*/
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        NasmCmp nasmCmp = new NasmCmp(label, oper, oper2, "JumpIfEqual 1");
        nasm.ajouteInst(nasmCmp);

        NasmJe nasmJe = new NasmJe(null, result, "JumpIfEqual 2");
        nasm.ajouteInst(nasmJe);
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {

        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        NasmCmp nasmCmp = new NasmCmp(label, oper, oper2, "on passe par un registre temporaire");
        nasm.ajouteInst(nasmCmp);

        NasmJne nasmJne = new NasmJne(null, result, "jumpIfNotEqual 2");
        nasm.ajouteInst(nasmJne);
        return null;

    }

    @Override
    public NasmOperand visit(C3aInstJumpIfLess inst) {
        NasmOperand label = (inst.label != null) ?
                inst.label.accept(this) :
                null;
        NasmOperand address = inst.result.accept(this);

        NasmOperand oper = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);

        NasmCmp nasmCmp = new NasmCmp(label, oper, oper2, "JumpIfLess 1");
        nasm.ajouteInst(nasmCmp);
        nasm.ajouteInst(new NasmJl(label,address, "JumpIfLess 2"));
        return null;
    }


    @Override
    public NasmOperand visit(C3aInstJump inst) {
        NasmOperand label = (inst.label != null) ?
                inst.label.accept(this) :
                null;
        NasmOperand adresse = inst.result.accept(this);
        nasm.ajouteInst(new NasmJmp(label, adresse,"Jump"));

        return null;

    }

    @Override
    public NasmOperand visit(C3aInstParam inst) {
        NasmOperand op = inst.op1.accept(this);
        nasm.ajouteInst(new NasmPush(null,op, "Param"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstReturn inst) {
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstWrite inst) {
        NasmOperand label = (inst.label != null) ?
                inst.label.accept(this) :
                null;

        NasmRegister eax = new NasmRegister(Nasm.REG_EAX);
        eax.colorRegister(Nasm.REG_EAX);

        NasmOperand op1 = inst.op1.accept(this);

        nasm.ajouteInst(new NasmMov(label, eax, op1,"Write 1"));
        nasm.ajouteInst(new NasmCall(null, new NasmLabel("iprintLF"),"Write 2"));

        return null;
    }

    @Override
    public NasmOperand visit(C3aConstant oper) {
        return new NasmConstant(oper.val);
    }

    @Override
    public NasmOperand visit(C3aLabel oper) {
        return new NasmLabel(oper.toString());
       // return null;
    }

    @Override
    public NasmOperand visit(C3aTemp oper) {
        return new NasmRegister(oper.num);
    }

    @Override
    public NasmOperand visit(C3aVar oper) {
        NasmRegister ebp = new NasmRegister(Nasm.REG_EBX);
        ebp.colorRegister(Nasm.REG_EBX);

        TsItemVar fct = oper.item;
        int adres = 8 + 4 *fct.portee.nbArg() - 4 * fct.adresse;

       // return new NasmAddress(ebp,"+",adres);
        return  null;
    }

    @Override
    public NasmOperand visit(C3aFunction oper) {
        return new NasmLabel( oper.toString() );
        //return null;
    }


    @Override
    public NasmOperand visit(C3aInstAdd inst) { //OK
        NasmOperand label = (inst.label != null) ?
                inst.label.accept(this) :
                null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmAdd(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstSub inst) { //OK
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

       /* NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
*/
        //nasm.ajouteInst(new NasmMov(label, oper2, eax, ""));
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmSub(null, dest, oper2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstMult inst) { //OK
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);
        nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
        nasm.ajouteInst(new NasmMul(null, dest, oper2, ""));

        return null;
    }

    @Override
    public NasmOperand visit(C3aInstDiv inst) { //OK
        NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
        NasmOperand oper1 = inst.op1.accept(this);
        NasmOperand oper2 = inst.op2.accept(this);
        NasmOperand dest = inst.result.accept(this);

        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);

        nasm.ajouteInst(new NasmMov(label, eax, oper1, ""));

        //Pour correspondre aux fichiers de ref
       // nasm.setTempCounter(4);
        NasmRegister register = nasm.newRegister();

        nasm.ajouteInst(new NasmMov(label, register, oper2, ""));
        nasm.ajouteInst(new NasmDiv(null, register, ""));

        //nasm.setTempCounter(0);
        NasmRegister newRegister = nasm.newRegister();
        nasm.ajouteInst(new NasmMov(label, newRegister, eax, ""));

        return null;
    }

}
