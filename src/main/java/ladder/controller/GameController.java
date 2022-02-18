package ladder.controller;

import ladder.domain.Item;
import ladder.view.InputView;
import ladder.domain.Ladder;
import ladder.domain.Player;
import ladder.view.OutputView;

import java.util.List;

public class GameController {
    public static void run() {
        List<Player> players = InputView.getInputPlayers("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
        List<Item> items = InputView.getInputItems("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");
        int ladderMaxHeight = InputView.getInputNumber("최대 사다리 높이는 몇 개인가요?");

        Ladder ladder = Ladder.valueOf(players, items, ladderMaxHeight);
        ladder.drawRandomLines();

        OutputView.print(ladder);
        InputView.close();
    }

    public static void main(String[] args) {
        GameController.run();
    }
}
