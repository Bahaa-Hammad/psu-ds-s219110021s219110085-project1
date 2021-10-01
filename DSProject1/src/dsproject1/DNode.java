package dsproject1;

public class DNode <T>{
     T value; // Object instance
     DNode<T> next; // Address for the next node in DLL
     DNode<T> prev; // Address for the previous node in DLL (Only in DLL)

    //constructors:
    public DNode(T value){
        this.value = value;
    }

    //Getters & Setters:
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DNode<T> getNext() {
        return next;
    }

    public void setNext(DNode<T> next) {
        this.next = next;
    }

    public DNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DNode<T> prev) {
        this.prev = prev;
    }
}
