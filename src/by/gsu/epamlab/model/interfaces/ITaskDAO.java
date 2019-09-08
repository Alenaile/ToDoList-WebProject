package by.gsu.epamlab.model.interfaces;

import by.gsu.epamlab.model.bean.Attachment;
import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.exceptions.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(User user) throws DAOException;

    void addTask(Task task) throws DAOException;

    void removeTasks(User user, String[] id) throws DAOException;

    void fixTasks(User user, String[] id) throws DAOException;

    void recoverTasks(User user, String[] id) throws DAOException;

    void permanentlyDeleteTasks(User user, String[] id) throws DAOException;

    void permanentlyDeleteAllTasks(User user) throws DAOException;

    void uploadFile(Task task, InputStream is, String fileName) throws DAOException, IOException;

    void deleteFile(Task task) throws DAOException;

    Attachment downloadFile(Task task) throws DAOException;

}
