package by.gsu.epamlab.bean;

import by.gsu.epamlab.enums.Section;

import java.io.File;
import java.sql.Date;
import java.util.Objects;

public class Task {

    private int idUser;
    private String description;
    private Date date;
    private File file;
    private Section section;
    private int taskId;


    public Task(Date date, String description, File file) {
        this.date = date;
        this.description = description;
        this.file = file;
    }

    public Task(Date date, String description, File file, Section section, int taskId) {
        this(date, description, file);
        this.section = section;
        this.taskId = taskId;
    }

    public Task(Date date, String description, File file, String section, int taskId) {
        this(date, description, file, Section.valueOf(section.toUpperCase()), taskId);
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(description, task.description) &&
                Objects.equals(file, task.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, file);
    }


    @Override
    public String toString() {
        return "Task{" +
                "idUser=" + idUser +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", file=" + file +
                ", section=" + section +
                ", taskId=" + taskId +
                '}';
    }
}
