package RoboRally.Game;

import RoboRally.Game.Objects.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {

    Robot robot;
    @BeforeEach
    public void setup(){
        robot = new Robot();
        robot.setLoc(5,5);
    }

    @Test
    public void moveNorthIncrementsY(){
        GameLib.move(robot,Direction.NORTH);

        assertEquals(5, robot.getLoc().x);
        assertEquals(6, robot.getLoc().y);
    }

    @Test
    public void moveSouthDecrementsY(){
        GameLib.move(robot,Direction.SOUTH);

        assertEquals(5, robot.getLoc().x);
        assertEquals(4, robot.getLoc().y);
    }

    @Test
    public void moveEastIncrementsX(){
        GameLib.move(robot,Direction.EAST);

        assertEquals(6, robot.getLoc().x);
        assertEquals(5, robot.getLoc().y);
    }

    @Test
    public void moveWestDecrementsX() {
        GameLib.move(robot,Direction.WEST);

        assertEquals(4, robot.getLoc().x);
        assertEquals(5, robot.getLoc().y);
    }
}
