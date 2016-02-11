/**
 * Created by pallamidessi on 11/02/16.
 */
public class Algorithm {
    public static int estimateOrderCost(Drone d, Order o, int currentTurn, int maxTurn) {
        int turnCount = 0;

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
                //turnCount += drone.load();
            }
        }

        result = (maxTurn - turnCount) / maxTurn * 100;
        return result;
    }

    public Algorithm() {}
}
