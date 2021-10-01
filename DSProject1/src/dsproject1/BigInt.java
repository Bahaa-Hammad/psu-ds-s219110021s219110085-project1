
package dsproject1;

// Wrapper Class
public class BigInt {

    //BigInt attributes
    boolean positive;
    final int blockSize = 3;
    DLinkedList representation = new DLinkedList(); // Doubly linked List


    //Constructions

    public BigInt(){
        DNode<Integer> tmp = new DNode<>(0);
    }

    public BigInt(String num){
        // Check if positive & delete the sign
        if(num.charAt(0) == '+'){
            this.positive = true;
            num = num.substring(1); // Strings are immutable
        }
        else if(num.charAt(0) == '-'){
            this.positive = false;
            num = num.substring(1); // Strings are immutable
        }else{ // Assuming not putting a sign means it is true.
            this.positive = true;
        }

        // Splitting string into blocks and storing it in the representation list

        int loop = (num.length())/(blockSize); // The number of blocks that the string will be cut into.
        int remainderBlocks = num.length() % blockSize; // Number of blocks that does not have the same number of digits.
        for (int i = 0; i < loop; i++) {
            String tmp = num.substring(0,blockSize); // Split string into blocks
            int val = Integer.parseInt(tmp); // Converting string to a numerical value
            representation.insertAtTail(val);
            num = num.substring(3); // Strings are immutable
        }

        // if there is a remainder digits, it will be stored in a node, with different block size.
        if (remainderBlocks != 0){
            representation.insertAtTail(num);
        }
    }

    @Override
    public String toString() {
        char sign;
        if(positive == true){
            sign = '+';
        }
        else{
            sign = '-';
        }

        return "BigInt{"+ sign + representation.printBigInt() +
                "}";
    }
}
