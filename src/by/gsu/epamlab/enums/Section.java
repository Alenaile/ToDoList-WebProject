package by.gsu.epamlab.enums;

public enum Section {
    ACTIVE {},
    FIXED {},
    RECYCLEBIN {};

    @Override
    public String toString() {
        return name();
    }
}
