package ladder.dto;

public class Player {
    private String name;
    private int pos;

    public static Player valueOf(String name, int pos) {
        Player player = new Player();
        player.name = name;
        player.pos = pos;
        return player;
    }

    public int getPos() {
        return pos;
    }

    public String getName() {
        return name;
    }

    public void moveRight() {
        pos += 1;
    }

    public void moveLeft() {
        pos -= 1;
    }
}
