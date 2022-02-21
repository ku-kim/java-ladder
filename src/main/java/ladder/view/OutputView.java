package ladder.view;

import ladder.dto.Item;
import ladder.domain.Ladder;
import ladder.domain.Line;
import ladder.dto.Player;
import ladder.util.StringUtils;

import java.util.HashMap;
import java.util.List;

public class OutputView {
    private OutputView() {
        throw new IllegalStateException("Utility class");
    }

    public static void print(Ladder ladder) {
        StringBuilder sb = new StringBuilder();

        sb.append("사다리 결과 \n\n");
        appendPlayers(sb, ladder.getPlayers());
        appendLines(sb, ladder.getLines());
        appendItems(sb, ladder.getItems());

        System.out.println(sb);
    }

    private static void appendItems(StringBuilder sb, List<Item> items) {
        sb.append("   ");
        for (Item item : items) {
            String modItemName = StringUtils.center(item.getName(), 5, ' ');
            modItemName = StringUtils.withLimitLength(modItemName, 5);

            sb.append(String.format("%5s ", modItemName));
        }
        sb.append("\n");
    }

    private static void appendPlayers(StringBuilder sb, List<Player> players) {
        sb.append("   ");
        for (Player player : players) {
            String modPlayerName = StringUtils.center(player.getName(), 5, ' ');
            modPlayerName = StringUtils.withLimitLength(modPlayerName, 5);

            sb.append(String.format("%5s ", modPlayerName));
        }
        sb.append("\n");
    }

    private static void appendLines(StringBuilder sb, List<Line> lines) {
        for (Line line : lines) {
            appendLine(sb, line);
        }
    }

    private static void appendLine(StringBuilder sb, Line line) {
        int playCount = line.getMaxLength();
        sb.append("     ");
        for (int i = 0; i < playCount - 1; i++) {
            appendLadder(sb, line, i);
        }
        sb.append("|\n");
    }

    private static void appendLadder(StringBuilder sb, Line line, int ladderPos) {
        if (line.isLadder(ladderPos)) {
            sb.append("|-----");
            return;
        }
        sb.append("|     ");
    }


    public static void printQueryResult(String query, HashMap<String, String> results) {
        StringBuilder sb = new StringBuilder();

        sb.append("실행 결과\n");
        if (query.equals("all")) {
            System.out.println(appendAllResults(results, sb));
            return;
        }
        appendQueryResult(query, results, sb);
        System.out.println(sb);
    }

    private static void appendQueryResult(String query, HashMap<String, String> results, StringBuilder sb) {
        if (!results.containsKey(query)) {
            sb.append(String.format("%s 사람이 없습니다.\n", query));
            return;
        }

        results.entrySet().stream()
                .filter(result -> result.getKey().equals(query))
                .forEach(result -> sb.append(result.getValue() + "\n"));
    }

    private static String appendAllResults(HashMap<String, String> results, StringBuilder sb) {
        results.forEach((playerName, itemName) ->
                sb.append(String.format("%s : %s\n", playerName, itemName)));
        return sb.toString();
    }

    public static void printExit() {
        System.out.println("게임을 종료합니다.");
    }
}
