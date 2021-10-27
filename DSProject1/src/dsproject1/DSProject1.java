
package dsproject1;
public class DSProject1 {
    public static void main(String[] args) {
        // Input x
        BigInt x = new BigInt("1111111");
        System.out.println("The first number in DLL representation: ");
        x.representation.printList();
        System.out.println("The first number in toString: " + x);
        System.out.println();

        // Input y
        BigInt y = new BigInt("9999999");
        System.out.println("The second number in DLL representation: ");
        y.representation.printList();
        System.out.println("The second number in toString: " + y);
        System.out.println();

        // Methods
        System.out.println("The result of addition: ");
        BigInt a = x.add(y);
        System.out.println(a);
        System.out.println("The result of subtraction: ");
        BigInt s = x.subtract(y);
        System.out.println(s);
        System.out.println("The result of comparing: ");
        System.out.println(+ x.compare(y));
    }
}
