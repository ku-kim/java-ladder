package ladder.view;

import ladder.domain.Item;
import ladder.domain.Ladder;
import ladder.domain.Line;
import ladder.domain.Player;
import ladder.util.StringUtils;

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


}
