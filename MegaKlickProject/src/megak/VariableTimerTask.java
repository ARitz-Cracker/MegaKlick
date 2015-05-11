//VariableTimerTask.java - a TimerTask that takes in a variable that can be used on run.
package megak;

import java.util.TimerTask;

class VariableTimerTask extends TimerTask {
	String param;

	public VariableTimerTask(String param) {
		this.param = param;
	}

	@Override
	public void run() {
		// You can do anything you want with param
	}
}
