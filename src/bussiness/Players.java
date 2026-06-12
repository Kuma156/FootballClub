package bussiness;

import java.util.ArrayList;
import java.util.List;
import model.Club;
import model.Player;
import utils.UI;
import utils.Inputter;
import utils.Validator;
import utils.ViewPlayer;

/**
 *
 * @author The Miracle Invoker
 */
public class Players {
    
    private static final int SKIP = -1, CONFLICT = -2;

    private List<Player> players = new ArrayList<>();
    private boolean modified = false;

    private ViewPlayer viewPlayer = new ViewPlayer();
    private Inputter input = new Inputter();

    //F6: List players sorted by club name, then shirt number
    public void listPlayersSorted(Clubs clubs) {
        if (players.isEmpty()) {
            System.out.println("  No players found.");
            return;
        }
        List<Player> sorted = new ArrayList<>(players);
        sorted.sort((a, b) -> {
            Club ca = clubs.findById(a.getClubId());
            Club cb = clubs.findById(b.getClubId());
            String nameA = ca != null ? ca.getClubName() : a.getClubId();
            String nameB = cb != null ? cb.getClubName() : b.getClubId();
            int cmp = nameA.compareToIgnoreCase(nameB);
            return cmp != 0 ? cmp : Integer.compare(a.getShirtNumber(), b.getShirtNumber());
        });
        viewPlayer.printPlayerHeader();
        for (Player p : sorted) {
            System.out.println("  " + p);
        }
        UI.printDivider();
        System.out.println("  Total: " + sorted.size() + " player(s).");
    }

