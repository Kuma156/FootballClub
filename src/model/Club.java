package model;

/**
 *
 * @author The Miracle Invoker
 */
public class Club {

    private String clubId;
    private String clubName;
    private String sponsorBrand;
    private double budget;

    public Club() {
    }

    public Club(String clubId, String clubName, String sponsorBrand, double budget) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.sponsorBrand = sponsorBrand;
        this.budget = budget;
    }

    public String getClubId() {
        return clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public String getSponsorBrand() {
        return sponsorBrand;
    }

    public double getBudget() {
        return budget;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setSponsorBrand(String sponsorBrand) {
        this.sponsorBrand = sponsorBrand;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-30s | %-10s | %.0f M EUR",
                clubId, clubName, sponsorBrand, budget);
    }

    public String toFileString() {
        return String.format("%s, %s, %s, %.0f", clubId, clubName, sponsorBrand, budget);
    }
}
