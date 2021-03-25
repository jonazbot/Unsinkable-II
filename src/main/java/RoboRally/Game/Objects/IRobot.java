package RoboRally.Game.Objects;

import RoboRally.Game.Cards.Card;
import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.Queue;

public interface IRobot{


    void touchFlag();

    Vector2 getLoc();

    void setLoc(Vector2 newLoc);

    Vector2 getSpawnLoc();

    void setSpawnLoc(Vector2 newLoc);

    Direction getDirection();

    void setDirection(Direction dir);

    Queue<Card> getRegisters();

    void setRegisters(Queue<Card> hand);

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

    /**
     * @return the robot piece.
     */
    Piece getPiece();

    /**
     * @return the robot health
     */
    int getHealth();

    /**
     *
     * @return life of robot
     */
    int getLives();

    /**
     * gives the Robot one damage when encountering a hazard.
     */
    void addDamage();

    void fixDamage();

    /**
     * Removes one life from robot and resets damage.
     *
     * @return true if robot has another life, false if not.
     */
    boolean killRobot();

    /**
     * @return the number of flags successfully touched.
     */
    int touchedFlags();

}
