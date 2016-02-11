/**
 * Created by thomas on 11/02/16.
 */
public class Command {

    public char type;

    public int destinationId;
    public int productType;
    public int numberOfProducts;

    public Command(){

    }

    public void output(){
        System.out.print(String.valueOf(type) + " ");
        System.out.print(String.valueOf(destinationId) + " ");
        System.out.print(String.valueOf(productType) + " ");
        System.out.print(String.valueOf(numberOfProducts) + " ");
    }


}
