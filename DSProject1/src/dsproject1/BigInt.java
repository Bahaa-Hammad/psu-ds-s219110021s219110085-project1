
package dsproject1;

// Wrapper Class
public class BigInt {

    //BigInt attributes
    boolean positive;
    final int blockSize = 3;
    DLinkedList representation = new DLinkedList(); // Doubly linked List


    //Constructions

    public BigInt(){
        DNode tmp = new DNode(0);
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
            String tmp = num.substring(num.length()-3,num.length()); // Split string into blocks
             int val = Integer.parseInt(tmp); // Converting string to a numerical value
            representation.insertAtHead(val);
            num = num.substring(0,num.length()-3); // Strings are immutable
        }

        // if there is a remainder digits, it will be stored in a node, with different block size.
        if (remainderBlocks != 0){
            representation.insertAtHead(Integer.parseInt(num));
        }
    }

    public BigInt add(BigInt num) {

        BigInt sumInt = new BigInt();

        DNode current_tmp = this.representation.tail;
        DNode num_tmp = num.representation.tail;

        DLinkedList sumList = new DLinkedList();
        sumInt.representation = sumList;

        int sum = 0;
        int carry = 0;

        while (current_tmp != null && num_tmp != null) {
            carry = (current_tmp.value + num_tmp.value) / 1000;
            sum = (current_tmp.value + num_tmp.value) % 1000;
            sumList.insertAtHead(sum);

            if (carry != 0){
                current_tmp = current_tmp.prev;
                num_tmp = num_tmp.prev;
                sum = (current_tmp.value + num_tmp.value + carry) % 1000 ;
                sumList.insertAtHead(sum);
            }
            current_tmp = current_tmp.prev;
            num_tmp = num_tmp.prev;
        }

        while (current_tmp != null){
            carry = (current_tmp.value) / 1000;
            sum = (current_tmp.value + carry) % 1000;
            sumList.insertAtHead(sum);
            current_tmp = current_tmp.prev;
        }

        while (num_tmp != null){
            carry = (num_tmp.value) / 1000;
            sum = (num_tmp.value + carry) % 1000;
            sumList.insertAtHead(sum);
            num_tmp = num_tmp.prev;
        }
        return sumInt;
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
