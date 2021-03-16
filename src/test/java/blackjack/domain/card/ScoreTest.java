package blackjack.domain.card;

import blackjack.domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @DisplayName("Score 객체를 생성한다.")
    @Test
    public void createScore() {
        Score score = new Score(16);

        assertThat(score).isInstanceOf(Score.class);
    }

    @DisplayName("총합에서 10을 뺀다. - 버스트가 아닌 경우")
    @Test
    public void minusTenIfBustTrue() {
        Score score = new Score(22);

        assertThat(score.minusTenIfBust()).isEqualTo(new Score(12));
    }

    @DisplayName("총합에서 10을 빼지 않는다. - 버스트가 아닌 경우")
    @Test
    public void minusTenIfBustFalse() {
        Score score = new Score(20);

        assertThat(score.minusTenIfBust()).isEqualTo(new Score(20));
    }

    @DisplayName("버스트인지 확인한다. - 버스트인 경우")
    @Test
    public void isBustTrue() {
        Score score = new Score(22);

        assertThat(score.isBust()).isTrue();
    }

    @DisplayName("버스트인지 확인한다. - 버스트가 아닌 경우")
    @Test
    public void isBustFalse() {
        Score score = new Score(20);

        assertThat(score.isBust()).isFalse();
    }

    @DisplayName("블랙잭인지 확인한다. - 블랙잭인 경우")
    @Test
    public void isBlackjackTrue() {
        Score score = new Score(21);

        assertThat(score.isBlackjack()).isTrue();
    }

    @DisplayName("블랙잭인지 확인한다. - 블랙잭이 아닌 경우")
    @Test
    public void isBlackjackFalse() {
        Score score = new Score(20);

        assertThat(score.isBlackjack()).isFalse();
    }

    @DisplayName("플레이어가 힛인지 확인한다. - 힛인 경우")
    @Test
    public void isPlayerHitTrue() {
        Score score = new Score(18);

        assertThat(score.isPlayerHit()).isTrue();
    }

    @DisplayName("플레이어가 힛인지 확인한다. - 힛이 아닌 경우")
    @Test
    public void isPlayerHitFalse() {
        Score score = new Score(23);

        assertThat(score.isPlayerHit()).isFalse();
    }

    @DisplayName("딜러가 힛인지 확인한다. - 힛인 경우")
    @Test
    public void isDealerHitTrue() {
        Score score = new Score(15);

        assertThat(score.isDealerHit()).isTrue();
    }

    @DisplayName("딜러가 힛인지 확인한다. - 힛이 아닌 경우")
    @Test
    public void isDealerHitFalse() {
        Score score = new Score(17);

        assertThat(score.isDealerHit()).isFalse();
    }
}
