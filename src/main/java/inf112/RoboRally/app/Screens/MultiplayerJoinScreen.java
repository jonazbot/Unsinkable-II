package inf112.RoboRally.app.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import inf112.RoboRally.app.RoboRally;

/**
 * The type Multiplayer join screen.
 */
public class MultiplayerJoinScreen extends MenuScreen {
    private final TextField ipField;
    private final Label label;
    private String hostIP;
    private int hostPort;
    /**
     * Instantiates a new Multiplayer join screen.
     *
     * @param game the game
     */
    public MultiplayerJoinScreen(RoboRally game) {

        super(game);
        setHeading("Join Multiplayer Game");
        this.hostIP = "";
        this.hostPort = 8888;
        this.label = addLabel("", Slot.TOP1);
        this.ipField = addTextField(hostIP, Slot.TOP2);
        ipField.setMessageText("hostIP:port#");
        addButton("Join", Slot.MIDDLE, middleListener());
        addButton("Back", Slot.BOTTOM, bottomListener());
    }

    @Override
    public InputListener topListener() { return null; }

    @Override
    public InputListener middleListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { joinPressed(); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener bottomListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(game.getTitleScreen()); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    private void joinPressed() {
        hostIP = ipField.getText();
        label.setText("Joining "+hostIP);
    }
}
