package ig;

import fg.FgSolution;
import nasm.Nasm;
import nasm.NasmAddress;
import nasm.NasmOperand;
import nasm.NasmRegister;
import util.graph.ColorGraph;
import util.graph.Graph;
import util.graph.Node;
import util.graph.NodeList;
import util.intset.IntSet;

import java.io.IOException;
import java.io.PrintStream;
import java.util.stream.IntStream;

public class Ig {
    public final FgSolution fgs;
    public final Nasm nasm;
    public final Graph graph = new Graph();
    public Node[] int2Node;

    public Ig(FgSolution fgs) {
        this.fgs = fgs;
        this.nasm = fgs.nasm;
        build();
    }

    public void build() {
        IntStream.range(0, nasm.getTempCounter()).forEach(dontCare -> graph.newNode());
        int2Node = graph.nodeArray();

        for (var inst : nasm.listeInst) {
            createEdges(fgs.in.get(inst));
            createEdges(fgs.out.get(inst));
        }
    }

    private void createEdges(IntSet intSet) {
        for (int from = 0; from < nasm.getTempCounter() - 1; ++from) {
            if (!intSet.isMember(from)) continue;
            for (int to = from + 1; to < nasm.getTempCounter(); ++to) {
                if (!intSet.isMember(to)) continue;
                graph.addEdge(int2Node[from], int2Node[to]);
                graph.addEdge(int2Node[to], int2Node[from]);
            }
        }
    }

    /**
     * get a pre-color for each Temp Register
     *
     * @return an array of colors
     */
    public int[] getPreColoredTemporaries() {
        int[] colors = new int[nasm.getTempCounter()];

        for (var inst : nasm.listeInst) {
            extractColor(inst.source, colors);
            extractColor(inst.destination, colors);
        }
        return colors;
    }

    /**
     * extract the color of operand from colors
     *
     * @param operand the operand we wanted to know color
     * @param colors  contain colors of each operand
     */
    private void extractColor(NasmOperand operand, int[] colors) {
        if (operand == null) return;
        if (operand.isGeneralRegister())
            colors[((NasmRegister) operand).val] = ((NasmRegister) operand).color;

        if (operand instanceof NasmAddress) {
            var address = (NasmAddress) operand;
            if (address.base.isGeneralRegister())
                colors[((NasmRegister) address.base).val] = ((NasmRegister) address.base).color;
            if (address.offset != null && address.offset.isGeneralRegister())
                colors[((NasmRegister) address.offset).val] = ((NasmRegister) address.offset).color;
        }
    }

    /**
     * Create a ColorGraph and set color of all instructions
     * here we set K to 4 because we don't have any way to set it dynamically
     */
    public void allocateRegisters() {
        ColorGraph cg = new ColorGraph(graph, 4, getPreColoredTemporaries());
        int[] colors = cg.color;
        for (var inst : nasm.listeInst) {
            allocateRegister(inst.source, colors);
            allocateRegister(inst.destination, colors);
        }
    }

    private void allocateRegister(NasmOperand operand, int[] colors) {
        if (operand == null) return;
        if (operand.isGeneralRegister())
            ((NasmRegister) operand).colorRegister(colors[((NasmRegister) operand).val]);

        if (operand instanceof NasmAddress) {
            var address = (NasmAddress) operand;
            if (address.base.isGeneralRegister())
                ((NasmRegister) address.base).colorRegister(colors[((NasmRegister) address.base).val]);
            if (address.offset != null && address.offset.isGeneralRegister())
                ((NasmRegister) address.offset).colorRegister(colors[((NasmRegister) address.offset).val]);
        }
    }

    public void print(String baseFileName) {
        String fileName;
        PrintStream out = System.out;

        if (baseFileName != null) {
            try {
                fileName = baseFileName + ".ig";
                out = new PrintStream(fileName);
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        for (int i = 0; i < nasm.getTempCounter(); i++) {
            Node n = this.int2Node[i];
            out.print(n + " : ( ");
            for (NodeList q = n.succ(); q != null; q = q.tail) {
                out.print(q.head.toString());
                out.print(" ");
            }
            out.println(")");
        }
    }
}


