/**
 *
 *  @author Wojas Kamil S23878
 *
 */


package zad3;

import java.util.Random;
import java.util.concurrent.*;

class CustomFutureTask
{
    public Future<Integer> fTask;
    private String strNameOfTask;
    private int intNumber1;
    private int intNumber2;
    private int intReqProcessTime;
    private TaskState enumTaskState;
    public CustomFutureTask(String strName)
    {

        Random random = new Random();
        this.intNumber1= random.nextInt(1000);
        this.intNumber2= random.nextInt(1000);
        this.intReqProcessTime= random.nextInt(30);
        this.strNameOfTask=strName;
        this.enumTaskState= TaskState.CREATED;
        this.fTask = new SumTwoNumbers().calculate(this,this.intNumber1,this.intNumber2,this.intReqProcessTime);
    }
    public enum TaskState
    {
        //typy stanów
        CREATED,
        RUNNING,
        ABORTED,
        READY;
    }
    public void vChangeToRunning()
    {
        this.enumTaskState=TaskState.RUNNING;
    }
    public void vChangeToAborted()
    {
        if(this.enumTaskState!=TaskState.READY)
        {
            this.enumTaskState=TaskState.ABORTED;
        }
    }
    public void vChangeToDone()
    {
        this.enumTaskState=TaskState.READY;
    }
    public TaskState getState()
    {

        return enumTaskState;
    }
    public class SumTwoNumbers
    {
        private ExecutorService executor = Executors.newSingleThreadExecutor();
        public Future<Integer> calculate(CustomFutureTask fTask,Integer intNumber1,Integer intNumber2,Integer intReqProcessTime)
        {
            fTask.vChangeToRunning();
            return executor.submit(() -> {
                Thread.sleep(intReqProcessTime*1000);
                fTask.vChangeToDone();
                return intNumber1 + intNumber2;
            });
        }
    }
    public String toString()
    {

        return this.strNameOfTask;
    }
}

