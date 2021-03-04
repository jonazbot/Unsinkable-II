package RoboRally;

import RoboRally.GUI.Screens.PlayerView;
import RoboRally.Game.Players.Player;
import RoboRally.Game.RoboRallyGame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import RoboRally.GUI.Screens.TitleScreen;

/**
 * RoboRally application entry point.
 */
public class RoboRallyApp extends Game {

    //================================================================
    //                         App configuration
    //================================================================
    public static final String TITLE = "RoboRally";
    public static final int TILE_SIZE = 300;
    public static final String ROBOT_SKINS = "Robots/RobotsV1.png";
    private static final String SKIN = "skin/rusty-robot-ui.json";
    private final String CARDSKIN = "ProgramCards/Cards.atlas";

    //================================================================
    //                         GUI Objects
    //================================================================
    private Skin GUI_SKIN;
    private Stage stage;
    private TitleScreen titleScreen;

    public static Vector2 CENTER; //TODO: Only used to move robot to middle of map, remove when robot is placed properly

    @Override
    public void create() {
        this.GUI_SKIN = new Skin(Gdx.files.internal(SKIN));
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.titleScreen = new TitleScreen(this);
        this.setScreen(titleScreen);
    }


    @Override
    public void render () {

        super.render();

    }

    @Override
    public void dispose() {}

    /**
     * @return the GUI skin being used by the application.
     */
    public Skin getGUI_SKIN() { return this.GUI_SKIN; }

    /**
     * @return the title screen starting point of the application GUI.
     */
    public TitleScreen getTitleScreen() { return this.titleScreen; }

    public void startNewGame() {
        game = new RoboRallyGame(this);
        this.self = game.addPlayer();
        this.setScreen(new PlayerView(this));
    }

    public RoboRallyGame getGame() { return game; }

    public Player getSelf() { return self; }

}