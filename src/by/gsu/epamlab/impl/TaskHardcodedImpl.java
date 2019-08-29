package by.gsu.epamlab.impl;

import by.gsu.epamlab.bean.Task;
import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.enums.Section;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.ITaskDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskHardcodedImpl implements ITaskDAO {
    Map<Integer,List<Task>> table = new HashMap<>();


    @Override
    public List<Task> getTasks(User user) throws DAOException {

        List<Task> tasks = table.get(user.getUserId());
        if (tasks == null) {
            table.put(user.getUserId(), tasks = new ArrayList<>());
        }

        return tasks;
    }

    @Override
    public void addTask(User user, Task task) throws DAOException {
        if (table.containsKey(user.getUserId())) {
            List<Task> tasks = table.get(user.getUserId());
            task.setIdUser(user.getUserId());
            task.setSection(Section.ACTIVE);

            task.setTaskId(tasks.size() + 1);
            tasks.add(task);
        }

    }

    @Override
    public void removeTasks(User user, String[] ids) throws DAOException {
        updateTask(user, ids, Section.RECYCLEBIN);
    }


    @Override
    public void fixTasks(User user, String[] ids) throws DAOException {
        updateTask(user, ids, Section.FIXED);
    }

    @Override
    public void recoverTasks(User user, String[] ids) throws DAOException {
        updateTask(user, ids, Section.ACTIVE);
    }

    @Override
    public void permanentlyDeleteTasks(User user, String[] ids) throws DAOException {
        List<Task> tasks = getTasks(user);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (ids != null) {
                for (String id: ids) {
                    if (task.getTaskId() == Integer.parseInt(id)) {
                        tasks.remove(task);
                    }
                }
            }
        }
    }


    private void updateTask(User user, String[] ids, Section section) throws DAOException {
        for (Task task: getTasks(user)) {
            if (ids != null) {
                for (String id: ids) {
                    if (task.getTaskId() == Integer.parseInt(id)) {
                        task.setSection(section);
                    }
                }
            }
        }
    }
}
