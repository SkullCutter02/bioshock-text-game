public class Map {
    private final int MAP_X;
    private final int MAP_Y;

    public Map(int MAP_X, int MAP_Y) {
        this.MAP_X = MAP_X;
        this.MAP_Y = MAP_Y;
    }

    public void displayMap(int playerXSpot, int playerYSpot) {
        System.out.println("Map: (X marks your current spot)");

        for(int i = 0; i < MAP_Y; i++) {
            for(int j = 0; j < MAP_X; j++) {
                if(i == playerYSpot && j == playerXSpot)
                    System.out.print("X ");
                else
                    System.out.print("O ");
            }

            System.out.println();
        }
    }
}
