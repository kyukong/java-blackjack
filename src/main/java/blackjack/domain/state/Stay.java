package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Stay extends Finished {

    // TODO: 딜러와 비교하여 값 변경
    private static final double PROFIT_RATE = 1;

    private final PlayingCards playingCards;
    private final Betting betting;

    public Stay(final PlayingCards playingCards, final Betting betting) {
        this.playingCards = playingCards;
        this.betting = betting;
    }

    @Override
    public void bet(final String betting) {
        throw new IllegalStateException("Stay 상태일 때는 bet 을 실행할 수 없습니다.");
    }

    @Override
    public State draw(final Card card) {
        throw new IllegalStateException("Stay 상태일 때는 draw 를 실행할 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("Stay 상태일 때는 stay 를 실행할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit() {
        return betting.profit(PROFIT_RATE);
    }

    @Override
    public PlayingCards playingCards() {
        return playingCards;
    }

    @Override
    public PlayingCards partOfPlayingCards() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(this.playingCards.getPartOfCard());

        return playingCards;
    }

    @Override
    public int cardTotal() {
        return playingCards.total();
    }
}
