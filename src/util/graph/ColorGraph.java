package util.graph;

import util.intset.IntSet;

import java.util.Stack;
import java.util.stream.IntStream;

public class ColorGraph {
    static int NOCOLOR = -1;
    public Graph G;
    public int R;
    public int K;
    public IntSet remove;
    public IntSet overflow;
    public int[] color;
    public Node[] int2Node;
    private Stack<Integer> stack;

    public ColorGraph(Graph G, int K, int[] phi) {
        this.G = G;
        this.K = K;
        stack = new Stack<Integer>();
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
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* associe une couleur à tous les sommets se trouvant dans la pile */
    /*-------------------------------------------------------------------------------------------------------------*/

    public void selection() {
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            color[vertex] = pickColor(colorOfNeighboor(vertex));
        }
    }

    private int pickVertex() {
        for (int v = 0; v < remove.getSize(); v++)
            if (!remove.isMember(v) && color[v] == NOCOLOR) return v;
        return -1;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* récupère les couleurs des voisins de t */
    /*-------------------------------------------------------------------------------------------------------------*/

    public IntSet colorOfNeighboor(int vertex) {
        IntSet colorSet = new IntSet(K);

        if (int2Node[vertex].succ() != null)
            for (var successor : int2Node[vertex].succ())
                if (color[successor.mykey] != NOCOLOR) colorSet.add(color[successor.mykey]);

        return colorSet;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* recherche une couleur absente de colorSet */
    /*-------------------------------------------------------------------------------------------------------------*/

    public int pickColor(IntSet colorSet) {
        for (int color = 0; color < colorSet.getSize(); ++color)
            if (!colorSet.isMember(color)) return color;
        return NOCOLOR;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* calcule le nombre de voisins du sommet t */
    /*-------------------------------------------------------------------------------------------------------------*/

    public int nbNeighboor(int t) {
        if (int2Node[t].succ() == null) return 0;
        int count = int2Node[t].outDegree();
        for (var successor : int2Node[t].succ())
            if (remove.isMember(successor.mykey)) count--;
        return count;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* simplifie le graphe d'interférence g                                                                        */
    /* la simplification consiste à enlever du graphe les temporaires qui ont moins de k voisins                   */
    /* et à les mettre dans une pile                                                                               */
    /* à la fin du processus, le graphe peut ne pas être vide, il s'agit des temporaires qui ont au moins k voisin */
    /*-------------------------------------------------------------------------------------------------------------*/

    public void simplify() {
        int N = R - (int) IntStream.of(color).filter(c -> c != NOCOLOR).count();//todo faire propre
        boolean isUpdated = true;
        stack = new Stack<>();
        while (stack.size() != N && isUpdated) {
            isUpdated = false;
            for (Node node : int2Node) {
                if (remove.isMember(node.mykey)) continue;
                if (nbNeighboor(node.mykey) < K && color[node.mykey] == NOCOLOR) {
                    remove.add(node.mykey);
                    stack.push(node.mykey);
                    isUpdated = true;
                }
            }
        }
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    public void overflow() {
        while (stack.size() != R) {
            int vertex = pickVertex();
            remove.add(vertex);
            overflow.add(vertex);
            simplify();
        }
    }


    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    public void color() {
        R = R - (int) IntStream.of(color).filter(c -> c != NOCOLOR).count();

        this.simplify();
        this.overflow();
        this.selection();
    }

    void print() {
        System.out.println("vertex\tcolor");
        for (int i = 0; i < R; i++) {
            System.out.println(i + "\t" + color[i]);
        }
    }


}
