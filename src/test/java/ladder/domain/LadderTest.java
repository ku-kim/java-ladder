package ladder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LadderTest {

    /*
     * | |
     * | |
     * */
    @Test
    @DisplayName("플레이어 2명, 사다리 2층, 연결 X")
    void player_2_line_2() {
        Ladder ladder = init_player_2_line_2();

        ladder.run();
        assertThat(ladder.getPlayers().get(0).getPos()).isEqualTo(0);
        assertThat(ladder.getPlayers().get(1).getPos()).isEqualTo(1);
    }

    /*
     * |-|
     * | |
     * */
    @Test
    @DisplayName("플레이어 2명, 사다리 2층, 사다리 1층 연결 O")
    void player_2_line_2_layer_1_connection_1() {
        Ladder ladder = init_player_2_line_2();

        ladder.drawLadder(0, 0);
        ladder.run();

        assertThat(ladder.getPlayers().get(0).getPos()).isEqualTo(1);
        assertThat(ladder.getPlayers().get(1).getPos()).isEqualTo(0);
    }

    /*
     * |-|
     * |-|
     * */
    @Test
    @DisplayName("플레이어 2명, 사다리 2층, 사다리 2층 연결 O")
    void player_2_line_2_layer_1_2_connection_1() {
        Ladder ladder = init_player_2_line_2();

        ladder.drawLadder(0, 0);
        ladder.drawLadder(1, 0);
        ladder.run();

        assertThat(ladder.getPlayers().get(0).getPos()).isEqualTo(0);
        assertThat(ladder.getPlayers().get(1).getPos()).isEqualTo(1);
    }

    /*
     * | | | |
     * | | | |
     * | | | |
     * */
    @Test
    @DisplayName("플레이어 4명, 사다리 3층, 연결 X")
    void player_4_line_3_layer_1_2_3_connection_0() {
        Ladder ladder = init_player_4_line_3();

        ladder.run();

        assertThat(ladder.getPlayers().get(0).getPos()).isEqualTo(0);
        assertThat(ladder.getPlayers().get(1).getPos()).isEqualTo(1);
        assertThat(ladder.getPlayers().get(2).getPos()).isEqualTo(2);
        assertThat(ladder.getPlayers().get(3).getPos()).isEqualTo(3);
    }

    /*
     * | | | |
     * |-| |-|
     * | |-| |
     * */
    @Test
    @DisplayName("플레이어 4명, 사다리 3층, 연결 복잡")
    void player_4_line_3_layer_2__connection_2_layer_3_conneciton_1() {
        Ladder ladder = init_player_4_line_3();

        ladder.drawLadder(1,0);
        ladder.drawLadder(1,2);
        ladder.drawLadder(2,1);
        ladder.run();

        assertThat(ladder.getPlayers().get(0).getPos()).isEqualTo(2);
        assertThat(ladder.getPlayers().get(1).getPos()).isEqualTo(0);
        assertThat(ladder.getPlayers().get(2).getPos()).isEqualTo(3);
        assertThat(ladder.getPlayers().get(3).getPos()).isEqualTo(1);
    }

    private Ladder init_player_2_line_2() {
        List<Player> players = new ArrayList<>();
        players.add(Player.valueOf("kukim", 0));
        players.add(Player.valueOf("lucid", 1));

        List<Item> items = new ArrayList<>();
        items.add(Item.of("꽝"));
        items.add(Item.of("300"));

        return Ladder.valueOf(players, items, 2);
    }

    private Ladder init_player_4_line_3() {
        List<Player> players = new ArrayList<>();
        players.add(Player.valueOf("kukim", 0));
        players.add(Player.valueOf("lucid", 1));
        players.add(Player.valueOf("phill", 2));
        players.add(Player.valueOf("ader", 3));

        List<Item> items = new ArrayList<>();
        items.add(Item.of("꽝"));
        items.add(Item.of("1000"));
        items.add(Item.of("꽝"));
        items.add(Item.of("3000"));

        return Ladder.valueOf(players, items, 3);
    }
}
