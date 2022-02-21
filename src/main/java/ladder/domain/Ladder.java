package ladder.domain;

import ladder.dto.Item;
import ladder.dto.Player;
import ladder.util.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ladder {
    private List<Line> lines;
    private List<Player> players;
    private List<Item> items;
    private HashMap<String, String> results;

    private Ladder() {
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
        setupResults();
    }

    private void setupResults() {
        HashMap<String, String> result = new HashMap<>();
        for (Player player : players) {
            result.put(player.getName(), items.get(player.getPos()).getName());
        }
        this.results = result;
    }

    private void runLine(Line line) {
        for (Player player : players) {
            line.run(player);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public HashMap<String, String> getResults() {
        return results;
    }
}
