package bussiness;

import java.util.ArrayList;
import java.util.List;
import model.Club;
import static utils.UI.printDivider;

import utils.Inputter;
import utils.Validator;
import utils.ViewClub;

/**
 *
 * @author The Miracle Invoker
 */
public class Clubs {

    private final List<Club> clubs = new ArrayList<>();
    private boolean modified = false;

    private ViewClub viewClub = new ViewClub();
    private Inputter input = new Inputter();

    //F1: List all clubs
    public void listAllClubs() {
        if (clubs.isEmpty()) {
            System.out.println("  No clubs found.");
            return;
        }
        viewClub.printClubHeader();
        for (Club c : clubs) {
            System.out.println("  " + c);
        }
        printDivider();
        System.out.println("  Total: " + clubs.size() + " club(s).");
    }

    //F2: Add a new club
    public void addClub() {
        System.out.println("\n--- Add New Club ---");
        String clubId = input.readNonEmpty("  Club ID (e.g. CL-0013): ");
        if (!Validator.isValidClubId(clubId)) {
            System.out.println("Invalid Club ID format! Must be CL-xxxx.");
            return;
        }
        if (findById(clubId) != null) {
            System.out.println("  This club ID already exists!");
            return;
        }
        String clubName = input.readNonEmpty("  Club Name: ");
        if (!Validator.isValidName(clubName)) {
            System.out.println("Invalid Club name format!");
            return;
        }

        String sponsor = input.readNonEmpty("  Sponsor Brand: ");
        if (!Validator.isValidName(sponsor)) {
            System.out.println("Invalid Sponsor Brand format!");
            return;
        }
        double budget = input.inputBudget("  Budget (million EUR): ");
        if(budget<0){
            System.out.println("Error: budget can't < 0");
            return;
        }
        if (!Validator.isValidClubId(clubId)) {
            System.out.println("Invalid budget format! Must be positive real number.");
            return;
        }
        
        clubs.add(new Club(clubId, clubName, sponsor, budget));
        modified = true;
        System.out.println("Club added successfully!");
    }

    //F3: Search club by ID
    public void searchClubById() {
        System.out.println("\n--- Search Club by ID ---");
        String id = input.readNonEmpty("  Enter Club ID: ");
        Club c = findById(id);
        if (c == null) {
            System.out.println("  This club does not exist!");
        } else {
            viewClub.printClubHeader();
            System.out.println("  " + c);
            printDivider();
        }
    }

    //F4: Update club by ID
    public void updateClub() {
        System.out.println("--- Update Club by ID ---");
        String id = input.readNonEmpty("  Enter Club ID to update: ");
        Club c = findById(id);
        if (c == null) {
            System.out.println("  This club does not exist!");
            return;
        }
        System.out.println("  Current: " + c);
        System.out.println("  (Press ENTER to skip a field)");

        String newName = input.inputString("  New Club Name [" + c.getClubName() + "]: ");
        String newSponsor = input.inputString("  New Sponsor Brand [" + c.getSponsorBrand() + "]: ");
        double newBudget = input.inputBudget("  New Budget [" + c.getBudget() + "]: ");

        if (!newName.isEmpty()) {
            c.setClubName(newName);
        }
        if (!newSponsor.isEmpty()) {
            c.setSponsorBrand(newSponsor);
        }
        if (newBudget > 0) {
            c.setBudget(newBudget);
        }

        modified = true;
        System.out.println("Club updated successfully!");
    }

    //F5: List clubs with budget <= input 
    public void listClubsByBudget() {
        System.out.println("\n--- Clubs with Budget ≤ Input ---");
        double maxBudget = input.inputBudget("  Enter max budget (million EUR): ");

        List<Club> result = new ArrayList<>();
        for (Club c : clubs) {
            if (c.getBudget() <= maxBudget) {
                result.add(c);
            }
        }

        if (result.isEmpty()) {
            System.out.println("  No clubs found with budget <= " + maxBudget + " M EUR.");
        } else {
            viewClub.printClubHeader();
            for (Club c : result) {
                System.out.println("  " + c);
            }
            printDivider();
            System.out.println("  Found: " + result.size() + " club(s).");
        }
    }

    public Club findById(String clubId) {
        for (Club c : clubs) {
            if (c.getClubId().equalsIgnoreCase(clubId)) {
                return c;
            }
        }
        return null;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public void printClubList() {
        viewClub.printClubHeader();
        for (Club c : clubs) {
            System.out.println("  " + c);
        }
        printDivider();
    }

}
