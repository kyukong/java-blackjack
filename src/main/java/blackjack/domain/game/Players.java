package blackjack.domain.game;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Players {

    public static final String DELIMITER = ",";
    private static final int MAXIMUM_PLAYER = 8;

    private final List<Player> players = new ArrayList<>();

    public Players(final String input) {
        List<String> names = trimNames(input);

        validateNames(names);

        for (String name : names) {
            players.add(new Player(name));
        }
    }

    public void deal(final CardDeck cardDeck) {
        for (Player player : players) {
            player.dealCards(cardDeck.pickInit());
        }
    }

    public void draw(final CardDeck cardDeck,
                     final Predicate<Player> predicate, final BiConsumer<String, List<String>> biConsumer) {
        for (Player player : players) {
            player.draw(cardDeck, predicate, biConsumer);
        }
    }

    private List<String> trimNames(final String input) {
        return Arrays.stream(input.split(DELIMITER, -1))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private void validateNames(final List<String> names) {
        validateDuplicatedName(names);
        validateMaximumPlayer(names);
    }

    private void validateDuplicatedName(final List<String> totalNames) {
        Set<String> names = new HashSet<>(totalNames);
        if (names.size() != totalNames.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private void validateMaximumPlayer(final List<String> names) {
        if (names.size() > MAXIMUM_PLAYER) {
            throw new IllegalArgumentException("플레이어 최대 인원은 " + MAXIMUM_PLAYER + "명 입니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
