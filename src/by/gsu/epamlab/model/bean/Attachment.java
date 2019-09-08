package by.gsu.epamlab.model.bean;

import by.gsu.epamlab.model.constants.Constants;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class Attachment {
    private String name;
    private File file;
    private int taskId;

    public Attachment(String name, Blob file, int taskId) {
        this.name = name;
        setFile(file);
        this.taskId = taskId;
    }

    public Attachment(String name, File file, int taskId) {
        this.name = name;
        this.file = file;
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFile(Blob blob) {
        File file = new File(name);
        try {
            InputStream in = blob.getBinaryStream();
            OutputStream out = new FileOutputStream(file);
            byte[] buff = new byte[4096];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            this.file = file;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(Constants.ERROR_CANNOT_CONVERT_BLOB);
        }
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "name='" + name + '\'' +
                ", file=" + file +
                '}';
    }
}
