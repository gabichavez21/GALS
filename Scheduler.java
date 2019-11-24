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


