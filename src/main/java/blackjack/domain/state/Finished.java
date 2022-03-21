package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public abstract class Finished extends Started {

    protected static final double DEFAULT_RATE = 1;

    protected double earningRate = DEFAULT_RATE;

    Finished(final PlayingCards playingCards) {
        super(playingCards);
    }

    public State draw(final Card card) {
        throw new IllegalStateException("현재 상태는 draw 를 실행할 수 없습니다.");
    }

    public State stay() {
        throw new IllegalStateException("현재 상태는 stay 를 실행할 수 없습니다.");
    }

    public boolean isRunning() {
        return false;
    }

    public boolean isFinished() {
        return true;
    }

    public double getEarningRate() {
        return earningRate;
    }

    public void decideRate(final double rate) {
        earningRate = rate;
    }
}
