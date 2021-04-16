public class Map {
    private final int MAP_X;
    private final int MAP_Y;

    private final int[][] vendingMachineLocations = {{2, 4}, {6, 1}, {4, 5}, {9, 3}};
    private final int[][] bossFightLocation = {{5, 0}};

    public Map(int MAP_X, int MAP_Y) {
        this.MAP_X = MAP_X;
        this.MAP_Y = MAP_Y;
    }

    public boolean isVendingMachineSpot(int x, int y) {
        boolean b = false;

        for(int[] location : vendingMachineLocations) {
            if (location[0] == x && location[1] == y) {
                b = true;
                break;
            }
        }

        return b;
    }

    public boolean isBossFightSpot(int x, int y) {
        return bossFightLocation[0][0] == x && bossFightLocation[0][1] == y;
    }

    public void displayMap(int playerXSpot, int playerYSpot) {
        System.out.println("Map: (X marks your current spot, V marks vending machine spots, E marks the final boss fight)");

        for(int i = 0; i < MAP_Y; i++) {
            for(int j = 0; j < MAP_X; j++) {
                if(isBossFightSpot(j, i))
                    System.out.print("E ");
                else if(i == playerYSpot && j == playerXSpot)
                    System.out.print("X ");
                else if(isVendingMachineSpot(j, i))
                    System.out.print("V ");
                else
                    System.out.print("O ");
            }

            System.out.println();
        }
    }
}
