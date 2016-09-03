/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buddyTools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author canishkadesilva
 */
public class CabMap {
    Map cabMap;
    
    public CabMap() {
        cabMap = new HashMap<>();
    }
    
    public void addCabMember(Cabinet _cab) {
        cabMap.put(_cab.getCabName(), _cab);  
    }
    
    public Cabinet getCabMember(String _cabName) {
        return (Cabinet) cabMap.get(_cabName);
    }
    
    public Map getMap() {
        return cabMap;
    }
    
    public void getCabNames() {
        Iterator entries = cabMap.keySet().iterator();
        while (entries.hasNext()) {
            String _cabName = (String) entries.next();
            System.out.println(_cabName + " ");
        }
    }
}
