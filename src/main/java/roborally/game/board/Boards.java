package roborally.game.board;

public enum Boards {
    RISKY_EXCHANGE("Maps/RiskyExchange.tmx", "Risky Exchange", 8),
    CHECKMATE("Maps/Checkmate.tmx", "Checkmate", 8),
    BLOODBATH_CHESS("Maps/BloodbathChess.tmx", "Bloodbath Chess", 8),
    DIZZY_DASH("Maps/DizzyDash.tmx", "Dizzy Dash", 8),
    TWISTER("Maps/Twister.tmx", "Twister", 8),
    DEATH_TRAP("Maps/DeathTrap.tmx", "Death Trap", 8),
    TESTMAP("Maps/Testmap2.tmx", "Test", 2),
    JUNIT_TEST_MAP("Maps/JUnitTestingMap.tmx", "JUnit_test", 8); // should not be included in ALL_BOARDS

    public static final Boards[] ALL_BOARDS = new Boards[] {
            RISKY_EXCHANGE,
            CHECKMATE,
            BLOODBATH_CHESS,
            DIZZY_DASH,
            TWISTER,
            DEATH_TRAP,
            TESTMAP
    };

    private final String path;
    private final String name;
    private final int maxPlayers;

    Boards(String path, String name, int maxPlayers) {
        this.path = path;
        this.name = name;
        this.maxPlayers = maxPlayers;
    }

    public String getPath() { return this.path; }
    public String getName() { return this.name; }
    public int getMaxPlayers() { return this.maxPlayers; }

}
