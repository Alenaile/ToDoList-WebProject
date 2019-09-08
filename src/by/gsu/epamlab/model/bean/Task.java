package by.gsu.epamlab.model.bean;

import by.gsu.epamlab.model.constants.Constants;
import by.gsu.epamlab.model.enums.Section;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Task {
    private int userId;
    private String description;
    private Date date;
    private String fileName;
    private File file;
    private Section section;
    private int id;

    public Task(int userId, String date, String description, String fileName, String section, int id) {
        this.userId = userId;
        setDate(date);
        this.description = description;
        this.fileName = fileName;
        setSection(section);
        this.id = id;
    }


    public Task(int userId, String description, String date, String section) {
        this.userId = userId;
        setDate(date);
        this.description = description;
        setSection(section);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setSection(String section) {
        this.section = Section.valueOf(section.toUpperCase());
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

    public void setDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_PATTERN);
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Task{" +
                "userId=" + userId +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", fileName='" + fileName + '\'' +
                ", section=" + section +
                ", id=" + id +
                '}';
    }
}
