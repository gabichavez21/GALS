import java.util.*;
import java.lang.*;
import java.io.*;
public class Scheduler {

    // method to create monthNumber hashtable
    public static Hashtable<String, Integer> createMonthNumber(){
        Hashtable<String, Integer>  months  = new Hashtable<String, Integer>();
        months.put("january", 0);
        months.put("february", 1);
        months.put("march", 2);
        months.put("april", 3);
        months.put("may", 4);
        months.put("june", 5);
        months.put("july", 6);
        months.put("august", 7);
        months.put("september", 8);
        months.put("october", 9);
        months.put("november", 10);
        months.put("december", 11);

        return months;
    }

    // method to create monthDays hashtable
    public static Hashtable<String, Integer> createMonthDays(){
        Hashtable<String, Integer>  months  = new Hashtable<String, Integer>();
        months.put("january", 31);
        months.put("february", 28);
        months.put("march", 31);
        months.put("april", 30);
        months.put("may", 31);
        months.put("june", 30);
        months.put("july", 31);
        months.put("august", 31);
        months.put("september", 30);
        months.put("october", 31);
        months.put("november", 30);
        months.put("december", 31);

        return months;
    }
  
  	//Method for creating a schedule of tasks using the shortest job first greedy algorithm
	//preconditions: takes in an array of class tasks that have been given by the user
	//				 assumes no task will take over 9999 minutes
	//Postcondition: Outputs an array of indexes depending on the order of the tasks to be completed
	public static int[] SJF(ArrayList<Task> tasks) {


	    int i, j = 0; //index
	    //first fill a new array with all the values of time in tasks
	    int len = tasks.size();
	    int[] times = new int[len];
	    for(i = 0; i < len; i++) {
	    	times[i] = tasks.get(i).time;
	    }
	    
	    //Find the job that takes the shortest amount of time first
	    //If two or more jobs have the same time then the algorithm defaults to FIFO
	    int smallest = 9999;
	    int smallind = -1; //index for smallest value
	    int indexes[] = new int[len];
	    while (j < len) { //fills array with indexes depending on task time in a increasing order
	    	for(i = 0; i < len; i++) { //checks all the values to find the next smallest one
	    	    if(times[i] <= smallest && times[i] >= 0) {	//finds smallest must be positive
	    	        smallest = times[i];
	    	        smallind = i;
	    	    } //end if
	    	} //end for
	    	indexes[j] = smallind;
	    	times[smallind] = -1; //sets the smallest values to -1 so not picked again
	    	smallest = 9999; //resets smallest to test on
	    	j++;
	    } //end while
	    
	    return indexes;
	} //end SJF
  
  
    public static void main(String[] args) {

        // used to verify input for month
        Hashtable<String, Integer>  monthNumber  = createMonthNumber();

        // used to get maximum number of days in a month
        Hashtable<String, Integer>  monthDays  = createMonthDays();
        int numDays = 0;

        // get current date information
        GregorianCalendar currentDate = new GregorianCalendar();
        //System.out.println(currentDate);
        int currentDay = currentDate.get(Calendar.DAY_OF_YEAR);
        int currentYear = currentDate.get(Calendar.YEAR);

	Scanner input = new Scanner(System.in);

	System.out.println("Scheduling Program");
	System.out.println(" ");

	//System.out.println("How many tasks do you need to complete?");
	//int t = input.nextInt();
        @SuppressWarnings({"unchecked", "rawtypes"})
	ArrayList<Task> tasks = new ArrayList();
        
        // string variables for date
        String strMonth = "";
        String strDay = "";
        String strYear = "";

        // int variables for date
        int month = -1;
        int day = -1;
        int year = -1;

        // variable to store days remaining til due date
        int daysRemaining = 0;
        int dueDateDay;

        String keepGoing = "yes";
        boolean validInput;
        boolean validDate;
        Set<String> keys = monthNumber.keySet();
        int i = -1;

        while ( keepGoing.equals("yes") || keepGoing.equals("y") ) {
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

            
            // get date input as string, loop til valid
            validDate = false;
            while ( !validDate ) {
	        System.out.println("When is this task due by?.");
                validInput = false;
                while( !validInput ) {
	            System.out.println("Please enter Month (e.g. January, February, etc..):" );
	            strMonth = input.nextLine();
                    strMonth = strMonth.toLowerCase();

                    // get month from user, loop til valid
                    month = -1;
                    for ( String key : keys ) {
                        if ( key.equals(strMonth) ){
                            month = monthNumber.get(key);
                            validInput = true;
                        }
                    }
                    if ( !validInput )
                        System.out.println("Invalid Input. Must be full name of month.\n");
                }
                
                // get day of month from user, loop til valid
                validInput = false;
                numDays = monthDays.get(strMonth);
                while( !validInput ){
	            System.out.println("Please enter 1-" + numDays + " for Day of Month (e.g. 1, 2, 3, etc..):" );
	            strDay = input.nextLine();
                    day = Integer.parseInt(strDay);

                    if ( !(day <= 0 || day > numDays) )
                        validInput = true;
                    else
                        System.out.println("Invalid Input.\n");

                }


                // get year from user, loop til valid
                validInput = false;
                while ( !validInput ) {
	            System.out.println("Please enter Year. Must be >= " + currentYear );
	            strYear = input.nextLine();
                    year = Integer.parseInt(strYear);

                    if ( !(year < currentYear) ){
                        validInput = true;
                    }
                    else
                        System.out.println("Invalid Input. Year must be >= " + currentYear);
                }
                System.out.println("Date entered: " + strMonth + " " + strDay + 
                        ", " + strYear) ;



                // calculate how many years in the future
                int futureYear = 0;
                if ( year > currentYear)  
                    futureYear = year - currentYear;

                // create gregorian calendar date with due date as params
                GregorianCalendar dueDate;
                dueDate = new GregorianCalendar(year, month, day);

                // find how many days remaining
                dueDateDay  = dueDate.get(Calendar.DAY_OF_YEAR);
                daysRemaining = (365 * futureYear) + dueDateDay - currentDay;

                // check to see if due date has already passed
                if ( daysRemaining < 0 )
                    System.out.println("\nDue date cannot have already passed.\n\n");
                else
                    validDate = true;
        }

            //convert from str to int
            tasks.get(i).days = daysRemaining;

            // keep going?
            System.out.println("\n\nEnter another task?");
            keepGoing = input.nextLine();
            keepGoing = keepGoing.toLowerCase();
            System.out.println("\n\n");

	    }


        // print all tasks, used for debugging
        for ( Task task : tasks ) {
            System.out.println(task.title);
            System.out.println(task.time);
            System.out.println(task.priority);
            System.out.println(task.days+ "\n");
        }
        
        System.out.println("\n\n");

        // get number of tasks
        int numTasks = tasks.size();

        // array to hold array indices in tasks
        // from shortest job to longest job
        int[] indexes = new int[numTasks];
        
        // sort array indices
        long startTime = System.nanoTime();
        indexes = SJF( tasks );
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        long seconds = (endTime - startTime)/1000000000;
        // checking validity of sorted indices
        for ( int index : indexes ){
            System.out.println(tasks.get(index).title);
            System.out.println(tasks.get(index).time);
        }

        // duration of SJF
        System.out.println("\n\nSJF Function Time in nano seconds: \n\n" + duration);
        System.out.println("\n\nSJF Function Time roughly in seconds: \n\n" + seconds);

        String durationString = Long.toString(duration);

        // creates the file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("SJF_times.txt", true));
            writer.append(durationString + "\n");
             
            writer.close();
        }
        catch ( IOException e ){
            System.out.println(e.toString());
            System.out.println("could not create file SJF_times.txt.");
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
