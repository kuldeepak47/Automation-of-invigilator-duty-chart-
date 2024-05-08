package Utils;
import java.util.ArrayList;
import java.util.Random;

public class Duty {

    int totalDuitesToFill(ArrayList<Header> date) {
        int count = 0;
        for (int i = 0; i < date.size(); i++) {
            count += date.get(i).getNoOfInvigilators();
        }
        // System.out.println(count);
        return count;
    }

    void FillDuty(ArrayList<Data> list, ArrayList<Header> date) {
        Random random = new Random();
        int i=0;
        while (i != totalDuitesToFill(date)) {
            // i=i%list.size();
            int p = random.nextInt(list.size());
            int d = random.nextInt(date.size());
            if (list.get(p).getName() != "" && list.get(p).getNoOfDuties() < list.get(p).getTotalDuty()
                    && date.get(d).getAssignendDuties() < date.get(d).getNoOfInvigilators() && !list.get(p).duty.contains(date.get(d).getDate()) && !list.get(p).checkAvailability(date.get(d).getDate())) {
                date.get(d).increaseDuty();
                list.get(p).increaseNoOfDuites();
                list.get(p).duty.add(date.get(d).getDate());
                // System.out.println(date.get(d).getDate()+"\t"+i);
                i++;
                // System.out.println(list.get(p).getName() + list.get(p).duty);
            }
        }
        System.out.println(i);
    }
}
