/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyTools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author canishkadesilva
 */
public class CMList {
    List cmList;
    
    public CMList() {
        cmList = new ArrayList<>();
    }
    
    public void addCM(CMember _cm) {
        cmList.add(_cm);
    }
    
    public void sortList() {
	Collections.sort(cmList, new Comparator<CMember>() {
            @Override
            public int compare(CMember o1, CMember o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
	});
    }
    
    public boolean isChoiceFree(Cabinet _cabChoice, CabMap _cab, int _traversalNum) {
        String choice = _cabChoice.getCabName();
        Cabinet _currCabinet = _cab.getCabMember(choice);
        return _currCabinet.hasSpaceInGroup(_traversalNum);
    }
    
    public void Traversal(CabMap _cab, int _traversalNum) {
        int traversalNum = _traversalNum;
        for (Iterator<CMember> iter = cmList.listIterator(); iter.hasNext(); ) {
            CMember _currCM = iter.next();
            String testName = _currCM.name;
            Cabinet _first = _currCM.getFirstChoice();
//            System.out.println("check : " +_first == null);
            String _firstName = _first.name;
            boolean hasSpaceFirst = _first.hasSpaceInGroup(traversalNum);
            Cabinet _second = _currCM.getSecondChoice();
            String _secondName = _second.name;
            boolean hasSpaceSecond = _second.hasSpaceInGroup(traversalNum);
            Cabinet _third = _currCM.getThirdChoice();
            String _thirdName = _third.name;
            boolean hasSpaceThird = _third.hasSpaceInGroup(traversalNum);
            if (isChoiceFree(_first, _cab, traversalNum)) {
                _first.addCM(_currCM);
                iter.remove();
                continue;
            }
            if (isChoiceFree(_second, _cab, traversalNum)) {
                _second.addCM(_currCM);
                iter.remove();
                continue;
            }
            if (isChoiceFree(_third, _cab, traversalNum)) {
                _third.addCM(_currCM);
                iter.remove();
            }
        }
    }
    
    public int numRemainingCM() {
        return cmList.size();
    }
    
    public void printRemCM() {
        if (this.numRemainingCM() > 0) {
            System.out.println("The remaining CM's are: ");
        }
        for (int count = 0; count < this.numRemainingCM(); count++) {
            Object tempCM = this.cmList.get(count);
            CMember cm = (CMember) tempCM;
            System.out.println(" " + cm.name + ",");
        }
    }
}
