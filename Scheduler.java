import java.util.*;

public class Scheduler {
	public static void main(String[] args) {
		GregorianCalendar gcal = new GregorianCalendar();
        gcal.getTimeZone();
        System.out.println("after time zone");
        System.out.println(gcal);
        System.out.println("\n");

		Scanner input = new Scanner(System.in);
	
		System.out.println("Scheduling Program");
		System.out.println(" ");
	
		//System.out.println("How many tasks do you need to complete?");
		//int t = input.nextInt();
	        @SuppressWarnings({"unchecked", "rawtypes"})
		ArrayList<Task> tasks = new ArrayList();
	    String strDate;
        String keepGoing = "yes";
        int i = -1;

        while ( !keepGoing.equals("*") ) {
            i++;
            // get task name
    	    System.out.println("What task would you like added to your schedule? ");
            tasks.add(new Task());
            tasks.get(i).title = input.next();
            
            // get task length
		    System.out.println("How long will this task take in minutes? ");
		    tasks.get(i).time = input.nextInt();

            // get task priority
		    System.out.println("Rate this task based on priority from 1-10.");
		    tasks.get(i).priority = input.nextInt();

            // get number of days til due date
		    System.out.println("When is this task due by?.");
		    strDate = input.next();

            //convert from str to int
            tasks.get(i).days = Integer.parseInt(strDate);

            // keep going?
            System.out.println("Keep Going?");
            keepGoing = input.next();
            System.out.println("keepgoing: " + keepGoing);

	    }

        /*
        for ( Task task : tasks ) {
            System.out.println(task.title);
            System.out.println(task.time);
            System.out.println(task.priority);
            System.out.println(task.days);
        }
        */
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