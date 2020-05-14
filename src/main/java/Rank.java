import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Rank {
    public static void main (String[] args) {
        if (args.length < 1) {
            System.err.println("Path to the games results expected as first argument.");
            System.exit(1);
        }

        String filename = args[0];
        HashMap<String, Player> teams = new HashMap<String, Player>();

        try
        {
            FileInputStream fis=new FileInputStream(filename);
            Scanner sc=new Scanner(fis);

            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                processLine(line, teams);
            }
            sc.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        ArrayList<Player> listTeams = new ArrayList<>(teams.values());
        Collections.sort(listTeams);
        printSortedTeams(listTeams);
    }

    private static void processLine(String line, HashMap<String, Player> result) {
        String[] teamsAndResults = line.split(", ");
        if (teamsAndResults.length != 2)
            return;
        String[] teams = new String[2];
        Integer[] goals = new Integer[2];

        // we're interested in first two teams in case there are more in one line
        for (int i=0; i < 2; i++) {
            String[] chunk = teamsAndResults[i].split(" ");
            int separator = chunk.length - 1;
            String teamName = String.join(" ", Arrays.copyOf(chunk, separator));
            int score = Integer.parseInt(chunk[separator]);

            teams[i] = teamName;
            goals[i] = score;
        }

        Player playerA = result.get(teams[0]);
        if (playerA == null)
            playerA = new Player(teams[0], 0);
            result.put(teams[0], playerA);
        Player playerB = result.get(teams[1]);
        if (playerB == null)
            playerB = new Player(teams[1], 0);
            result.put(teams[1], playerB);

        if (goals[0] == goals[1]) {
            playerA.tie();
            playerB.tie();
        }
        else if (goals[0] > goals[1]) {
            playerA.win();
        }
        else
            playerB.win();
    }

    private static void printSortedTeams(ArrayList<Player> teams) {
        int rank = 0;
        int lastScore = -1;
        for (Player p : teams) {
            String s = p.getName();
            Integer score = p.getScore();
            if (lastScore != score) {
                lastScore = score;
                rank += 1;
            }
            System.out.println(String.format("%d: %s, %s", rank, s, p.getStringScore()));
        }
    }
}
