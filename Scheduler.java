import java.util.*;
public class Scheduler {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
	
		System.out.println("Scheduling Program");
		System.out.println(" ");

		System.out.println("How many tasks do you need to complete?");
		int t = input.nextInt();
		String strTasks[] = new String[t];
		int time[] = new int[t];
		int priority[] = new int[t];
		Task[] tasks = new Task[t];
	
		for(int i = 0; i <= t; i++){
			System.out.println("What task would you like added to your schedule? ");
			strTasks[i] = input.nextLine();
			System.out.println("How long will this task take in minutes? ");
	    		time[i] = input.nextInt();
			System.out.println("Rate this task based on priority from 1-10.");
			priority[i] = input.nextInt();
		}
	}

    private class Task {
        public int dueDate;
        public int priority;
        public int time;
        public String title;
    }
}


