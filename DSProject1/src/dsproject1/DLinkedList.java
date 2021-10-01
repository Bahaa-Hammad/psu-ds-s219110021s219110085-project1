package dsproject1;

public class DLinkedList <T> {
    //Attributes of DLL:
    private DNode<T> head;
    private DNode<T> tail;
    int size;

    // Methods From Week 3 Slides \\

    //Methods:

    /*
        Insert Methods:
            Insert at head
            Insert at tail
            Insert at position i (between node i-1 and i)
     */

    //Insert at head:
    public void insertAtHead(T val){
        DNode<T> tmp = new DNode(val);
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
    public void insertAtTail(T val){
        DNode<T> tmp = new DNode(val);
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
    public void insertAtPos(T val, int pos){
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
        DNode<T> tmp = new DNode<>(val);

        //Find the node i by traversing the list starting from head:
        DNode before = head;
        for (int i = 0; i < pos - 1; i++) { // pos - 1 will arrive at the node before pos
            before = before.getNext();
        }
        DNode<T> after = before.getNext(); // the node after the pos, for simplification.

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
    public T deleteHead(){
        T val = head.getItem(); // To return the value for the user.

        //Edge Case: Empty List (BEFORE we delete the head)
        if (size == 0){
            return null;
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

    public T deleteTail(){ // Much faster than SLL
        T val = tail.getItem(); // To return the value for the user.

        //Edge Case: Empty list (BEFORE the tail is deleted).
        if (size == 0){
            return null;
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

    public T deletePos(int pos){
        // Check range:
        if (pos < size || pos > size-1){
            throw new IndexOutOfBoundsException("Invalid Pos: " + pos);
        }
        // To delete a node at i: we need three nodes:
        DNode<T> before = head; // Used to traverse the list until (pos - 1) = (previous node).
        DNode<T> current = null; // Used to store the node needed to be deleted.
        DNode<T> after = null; // The node after the node that is needed to be deleted.

        //Traverse the list:
        for (int i = 0; i < pos - 1 ; i++) {
            before= before.getNext();
        }
        //Set the values for the nodes:
        current = before.getNext();
        after = before.getNext();

        T val = current.getItem(); // To return it to the user.

        // Change References:
        before.setNext(after);
        after.setPrev(before);
        size --;

        return val; // value of the deleted object instance.
    }

    // Printing DLL:
    public void printList(){
        System.out.println("List Head: "+head.getItem()); // head is the node, We want to print the object instance inside the node (item)
        System.out.println("List Tail: " + tail.getItem());
        System.out.println("List size: " + this.size);
        System.out.println("=======================");
        DNode<T> current = head;
        for (int i = 0; i < size-1; i++) {
            System.out.print(current.getItem() + "==>");   // ==: Doubly
            current = current.getNext();
        }
        System.out.println(tail.getItem() + "==>Null");
    }



    // Bahaa Practice Extra\\

    public static int getCount(DNode head) {

        int size = 0;
        DNode tmp = head;
        while(tmp != null){
            size++;
            tmp = tmp.getNext();
        }
        return size;
    }

    //Function to check whether two linked lists are identical or not.
    public boolean isIdentical (DNode head1, DNode head2){
        DNode list1 = head1;
        DNode list2 = head2;
        int size1 = 0;
        int size2 = 0;

        while(list1 != null && list2 != null){
            if(list1.getItem() != list2.getItem()){
                return false;
            }
            list1 = list1.getNext();
            list2 = list2.getNext();
            size1++;
            size2++;
        }

        if(size1 != size2){
            return false;
        }
        return true;
    }

    //Return the sum of last k nodes
    public int sum(int k){
        DNode tmp = tail;

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum = sum + (int) tmp.getItem();
            tmp = tmp.getPrev();
        }

        return sum;
    }

    /*
         You don't need to read input or print anything. Complete the function count()
         which takes the head of the link list and search_for i.e- the key as input
         parameters and returns the count of occurrences of the given key
    */
    public static int count(DNode head, int search_for) {
        int counter = 0;
        DNode current = head;

        while(current != null){
            if( (int) current.getItem() == search_for ){
                counter++;
            }
            current = current.getNext();
        }
        return counter;
    }

    /*
        Given a doubly linked list, rotate the linked list counter-clockwise by P nodes.
         Here P is a given positive integer and is smaller than the count of nodes(N) in a linked list.
        Input:
        2 (index of where to rotate)
        1 2 3 4 5 6

        Output:
        3 4 5 6 1 2
     */

    public void rotateP (int p){

        DNode current = head;
        DNode tmp = null;

        for(int i =0; i < p-1; i++){
            current = current.getNext();
        }
        tail = current; // last element before the rotation is the tail of the list
        tmp = current.getNext();
        current.setNext(null);
        current = tmp;
        current.setPrev(null);

        DNode last = current;
        while(last.getNext() != null){
            last = last.getNext();
        }


        last.setNext(head);
        head.setPrev(null);
        head = current;


    }

    /*
   Question 5 (DLL):
       Describe in detail an algorithm for reversing a doubly linked list L using only a constant amount of
       additional space (without making a new list). Write method reverse() that reverses all contents in the
       list.
    */
    public void reverse(){
        /*
            To reverse a DLL:
            Each node in DLL must swap next and prev fields. (When swapping two variables, a tmp variable is needed)
            Finally: swap between head and tail (When swapping two variables, a tmp variable is needed).
         */

        DNode<T> current = head;
        DNode<T> tmp = null;

        while (current != null){
            // Swap between prev & next
            tmp = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(tmp);

            // Make current move to the next node in the list
            current = current.getPrev();  //  (After Swap)current.prev = current.next (Before the swap)
        }

        // Swap between head and tail:
        tmp = head;
        head = tail;
        tail = tmp;
    }
}
