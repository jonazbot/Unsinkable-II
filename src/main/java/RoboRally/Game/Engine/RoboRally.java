package RoboRally.Game.Engine;

import RoboRally.Game.Board.BoardActions;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Objects.IRobot;
import RoboRally.Game.Objects.Player;
import RoboRally.Multiplayer.Packets.TurnPacket;

import java.util.Deque;
import java.util.List;

public interface RoboRally extends Runnable {


    /**
     * Adds a new player to the game.
     *
     * @return the Player added.
     */
    Player addPlayer(int playerID);

    /**
     * Attempt run.
     */
    void attemptRun(Deque<Card> registers, boolean powerDown);

    /**
     * Process round and update all robot.
     *
     * @param turnPackets the game round packets
     */
    void updateAllRobotRegisters(List<TurnPacket> turnPackets);

    /**
     * Stops the game loop and ends the game.
     */
    void stopGame();

    /**
     * @return the local player.
     */
    Player getMyPlayer();

    /**
     * @return the list of current players.
     */
    List<Player> getPlayers();

    List<IRobot> getRobots();

    /**
     * @return the current board.
     */
    BoardActions getBoard();

    void setWinner(IRobot robot);

    void turn();
}
