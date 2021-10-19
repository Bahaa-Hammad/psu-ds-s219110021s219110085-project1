
package dsproject1;
public class DSProject1 {
    public static void main(String[] args) {
        BigInt x = new BigInt("123456");
        BigInt y = new BigInt("999999");
        BigInt z = x.subtract(y);
        z.representation.printList();
        //System.out.println(z.toString());
        System.out.println(z.toString());
        //System.out.println(y.toString());
        //x.representation.printList();
        //y.representation.printList();
        //BigInt z = x.subtract(y);
        //z.representation.printList();
        //System.out.println(z.toString());
        //System.out.println(x.representation.size);
        //System.out.println(x.toString());
        //x.representation.printList();
        //System.out.println(x.compare(y));
    }
}
