package model;

public class BankTurns {
    private Turn root;
    private Turn tail;

    public BankTurns() {
        this.root = null;
        this.tail = null;
    }

    /*
     * This function creates a node at the end, if the root is null
     * then the new node will be the root
     */
    public void addNodeAtEnd(int value) {
        Turn node = new Turn(value);

        if (root == null) {
            root = node;
            tail = node;
        } else {
            addNodeAtEnd(value, node);
        }

    }

    private void addNodeAtEnd(int value, Turn node) {
        tail.setNext(node);
        node.setPrev(tail);
        node.setNext(root);
        tail = node;
    }

    /*
     * This function returns the first element that matchs the value.
     * If no element matches the value, the function will return a null.
     */
    public Turn searchNodeByValue(Turn pointer, int value) {
        if (pointer == null) {
            return null;
        }

        if (pointer.getValue() != value) {
            pointer = searchNodeByValue(pointer.getNext(), value);
        }

        return pointer;
    }

    /*
     * This function will always return the node that is pointing to the target
     * node. As I am working with linear nodes, there is no problem with it.
     * if the last node .getNext() is null, that means that it is the last item
     * so the value was not found in the graf
     */
    public Turn searchNodeByEdge(Turn pointer, int value) {
        if (pointer.getNext() == null) {
            return null;
        }

        if (pointer.getNext().getValue() != value) {
            pointer = searchNodeByEdge(pointer.getNext(), value);
        }

        return pointer;
    }

    /*
     * This function deletes a node by searching its previous node and connecting
     * this previous node with
     * the next node of the target.
     * 
     * If the target is the root, it will delete and turn the next node as the new
     * root
     */
    public void deleteNodeByValue(Turn pointer, int value) {
        Turn before = searchNodeByEdge(pointer, value);
        Turn actual = searchNodeByValue(pointer, value);
        Turn next = before.getNext().getNext();

        if (actual == before) {
            root.setNext(null);
            root.setPrev(null);
            tail.setNext(null);
            tail.setPrev(null);
            root = null;
            tail = null;
            return;
        }

        if (actual == tail) {
            tail = next;
        } 

        if (actual == root) {
            root = next;
        }

        before.setNext(next);
        next.setPrev(before);
        actual.setNext(null);
        actual.setPrev(null);
    }

    public Turn getRoot() {
        return root;
    }

    public Turn getTail() {
        return tail;
    }
}
