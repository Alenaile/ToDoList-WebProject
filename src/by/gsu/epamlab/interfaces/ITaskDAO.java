package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.bean.Task;
import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.exceptions.DAOException;

import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(User user) throws DAOException;

    void addTask(User user, Task task) throws DAOException ;

    void removeTasks(User user, String[] id) throws DAOException;

    void fixTasks(User user, String[] id) throws DAOException;

    void recoverTasks(User user, String[] id) throws DAOException;

    void permanentlyDeleteTasks(User user, String[] id) throws DAOException;

}
