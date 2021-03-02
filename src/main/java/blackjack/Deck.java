package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final List<Card> cards = new ArrayList<>();

    static {
        for (Shape shape : Shape.values()) {
            cards.addAll(cardsOf(shape));
        }
    }

    private static List<Card> cardsOf(Shape shape) {
        return Arrays.stream(Number.values())
              .map(number -> new Card(number, shape))
              .collect(Collectors.toList());
    }

    public static List<Card> getCards() {
        return cards;
    }
}
