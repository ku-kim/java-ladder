package ladder.domain;

import ladder.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class Ladder {
    private List<Line> lines;
    private List<Player> players;
    private List<Item> items;

    private Ladder() {
    }

    public static Ladder create(List<Player> players, int ladderMaxHeight) {
        Ladder ladder = new Ladder();
        ladder.players = players;
        ladder.lines = initLinesWithLadderMaxHeight(players.size(), ladderMaxHeight);
        return ladder;
    }

    public static Ladder valueOf(List<Player> players, List<Item> items, int ladderMaxHeight) {
        Ladder ladder = new Ladder();
        ladder.players = players;
        ladder.items = items;
        ladder.lines = initLinesWithLadderMaxHeight(players.size(), ladderMaxHeight);
        return ladder;
    }

    private static List<Line> initLinesWithLadderMaxHeight(int playerCount, int ladderMaxHeight) {
        List<Line> lines = new ArrayList();
        for (int i = 0; i < ladderMaxHeight; i++) {
            lines.add(Line.createLineWithPlayerCount(playerCount));
        }
        return lines;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void drawRandomLines() {
        for (Line line : lines) {
            drawRandomLine(line);
        }
    }

    public void drawLadder(int linePos, int ladderPos) {
        lines.get(linePos).drawLadder(ladderPos);
    }

    private void drawRandomLine(Line line) {
        for (int ladderPos = 0; ladderPos < players.size() - 1; ladderPos++) {
            drawRandomLadder(line, ladderPos);
        }
    }

    private void drawRandomLadder(Line line, int ladderPos) {
        if (isRandomDraw())
            line.drawLadder(ladderPos);
    }

    private boolean isRandomDraw() {
        return RandomUtils.nextBoolean();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void run() {
        for (Line line : lines) {
            runLine(line);
        }
    }

    private void runLine(Line line) {
        for (Player player : players) {
            line.run(player);
        }
    }

    public List<Item> getItems() {
        return items;
    }
}
