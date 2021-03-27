package RoboRally.GUI.Screens.Game;

import RoboRally.Debugging.Debugging;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.*;
import java.util.List;

/**
 * Player User Interface.
 */
public class PlayerUI {

    private final RoboRallyApp app;
    private final Table mainTable;
    private final Table playerHandTable;
    private final Table runButtonTable;
    private final Table registryTable;
    private final Stage stage;
    private final FitViewport stageViewport;
    private final ButtonGroup<Button> handButtons, registryButtons;
    private final Map<Integer, Integer> registry;
    private final float width = Gdx.graphics.getWidth();
    private final float height = Gdx.graphics.getHeight();
    private final float cardWidth = width / 16f;
    private final float cardHeight = height / 6f;
    private final float vertPadding = height / 16f;

    private List<Card> hand;
    private int order;

    /**
     * Creates a new player UI.
     *
     * @param app the app
     */
    public PlayerUI(RoboRallyApp app) {
        System.out.println("Making UI");
        this.app = app;
        this.hand = new ArrayList<>();

        this.stageViewport = new FitViewport(width, height);
        this.stage = new Stage(stageViewport);

        this.mainTable = new Table();
        stage.addActor(mainTable);

        this.runButtonTable = new Table();
        this.playerHandTable = new Table();
        this.handButtons = new ButtonGroup<>();
        this.registryButtons = new ButtonGroup<>();
        this.registryTable = new Table();
        this.registry = new HashMap<>();
        this.order = 0;

        mainTableSetup();
        registryTableSetup();
        mainTable.row();
        mainTable.add(playerHandTable);
        runButtonSetup();
    }

    /**
     * Setup for the UI layout main table
     */
    private void mainTableSetup() {
        mainTable.setFillParent(true);
        mainTable.padLeft(width/2f);
        mainTable.padBottom(vertPadding).bottom();
        if (Debugging.isGuiDebug()) {
            mainTable.setDebug(true);
            registryTable.setDebug(true);
            playerHandTable.setDebug(true);
        }
    }



    private void handButtonsSetup() {
        handButtons.setMaxCheckCount(5);
        handButtons.setMinCheckCount(0);
        handButtons.setUncheckLast(false);
        handButtons.uncheckAll();
        addPlayerHandButtons();

    }

    private void addPlayerHandButtons() {
        for (int index = 0; index < hand.size(); ++index) {
            if (index % 3 == 0) {
                playerHandTable.padRight(cardWidth);
                playerHandTable.row();
                playerHandTable.padLeft(cardWidth);
            }
            Button button = new ImageButton(
                    makeCard(hand.get(index).getValue()),
                    makeCard(ProgramCard.BACKSIDE),
                    makeCard(ProgramCard.BACKSIDE));
            button.addListener(playerHandListener(index));

            playerHandTable.add(button).size(cardWidth, cardHeight);
            button.debug();
            handButtons.add(button);
        }
    }

    private ClickListener playerHandListener(int index) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (registry.size() < 5 && !handButtons.getButtons().get(index).isDisabled()) {
                    registry.put(index, ++order);
                    handButtons.getButtons().get(index).setDisabled(true);
                    addRegistryButton(index);
                }
            }
        };
    }

    /**
     * Setup wrapper for the table representing the registry.
     */
    private void registryTableSetup(){
        mainTable.row();
        registryTable.padBottom(vertPadding);
        mainTable.add(registryTable).left();
    }

    private void addRegistryButton(int index) {
        Button button = new ImageButton(makeCard(hand.get(index).getValue()));
        button.setSize(cardWidth, cardHeight);
        button.addListener(registryListener(index, button));
        registryTable.add(button)
                .size(cardWidth, cardHeight)
                .left()
                .bottom();
        registryButtons.add(button);
    }

    private ClickListener registryListener(int index, Button button) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handButtons.getButtons().get(index).setDisabled(false);
                handButtons.getButtons().get(index).setChecked(false);
                registry.remove(index);
                registryTable.getCell(button).reset();
                registryTable.removeActor(button);
            }
        };
    }

    /**
     * Clears the player hand table.
     */
    private void resetHand() {
        handButtons.clear();
        playerHandTable.clearChildren();
        playerHandTable.padTop(height/2f);
    }

    public void updateHand(List<Card> hand) {
        this.hand = hand;
        playerHandTable.padTop(0);
        handButtons.clear();
        handButtonsSetup();
    }

    /**
     * Clears the player hand table.
     */
    public void clearRegistry() {
        registry.clear();
        order = 0;
        registryButtons.clear();
        registryTable.clearChildren();
    }

    /**
     * Creates the registry of cards
     *
     * @return the queue of ordered cards
     */
    private Deque<Card> makeRegisters() {
        List<Integer> list = new LinkedList<>(registry.values());
        Deque<Card> registers = new LinkedList<>();
        Collections.sort(list);
        for (int value : list) {
            for (int handIndex : registry.keySet()) {
                if (registry.get(handIndex) == value) { registers.offer(hand.get(handIndex)); }
            }
        }
        return registers;
    }

    private TextureRegionDrawable makeCard(ProgramCard card) {
        return new TextureRegionDrawable(new TextureRegion(new Texture(card.getPath())));
    }

    private void runButtonSetup() {
        mainTable.row();
        runButtonTable.padTop(vertPadding);
        runButtonTable.add(addRunButton());
        runButtonTable.add(addPowerDownButton());
        mainTable.add(runButtonTable);
    }

    private Button addRunButton() {
        Button runButton = new TextButton("Run", app.getGameSkin());
        runButton.setSize(width /6f, height/6f);
        runButton.addListener(runButtonListener(false));
        return runButton;
    }

    private Button addPowerDownButton() {
        Button powerDownButton = new TextButton("Power Down", app.getGameSkin());
        powerDownButton.setSize(cardWidth, cardHeight);
        Deque<Card> emptyRegisters = new LinkedList<>();
        for (int i = 0; i < 5; ++i) { emptyRegisters.offer(new Card()); }
        powerDownButton.addListener(runButtonListener(true));
        return powerDownButton;
    }

    private ClickListener runButtonListener(boolean powerDown) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (registry.size() == 5) {
                    app.getGame().attemptRun(makeRegisters(), hand, powerDown);
                    resetHand();
                }
            }
        };
    }

    /**
     * @return the stage
     */
    public Stage getStage() { return this.stage; }

    /**
     * Dispose .
     */
    public void dispose(){ stage.dispose(); }


}
