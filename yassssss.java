
import java.util.*;
public class Scheduler {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
	
		System.out.println("Scheduling Program");
		System.out.println(" ");

		System.out.println("How many tasks do you need to complete?")
		int t = input.nextInt();
		String tasks[] = new String[t];
		int time[] = new int[t];
		int priority[] = new int[t];
	
		for(int i = 0; i <= t; i++){
			System.out.println("What task would you like added to your schedule? ");
			tasks[i] = input.nextLine();
			System.out.println("How long will this task take in minutes? " + number);
	    		time[i] = input.nextInt();
			System.out.println("Rate this task based on priority from 1-10.";
			priority[i] = input.nextInt();
		}
	}

