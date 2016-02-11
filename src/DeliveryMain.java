import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by pawantu on 11/02/16.
 */
public class DeliveryMain {
    public static void main(String argv[]) {

        System.out.println("Test");


        int r = 0;
        int c = 0;
        int d = 0;
        int t = 0;
        int p = 0;

        String filename = "schlepp.in";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String[] split;

            //first line
            line = br.readLine();
            split = line.split(" ");

            r = Integer.parseInt(split[0]);
            c = Integer.parseInt(split[1]);
            d = Integer.parseInt(split[2]);
            t = Integer.parseInt(split[3]);
            p = Integer.parseInt(split[4]);


//            while ((line = br.readLine()) != null) {
//                // process the line.
//
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
