import java.util.ArrayList;
import java.util.List;

public class Drone{

    public int x;
    public int y;

    public int nextX;
    public int nextY;
    public int turn;

    public boolean isBusy;
    public List<Command> commands;

    public int maxWeight;

    public List<Integer> inventory;


    public Drone(int maxw){
        commands = new ArrayList<>();
        inventory = new ArrayList<>();
        isBusy = false;

        x = 0;
        y = 0;

        nextX = 0;
        nextY = 0;
        maxWeight = maxw;
    }



}
