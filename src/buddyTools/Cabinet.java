/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Cabinet object that represents a cabinet member and stores required data 
 * for each instance. Includes helper methods that are to be run on these objects.
 * @author canishkadesilva
 */
public class Cabinet {
    String name;
    String position;
    Boolean officer;
    List restrictions;
    List buddyGroup;
        
    public Cabinet(String _name, String _position, Boolean _isOfficer) {
        name = _name;
        position = _position;
	officer = _isOfficer;
	restrictions = new ArrayList<>();
	buddyGroup = new ArrayList<>();
    }
        
    public boolean hasSpaceInGroup(int traversalNum) {
        switch (traversalNum) {
            case 1:
                return this.buddyGroup.size() < 1;
            case 2:
                return this.buddyGroup.size() < 2;
            default:
                return this.buddyGroup.size() < 3;
        }
    }
    
    public String getCabName() {
        return this.name;
    }
    
    public void addCM(CMember _cm) {
        this.buddyGroup.add(_cm);
    }
    
    public String getBuddies() {
        String names= "";
        for (int counter = 0; counter < buddyGroup.size(); counter++) {
            Object _cmHolder = buddyGroup.get(counter);
            CMember cm = (CMember) _cmHolder;
            names += cm.getName() + ", ";
        }
        if (names.length() > 0) {
            names = names.substring(0, names.length()-2);
        }
        return names;
    }
}
