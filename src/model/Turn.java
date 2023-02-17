package model;

public class Turn {
    private int value;
    private Turn next;
    private Turn prev;

    public Turn(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Turn getNext() {
        return next;
    }

    public void setNext(Turn next) {
        this.next = next;
    }

    public Turn getPrev() {
        return prev;
    }

    public void setPrev(Turn prev) {
        this.prev = prev;
    }
}
