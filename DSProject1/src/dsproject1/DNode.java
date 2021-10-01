package dsproject1;

public class DNode <T>{
    private T item; // Object instance
    private DNode<T> next; // Address for the next node in DLL
    private DNode<T> prev; // Address for the previous node in DLL (Only in DLL)

    //constructors:
    public DNode(T item){
        this.item = item;
    }

    //Getters & Setters:
    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
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
