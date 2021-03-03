package blackjack.domain.player;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Cards;
import blackjack.exception.GamerDuplicateException;
import blackjack.exception.PlayerNotFoundException;
import blackjack.util.BlackjackScoreCalculator;
import blackjack.util.ScoreCalculator;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Gamers {

    private final List<Player> gamers;

    public Gamers(List<String> names, ScoreCalculator scoreCalculator) {
        validateDuplicate(names);
        this.gamers = namesToGamers(names, scoreCalculator);
    }

    public Gamers(String... input) {
        this(Arrays.asList(input), new BlackjackScoreCalculator());
    }

    public Gamers(ScoreCalculator scoreCalculator, String... input) {
        this(Arrays.asList(input), scoreCalculator);
    }

    private void validateDuplicate(List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new GamerDuplicateException();
        }
    }

    private List<Player> namesToGamers(List<String> names, ScoreCalculator scoreCalculator) {
        return names.stream()
            .map(name -> new Gamer(name, scoreCalculator))
            .collect(Collectors.toList());
    }

    public void drawToGamers(Cards cards) {
        for (Player gamer : gamers) {
            gamer.addCardToDeck(cards.next());
        }
    }

    public Player findGamer(String name) {
        return gamers.stream()
            .filter(gamer -> gamer.isSameName(name))
            .findAny()
            .orElseThrow(PlayerNotFoundException::new);
    }

    public List<Player> getGamers() {
        return Collections.unmodifiableList(gamers);
    }

    public List<String> getGamerNames() {
        return gamers.stream()
            .map(Player::getName)
            .collect(toList());
    }
}

