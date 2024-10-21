package zad2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;

public class FutureTaskListDemo extends JFrame {
    private final DefaultListModel<FutureTask<String>> taskListModel;
    private final ExecutorService executor;

    public FutureTaskListDemo() {
        super("FutureTask List Demo");
        executor = Executors.newCachedThreadPool();
        taskListModel = new DefaultListModel<>();
        JList<FutureTask<String>> taskList = new JList<>(taskListModel);
        JButton addButton = new JButton("Add Task");
        JButton cancelButton = new JButton("Cancel Selected Task");
        JButton resultButton = new JButton("Show Result of Selected Task");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(resultButton);
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addTask());
        cancelButton.addActionListener(e -> cancelSelectedTask(taskList.getSelectedValue()));
        resultButton.addActionListener(e -> showSelectedTaskResult(taskList.getSelectedValue()));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    private void addTask() {
        Callable<String> task = () -> {
            // Symulacja pracy wykonującej się przez 2 sekundy
            Thread.sleep(2000);
            return "Task completed!";
        };
        FutureTask<String> futureTask = new FutureTask<>(task);
        taskListModel.addElement(futureTask);
        executor.submit(futureTask);
    }

    private void cancelSelectedTask(FutureTask<String> selectedTask) {
        if (selectedTask != null) {
            selectedTask.cancel(true);
            taskListModel.removeElement(selectedTask);
        }
    }

    private void showSelectedTaskResult(FutureTask<String> selectedTask) {
        if (selectedTask != null && selectedTask.isDone()) {
            try {
                String result = selectedTask.get();
                JOptionPane.showMessageDialog(this, result, "Task Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (InterruptedException | ExecutionException e) {
                JOptionPane.showMessageDialog(this, "Error getting task result: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new FutureTaskListDemo();
    }
}
