package ladder.domain;

import ladder.dto.Item;
import ladder.dto.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ladder 클래스")
class LadderTest {

    @Nested
    @DisplayName("run 메소드는")
    class Describe_run {
        /*
         * | |
         * | |
         * */
        @Nested
        @DisplayName("플레이어 2명, 사다리 2층, 연결 X")
        class Context_2_player_2_line_0_connection {

            @Test
            @DisplayName("2명 위치는 유지한다.")
            void it_maintain_2_position() {
                Ladder ladder = init_player_2_line_2();

                ladder.run();
                assertThat(ladder.getPlayers().get(0).getPos()).isEqualTo(0);
                assertThat(ladder.getPlayers().get(1).getPos()).isEqualTo(1);
            }
        }

        /*
         * |-|
         * | |
         * */
        @Nested
        @DisplayName("플레이어 2명, 사다리 2층, 연결 X")
        class Context_2_player_2_line_1_layer_1_connection {

            @Test
            @DisplayName("2명의 위치는 바뀐다. 0->1 , 1->0")
            void it_swap_2_position() {
                Ladder ladder = init_player_2_line_2();

                ladder.drawLadder(0, 0);
                ladder.run();

                assertThat(ladder.getPlayers().get(0).getPos()).isEqualTo(1);
                assertThat(ladder.getPlayers().get(1).getPos()).isEqualTo(0);
            }
        }

        /*
         * |-|
         * |-|
         * */
        @Nested
        @DisplayName("플레이어 2명, 사다리 2층, 사다리 2층 연결 O")
        class Context_2_player_2_line_0_layer_1_2_connection_1 {
            @Test
            @DisplayName("2명 위치는 유지한다.")
            void it_maintain_2_position() {
                Ladder ladder = init_player_2_line_2();

                ladder.drawLadder(0, 0);
                ladder.drawLadder(1, 0);
                ladder.run();

                assertThat(ladder.getPlayers().get(0).getPos()).isEqualTo(0);
                assertThat(ladder.getPlayers().get(1).getPos()).isEqualTo(1);
            }
        }

        /*
         * | | | |
         * | | | |
         * | | | |
         * */
        @Nested
        @DisplayName("플레이어 2명, 사다리 2층, 연결 X")
        class Context_4_player_4_line_0_connection {

            @Test
            @DisplayName("4명 위치는 유지한다.")
            void it_maintain_4_position() {
                Ladder ladder = init_player_4_line_3();

                ladder.run();

                assertThat(ladder.getPlayers().get(0).getPos()).isEqualTo(0);
                assertThat(ladder.getPlayers().get(1).getPos()).isEqualTo(1);
                assertThat(ladder.getPlayers().get(2).getPos()).isEqualTo(2);
                assertThat(ladder.getPlayers().get(3).getPos()).isEqualTo(3);
            }
        }

        /*
         * | | | |
         * |-| |-|
         * | |-| |
         * */
        @Nested
        @DisplayName("플레이어 4명, 사다리 3층, 연결 복잡")
        class Context_4_player_3_line_complex_connection {

            @Test
            @DisplayName("4명 위치는 복잡하다")
            void it_swap_4_position() {
                Ladder ladder = init_player_4_line_3();

                ladder.drawLadder(1, 0);
                ladder.drawLadder(1, 2);
                ladder.drawLadder(2, 1);
                ladder.run();

                assertThat(ladder.getPlayers().get(0).getPos()).isEqualTo(2);
                assertThat(ladder.getPlayers().get(1).getPos()).isEqualTo(0);
                assertThat(ladder.getPlayers().get(2).getPos()).isEqualTo(3);
                assertThat(ladder.getPlayers().get(3).getPos()).isEqualTo(1);
            }
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
}
