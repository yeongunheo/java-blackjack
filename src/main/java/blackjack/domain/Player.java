package blackjack.domain;

import blackjack.exception.UserNameEmptyException;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
    private static final int USER_INITIAL_CARDS_SIZE = 2;

    public Player(String name) {
        if (name.isEmpty()) {
            throw new UserNameEmptyException("유저의 이름은 공백일 수 없습니다.");
        }
        this.name = name;
        this.status = Status.NONE;
    }

    @Override
    public List<Card> getInitialCards() {
        return new ArrayList<>(cards)
                .subList(START_INDEX, USER_INITIAL_CARDS_SIZE);
    }

    @Override
    public boolean isReceivableOneMoreCard() {
        return this.status == Status.NONE;
    }
}
