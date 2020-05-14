import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerTest extends TestCase {
    private final List<Player> players = new ArrayList<Player>();

    public void setUp() throws Exception {
        super.setUp();

        List<String> playerNames = new ArrayList<String>(
                List.of("Lions", "Snakes", "Tarantulas", "FC Awesome", "Grouches"));
        List<Integer> playerScores = new ArrayList<Integer>(
                List.of(5, 1, 6, 1, 0));
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), playerScores.get(i)));
        }

        Collections.sort(players);
    }

    public void testCompareTo() {
        assertTrue(players.get(0).getName().equals("Tarantulas"));
        assertTrue(players.get(4).getName().equals("Grouches"));
        assertTrue(players.get(2).getName().equals("FC Awesome"));
        assertTrue(players.get(3).getName().equals("Snakes"));
    }

    public void testGetStringScore() {
        assertTrue(players.get(3).getStringScore().equals("1 pt"));
        assertTrue(players.get(0).getStringScore().equals("6 pts"));
    }
}