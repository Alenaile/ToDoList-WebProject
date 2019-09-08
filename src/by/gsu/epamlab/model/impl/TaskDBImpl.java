package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.constants.ConstantsSQL;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import by.gsu.epamlab.model.utils.ConnectionManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class TaskDBImpl implements ITaskDAO {
    private static final Logger LOGGER = Logger.getLogger(TaskDBImpl.class);

    @Override
    public List<Task> getTasks(User user) throws DAOException {
        List<Task> tasks = new ArrayList<>();
        ResultSet rs;

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(ConstantsSQL.GET_TASKS)) {

            pst.setInt(1, user.getId());
            rs = pst.executeQuery();
            while (rs.next()) {
                Task task = new Task(rs.getInt(ConstantsSQL.ID_USER_COLUMN_IND),
                        rs.getString(ConstantsSQL.DATE_COLUMN_IND), rs.getString(ConstantsSQL.DESCRIPTION_COLUMN_IND),
                        rs.getString(ConstantsSQL.FILE_NAME_COLUMN_IND), rs.getString(ConstantsSQL.SECTION_COLUMN_IND),
                        rs.getInt(ConstantsSQL.TASK_ID_COLUMN_IND));
                tasks.add(task);
            }

        } catch (SQLException e) {
            LOGGER.error(e.toString(), e);
            throw new DAOException(Constants.ERROR_SOURCE);
        } finally {
            ConnectionManager.closeResultSets();
        }
        return tasks;
    }

    @Override
    public void addTask(Task task) throws DAOException {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(ConstantsSQL.ADD_TASK)) {

            pst.setInt(1, task.getUserId());
            pst.setString(2, new SimpleDateFormat(Constants.DATE_PATTERN).format(task.getDate()));
            pst.setString(3, task.getDescription());
            pst.setString(4, task.getSection().toString());

            pst.execute();
        } catch (SQLException e) {
            LOGGER.error(e.toString(), e);
            throw new DAOException(Constants.ERROR_SOURCE);
        }
    }

    @Override
    public void permanentlyDeleteAllTasks(User user) throws DAOException {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(ConstantsSQL.DELETE_TASKS)) {

            pst.setInt(1, user.getId());
            pst.execute();

        } catch (SQLException e) {
            LOGGER.error(e.toString(), e);
            throw new DAOException(Constants.ERROR_SOURCE);
        }
    }

    @Override
    public void removeTasks(User user, String[] id) throws DAOException {
        updateTasks(ConstantsSQL.REMOVE_TASK, id);
    }

    @Override
    public void fixTasks(User user, String[] id) throws DAOException {
        updateTasks(ConstantsSQL.FIX_TASK, id);
    }

    @Override
    public void recoverTasks(User user, String[] id) throws DAOException {
        updateTasks(ConstantsSQL.RECOVER_TASK, id);
    }

    @Override
    public void permanentlyDeleteTasks(User user, String[] id) throws DAOException {
        updateTasks(ConstantsSQL.DELETE_TASK, id);
    }

    @Override
    public void uploadFile(Task task, InputStream is, String fileName) throws DAOException {
        try (Connection conn = ConnectionManager.getConnection()) {

            PreparedStatement pst = conn.prepareStatement(ConstantsSQL.UPLOAD_FILE);

            pst.setString(1, fileName);
            pst.setBlob(2, is);
            pst.setInt(3, task.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.toString(), e);
            throw new DAOException(ConstantsSQL.ERROR_UPLOAD_FILE);
        }
    }

    @Override
    public void deleteFile(Task task) throws DAOException {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(ConstantsSQL.DELETE_FILE)) {

            pst.setInt(1, task.getId());

            pst.execute();
        } catch (SQLException e) {
            LOGGER.error(e.toString(), e);
            throw new DAOException(ConstantsSQL.ERROR_DELETE_FILE);
        }
    }

    @Override
    public void downloadFile(Task task) throws DAOException {
        ResultSet rs;

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(ConstantsSQL.SELECT_FILE_TO_DOWNLOAD)) {

            pst.setInt(1, task.getId());
            rs = pst.executeQuery();


            while (rs.next()) {
                InputStream input = rs.getBinaryStream(ConstantsSQL.KEY_FILE);

                Path path = Paths.get(Constants.DOWNLOAD_PATH + task.getFileName());
                Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (SQLException e) {
            LOGGER.error(e.toString(), e);
            throw new DAOException(ConstantsSQL.ERROR_DOWNLOAD_FILE);
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
            throw new DAOException(e.getMessage());
        } finally {
            ConnectionManager.closeResultSets();
        }
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
            LOGGER.error(e.toString(), e);
            throw new DAOException(Constants.ERROR_SOURCE);
        }
    }

}




