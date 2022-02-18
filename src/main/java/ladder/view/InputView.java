package ladder.view;

import ladder.dto.Item;
import ladder.dto.Player;

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
        int result = Integer.parseInt(scanner.nextLine());
        System.out.println();
        return result;
    }

    public static void close() {
        scanner.close();
    }

    public static List<Player> getInputPlayers(String message) {
        System.out.println(message);

        List<Player> players = new ArrayList<>();
        String[] names = scanner.nextLine().replaceAll(" ", "").split(",");
        System.out.println();
        for (int i = 0; i < names.length; i++) {
            players.add(Player.valueOf(names[i], i));
        }
        return players;
    }

    public static List<Item> getInputItems(String message) {
        System.out.println(message);

        List<Item> items = new ArrayList<>();
        String[] names = scanner.nextLine().replaceAll(" ", "").split(",");
        System.out.println();
        for (String name : names) {
            items.add(Item.of(name));
        }
        return items;
    }

    public static String getQuery() {
        System.out.println("결과를 보고 싶은 사람은?");
        String query = scanner.nextLine();
        System.out.println();
        return query;
    }
}
