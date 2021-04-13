import java.util.*;

public class Game {
    private final int MAP_X = 11;
    private final int MAP_Y = 7;

    private final Map map = new Map(MAP_X, MAP_Y);

    private int currentXSpot = 5;
    private int currentYSpot = 6;

    private void movePlayer(String command) {
        switch (command) {
            case "u" -> {
                if (currentYSpot - 1 < 0)
                    System.out.println("You cannot go up any further!");
                else
                    currentYSpot -= 1;
            }
            case "d" -> {
                if (currentYSpot + 1 >= MAP_Y)
                    System.out.println("You cannot go down any further!");
                else
                    currentYSpot += 1;
            }
            case "l" -> {
                if (currentXSpot - 1 < 0)
                    System.out.println("You cannot go left any further!");
                else
                    currentXSpot -= 1;
            }
            case "r" -> {
                if (currentXSpot + 1 >= MAP_X)
                    System.out.println("You cannot go right any further!");
                else
                    currentXSpot += 1;
            }
        }
    }

    public void main() {
        map.displayMap(currentXSpot, currentYSpot);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Type L for left, R for right, U for up and D for down. " +
                        "Type commands to show all the other commands you can type");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("u") || input.equals("d") || input.equals("r") || input.equals("l")) {
                    movePlayer(input);
                } else if (input.equals("map")) {
                    map.displayMap(currentXSpot, currentYSpot);
                } else {
                    System.out.println("Try again with a valid input!\n");
                }
            } catch (Exception e) {
                System.out.println("Try again with a valid input!\n");
            }
        }
    }
}
