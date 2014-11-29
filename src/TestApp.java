public class TestApp {

	// In the example code below, I show how to use a TaskManager thread. This can be adapted to your own needs. Check
	// TaskManager.java for a full understanding.

	public static void main(String[] args) {
		Processor exampleProcessor = new Processor();
		Thread exampleThread = new Thread(exampleProcessor);
		TaskManager taskM = new TaskManager();

		// Add tasks ---> solutions
		taskM.addTask("Do laundry", "round up dirty clothes");
		taskM.addTask("Fix hunger", "eat pizza");

		// Start our thread and give it one of our tasks
		exampleThread.start();
		exampleProcessor.giveCommand(taskM.getSolution("Do laundry"));

		// If we want to change a solution, we can do so like this
		taskM.changeSolution("do laundry", "eat pizza"); // (Who wouldn't agree with this solution?)

		// Show that it has indeed changed. As a safety precaution, you can see if the task has a solution.
		if (taskM.hasSolution("do laundry")) {
			exampleProcessor.giveCommand(taskM.getSolution("do laundry"));
		}

		if (taskM.hasSolution("do")) {
			System.out.println("This shouldn't run.");
		}

		// Wait for it to complete its tasks
		try {
			exampleThread.join();
			taskM.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Bask in the joy of an obedient robot
		System.out.println("Example procedure complete.");
	}
}

class Processor implements Runnable { // Example class for something that could process tasks

	public void run() {
		// Need this method here so it can be used in a thread
	}

	public void giveCommand(String command) {

		// React to incoming commands

		switch (command.toLowerCase()) {
		case "round up dirty clothes":
			System.out.println("Oh no! I have to round up the dirty clothes. *sigh*");
			break;
		case "eat pizza":
			System.out.println("This is delicious! Thank you.");
			break;
		default:
			System.out.println("You haven't given me a recognizable command!");
			break;
		}
	}

}
