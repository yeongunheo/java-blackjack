package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String REQUEST_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NAME_DELIMITER = ",";

    public static List<String> requestNames() {
        System.out.println(REQUEST_NAMES_MESSAGE);
        String input = readLine();
        System.out.println();
        return Arrays.asList(input.split(NAME_DELIMITER));
    }

    private static String readLine() {
        return scanner.nextLine();
    }
}
