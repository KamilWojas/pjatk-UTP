/**
 *
 *  @author Wojas Kamil S23878
 *
 */

package zad2;

public class StringTask implements Runnable {
    private StringTask.TaskState enumTaskState;
    private String strLetter;
    private int intRepeatCount;
    private volatile String strConcatResult;
    private volatile boolean blnDone;
    private Thread newThread;

    public StringTask(String strLetter, int intRepeatCount) {
        this.intRepeatCount = intRepeatCount;
        this.strLetter = strLetter;
        this.strConcatResult = "";
        this.blnDone = false;
        this.enumTaskState = StringTask.TaskState.CREATED;
        this.newThread = new Thread(() -> {
            this.run();
        });
    }

    public String getResult() {
        return this.strConcatResult;
    }

    public StringTask.TaskState getState() {
        return this.enumTaskState;
    }

    public void start() {
        this.newThread.start();
        this.enumTaskState = StringTask.TaskState.RUNNING;
    }

    public void abort() {
        this.blnDone = true;
        this.enumTaskState = StringTask.TaskState.ABORTED;
        this.newThread.interrupt();
    }

    public boolean isDone() {
        return this.blnDone;
    }

    public void run() {
        int i;
        for(i = 0; i < this.intRepeatCount & !this.newThread.isInterrupted(); this.strConcatResult = this.strConcatResult + this.strLetter) {
            ++i;
        }

        if (i == this.intRepeatCount) {
            this.blnDone = true;
            this.enumTaskState = StringTask.TaskState.READY;
            this.newThread.interrupt();
        }

    }

    public static enum TaskState {
        CREATED,
        RUNNING,
        ABORTED,
        READY;

        private TaskState() {
        }
    }
}
