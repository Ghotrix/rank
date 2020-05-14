public class Player implements Comparable<Player> {
    private final String name;
    private Integer score;

    Player(String name, Integer score){
        this.name = name;
        this.score = score;
    }
    String getName(){return name;}
    Integer getScore(){return score;}
    void win(){score += 3;}
    void tie(){score += 1;}

    @Override
    public int compareTo(Player player) {
        int scoreDiff = player.getScore() - score;
        return scoreDiff != 0 ? scoreDiff : this.name.compareTo(player.getName());
    }

    public String getStringScore() {
        return String.format("%d %s", score, score == 1 ? "pt" : "pts");
    }
}
