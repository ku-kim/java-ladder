package ladder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Line 클래스")
class LineTest {

    @Nested
    @DisplayName("createLineWithPlayerCount 메소드는")
    class Describe_createLineWithPlayerCount {

        @Nested
        @DisplayName("만약 음수값이 주어진다면")
        class Context_with_negative {
            @Test
            @DisplayName("IllegalArgumentException 예외를 던진다.")
            void it_throws_a_exception() {
                assertThatThrownBy(() -> Line.createLineWithPlayerCount(-1))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("만약 양수 n이 주어진다면")
        class Context_with_positive {
            @Test
            @DisplayName("주어진 n개의 point를 갖고, 사다리가 비어있는 Line 객체를 리턴한다.")
            void it_returns_a_vaild_line() {
                Line result = Line.createLineWithPlayerCount(3);

                assertThat(result.isLadder(0)).isFalse();
                assertThat(result.isLadder(1)).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("drawLadder 메소드는")
    class Describe_isLadder {

        @Nested
        @DisplayName("먄약 Line(3) 객체에 첫 번째 위치에 1개의 사다리를 그린다면 ")
        class Context_with_one_ladder {
            @Test
            @DisplayName("Line의 첫 번째 위치에만 사다리를 그린다.")
            void it_draws_a_ladder() {
                Line line = Line.createLineWithPlayerCount(3);
                line.drawLadder(0);

                assertThat(line.isLadder(0)).isTrue();
                assertThat(line.isLadder(1)).isFalse();
            }
        }

        @Nested
        @DisplayName("먄약 Line(3) 객체에 두 개의 사다리를 그린다면")
        class Context_with_two_ladder {
            @Test
            @DisplayName("연속되게 그릴 수 없기 때문에 처음 그린 위치에만 사다리를 그린다.")
            void it_draws_a_ladder() {
                Line line = Line.createLineWithPlayerCount(3);
                line.drawLadder(0);
                line.drawLadder(1);

                assertThat(line.isLadder(0)).isTrue();
                assertThat(line.isLadder(1)).isFalse();
            }
        }

        @Nested
        @DisplayName("만약 그릴 수 없는 곳에 사다리를 그린다면")
        class Context_with_draw_ladder_in_error_pos {
            @Test
            @DisplayName("IllegalArgumentException 예외를 던진다.")
            void it_throws_a_exception() {
                Line line = Line.createLineWithPlayerCount(3);

                assertThatThrownBy(() -> line.drawLadder(-1))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("먄약 Line(5) 객체에 5개의 사다리를 그린다면")
        class Context_with_fully_draw_ladder {
            @Test
            @DisplayName("연속되게 그릴 수 없기 때문에 처음, 중간만 그린다.")
            void it_draws_a_ladder() {
                Line line = Line.createLineWithPlayerCount(5);
                line.drawLadder(0);
                line.drawLadder(1);
                line.drawLadder(2);
                line.drawLadder(3);

                assertThat(line.isLadder(0)).isTrue();
                assertThat(line.isLadder(1)).isFalse();
                assertThat(line.isLadder(2)).isTrue();
                assertThat(line.isLadder(3)).isFalse();
            }
        }
    }

    @Test
    @DisplayName("사람 2명, 사다리 모양 | |")
    void player2_connection_0() {
        Line line = Line.createLineWithPlayerCount(2);
        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);

        line.run(player1);
        line.run(player2);

        assertThat(player1.getPos()).isEqualTo(0);
        assertThat(player2.getPos()).isEqualTo(1);
    }

    @Test
    @DisplayName("사람 2명, 사다리 모양 |-|")
    void player2_connection_1() {
        Line line = Line.createLineWithPlayerCount(2);
        line.drawLadder(0);
        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);

        line.run(player1);
        line.run(player2);

        assertThat(player1.getPos()).isEqualTo(1);
        assertThat(player2.getPos()).isEqualTo(0);
    }

    @Test
    @DisplayName("사람 3명, 사다리 모양 | | |")
    void player3_connection_0() {
        Line line = Line.createLineWithPlayerCount(3);

        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);
        Player player3 = Player.valueOf("phill", 2);

        line.run(player1);
        line.run(player2);
        line.run(player3);


        assertThat(player1.getPos()).isEqualTo(0);
        assertThat(player2.getPos()).isEqualTo(1);
        assertThat(player3.getPos()).isEqualTo(2);
    }

    @Test
    @DisplayName("사람 3명, 사다리 모양 |-| |")
    void player3_left_connection_1() {
        Line line = Line.createLineWithPlayerCount(3);
        line.drawLadder(0);

        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);
        Player player3 = Player.valueOf("phill", 2);

        line.run(player1);
        line.run(player2);
        line.run(player3);

        assertThat(player1.getPos()).isEqualTo(1);
        assertThat(player2.getPos()).isEqualTo(0);
        assertThat(player3.getPos()).isEqualTo(2);
    }

    @Test
    @DisplayName("사람 3명, 사다리 모양 | |-|")
    void player3_right_connection_1() {
        Line line = Line.createLineWithPlayerCount(3);
        line.drawLadder(1);

        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);
        Player player3 = Player.valueOf("phill", 2);

        line.run(player1);
        line.run(player2);
        line.run(player3);

        assertThat(player1.getPos()).isEqualTo(0);
        assertThat(player2.getPos()).isEqualTo(2);
        assertThat(player3.getPos()).isEqualTo(1);
    }

