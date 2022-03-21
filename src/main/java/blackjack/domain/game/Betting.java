package blackjack.domain.game;

public class Betting {

    private static final int MONEY_UNIT = 10;

    private final int betting;

    public Betting(final String betting) {
        this(Integer.parseInt(betting));
    }

    public Betting(final int betting) {
        validateBetting(betting);
        this.betting = betting;
    }

    public double getEarning(final double rate) {
        return betting * rate;
    }

    private void validateBetting(final int betting) {
        validateNegative(betting);
        validateMoneyUnit(betting);
    }

    private void validateNegative(final int betting) {
        if (betting < 0) {
            throw new IllegalArgumentException("베팅 금액에 음수를 입력할 수 없습니다.");
        }
    }

    private void validateMoneyUnit(final int betting) {
        if (betting % MONEY_UNIT != 0) {
            throw new IllegalArgumentException("베팅 금액은 " + MONEY_UNIT + "원 단위로 입력해주세요.");
        }
    }
}
