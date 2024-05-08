package Utils;

import java.util.ArrayList;
import java.util.Date;

public class Data {

    private String name;
    private int age;
    private String department;
    private String designation;
    public ArrayList<Date> availability = new ArrayList<Date>();
    public ArrayList<Date> duty = new ArrayList<Date>();
    private int currentDuties = 0;
    private int totalDuty = 0;

    public Data(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    String getDesignation() {
        return designation;
    }

    void setDesignation(String designation) {
        this.designation = designation;
    }

    String getDepartment() {
        return this.department;
    }

    void setDepartment(String department) {
        this.department = department;
    }

    Boolean checkAvailability(Date d){
        for (int i = 0; i < availability.size(); i++) {
            if(availability.get(i).equals(d)){
                System.out.println(availability.get(i)+"\t"+d);
                return true;
            }
        }
        return false;
    }

    void setTotalDuty() {
        if (getDesignation().compareToIgnoreCase("professor") == 0)
            this.totalDuty = 1;
        else if (getDesignation().compareToIgnoreCase("associate professor") == 0)
            this.totalDuty = 2;
        else if (getDesignation().compareToIgnoreCase("assistant professor") == 0
                || getDesignation().compareToIgnoreCase("asstt.w/s supdt.") == 0)
            this.totalDuty = 3;
        else if (getDesignation().compareToIgnoreCase("head") == 0 || getDesignation().compareToIgnoreCase("dean") == 0
                || getDesignation().compareToIgnoreCase("director") == 0)
            this.totalDuty = 0;
        else
            this.totalDuty = 6;
    }

    int getTotalDuty() {
        setTotalDuty();
        return this.totalDuty;
    }

    int getNoOfDuties() {
        return this.currentDuties;
    }

    void increaseNoOfDuites() {
        this.currentDuties++;
    }
}