    @Test
    @DisplayName("사람 4명, 사다리 모양 | | | |")
    void player4_connection_0() {
        Line line = Line.createLineWithPlayerCount(4);

        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);
        Player player3 = Player.valueOf("phill", 2);
        Player player4 = Player.valueOf("miller", 3);

        line.run(player1);
        line.run(player2);
        line.run(player3);
        line.run(player4);

        assertThat(player1.getPos()).isEqualTo(0);
        assertThat(player2.getPos()).isEqualTo(1);
        assertThat(player3.getPos()).isEqualTo(2);
        assertThat(player4.getPos()).isEqualTo(3);
    }

    @Test
    @DisplayName("사람 4명, 사다리 모양 |-| | |")
    void player4_left_connection_1() {
        Line line = Line.createLineWithPlayerCount(4);
        line.drawLadder(0);

        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);
        Player player3 = Player.valueOf("phill", 2);
        Player player4 = Player.valueOf("miller", 3);

        line.run(player1);
        line.run(player2);
        line.run(player3);
        line.run(player4);

        assertThat(player1.getPos()).isEqualTo(1);
        assertThat(player2.getPos()).isEqualTo(0);
        assertThat(player3.getPos()).isEqualTo(2);
        assertThat(player4.getPos()).isEqualTo(3);
    }

    @Test
    @DisplayName("사람 4명, 사다리 모양 | |-| |")
    void player4_center_connection_1() {
        Line line = Line.createLineWithPlayerCount(4);
        line.drawLadder(1);

        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);
        Player player3 = Player.valueOf("phill", 2);
        Player player4 = Player.valueOf("miller", 3);

        line.run(player1);
        line.run(player2);
        line.run(player3);
        line.run(player4);

        assertThat(player1.getPos()).isEqualTo(0);
        assertThat(player2.getPos()).isEqualTo(2);
        assertThat(player3.getPos()).isEqualTo(1);
        assertThat(player4.getPos()).isEqualTo(3);
    }

    @Test
    @DisplayName("사람 4명, 사다리 모양 | | |-|")
    void player4_right_connection_1() {
        Line line = Line.createLineWithPlayerCount(4);
        line.drawLadder(2);

        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);
        Player player3 = Player.valueOf("phill", 2);
        Player player4 = Player.valueOf("miller", 3);

        line.run(player1);
        line.run(player2);
        line.run(player3);
        line.run(player4);

        assertThat(player1.getPos()).isEqualTo(0);
        assertThat(player2.getPos()).isEqualTo(1);
        assertThat(player3.getPos()).isEqualTo(3);
        assertThat(player4.getPos()).isEqualTo(2);
    }

    @Test
    @DisplayName("사람 4명, 사다리 모양 |-| |-|")
    void player4_all_connection() {
        Line line = Line.createLineWithPlayerCount(4);
        line.drawLadder(0);
        line.drawLadder(2);

        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);
        Player player3 = Player.valueOf("phill", 2);
        Player player4 = Player.valueOf("miller", 3);

        line.run(player1);
        line.run(player2);
        line.run(player3);
        line.run(player4);

        assertThat(player1.getPos()).isEqualTo(1);
        assertThat(player2.getPos()).isEqualTo(0);
        assertThat(player3.getPos()).isEqualTo(3);
        assertThat(player4.getPos()).isEqualTo(2);
    }

    @Test
    @DisplayName("사람 5명, 라인 1줄, 사다리 |-| |-| |")
    void Player4_Connection2() {
        Line line = Line.createLineWithPlayerCount(5);
        line.drawLadder(0);
        line.drawLadder(2);

        Player player1 = Player.valueOf("lucid", 0);
        Player player2 = Player.valueOf("ader", 1);
        Player player3 = Player.valueOf("phill", 2);
        Player player4 = Player.valueOf("miller", 3);
        Player player5 = Player.valueOf("kukim", 4);

        line.run(player1);
        line.run(player2);
        line.run(player3);
        line.run(player4);
        line.run(player5);

        assertThat(player1.getPos()).isEqualTo(1);
        assertThat(player2.getPos()).isEqualTo(0);
        assertThat(player3.getPos()).isEqualTo(3);
        assertThat(player4.getPos()).isEqualTo(2);
        assertThat(player5.getPos()).isEqualTo(4);
    }
}