    //F7: Search by partial name
    public void searchByPartialName() {
        System.out.println("\n--- Search Player by Name ---");
        String keyword = input.readNonEmpty("  Enter partial name: ");
        List<Player> result = new ArrayList<>();
        for (Player p : players) {
            if (p.getPlayerName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        if (result.isEmpty()) {
            System.out.println("  No players found matching \"" + keyword + "\".");
        } else {
            viewPlayer.printPlayerHeader();
            for (Player p : result) {
                System.out.println("  " + p);
            }
            UI.printDivider();
            System.out.println("  Found: " + result.size() + " player(s).");
        }
    }

    //F8: Add a new player
    public void addPlayer(Clubs clubService) {
        System.out.println("\n--- Add New Player ---");

        // 1. Input and validate Player ID
        String playerId = getValidPlayerId();
        if (playerId == null) {
            return; // Abort if ID is invalid or already exists
        }
        // 2. Input and validate Club ID
        String clubId = getValidClubId(clubService);
        if (clubId == null) {
            return; // Abort if Club does not exist
        }
        // 3. Input Player Name
        String name = input.readNonEmpty("  Player Name: ");
        if (clubId == null) {
            return; // Abort if name is empty
        }
        if (!Validator.isValidName(name)) {
            return; // Abort if name is wrong format
        }
        // 4. Input and validate Position
        String position = getValidPosition();
        if (position == null) {
            return;
        }

        // 5. Input and validate Shirt Number
        int shirt = getValidShirtNumber(clubId);
        if (shirt == -1) {
            return; // Abort if shirt number is duplicated in this club
        }
        // 6. Add new player to the list
        players.add(new Player(playerId, clubId, name, position, shirt));
        modified = true;
        System.out.println("Player added successfully!");
    }

    private String getValidPlayerId() {
        String playerId = input.readNonEmpty("  Player ID (e.g. P0016): ");
        if (!Validator.isValidPlayerId(playerId)) {
            System.out.println("Invalid Player ID format! Must be Pxxxx.");
            return null;
        }
        if (findById(playerId) != null) {
            System.out.println("  This player ID already exists!");
            return null;
        }
        return playerId;
    }

    private String getValidClubId(Clubs clubService) {
        System.out.println("\n  --- Available Clubs ---");
        clubService.printClubList();

        String clubId = input.readNonEmpty("  Enter Club ID: ");
        if (!Validator.isValidClubId(clubId) || clubService.findById(clubId) == null) {
            System.out.println("  This club does not exist!");
            return null;
        }
        return clubId;
    }

    private String getValidPosition() {
        System.out.println("  Positions: Goalkeeper, Defender, Midfielder, Forward, Winger");
        String position = input.readNonEmpty("  Position: ");
        if (!Validator.isValidPosition(position)) {
            System.out.println("Invalid position!");
            return null;
        }
        return position;
    }

    private int getValidShirtNumber(String clubId) {
        int shirt = input.inputShirtNumber("  Shirt Number (1-99): ", 1, 99);
        if (shirtExistsInClub(clubId, shirt)) {
            System.out.println("  This shirt number already exists in this club!");
            return -1; // Returns -1 to signal invalid shirt number (since valid range is 1-99)
        }
        return shirt;
    }

    //F9: Remove player by ID
    public void removePlayer() {
        System.out.println("\n--- Remove Player ---");
        String id = input.readNonEmpty("  Enter Player ID to remove: ");
        Player p = findById(id);
        if (p == null) {
            System.out.println("  This player does not exist!");
        } else {
            getPlayerInfo(p);
            if (input.inputYesNo()) {
                players.remove(p);
                modified = true;
                System.out.println("Player \"" + p.getPlayerName() + "\" removed successfully!");
            }
        }
    }

    //F10: Update player
    public void updatePlayer() {
        System.out.println("\n--- Update Player by ID ---");
        String id = input.readNonEmpty("  Enter Player ID to update: ");
        Player p = findById(id);
        if (p == null) {
            System.out.println("  This player does not exist!");
            return;
        }

        System.out.println("  Current: " + p);
        System.out.println("  (Press ENTER to skip a field)");

        // 1. Update Name (If not skipped)
        String newName = getUpdatedName(p);
        if (!newName.isEmpty()) {
            p.setPlayerName(newName);
        }

        // 2. Update Position (If not skipped and valid)
        String newPosition = getUpdatedPosition(p);
        if (newPosition == null) {
            return; // Aborted due to invalid position format
        }
        if (!newPosition.isEmpty()) {
            p.setPosition(newPosition);
        }

        // 3. Update Shirt Number (If not skipped and unique)
        int newShirt = getUpdatedShirtNumber(p);
        if (newShirt == -2) {
            return; // Aborted due to shirt number conflict in the club
        }
        if (newShirt != -1) {
            p.setShirtNumber(newShirt);
        }

        modified = true;
        System.out.println("Player updated successfully!");
    }

// --- SUB-METHODS (HELPER FUNCTIONS) ---
    private String getUpdatedName(Player p) {
        return input.inputString("  New Player Name [" + p.getPlayerName() + "]: ");
    }

    private String getUpdatedPosition(Player p) {
        String newPosition = input.inputString("  New Position [" + p.getPosition() + "]: ");
        if (!newPosition.isEmpty()) {
            if (!Validator.isValidPosition(newPosition)) {
                System.out.println("Invalid position. Update aborted.");

            }
        }
        return newPosition;
    }

    private int getUpdatedShirtNumber(Player p) {
        int newShirt = input.inputShirtNumber("  New Shirt Number [" + p.getShirtNumber() + "]: ", 1, 99);

        if (newShirt != -1) {
            // Check uniqueness (exclude current player)
            boolean conflict = false;
            for (Player other : players) {
                if (!other.getPlayerId().equals(p.getPlayerId())
                        && other.getClubId().equals(p.getClubId())
                        && other.getShirtNumber() == newShirt) {
                    conflict = true;
                    break;
                }
            }
            if (conflict) {
                System.out.println("  This shirt number already exists in this club!");
                return -2; // Returns -2 to signal a conflict (aborted)
            }
        }
        return newShirt; // Returns the new shirt number or -1 if skipped
    }

    //F11: List player by position
    public void listByPosition() {
        System.out.println("\n--- List Players by Position ---");
        System.out.println("  Positions: Goalkeeper, Defender, Midfielder, Forward, Winger");
        String pos = input.readNonEmpty("  Enter Position: ");
        if (!Validator.isValidPosition(pos)) {
            System.out.println("Invalid position!");
            return;
        }
        List<Player> result = new ArrayList<>();
        for (Player p : players) {
            if (p.getPosition().equalsIgnoreCase(pos)) {
                result.add(p);
            }
        }
        if (result.isEmpty()) {
            System.out.println("  No players found with position: " + pos);
        } else {
            viewPlayer.printPlayerHeader();
            for (Player p : result) {
                System.out.println("  " + p);
            }
            UI.printDivider();
            System.out.println("  Found: " + result.size() + " player(s).");
        }
    }

    public Player findById(String playerId) {
        for (Player p : players) {
            if (p.getPlayerId().equalsIgnoreCase(playerId)) {
                return p;
            }
        }
        return null;
    }

    public boolean shirtExistsInClub(String clubId, int shirtNumber) {
        for (Player p : players) {
            if (p.getClubId().equals(clubId) && p.getShirtNumber() == shirtNumber) {
                return true;
            }
        }
        return false;
    }

    public void getPlayerInfo(Player p) {
        System.out.println("------------------------------------------------------------"
                + "\nClub ID: " + p.getClubId()
                + "\nPlayer ID: " + p.getPlayerId()
                + "\nName: " + p.getPlayerName()
                + "\nPosition: " + p.getPosition()
                + "\n------------------------------------------------------------\n"
                + "Do you want to delete this player (y/n): "
                + "\n------------------------------------------------------------\n");
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

}
