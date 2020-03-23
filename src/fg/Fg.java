package fg;

import nasm.*;
import util.graph.Graph;
import util.graph.Node;
import util.graph.NodeList;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Fg implements NasmVisitor<Void> {
    public Nasm nasm;
    public Graph graph;
    Map<NasmInst, Node> inst2Node;
    Map<Node, NasmInst> node2Inst;
    Map<String, NasmInst> label2Inst;

    public Fg(Nasm nasm) {
        this.nasm = nasm;
        this.inst2Node = new HashMap<NasmInst, Node>();
        this.node2Inst = new HashMap<Node, NasmInst>();
        this.label2Inst = new HashMap<String, NasmInst>();
        this.graph = new Graph();
        for (NasmInst nasmInst : nasm.listeInst) {
            // creer les sommet
            Node node = graph.newNode();
            inst2Node.put(nasmInst, node);
            node2Inst.put(node, nasmInst);
            // Si a un label
            if (nasmInst.label != null) {
                label2Inst.put(nasmInst.label.toString(), nasmInst);
            }
        }
    }

    public void affiche(String baseFileName) {
        String fileName;
        PrintStream out = System.out;

        if (baseFileName != null) {
            try {
                baseFileName = baseFileName;
                fileName = baseFileName + ".fg";
                out = new PrintStream(fileName);
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        for (NasmInst nasmInst : nasm.listeInst) {
            Node n = this.inst2Node.get(nasmInst);
            out.print(n + " : ( ");
            for (NodeList q = n.succ(); q != null; q = q.tail) {
                out.print(q.head.toString());
                out.print(" ");
            }
            out.println(")\t" + nasmInst);
        }
    }

    private void addArcToNextNode(NasmInst inst) {
        Node actualNode = inst2Node.get(inst);
        Node nextNode = graph.nodes().head;
        NodeList succesorOfNextNode = graph.nodes().tail;
        while (actualNode != nextNode) {//todo remplacer par un for et print un message d'erreur si pas de correspondance
            nextNode = succesorOfNextNode.head;
            succesorOfNextNode = succesorOfNextNode.tail;
        }
        nextNode = succesorOfNextNode.head;
        graph.addEdge(actualNode, nextNode);
    }

    private void addLabelToArcNode(NasmInst inst) {
        if (label2Inst.containsKey(inst.address)) {
            graph.addEdge(inst2Node.get(inst), inst2Node.get(label2Inst.get(inst.address)));
        }
        return;
    }


    public Void visit(NasmAdd inst) {
        addArcToNextNode(inst);
        return null;
    }


    public Void visit(NasmCall inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmDiv inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmJe inst) {
        addArcToNextNode(inst);
        addLabelToArcNode(inst);
        return null;
    }

    public Void visit(NasmJle inst) {
        addArcToNextNode(inst);
        addLabelToArcNode(inst);
        return null;
    }

    public Void visit(NasmJne inst) {
        addArcToNextNode(inst);
        addLabelToArcNode(inst);
        return null;
    }

    public Void visit(NasmMul inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmOr inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmCmp inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmInst inst) {//todo a voir
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmJge inst) {
        addArcToNextNode(inst);
        addLabelToArcNode(inst);
        return null;
    }

    public Void visit(NasmJl inst) {
        addArcToNextNode(inst);
        addLabelToArcNode(inst);
        return null;
    }

    public Void visit(NasmNot inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmPop inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmRet inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmXor inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmAnd inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmJg inst) {
        addArcToNextNode(inst);
        addLabelToArcNode(inst);
        return null;
    }

    public Void visit(NasmJmp inst) {
        addArcToNextNode(inst);
        addLabelToArcNode(inst);
        return null;
    }

    public Void visit(NasmMov inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmPush inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmSub inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmEmpty inst) {
        addArcToNextNode(inst);
        return null;
    }

    public Void visit(NasmAddress operand) {

        return null;
    }

    public Void visit(NasmConstant operand) {
        return null;
    }

    public Void visit(NasmLabel operand) {
        return null;
    }

    public Void visit(NasmRegister operand) {
        return null;
    }


}
