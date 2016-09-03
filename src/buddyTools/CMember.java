/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyTools;

import java.util.Date;

/**
 * CMember object that represents a committee member and stores required data 
 * for each instance. Includes helper methods that are to be run on these objects.
 * @author canishkadesilva
 */
public class CMember {
    String name;
    String committee;
    Date dateAdded;
    Cabinet firstChoice;
    Cabinet secondChoice;
    Cabinet thirdChoice;
    
    public CMember(String _name, String _committee, Date _dateAdded, Cabinet _first, Cabinet _second, Cabinet _third) {
        name = _name;
        committee = _committee;
        dateAdded = _dateAdded;
        firstChoice = _first;
        secondChoice = _second;
        thirdChoice = _third;               
    }
    
    public Cabinet getFirstChoice() {
        return this.firstChoice;
    }
    
    public Cabinet getSecondChoice() {
        return this.secondChoice;
    }
    
    public Cabinet getThirdChoice() {
        return this.thirdChoice;
    }
    
    public Date getDateTime() {
        return this.dateAdded;
    }
    
    public String getName() {
        return this.name;
    }
}
