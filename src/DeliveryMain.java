import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by pawantu on 11/02/16.
 */
public class DeliveryMain {
    public static void main(String argv[]) {


        System.out.println("Test");

        String filename = "busy_day.in";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
