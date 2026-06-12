package model;

import utils.Validator;

/**
 *
 * @author The Miracle Invoker
 */
public class Player {

    private String playerId;
    private String clubId;
    private String playerName;
    private String position;
    private int shirtNumber;

    public Player() {
    }

    public Player(String playerId, String clubId, String playerName, String position, int shirtNumber) {
        this.playerId = playerId;
        this.clubId = clubId;
        this.playerName = playerName;
        this.position = position;
        this.shirtNumber = shirtNumber;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getClubId() {
        return clubId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPosition(String position) {
        if (Validator.isValidPosition(position)) {
            this.position = position;
        }
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-8s | %-25s | %-12s | %2d",
                playerId, clubId, playerName, position, shirtNumber);
    }

    public String toFileString() {
        return String.format("%s, %s, %s, %s, %d",
                playerId, clubId, playerName, position, shirtNumber);
    }
}
