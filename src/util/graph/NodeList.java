package util.graph;

import java.util.Iterator;

public class NodeList implements Iterable<Node> {
  public Node head;
  public NodeList tail;

  public NodeList(Node h, NodeList t) {
    head = h;
    tail = t;
  }

  @Override
  public Iterator<Node> iterator() {
    return new NodeIterator();
  }

  private class NodeIterator implements Iterator<Node> {
    private Node head = NodeList.this.head;
    private NodeList tail = NodeList.this.tail;

    @Override
    public boolean hasNext() {
      return head != null;
    }

    @Override
    public Node next() {
      Node next = head;
      head = tail != null ? tail.head : null;
      tail = tail != null ? tail.tail : null;

      return next;
    }
  }
}



