
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

    public BigInt absAdd(BigInt num) {

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

    public BigInt add(BigInt num){

        BigInt res = new BigInt();
        // Case One: Both Numbers are negative
        if(this.positive == false && num.positive == false){ // Both BigInts are negative
            res = absAdd(num);
            res.positive = false;
        }

        //Case Two: Both Numbers are positive
        if(this.positive == true && num.positive == true){
            res = absAdd(num);
            res.positive = true;
        }

        //Case Three: One of the numbers is negative:
        if(this.positive == true && num.positive == false){
            num.positive = true;
            //Check which number is bigger:
            int c = this.compare(num);
           switch (c){
                case 1:
                    res = this.subtract(num);
                    break;
                case -1:
                    res = num.absSubtract(this);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + c);
            }
        }

        if (this.positive == false && num.positive == true){
            num.positive = false;
            //Check which number is bigger:
            int c = this.compare(num);
            switch (c){
                case 1:
                    res = this.absSubtract(num);
                    break;
                case -1:
                    res = num.subtract(this);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + c);
            }
        }
        return res;
    }

    BigInt subtract(BigInt num){
        // Find the largest value:
        int c = this.compare(num);

        BigInt res = new BigInt();

        if (c == 1){
             res = this.absSubtract(num);
             res.positive = true;
        }

        if (c == -1){
            res = num.absSubtract(this);
             res.positive = false;
        }

        if (c == 0){
            res = new BigInt("0");
        }
        return res;
    }

    BigInt absSubtract(BigInt num){
        BigInt subtInt = new BigInt();

        DNode current_tmp = this.representation.tail;
        DNode num_tmp = num.representation.tail;

        DLinkedList subtList = new DLinkedList();
        subtInt.representation = subtList;

        int subt = 0;
        int borrow = 0;

        while (current_tmp != null) {
            subt = borrow + (current_tmp.value - (num_tmp != null? num_tmp.value : 0) % 1000);
            if (subt < 0){
                borrow = -1;
                subt += 1000;
            }
            else {
                borrow = 0;
            }
            subtList.insertAtHead(subt);

            current_tmp = current_tmp.prev;
            if (num_tmp != null){
                num_tmp = num_tmp.prev;
            }
        }
        return subtInt;
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

    public int compare(BigInt num){


            //Check + , - :

        if (this.positive == true && num.positive == false){
            return 1;
        }

        if (this.positive == false && num.positive == true){
            return -1;
        }


        if (this.representation.size > num.representation.size){
            return 1;
        }

        if (this.representation.size < num.representation.size){
            return -1;
        }

        // If size is the same: check each element in each node:

        int length = this.representation.size;

        DNode current_tmp = this.representation.head;
        DNode num_tmp = num.representation.head;

        while(current_tmp != null){

            if(current_tmp.value > num_tmp.value){
                return 1;
            }
            if (current_tmp.value < num_tmp.value){
                return -1;
            }
            current_tmp = current_tmp.next;
            num_tmp = num_tmp.next;
        }
        return 0;

    }

}
