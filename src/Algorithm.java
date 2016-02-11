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

    public static int estimateOrderCost(Drone d, Order o, Warehouse[] warehouses, int itemsType, int currentTurn, int maxTurn, boolean writeMode) {

        int turnCount = 0;
        List<Command> commands = new ArrayList<>();
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

            while (wanted > 0) {
                for (int w = 0; w < warehouses.length; w++){
                    if (warehouses[w].items.get(type) != 0){


                        turnCount += dist(currentX, currentY, warehouses[w].x, warehouses[w].y); //going to warehouse
                        turnCount++; //loading

                        currentX = warehouses[w].x;
                        currentY = warehouses[w].y;
                        d.isBusy = true;

                        if (writeMode){
                            d.
                        }

                        wanted -= warehouses[w].items.get(type);

                        if (wanted <= 0){
                            break;
                        }
                    }
                }
            }

        }


        result = (maxTurn - turnCount) / maxTurn * 100;
        return result;
    }

    public void run( List<Drone> drones, List<Order> orders, Warehouse[] warehouses, int itemsType, int maxTurn) {
        int max = 0;
        int bestDrone = 0;
        int bestOrder = 0;

        for(Drone d : drones) {
            for(Order o : orders) {
                int res = estimateOrderCost(d, o, warehouses, itemsType, 0, maxTurn, false);

                if (res > max) {
                    bestDrone = 0;
                    bestOrder = 0;
                    max = res;
                }
            }
        }

        greedyDescent(drones, orders, warehouses, itemsType, maxTurn);
    }

    public void greedyDescent(List<Drone> drones, List<Order> orders, Warehouse[] warehouses, int itemsType, int maxTurn) {
        int max = -1;
        int bestDrone = 0;
        int bestOrder = 0;

        for(Drone d : drones) {
            for(Order o : orders) {
                if (!o.isDone) {
                    int res = estimateOrderCost(d, o, warehouses, itemsType, 0, maxTurn, false);

                    if (res > max) {
                        bestDrone = 0;
                        bestOrder = 0;
                        max = res;
                    }
                }
            }
        }

        int nbTurn = estimateOrderCost(drones.get(bestDrone), orders.get(bestOrder), warehouses, itemsType, 0, maxTurn, true);

        Drone tmpDrone = drones.get(bestDrone);
        tmpDrone.turn = nbTurn;

        drones.set(bestDrone, tmpDrone);

        Order tmpOrder = orders.get(bestDrone);
        tmpDrone.turn = nbTurn;

        orders.set(bestOrder, tmpOrder);

        if (max == -1) {
            return;
        }

        greedyDescent(drones, orders, warehouses, itemsType, maxTurn);

    }

}
