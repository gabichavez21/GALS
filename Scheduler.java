import java.util.*;                     // Scanner and ArrayList

import java.io.*;                       // Used for FileWriter
                                        // and BufferedReader

public class Scheduler{

    private int[] schedule;             // Array of ordered indices that form
                                        // a schedule of taskList
    private ArrayList<Task> tasks;      // ArrayList of task objects to 
                                        // manage tasks

    // create a new schedule 
    public Scheduler(){

        tasks = new ArrayList<Task>();
    }

    // add a task to the task list 
    public void addTask(){

        tasks.add( new Task() );
        
    }
   
    // retrieve a task
    public Task getTask(int index){

        return tasks.get(index);
    }

    // number of tasks in task list
    public int size(){

        return this.tasks.size();
    }

/*===================================================================================
        SCHEDULING ALGORITHMS
=====================================================================================*/

    
  //Method for creating a schedule of tasks using the shortest job first greedy algorithm
  //preconditions: takes in an array of class tasks that have been given by the user
  //				 assumes no task will take over 9999 minutes
  //Postcondition: Outputs an array of indexes depending on the order of the tasks to be completed
  public int[] SJF() {
  	int i, j = 0; //index
  	//first fill a new array with all the values of time in tasks
  	int len = tasks.size();
  	int[] times = new int[len];
  	for(i = 0; i < len; i++) {
  		times[i] = this.getTask(i).time;
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
  







  //Method for creating a schedule of tasks using the First Come First Serve algorithm
  //Pre: takes in an array of class Tasks that have been given by the user
  //Post: returns an array of indexes depending on the input order
  public int[] FCFS() {
      int len = tasks.size();
      int indexes[] = new int[len];
      for(int i = 0; i < len; i++){
          indexes[i] = tasks.indexOf(this.getTask(i));//returning index of list in order it was put in
      }
      return indexes ;
  }//end FCFS









    public static void main(String[] args) {
        
/*===================================================================================
        INITIAL SETUP FOR MAIN
=====================================================================================*/

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


        // initialize scanner object for user input
	Scanner input = new Scanner(System.in);


        // print title
	System.out.println("Scheduling Program");
	System.out.println(" ");

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

        // variables to control loops and validate input
        String keepGoing = "yes";
        boolean validInput;
        boolean validDate;
        Set<String> keys = monthNumber.keySet();
        int i = -1;



/*===================================================================================
        GET USER INPUT
=====================================================================================*/



        // create Schedule object
        Scheduler sched = new Scheduler();

        while ( keepGoing.equals("yes") || keepGoing.equals("y") ) {
            i++;
            // get task name
    	    System.out.println("What task would you like added to your schedule? ");
            sched.addTask();
            Task currentTask = sched.getTask(i);
	    currentTask.title = input.nextLine();
            
            // get task length
	    System.out.println("How long will this task take in minutes? ");
	    currentTask.time = Integer.parseInt(input.nextLine());

            // get task priority
	    System.out.println("Rate this task based on priority from 1-10.");
	    currentTask.priority = Integer.parseInt(input.nextLine());

            
            // Below logic gets due date from user and validates
            // it against the current date pulled from the calendar.
            //
            // Date must be entered as:         > full month name
            //                                  > day of month
            //                                  > year
            //
            // Date must also be current date or future date
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
            currentTask.days = daysRemaining;

            // keep going?
            System.out.println("\n\nEnter another task?");
            keepGoing = input.nextLine();
            keepGoing = keepGoing.toLowerCase();
            System.out.println("\n\n");

	    }





        // print all tasks, used for debugging
        for ( Task task : sched.tasks ) {
            System.out.println(task.title);
            System.out.println(task.time);
            System.out.println(task.priority);
            System.out.println(task.days+ "\n");
        }
        
        System.out.println("\n\n");


/*===================================================================================
        CREATE SCHEDULES AND TESTING
=====================================================================================*/
        // get number of tasks
        int numTasks = sched.tasks.size();

        
        // create schedule using SJF
        // and time it
        int[] SJF_sched = new int[numTasks];
        Test test1 = new Test();
        test1.start();
        SJF_sched = sched.SJF( );
        test1.stop();
        test1.display();
        test1.write("SJF_times.txt");


        // create schedule using FCFS_times
        // and time it
        int[] FCFS_times = new int[numTasks];
        Test test2 = new Test();
        test2.start();
        FCFS_times = sched.FCFS( );
        test2.stop();
        test2.display();
        test2.write("FCFS_times.txt");

        // checking validity of sorted indices
        /*
        for ( int index : indexes ){
            System.out.println(sched.tasks.get(index).title);
            System.out.println(sched.tasks.get(index).time);
        }
        */
         
    }


/*===================================================================================
        SUPPORTING CLASSES AND METHODS
=====================================================================================*/

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

    // helper class used to simulate a struct
    // used to keep track of each task's details
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


    // helper class used for timing each
    // scheduling algorithm's completion time
    private static class Test {

        private long time;      // start time of test in nanoseconds
        private long duration;  // completion time of test in nanoseconds
        private long seconds;   // completion time in seconds 
        private String durationString;  // String format of completion time for
                                        // writing to the file


        // initialize a new test
        public Test(){
            time = 0;
            duration = 0;
            seconds = 0;
            durationString = "";
        }

        // start timer
        public void start(){

            time = System.nanoTime();
        }

        // stop timer and make conversions
        public void stop(){

            long stopTime = System.nanoTime();

            duration = stopTime - time;
            durationString = Long.toString(duration);
            seconds = duration / 1000000000;
        }

        // display completion time to console
        public void display(){

            System.out.println("\n\nSJF Function Time in nano seconds: \n\n" + duration);
            System.out.println("\n\nSJF Function Time roughly in seconds: \n\n" + seconds);
        }

        // create txt file of times
        public void write(String fileName){
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
                writer.append(durationString + "\n");
                 
                writer.close();
            }
            catch ( IOException e ){
                System.out.println(e.toString());
                System.out.println("could not create file " + fileName + ".");
            }
        }


    }

}   

