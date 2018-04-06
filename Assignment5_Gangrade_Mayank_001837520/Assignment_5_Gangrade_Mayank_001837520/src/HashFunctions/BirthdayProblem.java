/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HashFunctions;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Mayank
 */
public class BirthdayProblem {

    // Function to generate date
    public static LocalDate getDate() {
        LocalDate startDate = LocalDate.of(1990, 1, 1); //start date
        long start = startDate.toEpochDay();
        //System.out.println(start);
        LocalDate endDate = LocalDate.of(1990, 12, 31); //end date
        long end = endDate.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        return (LocalDate.ofEpochDay(randomEpochDay));
    }

    // Function to generate hash code of the date
    public static int getHash(LocalDate randomEpochDay) {
        return randomEpochDay.hashCode();
    }

    // Function to convert hash code of the date to arry index
    public static boolean calcIndexValue(LocalDate date, boolean[] storeDate) {
        return storeDate[getHash(date) % storeDate.length];
    }

    public static void main(String[] args) {
        int M = 50;                // M is the number of bins/slot
        boolean[] storeDate = new boolean[M];// declares an array of boolean & allocates memory for M values

        int hash = 0;
        boolean flag = true;

        
        while (flag) {
            LocalDate date = getDate();
            if (calcIndexValue(date, storeDate) == false) {
                storeDate[getHash(date) % storeDate.length] = true;
                hash += 1;
            } else {
                flag = false;
            }
        }

        System.out.println("The (average) number of hashes before the first collision in encountered C1(m)~ " + hash);
    }
}
