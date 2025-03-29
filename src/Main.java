import java.util.ArrayList;
import java.util.Collections;


public class Main {
    public static void main(String[] args) {
        ArrayList<Date> dateList = new ArrayList<>();
        Date date1 = new Date(2023, 5, 10);
        Date date2 = new Date(2021, 8, 15);
        Date date3 = new Date(2025, 1, 1);
        Date date4 = new Date(2020, 2, 29);
        dateList.add(date1);
        dateList.add(date2);
        dateList.add(date3);
        dateList.add(date4);

        System.out.println("Before sorting:");
        for (Date date : dateList) {
            date.printDate();
        }
        Collections.sort(dateList);
        System.out.println("\nAfter sorting:");
        for (Date date : dateList) {
            date.printDate();
        }

        System.out.println("\nDay of the week for date1: " + date1.getDayOfWeek());

        System.out.println("Difference between date1 and date2: " + date1.calculateDifference(date2) + " days");

        date1.updateDate(2022, 12, 31);
        System.out.println("\nUpdated date1:");
        date1.printDate();

    }
}

class Date implements Comparable<Date> {
    int year, month, day;
    Date(int year,int month,int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    int[] dainmo = {31,28,31,30,31,30,31,31,30,31,30,31};
    String[] monthsNames = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    boolean isValid(int year, int month, int day){
        if(month < 1 || month > 12) {
            return false;
        } else {
            if(((year%4==0 && year%100!=0) || year%400==0) && month == 2) {
                if(day < 1 || day > 29) {
                    return false;
                } else {
                    return true;
                }


            } else {
                if(day < 1 || day > dainmo[month-1]) {
                    return false;
                } else {
                    return true;
                }
            }
        }

    }



    void updateDate(int year, int month, int day) {
        if (isValid(year, month, day)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            System.out.println("Invalid date");
        }
    }

    String getDayOfWeek() {
        if (!isValid(year, month, day)) {
            return "Invalid date";
        }
        int[] monthOffvalues = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        if (month < 3) {
            year -= 1;
        }
        int dayOfWeek = (year + year/4 - year/100 + year/400 + monthOffvalues[month - 1] + day) % 7;
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return daysOfWeek[dayOfWeek];
    }

    int calculateDifference(Date other) {
        return Math.abs(toDays() - other.toDays());
    }

    boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    int toDays() {
        int ds = year*365 + year/4 - year/100 + year/400;
        int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }
        for (int i = 0; i < month - 1; i++) {
            ds += daysInMonth[i];
        }
        return ds + day;
    }

    void printDate() {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.println(monthNames[month - 1] + " " + day + ", " + year);
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        } else if (this.month != other.month) {
            return this.month - other.month;
        } else {
            return this.day - other.day;
        }
    }

}