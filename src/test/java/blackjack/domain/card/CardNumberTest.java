package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardNumberTest {

    @DisplayName("블랙잭인지 확인한다.")
    @Test
    void is_blackjack_true() {
        List<CardNumber> cardNumbers = new ArrayList<>(List.of(CardNumber.ACE, CardNumber.QUEEN));

        assertThat(CardNumber.isBlackjack(cardNumbers)).isTrue();
    }

    @DisplayName("블랙잭이 아닌 것을 확인한다.")
    @Test
    void is_blackjack_false() {
        List<CardNumber> cardNumbers = new ArrayList<>(List.of(CardNumber.ACE, CardNumber.QUEEN, CardNumber.QUEEN));

        assertThat(CardNumber.isBlackjack(cardNumbers)).isFalse();
    }

    @DisplayName("모든 카드의 합계를 확인한다.")
    @Test
    void total() {
        List<CardNumber> cardNumbers = new ArrayList<>(List.of(CardNumber.values()));
        int total = CardNumber.getTotal(cardNumbers);

        assertThat(total).isEqualTo(85);
    }

    @DisplayName("ACE 가 없는 경우 합계를 확인한다.")
    @Test
    void total_not_contains_ace() {
        int total = CardNumber.getTotal(List.of(CardNumber.KING, CardNumber.QUEEN));

        assertThat(total).isEqualTo(20);
    }

    @DisplayName("ACE 가 1로 사용될 경우 합계를 확인한다.")
    @Test
    void total_ace_1() {
        int total = CardNumber.getTotal(List.of(CardNumber.ACE, CardNumber.KING, CardNumber.QUEEN));

        assertThat(total).isEqualTo(21);
    }

    @DisplayName("ACE 가 11로 사용될 경우 합계를 확인한다.")
    @Test
    void total_ace_11() {
        int total = CardNumber.getTotal(List.of(CardNumber.ACE, CardNumber.QUEEN));

        assertThat(total).isEqualTo(21);
    }

    @DisplayName("ACE 가 2개일 경우 합계를 확인한다.")
    @Test
    void total_two_ace() {
        int total = CardNumber.getTotal(List.of(CardNumber.ACE, CardNumber.ACE));

        assertThat(total).isEqualTo(12);
    }

    @DisplayName("나머지 카드 합계가 11 이며 ACE 가 있을 경우 합계를 확인한다.")
    @Test
    void total_11_and_ace() {
        int total = CardNumber.getTotal(List.of(CardNumber.TWO, CardNumber.NINE, CardNumber.ACE));

        assertThat(total).isEqualTo(12);
    }
}
