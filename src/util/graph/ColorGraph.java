package util.graph;

import util.intset.IntSet;

import java.util.Stack;
import java.util.stream.IntStream;

public class ColorGraph {
    static int NOCOLOR = -1;
    public Graph G;
    public int R;
    public int K;
    public IntSet enleves;
    public IntSet deborde;
    public int[] couleur;
    public Node[] int2Node;
    private Stack<Integer> pile;

    public ColorGraph(Graph G, int K, int[] phi) {
        this.G = G;
        this.K = K;
        pile = new Stack<Integer>();
        R = G.nodeCount();
        couleur = new int[R];
        enleves = new IntSet(R);
        deborde = new IntSet(R);
        int2Node = G.nodeArray();
        for (int v = 0; v < R; v++) {
            int preColor = phi[v];
            if (preColor >= 0 && preColor < K)
                couleur[v] = phi[v];
            else
                couleur[v] = NOCOLOR;
        }
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* associe une couleur à tous les sommets se trouvant dans la pile */
    /*-------------------------------------------------------------------------------------------------------------*/

    public void selection() {
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* récupère les couleurs des voisins de t */
    /*-------------------------------------------------------------------------------------------------------------*/

    public IntSet couleursVoisins(int t) {
        return null;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* recherche une couleur absente de colorSet */
    /*-------------------------------------------------------------------------------------------------------------*/

    public int choisisCouleur(IntSet colorSet) {
        return 0;
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* calcule le nombre de voisins du sommet t */
    /*-------------------------------------------------------------------------------------------------------------*/

    public int nbVoisins(int t) {
        return int2Node[t].len(int2Node[t].adj());
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* simplifie le graphe d'interférence g                                                                        */
    /* la simplification consiste à enlever du graphe les temporaires qui ont moins de k voisins                   */
    /* et à les mettre dans une pile                                                                               */
    /* à la fin du processus, le graphe peut ne pas être vide, il s'agit des temporaires qui ont au moins k voisin */
    /*-------------------------------------------------------------------------------------------------------------*/

    public void simplification() {//todo a finir d'implémenter
        int N = R - (int) IntStream.of(couleur).filter(c -> c != NOCOLOR).count();
        boolean modif = true;
        pile = new Stack<>();
        while (pile.size() != N && modif) {
            modif = false;
            for (Node node : int2Node) {
                if (enleves.isMember(node.mykey)) continue;
                if (nbVoisins(node.mykey) < K && couleur[node.mykey] == NOCOLOR) {
                    enleves.add(node.mykey);
                    pile.push(node.mykey);
                    modif = true;
                }
            }
        }
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    public void debordement() {
        deborde = new IntSet(R);
      /*  while (pile.size() != R){

        }*/
    }


    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    public void coloration() {
        this.simplification();
        this.debordement();
        this.selection();
    }

    void affiche() {
        System.out.println("vertex\tcolor");
        for (int i = 0; i < R; i++) {
            System.out.println(i + "\t" + couleur[i]);
        }
    }


}
