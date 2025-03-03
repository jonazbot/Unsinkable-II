package roborally.game;

/**
 * Class to represent cardinal directions.
 */
public enum Direction {
    NORTH(0,1, 0),
    WEST(-1,0, 1),
    SOUTH(0,-1, 2),
    EAST(1,0, 3);

    public static final Direction[] DIRECTIONS = new Direction[] {NORTH, WEST, SOUTH, EAST};

    private final int x;
    private final int y;
    private final int  direction;

    Direction(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() { return this.x; }

    public int getY() { return this.y; }

    public int getDirection() { return this.direction; }

    /**
     * @param rotation number of counter clockwise cardinal steps.
     * @return the new direction.
     */
    public Direction rotate(int rotation) { return DIRECTIONS[(4 + this.direction + rotation) %4]; }
}
