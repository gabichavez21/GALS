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
        lottery(tasks);
        /*
        for ( Task task : tasks ) {
            System.out.println(task.title);
            System.out.println(task.time);
            System.out.println(task.priority);
            System.out.println(task.days);
        }
        */
    }
    public static void lottery(ArrayList<Task> tasks){
	    int length = tasks.size();
	    ArrayList<Integer> lott = new ArrayList();
	    int prior = 0;
	    ArrayList<Integer> bag = new ArrayList();
	    for(int i = 0; i <= length; i++){
	        //add tickets to bag for each task
	        prior = tasks.get(i).priority;
	        //adds multiple tickets
	        for(int j = 0; j <= prior; j++){
	            bag.add(i);
	        }
	    }
	    //choosing a random number
	    while(bag.size() > 1){
	        int high = bag.size();
	        int r = (int) (Math.random() * (high));
	        lott.add(r);
	        //removing r from the "bag"
	        for(int i = 0; i <= high; i++){
	            if(r == bag.get(i)){
	                bag.remove(i);
	                i--;
	            }
	        }
	    }
	    int n = lott.size();
	    int result[] = new int[n];
	    for(int i = 0; i <= n; i++){
	        result[i] = lott.get(i);
	        System.out.println("Task " + i + " " + result[i]);
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
