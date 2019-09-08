package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.model.bean.Task;
import by.gsu.epamlab.model.bean.User;
import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.enums.Section;
import by.gsu.epamlab.model.interfaces.ITaskDAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskHardcodedImpl implements ITaskDAO {
    Map<Integer,List<Task>> table = new HashMap<>();

    @Override
    public List<Task> getTasks(User user) {
        return table.computeIfAbsent(user.getId(), tasks -> new ArrayList<>());
    }

    @Override
    public void addTask(Task task) {
        if (table.containsKey(task.getUserId())) {
            List<Task> tasks = table.get(task.getUserId());
            task.setId(tasks.size() + 1);
            tasks.add(task);
        }
    }

    @Override
    public void removeTasks(User user, String[] ids) {
        updateTask(user, ids, Section.RECYCLEBIN);
    }

    @Override
    public void fixTasks(User user, String[] ids) {
        updateTask(user, ids, Section.FIXED);
    }

    @Override
    public void recoverTasks(User user, String[] ids) {
        updateTask(user, ids, Section.ACTIVE);
    }

    @Override
    public void permanentlyDeleteTasks(User user, String[] ids) {
        List<Task> tasks = table.get(user.getId());

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (ids != null) {
                for (int j = 0; j < ids.length; j++) {
                    int id = Integer.parseInt(ids[i]);
                    if (task.getId() == id) {
                        tasks.remove(task);
                    }
                }
            }
        }
    }

    @Override
    public void permanentlyDeleteAllTasks(User user) {
        List<Task> tasks = table.get(user.getId());
        tasks.clear();
    }

    @Override
    public void uploadFile(Task task, InputStream inputStream, String fileName) throws IOException {
        FileOutputStream out = new FileOutputStream(new File(Constants.UPLOAD_PATH+ fileName));
            int read ;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            task.setFileName(fileName);

    }

    @Override
    public void deleteFile(Task task) {
        Path pathSource = Paths.get(Constants.UPLOAD_PATH+ task.getFileName());
        try {
            Files.delete(pathSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        task.setFileName(null);
    }

    @Override
    public void downloadFile(Task task) {
        Path uploads = Paths.get(Constants.UPLOAD_PATH + task.getFileName());
        Path downloads = Paths.get(Constants.DOWNLOAD_PATH + task.getFileName());

        try {
            Files.copy(uploads, downloads, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void updateTask(User user, String[] ids, Section section) {
        List<Task> tasks = table.get(user.getId());
        for (Task task: tasks) {
            if (ids != null) {
                for (String id: ids) {
                    if (task.getId() == Integer.parseInt(id)) {
                        task.setSection(section);
                    }
                }
            }
        }
    }
}
