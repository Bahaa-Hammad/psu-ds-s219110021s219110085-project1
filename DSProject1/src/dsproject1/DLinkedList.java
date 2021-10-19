package dsproject1;

public class DLinkedList {
    //Attributes of DLL:
     DNode head;
     DNode tail;
     int size;

    //Methods:

    /*
        Insert Methods:
            Insert at head
            Insert at tail
            Insert at position i (between node i-1 and i)
     */

    //Insert at head:
    public void insertAtHead(int val){
        DNode tmp = new DNode(val);
        tmp.setNext(head);
        tmp.setPrev(null);

        //Edge Case:
        if (head != null){
            head.setPrev(tmp);
        }
        head = tmp;

        //Edge Case: Empty list
        if (size == 0){
            tail = head;
        }
        size++;
    }

    // Insert At Tail:
    public void insertAtTail(int val){
        DNode tmp = new DNode(val);
        tmp.setNext(null);
        tmp.setPrev(tail);

        //Edge Case:
        if (tail != null){
            tail.setNext(tmp);
        }

        tail = tmp;

        //Edge Case: Empty list
        if (size == 0){
            head = tail;
        }
        size++;
    }

    //Insert at position i (between node i-1 and i):
    public void insertAtPos(int val, int pos){
        //Check Range:
        if (pos < size || pos > size-1){
            throw new IndexOutOfBoundsException("Invalid Index: " + pos);
        }

        //Shortcut Cases:
        if (pos == 0){
            insertAtHead(val);
            return;
        }
        if (pos == size-1){
            insertAtTail(val);
            return;
        }

        // tmp node:
        DNode tmp = new DNode(val);

        //Find the node i by traversing the list starting from head:
        DNode before = head;
        for (int i = 0; i < pos - 1; i++) { // pos - 1 will arrive at the node before pos
            before = before.getNext();
        }
        DNode after = before.getNext(); // the node after the pos, for simplification.

        /*
            Current visualization of the process:
            pos = 2
            Head -> DNode -> DNode -> DNode -> tail
                   (before)  (after)
                         \tmp/
         */

        // insert the tmp node to the list: (Always start changing variables for the new node first)
        tmp.setPrev(before);
        tmp.setNext(after);

        //Change the nodes in the list:
        before.setNext(tmp);
        after.setPrev(tmp);

        size++;
    }

     /*
        Deleting Methods:
        - Delete Head
        - Delete Tail
        - Delete At Pos i
     */

    // Delete Head:
    public int deleteHead(){
        int val = head.getValue(); // To return the value for the user.

        //Edge Case: Empty List (BEFORE we delete the head)
        if (size == 0){
            throw new NullPointerException("List is empty");
        }
        head = head.getNext();
        size--; // Must Decrement immediately after setting the new head(Critical for the next Edge Case).

        //Edge Case: Empty List (AFTER we delete the head)
        if (size == 0){
            head = tail = null;
        }
        else{
            head.setPrev(null);
        }
        return val; // Value of item in the node.
    }

    // Delete Tail:

    public int deleteTail(){ // Much faster than SLL
        int val = tail.getValue(); // To return the value for the user.

        //Edge Case: Empty list (BEFORE the tail is deleted).
        if (size == 0){
            throw new NullPointerException("List is empty");
        }
        tail = tail.getPrev();
        size --; // Must Decrement immediately after setting the new tail,(Critical for the next Edge Case).


        //Edge Case: Empty list (AFTER the tail is deleted).
        if (size == 0){
            head = tail = null;
        }
        else{
            tail.setNext(null);
        }
        return val;
    }

    // Delete node i:

    public int deletePos(int pos){
        // Check range:
        if (pos < size || pos > size-1){
            throw new IndexOutOfBoundsException("Invalid Pos: " + pos);
        }
        // To delete a node at i: we need three nodes:
        DNode before = head; // Used to traverse the list until (pos - 1) = (previous node).
        DNode current = null; // Used to store the node needed to be deleted.
        DNode after = null; // The node after the node that is needed to be deleted.

        //Traverse the list:
        for (int i = 0; i < pos - 1 ; i++) {
            before= before.getNext();
        }
        //Set the values for the nodes:
        current = before.getNext();
        after = before.getNext();

        int val = current.getValue(); // To return it to the user.

        // Change References:
        before.setNext(after);
        after.setPrev(before);
        size --;

        return val; // value of the deleted object instance.
    }

    // Printing DLL:
    public void printList(){
        System.out.println("List Head: " + (head==null?null:head.value)); // head is the node, We want to print the object instance inside the node (item)
        System.out.println("List Tail: " + (tail==null?null:tail.value));
        System.out.println("List size: " + this.size);
        System.out.println("=======================");
        DNode current = head;
        for (int i = 0; i < size-1; i++) {
            System.out.print(current.getValue() + "->");
            current = current.getNext();
        }
        System.out.println(tail == null? null: tail.value);
    }

    public String printBigInt(){
        DNode current = head;
        String total = "";
        String value;

        // Adding the head without checking because deleting the leading zeros would not affect the number.
        total = total.concat((Integer.toString(current.value)));
        current = current.next;

        for (int i = 0; i < size - 1; i++) {
             value = (Integer.toString(current.value));

             // Preventing Deleting Leading Zeros in nodes:
             if (value.length() == 2 || (value.length() == 1)){ // Adding the leading zeros
                value = String.format("%03d", current.value);
            }
            total = total.concat(value);
            current = current.next;
        }
       return total;
    }
}
