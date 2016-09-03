

import buddyTools.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author canishkadesilva
 */
public class BuddyGroup {
    
    public Date convertToDate(String _currDate) {
        String DateString = _currDate;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
        Date _date = null;
        try {
            _date = df.parse(DateString);
//            String newDateString = df.format(_date);
//            System.out.println(newDateString);
        } catch (ParseException e){}
        return _date;
    }
    
    public void readCabFile(String url, CabMap cab) {
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(url));
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] cabMember = line.split(csvSplitBy);
                String cabName = cabMember[0];
                String position = cabMember[1];
                boolean isOfficer = Boolean.parseBoolean(cabMember[2]);
                Cabinet cabMem = new Cabinet(cabName, position, isOfficer);
                cab.addCabMember(cabMem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void readCMFile(String url, CMList _cmList, CabMap cab) {
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(url));
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] cMember = line.split(csvSplitBy);
                String _date = cMember[0];
                Date date = convertToDate(_date);
                String cmName = cMember[1];
                String _memberID = null;
                String _committee = cMember[2];
                Cabinet _first = cab.getCabMember(cMember[3]);
                Cabinet _second = cab.getCabMember(cMember[4]);
                Cabinet _third = cab.getCabMember(cMember[5]);
                CMember _cm = new CMember(cmName, _memberID, _committee, date, _first, _second, _third);
                _cmList.addCM(_cm);
//                System.out.println(cmName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // CHANGE TO CAPTURE PATHC
        String buddyPath = System.getProperty("user.home") + "/Desktop/BuddySignups.csv";
        String officerPath = System.getProperty("user.home") + "/Desktop/officers.csv";
        BuddyGroup _bgInstance = new BuddyGroup();
        CabMap _cabGroupInstance = new CabMap();
        CMList _cmListInstance = new CMList();
        _bgInstance.readCabFile(officerPath, _cabGroupInstance);
//        _cabGroupInstance.getCabNames();
        _bgInstance.readCMFile(buddyPath, _cmListInstance, _cabGroupInstance);
//        _cmListInstance.sortList();
        _cmListInstance.Traversal(_cabGroupInstance, 1);
        _cmListInstance.Traversal(_cabGroupInstance, 2);
        _cmListInstance.Traversal(_cabGroupInstance, 3);
        Iterator entries = _cabGroupInstance.getMap().keySet().iterator();
        while (entries.hasNext()) {
            String _cabName = (String) entries.next();
            String buddies = _cabGroupInstance.getCabMember(_cabName).getBuddies();
            System.out.println(_cabName + " -> " + buddies);
        }
        _cmListInstance.printRemCM();
        
    }
    
}
