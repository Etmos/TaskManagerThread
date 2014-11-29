import java.util.HashMap;
import java.util.Map;

/* TaskManager.java
 * Written by: Etmos
 * Date Created: 11-25-2014
 * Last Update:  11-28-2014
 * 
 * == PURPOSE ==	
 *	 Allow the use of separate threads for task management, each with their own unique set of tasks and solutions. This allows for less
 *	 congestion within the main framework of the program. The only prerequisite is having to add the tasks/solutions to the specific thread.
 *
 *	 An example use would be if you had one thread handling user input and another handling background music. You could have a TaskManager 
 *	 thread for both and set it to your own specifications. Another choice would be to give each its own TaskManager thread with
 *   exclusive rules. The possibilities are endless!
 *
 * == VARIABLE SUMMARY ==
 * 	|/| taskSolutions (private)
 * 			This HashMap<String, String> holds "tasks" as keys and "solutions" as their values.
 *  |/| taskCompleted (private)
 *  		A HashMap<String, Boolean> containing "tasks" as keys and their "completion" as values.
 *  
 *  |/| DEBUGGING (private static final)
 *  		Are we in debugging mode? If so, print special messages into the console that may help.
 * 
 * == METHOD SUMMARY ==
 * 	|o| addTask(String task, String solution) 
 *			Adds task as a key to the taskSolutions map with a value of solution
 *
 *	|o| removeTask(String task)
 *			Removes task from both the taskSolutions and taskCompleted maps
 * 	
 * 	|o| getSolution(String task) 
 *			Returns a string pulled from the taskSolution map at task. (If assigned correctly, this should be the "solution" to the task) 
 *
 *	|o| changeSolution(String task, String newSolution)
 *			Changes the solution for task to newSolution, within the taskSolution map.
 * 
 *	|o| isCompleted(String task)
 *			Given a task, it will return a boolean that states whether it has been completed. (checking the taskCompleted map)
 *	
 *	|o| setCompleted(String task)
 *			Sets chosen task to "completed" within the taskCompleted map.
 *
 *	|o| setCompleted(String task, boolean setBooleanValue)
 *			Within the taskCompleted map, it will set the boolean value of the task to setBooleanValue.
 *	|o| hasSolution(String task)
 *			Returns a boolean value that dictates whether a solution exists for task or not.
 */

public class TaskManager implements Runnable {
	private Map<String, String> taskSolutions = new HashMap<String, String>();
	private Map<String, Boolean> taskCompleted = new HashMap<String, Boolean>();
	private static final boolean DEBUGGING = false; // Are we in debug mode?

	public TaskManager() {
		if (DEBUGGING) System.out.println("New TaskManaged thread started.");
	}

	public synchronized void addTask(String task, String solution) {
		taskSolutions.put(task, solution);
		taskCompleted.put(task, false);
		if (DEBUGGING) System.out.println("Added task(" + task + ") with solution(" + solution + ") to taskSolutions map successfully. Set taskCompleted to false for that task as well.");
	}

	public synchronized void removeTask(String task) {
		taskSolutions.remove(task);
		taskCompleted.remove(task);
		if (DEBUGGING) System.out.println("Removed task: (" + task + ") from both the taskSolutions and taskCompleted map.");
	}

	public synchronized String getSolution(String task) {
		return taskSolutions.get(task);
	}

	public synchronized void changeSolution(String task, String newSolution) {
		addTask(task, newSolution);
		if (DEBUGGING) System.out.println("Changed solution of task(" + task + ") to (" + taskSolutions.get(task) + ")");
	}

	public synchronized boolean isCompleted(String task) {
		return taskCompleted.get(task);
	}

	public synchronized void setCompleted(String task) {
		taskCompleted.put(task, true);
		if (DEBUGGING) System.out.println("Set task(" + task + ") to completed.");
	}

	public synchronized void setCompleted(String task, boolean setBooleanValue) {
		taskCompleted.put(task, setBooleanValue);
		if (DEBUGGING) System.out.println("Set completion value of task(" + task + ") to " + setBooleanValue);
	}

	public synchronized boolean hasSolution(String task) {
		if (taskSolutions.containsKey(task) != true) return false;
		return (taskSolutions.get(task) != "");
	}

	public boolean isDebugging() {
		return DEBUGGING;
	}

	public void run() {
	}

	public void stop() {
	}

}
