package ladder.view;

import ladder.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new IllegalStateException("Utility class");
    }

    public static int getInputNumber(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }

    public static void close() {
        scanner.close();
    }

    public static List<Player> getInputPlayers(String message) {
        System.out.println(message);

        List<Player> players = new ArrayList<>();

        String[] names = scanner.nextLine().replaceAll(" ", "").split(",");

        for (String name : names) {
            players.add(Player.createPlayerWithName(name));
        }

        return players;
    }
}
