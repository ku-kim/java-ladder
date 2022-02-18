package ladder.domain;

public class Player {
    private String name;
    private int pos;

    public static Player createPlayerWithName(String name) {
        Player player = new Player();
        player.name = name;
        return player;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos() {

        return pos;
    }

    public static Player valueOf(String name, int pos) {
        Player player = new Player();
        player.name = name;
        player.pos = pos;
        return player;
    }

    public String getName() {
        return name;
    }
}
