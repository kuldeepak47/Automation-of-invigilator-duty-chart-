package Utils;

import java.util.Date;

public class Header {
    private Date date;
    private int assignendDuties = 0;
    private double noOfStudents;
    private int noOfInvigilators;
    private int totalD = 0;

    public void increaseTotalD() {
        this.totalD++;
    }

    public int getTotalD() {
        return this.totalD;
    }

    public int getAssignendDuties() {
        return this.assignendDuties;
    }

    public void increaseDuty() {
        this.assignendDuties++;
    }

    public Header(Date date, Double noOfStudents) {
        this.date = date;
        this.noOfStudents = noOfStudents;
    }

    Date getDate() {
        return date;
    }

    void setDate(Date date) {
        this.date = date;
    }

    double getNoOfStudents() {
        return noOfStudents;
    }

    void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    int getNoOfInvigilators() {
        int x = (int) this.noOfStudents / 20;
        return x + (x / 10) + 2;
    }

    void setNoOfInvigilators(int noOfInvigilators) {
        this.noOfInvigilators = noOfInvigilators;
    }
}
