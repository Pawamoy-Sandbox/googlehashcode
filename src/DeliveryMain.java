import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

            Drone[] drones = new Drone[d];

            for (int i = 0; i < d; i++){
                drones[i] = new Drone(p);
            }


            // second line, number of product type
            line = br.readLine();
            int productTypeNumber = Integer.parseInt(line);
            int[] productTypeWeight = new int[productTypeNumber];

            // weights of product type
            line = br.readLine();
            split = line.split(" ");

            for (int i = 0; i < productTypeNumber; i++){
                productTypeWeight[i] = Integer.parseInt(split[i]);
            }


            //
            line = br.readLine();
            int warehouseNumber = Integer.parseInt(line);
            Warehouse[] warehouses = new Warehouse[warehouseNumber];

            for (int i = 0; i < warehouseNumber; i++){
                //
                warehouses[i] = new Warehouse();

                line = br.readLine();
                split = line.split(" ");

                warehouses[i].x = Integer.parseInt(split[0]);
                warehouses[i].y = Integer.parseInt(split[1]);

                line = br.readLine();
                split = line.split(" ");

                for (int j = 0; j < productTypeNumber; j++){
                    warehouses[i].items.add(Integer.parseInt(split[j]));
                }
            }


            line = br.readLine();
            int orderNumber = Integer.parseInt(line);
            Order[] orders = new Order[orderNumber];

            for (int i = 0; i < orderNumber; i++){
                //
                orders[i] = new Order();

                line = br.readLine();
                split = line.split(" ");

                orders[i].x = Integer.parseInt(split[0]);
                orders[i].y = Integer.parseInt(split[1]);

                line = br.readLine();
                orders[i].noOfItems = Integer.parseInt(line);

                line = br.readLine();
                split = line.split(" ");

                for (int j = 0; j < orders[i].noOfItems; j++){
                    orders[i].items.add(Integer.parseInt(split[j]));
                }
            }

            System.out.println("finished parsing");


            int cmdCount = 0;
            for (int i = 0; i < d; i++){
                cmdCount += drones[i].commands.size();
            }

            for (int i = 0; i < d; i++){
                for (Command cmd : drones[i].commands){
                    cmd.output();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
