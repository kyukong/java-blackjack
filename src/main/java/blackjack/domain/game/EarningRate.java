package blackjack.domain.game;

public enum EarningRate {

    BLACKJACK_WIN(1.5),
    WIN(1),
    TIE(0),
    LOSE(-1),
    ;

    private final double rate;

    EarningRate(final double rate) {
        this.rate = rate;
    }

    public static double rateBlackjack(final Dealer dealer, final Player player) {
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return LOSE.rate;
        }
        if (!dealer.isBlackjack() && player.isBlackjack()) {
            return BLACKJACK_WIN.rate;
        }
        return TIE.rate;
    }

    public static double rateStay(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return LOSE.rate;
        }
        if (dealer.isBust() || dealer.isLowerScore(player)) {
            return WIN.rate;
        }
        if (dealer.isHigherScore(player)) {
            return LOSE.rate;
        }
        return TIE.rate;
    }
}
