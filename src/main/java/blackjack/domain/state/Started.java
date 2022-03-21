package blackjack.domain.state;

import blackjack.domain.game.PlayingCards;

public abstract class Started implements State {

    protected final PlayingCards playingCards;

    Started(final PlayingCards playingCards) {
        this.playingCards = playingCards;
    }

    public PlayingCards getPlayingCards() {
        return playingCards;
    }

    public PlayingCards getPartOfPlayingCards() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(this.playingCards.getPartOfCard());

        return playingCards;
    }

    public int cardTotal() {
        return playingCards.total();
    }
}
