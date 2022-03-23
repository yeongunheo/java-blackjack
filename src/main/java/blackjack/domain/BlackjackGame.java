package blackjack.domain;

import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Bets;
import blackjack.domain.card.Hand;
import blackjack.domain.card.deck.Deck;
import blackjack.domain.dto.ResponseCardResultDto;
import blackjack.domain.dto.ResponseInitHandDto;
import blackjack.domain.dto.ResponseProfitDto;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.domain.user.UserName;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {

    private Players players;
    private User dealer;

    public Players start(List<String> names) {
        List<Player> players = names.stream()
                .map(UserName::new)
                .map(Player::new)
                .collect(Collectors.toList());
        this.players = new Players(players);
        this.dealer = new Dealer();

        return this.players;
    }

    public ResponseInitHandDto takeInitHand(Deck deck) {
        dealer.takeInitHand(deck);
        players.takeInitHand(deck);

        return new ResponseInitHandDto(dealer, players);
    }

    public User takePlayerCard(User player, Deck deck) {
        player.hit(deck.pick());
        return player;
    }

    public User takeDealerCard(Deck deck) {
        dealer.hit(deck.pick());
        return dealer;
    }

    public ResponseCardResultDto calculateCardResult() {
        Map<String, Hand> results = new LinkedHashMap<>();
        results.put(dealer.getName().get(), dealer.getCards());
        for (User player : players.get()) {
            results.put(player.getName().get(), player.getCards());
        }

        return new ResponseCardResultDto(results);
    }

    public ResponseProfitDto calculateProfit(Map<String, BetMoney> playerNameAndBets) {
        Map<String, Integer> playersProfit = new LinkedHashMap<>();
        Bets bets = new Bets(playerNameAndBets);
        for (User player : players.get()) {
            double profit = bets.calculatePlayerProfit(dealer, player);
            playersProfit.put(player.getName().get(), (int) profit);
        }
        int dealerProfit = bets.calculateDealerProfit(playersProfit);

        return new ResponseProfitDto(dealerProfit, playersProfit);
    }

    public boolean isPlayerFinished(User player) {
        return !player.isValidRange();
    }

    public boolean isDealerFinished() {
        return !dealer.isValidRange();
    }
}
