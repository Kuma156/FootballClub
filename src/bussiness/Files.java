package bussiness;

import model.Club;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.Player;
import utils.Validator;


/**
 *
 * @author The Miracle Invoker
 */

public class Files {

    private static final String CLUBS_FILE   = "data\\clubs.txt";
    private static final String PLAYERS_FILE = "data\\players.txt";

    private final Clubs   clubService;
    private final Players playerService;

    public Files(Clubs clubService, Players playerService) {
        this.clubService   = clubService;
        this.playerService = playerService;
    }

    // F12: Save both files
    public void saveData() {
        System.out.println("\n--- Save Data to Files ---");
        boolean clubOk   = saveClubs();
        boolean playerOk = savePlayers();

        if (clubOk && playerOk) {
            clubService.setModified(false);
            playerService.setModified(false);
            System.out.println("  Data saved successfully!");
        } else {
            System.out.println("  Save failed! Please check file permissions.");
        }
    }

    // ─────────────────────────────────────────────────────────────
    // F13: Load both lists
    // ─────────────────────────────────────────────────────────────
    public void loadData() {
        System.out.println("\n--- Load Data from Files ---");
        boolean success = loadClubsFromFile() && loadPlayersFromFile();
        if (success) {
            System.out.println("  Load data successfully!");
        } else {
            System.out.println("  Load data failed!");
        }
    }

    // Auto load on start up
    public void autoLoad() {
        boolean success = loadClubsFromFile() && loadPlayersFromFile();
        if (success) {
            System.out.println("  Data loaded successfully.");
        } else {
            System.out.println("  Warning: Could not load data from files.");
        }
    }

    // Quit program
    public void saveIfModified() {
        if (clubService.isModified() || playerService.isModified()) {
            boolean clubOk   = saveClubs();
            boolean playerOk = savePlayers();
            if (clubOk && playerOk) {
                System.out.println("  Changes saved before exit.");
            } else {
                System.out.println("  Warning: Could not save changes.");
            }
        }
    }


    private boolean saveClubs() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CLUBS_FILE))) {
            for (Club c : clubService.getClubs()) {
                pw.println(c.toFileString());
            }
            return true;
        } catch (IOException e) {
            System.out.println("  Error saving clubs: " + e.getMessage());
            return false;
        }
    }

    private boolean savePlayers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PLAYERS_FILE))) {
            for (Player p : playerService.getPlayers()) {
                pw.println(p.toFileString());
            }
            return true;
        } catch (IOException e) {
            System.out.println("  Error saving players: " + e.getMessage());
            return false;
        }
    }


    // Clears the current club list and reloads from clubs.txt.

    private boolean loadClubsFromFile() {
        clubService.getClubs().clear();
        try (BufferedReader br = new BufferedReader(new FileReader(CLUBS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 4) return false;

                String clubId    = parts[0].trim();
                String clubName  = parts[1].trim();
                String sponsor   = parts[2].trim();
                String budgetStr = parts[3].trim();

                // Validate each field strictly
                if (!Validator.isValidClubId(clubId))    return false;
                if (clubName.isEmpty())                  return false;
                if (sponsor.isEmpty())                   return false;
                if (!Validator.isValidBudget(budgetStr)) return false;

                // Duplicate ID check
                if (clubService.findById(clubId) != null) return false;

                double budget = Double.parseDouble(budgetStr);
                clubService.getClubs().add(new Club(clubId, clubName, sponsor, budget));
            }
            return true;
        } catch (IOException e) {
            System.out.println("  File error (clubs): " + e.getMessage());
            return false;
        }
    }

    // Clears the current player list and reloads from players.txt.
    
    private boolean loadPlayersFromFile() {
        playerService.getPlayers().clear();
        try (BufferedReader br = new BufferedReader(new FileReader(PLAYERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 5) return false;

                String playerId   = parts[0].trim();
                String clubId     = parts[1].trim();
                String playerName = parts[2].trim();
                String position   = parts[3].trim();
                String shirtStr   = parts[4].trim();

                // Validate each field strictly
                if (!Validator.isValidPlayerId(playerId))  return false;
                if (playerName.isEmpty())                  return false;
                if (!Validator.isValidPosition(position))  return false;
                if (!Validator.isValidShirtNumber(shirtStr)) return false;

                // Club must exist
                if (clubService.findById(clubId) == null) return false;

                // Duplicate player ID check
                if (playerService.findById(playerId) != null) return false;

                int shirtNumber = Integer.parseInt(shirtStr);

                // Shirt number uniqueness within the same club
                if (playerService.shirtExistsInClub(clubId, shirtNumber)) return false;

                playerService.getPlayers().add(
                    new Player(playerId, clubId, playerName, position, shirtNumber)
                );
            }
            return true;
        } catch (IOException e) {
            System.out.println("  File error (players): " + e.getMessage());
            return false;
        }
    }
}