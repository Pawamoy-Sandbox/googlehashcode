import java.util.ArrayList;
import java.util.List;

public class Drone{

    public int x;
    public int y;

    public int nextX;
    public int nextY;

    public boolean isBusy;
    public List<Command> currentCommand;

    public int maxWeight;

    public List<Integer> inventory;


    public Drone(){
        currentCommand = new ArrayList<>();
        inventory = new ArrayList<>();
        isBusy = false;
    }



}
