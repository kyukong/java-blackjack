package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;

public enum Grade {

    WIN("승"),
    TIE("무"),
    LOSE("패"),
    PROCEED("진행")
    ;

    private final String grade;

    Grade(final String grade) {
        this.grade = grade;
    }

    public static Grade gradeToInitCards(final Dealer dealer, final Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return TIE;
        }
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return LOSE;
        }
        if (!dealer.isBlackjack() && player.isBlackjack()) {
            return WIN;
        }
        return PROCEED;
    }

    public static Grade grade(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust() || dealer.isLowerScore(player)) {
            return WIN;
        }
        if (dealer.isHigherScore(player)) {
            return LOSE;
        }
        return TIE;
    }

    public String getGrade() {
        return grade;
    }
}
