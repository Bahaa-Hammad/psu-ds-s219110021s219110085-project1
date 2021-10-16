
package dsproject1;
public class DSProject1 {
    public static void main(String[] args) {
        BigInt x = new BigInt("123456789");
        BigInt y = new BigInt("111111");
        x.representation.printList();
        //y.representation.printList();
        BigInt z = x.add(y);
        z.representation.printList();
        //System.out.println(x.representation.size);
        //System.out.println(x.toString());
        //x.representation.printList();
    }
    
}
