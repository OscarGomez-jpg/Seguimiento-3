package model;

public class DoubleLinkedList {
    private Node root;
    private Node tail;

    public DoubleLinkedList() {
        this.root = null;
        this.tail = null;
    }
    
    /*
     * This function creates a node at the end, if the root is null
     * then the new node will be the root
     */
    public void addNodeAtEnd(int value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
        }

        tail = node;
    }

    /*
     * This function works but cannot resolve itself when a node is null
     */
    public void addNodeAfter(int value, int prevNode) {
        Node node = new Node(value);
        Node before = searchNodeByValue(root, prevNode);
        if (before != null) {
            node.setNext(before.getNext());
            node.setPrev(before);
            before.setNext(node);
        }
    }

    /*
     * This function sets a new node as the root of the list
     */
    public void addNodeToStart(int value) {
        Node node = new Node(value);

        if (root != null) {
            root.setPrev(node);
            node.setNext(root);
        }

        root = node;
    }

    /*
     * This function prints the graff
     */
    public void printGraf() {
        printGraf(root);
    }

    private void printGraf(Node pointer) {
        if (pointer != null) {
            System.out.print("Value: " + pointer.getValue());

            if (pointer.getNext() != null) {
                System.out.print(" Next: " + pointer.getNext().getValue());
            }

            if (pointer != root) {
                System.out.println(" Prev: " + pointer.getPrev().getValue());
            } else {
                System.out.print("\n");
            }

            printGraf(pointer.getNext());
        }

        System.out.println("");
    }

    /*
     * This function returns the first element that matchs the value.
     * If no element matches the value, the function will return a null.
     */
    public Node searchNodeByValue(Node pointer, int value) {
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
    public Node searchNodeByEdge(Node pointer, int value) {
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
    public void deleteNodeByValue(Node pointer, int value) {
        Node before = searchNodeByEdge(pointer, value);
        if (before == null) {
            if (root.getNext() == null) {
                root = null;
            } else {
                root = root.getNext();
            }
        } else {
            before.setNext(before.getNext().getNext());
            before.getNext().setPrev(before);
        }
    }

    public Node getRoot() {
        return root;
    }
}
