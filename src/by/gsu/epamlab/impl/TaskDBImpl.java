package by.gsu.epamlab.impl;

import by.gsu.epamlab.bean.Task;
import by.gsu.epamlab.bean.User;
import by.gsu.epamlab.connection.ConnectionManager;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.enums.Section;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.ITaskDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskDBImpl implements ITaskDAO {
    private static final Logger LOGGER = Logger.getLogger(TaskDBImpl.class.getName());


    @Override
    public List<Task> getTasks(User user) throws DAOException {
        List<Task> tasks = new ArrayList<>();

        ResultSet rs;
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(Constants.GET_TASKS)) {

            pst.setInt(1, user.getUserId());
            rs = pst.executeQuery();
            while (rs.next()) {
                tasks.add(new Task(rs.getDate(1), rs.getString(2), null, rs.getString(4), rs.getInt(5)));
            }


        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DAOException(Constants.ERROR_GET_TASKS);
        } finally {
            ConnectionManager.closeResultSets();
        }

        return tasks;
    }


    @Override
    public void addTask(User user, Task task) throws DAOException {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(Constants.ADD_TASK)) {

            pst.setInt(1, user.getUserId());
            pst.setDate(2, task.getDate());
            pst.setString(3, task.getDescription());
            pst.setObject(4, null);
            pst.setString(5, Section.ACTIVE.toString());
            pst.execute();


        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DAOException(Constants.ERROR_ADD_TASK);

        }
    }


    @Override
    public void removeTasks(User user, String[] id) throws DAOException {
        updateTasks(Constants.REMOVE_TASK, id);
    }

    @Override
    public void fixTasks(User user, String[] id) throws DAOException {
        updateTasks(Constants.FIX_TASK, id);
    }

    @Override
    public void recoverTasks(User user, String[] id) throws DAOException {
        updateTasks(Constants.RECOVER_TASK, id);
    }

    @Override
    public void permanentlyDeleteTasks(User user, String[] id) throws DAOException {
        updateTasks(Constants.DELETE_TASK, id);
    }


    private void updateTasks(String sql, String[] ids) throws DAOException {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            if (ids != null) {
                for (String id: ids) {
                    pst.setInt(1, Integer.parseInt(id));
                    pst.execute();
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DAOException(Constants.ERROR_UPDATE_TASKS);
        }
    }

}




