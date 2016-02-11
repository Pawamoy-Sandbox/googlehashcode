import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by pallamidessi on 11/02/16.
 */
public class Algorithm {
    public static int estimateOrderCost (Drone d, Order o, int currentTurn, int maxTurn, boolean writeMode) {
        int turnCount = 0;
        List<Command> commands = new ArrayList<>();
        int currentX = d.x;
        int currentY = d.y;

        if (d.isBusy) {
            currentX = d.nextY;
            currentY = d.nextX;
        }

        int result = 0;

        for (int i = 0; i < o.items.size(); i++) {
            boolean missing = false;

            if (missing) {
                //turnCount += drone.load(x, y, );
            }
        }

        result = (maxTurn - turnCount) / maxTurn * 100;
        return result;
    }

    public void run( List<Drone> drones, List<Order> orders, int maxTurn) {
        int max = 0;
        int bestDrone = 0;
        int bestOrder = 0;

        for(Drone d : drones) {
            for(Order o : orders) {
                int res = estimateOrderCost(d, o, 0, maxTurn, false);

                if (res > max) {
                    bestDrone = 0;
                    bestOrder = 0;
                    max = res;
                }
            }
        }

        greedyDescent(drones, orders, maxTurn);
    }

    public void greedyDescent(List<Drone> drones, List<Order> orders, int maxTurn) {
        int max = -1;
        int bestDrone = 0;
        int bestOrder = 0;

        for(Drone d : drones) {
            for(Order o : orders) {
                if (!o.isDone) {
                    int res = estimateOrderCost(d, o, 0, maxTurn, false);

                    if (res > max) {
                        bestDrone = 0;
                        bestOrder = 0;
                        max = res;
                    }
                }
            }
        }

        int nbTurn = estimateOrderCost(drones.get(bestDrone), orders.get(bestOrder), 0, maxTurn, true);

        Drone tmpDrone = drones.get(bestDrone);
        tmpDrone.turn = nbTurn;

        drones.set(bestDrone, tmpDrone);

        Order tmpOrder = orders.get(bestDrone);
        tmpDrone.turn = nbTurn;

        orders.set(bestOrder, tmpOrder);

        if (max == -1) {
            return;
        }

        greedyDescent(drones, orders, maxTurn);
    }
    
}
