import java.util.ArrayList;
import java.util.List;

/**
 * Created by pallamidessi on 11/02/16.
 */
public class Algorithm {

    public static int getItemsFromOrder(Order o, int type){
        int sum = 0;
        for (int obj : o.items){
            if (obj == type)
                sum++;
        }
        return sum;
    }

    public static int dist(int x1, int y1, int x2, int y2){
        double dx = x2 - x1;
        double dy = y2 - y1;
        double d = Math.sqrt(dx*dx + dy*dy);

        return (int)Math.ceil(d);
    }

    public static int estimateOrderCost(Drone d, Order o, int oindex, Warehouse[] warehouses, int itemsType, int currentTurn, int maxTurn, boolean writeMode) {

        int turnCount = 0;
        List<Command> commands = new ArrayList<>();

        d.x = warehouses[0].x;
        d.y = warehouses[0].y;

        int currentX = d.x;
        int currentY = d.y;

        if (d.isBusy) {
            currentX = d.nextY;
            currentY = d.nextX;
        }

        int result = 0;
        int[] items = new int[itemsType];

        for (int type = 0; type < itemsType; type++){
            items[type] = getItemsFromOrder(o, type);

            if (items[type] == 0)
                continue;

            int wanted = items[type];
            int currentLoad = wanted;

            while (wanted > 0) {
                for (int w = 0; w < warehouses.length; w++){
                    if (warehouses[w].items.get(type) != 0){

                        turnCount += dist(currentX, currentY, warehouses[w].x, warehouses[w].y); //going to warehouse
                        turnCount++; //loading

                        currentX = warehouses[w].x;
                        currentY = warehouses[w].y;
                        d.isBusy = true;

                        if (warehouses[w].items.get(type) <= wanted){
                            currentLoad = warehouses[w].items.get(type);
                            wanted -= currentLoad;
                        }
                        else
                        {
                            currentLoad = wanted;
                            wanted = 0;
                        }

                        warehouses[w].items.set(type, warehouses[w].items.get(type)-currentLoad);

                        if (wanted <= 0){
                            if (writeMode){
                                Command c = new Command();
                                c.type = 'L';
                                c.destinationId = w;
                                c.productType = type;
                                c.numberOfProducts = currentLoad;
                                c.output();
                                System.out.print('\n');
                            }

                            break;
                        }
                    }
                }
            }

        }

        turnCount += dist(currentX, currentY, o.x, o.y); //going to the order

        for (int type = 0; type < itemsType; type++) {
            items[type] = getItemsFromOrder(o, type);

            if (items[type] != 0){
                turnCount++; //loading

                if (writeMode){
                    Command c = new Command();
                    c.type = 'D';
                    c.destinationId = oindex;
                    c.productType = type;
                    c.numberOfProducts = items[type];
                    c.output();
                    System.out.print('\n');
                }
            }


        }

        if (writeMode) {
            if (turnCount > maxTurn) {
                return -1;
            }
            return turnCount;
        }

        result = (maxTurn - turnCount) / maxTurn * 100;
        return result;
    }

    public static void run( List<Drone> drones, List<Order> orders, Warehouse[] warehouses, int itemsType, int maxTurn) {
        int max = 0;
        int bestDrone = 0;
        int bestOrder = 0;
        int currentDrone = 0;
        int currentOrder = 0;

        for(Drone d : drones) {
            currentDrone++;
            currentOrder = 0;
            for(Order o : orders) {
                Warehouse[] ww = new Warehouse[warehouses.length];
                System.arraycopy( warehouses, 0, ww, 0, warehouses.length );
                int res = estimateOrderCost(d, o, currentOrder, ww, itemsType, 0, maxTurn, false);
                
                currentOrder++;

                if (res > max) {
                    bestDrone = currentOrder;
                    bestOrder = currentDrone;
                    max = res;
                }
            }
        }

        int nbTurn = estimateOrderCost(drones.get(bestDrone), orders.get(bestOrder), currentOrder, warehouses, itemsType, 0, maxTurn, true);

        Drone tmpDrone = drones.get(bestDrone);
        tmpDrone.turn = nbTurn;

        drones.set(bestDrone, tmpDrone);

        Order tmpOrder = orders.get(bestDrone);
        tmpDrone.turn = nbTurn;

        orders.set(bestOrder, tmpOrder);

        while (greedyDescent(drones, orders, warehouses, itemsType, maxTurn) != -1) {}

    }

    public static int greedyDescent(List<Drone> drones, List<Order> orders, Warehouse[] warehouses, int itemsType, int maxTurn) {
        int max = -1;
        int bestDrone = 0;
        int bestOrder = 0;

        int currentDrone = 0;
        int currentOrder = 0;

        for(Drone d : drones) {
            bestDrone++;
            currentOrder = 0;
            for(Order o : orders) {
                if (!o.isDone) {
                    int res = estimateOrderCost(d, o, currentOrder, warehouses, itemsType, 0, maxTurn, false);

                    bestOrder++;

                    if (res > max) {
                        bestDrone = currentDrone;
                        bestOrder = currentOrder;
                        max = res;
                    }
                }
            }
        }

        int nbTurn = estimateOrderCost(drones.get(bestDrone), orders.get(bestOrder), currentOrder, warehouses, itemsType, 0, maxTurn, true);

        Drone tmpDrone = drones.get(bestDrone);
        tmpDrone.turn += nbTurn;

        drones.set(bestDrone, tmpDrone);

        Order tmpOrder = orders.get(bestDrone);
        tmpDrone.turn = nbTurn;

        orders.set(bestOrder, tmpOrder);

        if (max == -1) {
            return -1;
        }

	return 0;
    }

}
