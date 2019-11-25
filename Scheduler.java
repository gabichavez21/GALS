import java.util.*;
public class Scheduler {

    // method to create months hashtable
    public static Hashtable<String, Integer> createMonthsHash(){
        Hashtable<String, Integer>  months  = new Hashtable<String, Integer>();
        months.put("January", 0);
        months.put("February", 1);
        months.put("March", 2);
        months.put("April", 3);
        months.put("May", 4);
        months.put("June", 5);
        months.put("July", 6);
        months.put("August", 7);
        months.put("September", 8);
        months.put("October", 9);
        months.put("November", 10);
        months.put("December", 11);

        return months;
    }
    public static void main(String[] args) {

        Hashtable<String, Integer>  months  = createMonthsHash();

        GregorianCalendar currentDate = new GregorianCalendar();
        int currentDay = currentDate.get(Calendar.DAY_OF_YEAR);

	Scanner input = new Scanner(System.in);

	System.out.println("Scheduling Program");
	System.out.println(" ");

	//System.out.println("How many tasks do you need to complete?");
	//int t = input.nextInt();
        @SuppressWarnings({"unchecked", "rawtypes"})
	ArrayList<Task> tasks = new ArrayList();
        
        // string variables for date
        String strMonth;
        String strDay;
        String strYear;

        // int variables for date
        int month;
        int day;
        int year;

        // variable to store days remaining til due date
        int daysRemaining;
        int dueDateDay;

        String keepGoing = "yes";
        int i = -1;

        while ( !keepGoing.equals("*") ) {
            i++;
            // get task name
    	    System.out.println("What task would you like added to your schedule? ");
            tasks.add(new Task());
	    tasks.get(i).title = input.nextLine();
            
            // get task length
	    System.out.println("How long will this task take in minutes? ");
	    tasks.get(i).time = Integer.parseInt(input.nextLine());

            // get task priority
	    System.out.println("Rate this task based on priority from 1-10.");
	    tasks.get(i).priority = Integer.parseInt(input.nextLine());

            // get date input as string
	    System.out.println("When is this task due by?.");
	    System.out.println("Please enter Month (e.g. January, February, etc..):" );
	    strMonth = input.nextLine();
	    System.out.println("Please enter Day of Month (e.g. 1, 2, 3, etc..):" );
	    strDay = input.nextLine();
	    System.out.println("Please enter Year (e.g. 2017, 2018, 2019, etc..):" );
	    strYear = input.nextLine();
            System.out.println("Date entered: " + strMonth + " " + strDay + 
                    ", " + strYear) ;

            // convert strings to ints
            Set<String> keys = months.keySet();
            month = -1;
            for ( String key : keys ) {
                if ( key.equals(strMonth) )
                    month = months.get(key);
            }

            day = Integer.parseInt(strDay);
            year = Integer.parseInt(strYear);

            int futureYear = 0;
            if ( year > currentDate.get(Calendar.YEAR) ) 
                futureYear = year - currentDate.get(Calendar.YEAR);

            // create gregorian calendar date with due date as params
            GregorianCalendar dueDate;
            if ( month != -1 )
                dueDate = new GregorianCalendar(futureYear, month, day);
            else
                throw new IllegalArgumentException("Invalid input for month");

            // find how many days remaining
            dueDateDay  = dueDate.get(Calendar.DAY_OF_YEAR);
            System.out.println("futureYear: " + futureYear);
            System.out.println("year: " + year);
            //daysRemaining = (dueDateDay - currentDay) + (futureYear * 365);
            daysRemaining = (dueDateDay - currentDay);
                

            //convert from str to int
            tasks.get(i).days = daysRemaining;

            // keep going?
            System.out.println("Keep Going?");
            keepGoing = input.nextLine();
            System.out.println("\n\n");

	    }

        for ( Task task : tasks ) {
            System.out.println(task.title);
            System.out.println(task.time);
            System.out.println(task.priority);
            System.out.println(task.days+ "\n");
        }
    }

    private static class Task {
        public int days;
        public int priority;
        public int time;
        public String title;

        public Task(){
            days = 0;
            priority = 0;
            time = 0;
            title = "";
        }
    }
}


