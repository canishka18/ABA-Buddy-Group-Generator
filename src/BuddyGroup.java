
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
 * Main class that will also have a few helper functions to parse through files.
 * 
 */
public class BuddyGroup {
    
    /**
     * Helper function that converts String object to Date object
     * @param _currDate
     * @return converted Date
     */
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
    /**
     * Takes in the path of the csv file to be parsed and creates a Cabinet 
     * object, and adds it into the CabMap object passed in as a parameter.
     * @param path
     * @param cab 
     */
    public void readCabFile(String path, CabMap cab) {
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(path));
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
    /**
     * Takes in the path of the csv file to be parsed and creates a CMember 
     * object, and adds it into the CMList object passed in as a parameter.
     * Also uses the cab param to obtain the necessary Cabinet objects from the
     * data structure.
     * @param url
     * @param _cmList
     * @param cab 
     */
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
                String _committee = cMember[2];
                Cabinet _first = cab.getCabMember(cMember[3]);
                Cabinet _second = cab.getCabMember(cMember[4]);
                Cabinet _third = cab.getCabMember(cMember[5]);
                CMember _cm = new CMember(cmName, _committee, date, _first, _second, _third);
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
     * 2 args that point to the csv files.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String buddyPath = System.getProperty("user.home") + args[0];
        String officerPath = System.getProperty("user.home") + args[1];
        //Create a BuddyGroup, CabMap and CMList instance
        BuddyGroup _bgInstance = new BuddyGroup();
        CabMap _cabGroupInstance = new CabMap();
        CMList _cmListInstance = new CMList();
        //Parse files
        _bgInstance.readCabFile(officerPath, _cabGroupInstance);
        _bgInstance.readCMFile(buddyPath, _cmListInstance, _cabGroupInstance);
        //Traverse the CMList object to assign CM's to buddy groups
        _cmListInstance.Traversal(_cabGroupInstance, 1);
        _cmListInstance.Traversal(_cabGroupInstance, 2);
        _cmListInstance.Traversal(_cabGroupInstance, 3);
        Iterator entries = _cabGroupInstance.getMap().keySet().iterator();
        //Print buddy groups
        while (entries.hasNext()) {
            String _cabName = (String) entries.next();
            String buddies = _cabGroupInstance.getCabMember(_cabName).getBuddies();
            System.out.println(_cabName + " -> " + buddies);
        }
        _cmListInstance.printRemCM();
        
    }
    
}
