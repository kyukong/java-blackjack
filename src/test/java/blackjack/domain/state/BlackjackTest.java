package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.fixture.Fixture.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackjackTest {

    private static final PlayingCards playingCards = new PlayingCards();

    @DisplayName("draw 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void draw_exception() {
        Blackjack blackjack = new Blackjack(playingCards);

        assertThatThrownBy(() -> blackjack.draw(Card.of(Denomination.KING, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 draw 를 실행할 수 없습니다.");
    }

    @DisplayName("stay 를 실행하여 예외가 발생하는 것을 확인한다.")
    @Test
    void stay_exception() {
        Blackjack blackjack = new Blackjack(playingCards);

        assertThatThrownBy(blackjack::stay)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 stay 를 실행할 수 없습니다.");
    }

    @DisplayName("게임이 진행중인 상태인지 확인한다.")
    @Test
    void is_running() {
        Blackjack blackjack = new Blackjack(playingCards);

        assertThat(blackjack.isRunning()).isFalse();
    }

    @DisplayName("종료된 상태인지 확인한다.")
    @Test
    void is_finished() {
        Blackjack blackjack = new Blackjack(playingCards);

        assertThat(blackjack.isFinished()).isTrue();
    }

    @DisplayName("블랙잭인지 확인한다.")
    @Test
    void is_blackjack() {
        Blackjack blackjack = new Blackjack(playingCards);

        assertThat(blackjack.isBlackjack()).isTrue();
    }

    @DisplayName("버스트인지 확인한다.")
    @Test
    void is_bust() {
        Blackjack blackjack = new Blackjack(playingCards);

        assertThat(blackjack.isBust()).isFalse();
    }

    @DisplayName("카드 총합을 확인한다.")
    @Test
    void card_total() {
        playingCards.add(List.of(TEN, TEN));
        Blackjack blackjack = new Blackjack(playingCards);

        assertThat(blackjack.cardTotal()).isEqualTo(20);
    }
}