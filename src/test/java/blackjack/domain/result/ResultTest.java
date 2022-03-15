package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    private Dealer dealer;
    private Players players;
    private Player player;
    private Result result;
    private Card aceSpade;
    private Card twoSpade;
    private Card threeSpade;
    private Card queenSpade;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        players = new Players("test1, test2");
        player = new Player("pobi");
        result = new Result(players);
        aceSpade = Card.of(CardNumber.ACE, CardShape.SPADE);
        twoSpade = Card.of(CardNumber.TWO, CardShape.SPADE);
        threeSpade = Card.of(CardNumber.THREE, CardShape.SPADE);
        queenSpade = Card.of(CardNumber.QUEEN, CardShape.SPADE);
    }

    @DisplayName("초기 카드를 받은 후 딜러가 블랙잭이면 게임을 종료하는지 확인한다.")
    @Test
    void is_keep_playing_dealer_blackjack() {
        dealer.dealCards(List.of(queenSpade, aceSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(twoSpade, threeSpade));
        }

        assertThat(result.isKeepPlaying(dealer)).isFalse();
    }

    @DisplayName("초기 카드를 받은 후 딜러가 블랙잭 아닌데 모든 플레이어 블랙잭이면 게임을 종료하는지 확인한다.")
    @Test
    void is_keep_playing_all_player_blackjack() {
        dealer.dealCards(List.of(twoSpade, threeSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade, aceSpade));
        }

        assertThat(result.isKeepPlaying(dealer)).isFalse();
    }

    @DisplayName("초기 카드를 받은 후 딜러가 블랙잭 아닌데 블랙잭이 아닌 플레이어가 있으면 게임을 진행하는 것을 확인한다.")
    @Test
    void is_keep_playing_any_player_blackjack() {
        dealer.dealCards(List.of(twoSpade, threeSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(twoSpade, threeSpade));
        }

        assertThat(result.isKeepPlaying(dealer)).isTrue();
    }

    @DisplayName("플레이어가 버스트일 경우 패배임을 확인한다.")
    @Test
    void compete_player_bust() {
        dealer.dealCards(List.of(queenSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade, queenSpade, queenSpade));
        }

        result.compete(dealer);

        for (Player player : players.getPlayers()) {
            assertThat(result.getGrade(player)).isEqualTo(Grade.LOSE);
        }
    }

    @DisplayName("딜러가 버스트일 경우 승리임을 확인한다.")
    @Test
    void compete_dealer_bust() {
        dealer.dealCards(List.of(queenSpade, queenSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade, queenSpade));
        }

        result.compete(dealer);

        for (Player player : players.getPlayers()) {
            assertThat(result.getGrade(player)).isEqualTo(Grade.WIN);
        }
    }

    @DisplayName("딜러와 플레이어가 버스트가 아니며 점수가 동일할 경우 무승부임을 확인한다.")
    @Test
    void compete_tie() {
        dealer.dealCards(List.of(queenSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade, queenSpade));
        }

        result.compete(dealer);

        for (Player player : players.getPlayers()) {
            assertThat(result.getGrade(player)).isEqualTo(Grade.TIE);
        }
    }

    @DisplayName("딜러와 플레이어가 버스트가 아니며 딜러의 점수가 클 경우 패배임을 확인한다.")
    @Test
    void compete_lose() {
        dealer.dealCards(List.of(queenSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade));
        }

        result.compete(dealer);

        for (Player player : players.getPlayers()) {
            assertThat(result.getGrade(player)).isEqualTo(Grade.LOSE);
        }
    }

    @DisplayName("딜러와 플레이어가 버스트가 아니며 플레이어의 점수가 클 경우 승리임을 확인한다.")
    @Test
    void compete_win() {
        dealer.dealCards(List.of(queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade, queenSpade));
        }

        result.compete(dealer);

        for (Player player : players.getPlayers()) {
            assertThat(result.getGrade(player)).isEqualTo(Grade.WIN);
        }
    }
}
