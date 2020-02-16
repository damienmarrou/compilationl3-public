package c3a;

public interface C3aVisitor<T> {
    T visit(C3aInstAdd inst);

    T visit(C3aInstCall inst);

    T visit(C3aInstFBegin inst);

    T visit(C3aInst inst);

    T visit(C3aInstJumpIfLess inst);

    T visit(C3aInstMult inst);

    T visit(C3aInstRead inst);

    T visit(C3aInstSub inst);

    T visit(C3aInstAffect inst);

    T visit(C3aInstDiv inst);

    T visit(C3aInstFEnd inst);

    T visit(C3aInstJumpIfEqual inst);

    T visit(C3aInstJumpIfNotEqual inst);

    T visit(C3aInstJump inst);

    T visit(C3aInstParam inst);

    T visit(C3aInstReturn inst);

    T visit(C3aInstWrite inst);

    T visit(C3aConstant oper);

    T visit(C3aLabel oper);

    T visit(C3aTemp oper);

    T visit(C3aVar oper);

    T visit(C3aFunction oper);
}
