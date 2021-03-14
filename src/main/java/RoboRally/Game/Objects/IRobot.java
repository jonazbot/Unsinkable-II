package RoboRally.Game.Objects;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.Queue;

public interface IRobot {

    /**
     * @return the direction the robot is facing.
     */
    Direction getDirection();

    /**
     * @param dir the new direction the robot is facing.
     */
    void setDirection(Direction dir);

    /**
     * @return the robot's current location.
     */
    Vector2 getLoc();

    /**
     * @param newLoc the new robot location.
     */
    void setLoc(Vector2 newLoc);

    Queue<ProgramCard> getRegisters();

    void setRegisters(Queue<ProgramCard> hand);

    /**
     * @return robot normal cell.
     */
    TiledMapTileLayer.Cell getCell();

    /**
     * @return robot died cell.
     */
    TiledMapTileLayer.Cell getDiedCell();

    /**
     * @return robot won cell.
     */
    TiledMapTileLayer.Cell getWonCell();

    Piece getPiece();

    int getHealth();
}
