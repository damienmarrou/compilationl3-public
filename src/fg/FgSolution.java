package fg;

import nasm.Nasm;
import nasm.NasmAddress;
import nasm.NasmInst;
import nasm.NasmOperand;
import nasm.NasmRegister;
import util.graph.Node;
import util.intset.IntSet;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class FgSolution {
    public Nasm nasm;
    public Map<NasmInst, IntSet> use = new HashMap<>();
    public Map<NasmInst, IntSet> def = new HashMap<>();
    public Map<NasmInst, IntSet> in = new HashMap<>();
    public Map<NasmInst, IntSet> out = new HashMap<>();
    int nbIteration = 0;
    Fg fg;

    public FgSolution(Nasm nasm, Fg fg) {
        this.nasm = nasm;
        this.fg = fg;
        nasm.listeInst.forEach(this::initialisation);
        makeInOut();
    }

    private void initialisation(NasmInst inst) {
        in.put(inst, new IntSet(nasm.getTempCounter()));
        out.put(inst, new IntSet(nasm.getTempCounter()));
        use.put(inst, new IntSet(nasm.getTempCounter()));
        def.put(inst, new IntSet(nasm.getTempCounter()));

        if (inst.srcUse)
            addOperand(inst.source, use.get(inst));
        if (inst.destUse)
            addOperand(inst.destination, use.get(inst));
        if (inst.destDef)
            addOperand(inst.destination, def.get(inst));
    }

    private void addOperand(NasmOperand operand, IntSet intSet) {
        if (operand.isGeneralRegister())
            intSet.add(((NasmRegister) operand).val);

        if (operand instanceof NasmAddress) {
            var address = (NasmAddress) operand;
            if (address.base.isGeneralRegister())
                intSet.add(((NasmRegister) address.base).val);
            if (address.offset != null && address.offset.isGeneralRegister())
                intSet.add(((NasmRegister) address.offset).val);
        }
    }

    private void makeInOut() {
        boolean isStable;
        Node[] nodes = fg.graph.nodeArray();

        do {
            isStable = true;
            nbIteration++;

            for (Node node : nodes) {
                NasmInst currentInst = fg.node2Inst.get(node);

                IntSet in = this.in.get(currentInst).copy();
                IntSet out = this.out.get(currentInst).copy();

                this.in.replace(currentInst, use.get(currentInst).copy().union(this.out.get(currentInst).copy().minus(def.get(currentInst))));

                if (node.succ() != null)
                    for (var successor : node.succ())
                        this.out.get(currentInst).union(this.in.get(fg.node2Inst.get(successor)));

                if (isStable)
                    isStable = in.equal(this.in.get(currentInst)) && out.equal(this.out.get(currentInst));
            }
        } while (!isStable);
    }


    public void affiche(String baseFileName) {
        String fileName;
        PrintStream out = System.out;

        if (baseFileName != null) {
            try {
                baseFileName = baseFileName;
                fileName = baseFileName + ".fgs";
                out = new PrintStream(fileName);
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        out.println("iter num = " + nbIteration);
        for (NasmInst nasmInst : this.nasm.listeInst) {
            out.println("use = " + this.use.get(nasmInst) + " def = " + this.def.get(nasmInst) + "\tin = " + this.in.get(nasmInst) + "\t \tout = " + this.out.get(nasmInst) + "\t \t" + nasmInst);
        }
    }
}

    
