package by.gsu.epamlab.model.enums;

public enum Section {
    ACTIVE {},
    FIXED {},
    RECYCLEBIN {};


    @Override
    public String toString() {
        return name();
    }
}
