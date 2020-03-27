package util.graph;

import util.intset.IntSet;

import java.util.Stack;

public class ColorGraph {
    static int NOCOLOR = -1;
    public int R; //Nombre de sommets
    public int K; //Nombre de couleurs
    public IntSet remove; //Sommets enlevés
    public IntSet overflow; //Sommets qui débordent
    public int[] color; //Tableau des couleurs
    public Node[] int2Node; //Tableau pour accéder à un sommet
    private Stack<Integer> stack; //pile

    public ColorGraph(Graph G, int K, int[] phi) {
        this.K = K;
        stack = new Stack<>();
        R = G.nodeCount();
        color = new int[R];
        remove = new IntSet(R);
        overflow = new IntSet(R);
        int2Node = G.nodeArray();
        for (int v = 0; v < R; v++) {
            int preColor = phi[v];
            if (preColor >= 0 && preColor < K)
                color[v] = phi[v];
            else
                color[v] = NOCOLOR;
        }
        color();
    }

    public void selection() {
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            remove.remove(vertex);
            if (color[vertex] == NOCOLOR) {
                IntSet nc = colorOfNeighboor(vertex);
                if (nc.getSize() != K) {
                    color[vertex] = pickColor(nc);
                }
            }
            color[vertex] = pickColor(colorOfNeighboor(vertex));
        }
    }

    private int pickVertex() {
        for (int vertex = 0; vertex < R; vertex++) {
            if (!stack.contains(vertex)) return vertex;
        }
        return -1;
    }


    public IntSet colorOfNeighboor(int vertex) {
        IntSet colorSet = new IntSet(K);
        NodeList nodeList = int2Node[vertex].pred();
        while (nodeList != null) {
            if (!remove.isMember(nodeList.head.mykey) && color[nodeList.head.mykey] != NOCOLOR) {
                colorSet.add(color[nodeList.head.mykey]);
            }
            nodeList = nodeList.tail;
        }
        return colorSet;

        /*
        if (int2Node[vertex].succ() != null)
            for (Node successor : int2Node[vertex].succ())
                if (color[successor.mykey] != NOCOLOR) colorSet.add(color[successor.mykey]);
        return colorSet;*/
    }


    public int pickColor(IntSet colorSet) {
        for (int i = 0; i < K; i++)
            if (!colorSet.isMember(i)) return i;
        return NOCOLOR;
    }

    public int nbNeighboor(int vertex) {
        //int count = int2Node[vertex].outDegree();
        int count = 0;
        NodeList nodeList = int2Node[vertex].pred();
        while (nodeList != null) {
            if (!remove.isMember(nodeList.head.mykey)) {
                count++;
            }
            nodeList = nodeList.tail;
        }


        /*if (int2Node[vertex].succs != null)
            for (Node successor : int2Node[vertex].succ())
                if (remove.isMember(successor.mykey)) count--;*/
        return count;

    }

    public void simplify() {
        boolean isUpdated = true;

        while (stack.size() != R && isUpdated) {
            isUpdated = false;
            for (int vertex = 0; vertex < R; vertex++) {
                if (!stack.contains(vertex)) {
                    if (nbNeighboor(vertex) < K && color[vertex] == NOCOLOR) {
                        remove.add(vertex);
                        stack.push(vertex);
                        isUpdated = true;
                    } /*else {
                        overflow();
                    }*/
                }
                //if (remove.isMember(node.mykey)) continue;
            }
        }
    }

    public ColorGraph overflow() {
        while (stack.size() != R) {
            int vertex = pickVertex();
            stack.push(vertex);
            remove.add(vertex);
            overflow.add(vertex);
            simplify();
        }
        return this;
    }


    public void color() {
        //R = R - (int) IntStream.of(color).filter(c -> c != NOCOLOR).count();//todo refactor
        this.simplify();
        this.overflow();
        this.selection();
    }

    void print() {
        System.out.println("vertex\tcolor");
        for (int i = 0; i < int2Node.length; i++) {
            System.out.println(i + "\t  \t  " + color[i]);
        }
    }

}
