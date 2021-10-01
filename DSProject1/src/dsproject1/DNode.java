package dsproject1;

public class DNode {
     int value; // Object instance
     DNode next; // Address for the next node in DLL
     DNode prev; // Address for the previous node in DLL (Only in DLL)

    //constructors:
    public DNode(int value){
        this.value = value;
    }

    //Getters & Setters:
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DNode getNext() {
        return next;
    }

    public void setNext(DNode next) {
        this.next = next;
    }

    public DNode getPrev() {
        return prev;
    }

    public void setPrev(DNode prev) {
        this.prev = prev;
    }
}
