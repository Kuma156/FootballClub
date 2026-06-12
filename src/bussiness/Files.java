package bussiness;

import bussiness.Clubs;
import model.Club;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import utils.Validator;


/**
 *
 * @author The Miracle Invoker
 */
public class Files {

    public Clubs loadFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            double budget = 0;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 4) return false;

                String clubId      = parts[0].trim();
                String clubName    = parts[1].trim();
                String sponsor     = parts[2].trim();
                String budgetStr   = parts[3].trim();
                
                if(Validator.isValidBudget(budgetStr))
                    budget = Double.parseDouble(budgetStr);

                return(new Club(clubId, clubName, sponsor, budget));
            }
        } catch(IOException e){
            System.out.println("File I/O error: "+e.getMessage());            
        }
    }

    public boolean saveToFile(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Club c : clubs) {
                pw.println(c.toFileString());
            }
            modified = false;
            return true;
        } catch (IOException e) {
            return false;
        }
    }
