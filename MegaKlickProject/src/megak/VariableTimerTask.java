package megak;
import java.util.TimerTask;



class VariableTimerTask extends TimerTask  {
     String param;
     public VariableTimerTask(String param) {
         this.param = param;
     }

     @Override
     public void run() {
         // You can do anything you want with param 
     }
}

