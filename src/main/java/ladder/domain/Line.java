package ladder.domain;

import ladder.dto.Player;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private static final int FIRST_POS = 0;
    private List<Boolean> points;
    private int maxLength;

    private Line() {
    }

    public static Line createLineWithPlayerCount(int count) {
        Line line = new Line();
        line.checkCount(count);
        line.maxLength = count;
        line.points = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            line.points.add(false);
        }
        return line;
    }

    public void drawLadder(int ladderPos) {
        checkPos(ladderPos);
        if (isDrawLadder(ladderPos)) {
            points.set(ladderPos, true);
        }
    }


    public boolean isLadder(int ladderPos) {
        checkPos(ladderPos);
        return points.get(ladderPos);
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void run(Player player) {
        int playerPos = player.getPos();

        if (isFirstPos(playerPos)) {
            moveRight(player, playerPos);
            return;
        }
        if (isLastPos(playerPos)) {
            moveLeft(player, playerPos);
            return;
        }
        moveRight(player, playerPos);
        moveLeft(player, playerPos);
    }

    private void checkCount(int count) {
        if (count < FIRST_POS) {
            throw new IllegalArgumentException("count가 정상 범위를 넘었습니다.");
        }
    }

    private boolean isDrawLadder(int ladderPos) {
        if (isFirstPos(ladderPos)) {
            return !isLadder(ladderPos) && !isRightLadder(ladderPos);
        }
        return !isLeftLadder(ladderPos) && !isLadder(ladderPos) && !isRightLadder(ladderPos);
    }

    private void checkPos(int point) {
        int lastPos = maxLength;
        if (point < FIRST_POS || point >= lastPos) {
            throw new IllegalArgumentException("pos가 정상 범위를 넘었습니다.");
        }
    }

    private boolean isLeftLadder(int ladderPos) {
        return points.get(ladderPos - 1);
    }

    private boolean isRightLadder(int ladderPos) {
        return points.get(ladderPos + 1);
    }

    private boolean isLastPos(int playerPos) {
        return playerPos == maxLength - 1;
    }

    private void moveLeft(Player player, int playerPos) {
        if (isLeftLadder(playerPos))
            player.moveLeft();
    }

    private void moveRight(Player player, int playerPos) {
        if (isLadder(playerPos))
            player.moveRight();
    }

    private boolean isFirstPos(int pos) {
        return pos == FIRST_POS;
    }
}
