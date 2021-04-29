package task6;

import java.util.Objects;

public class NotepadEntry {

    /**
     * Текст записи в блокноте
     */
    private String entry;

    public NotepadEntry(String entry) {
        this.entry = entry;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotepadEntry that = (NotepadEntry) o;
        return Objects.equals(entry, that.entry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entry);
    }
}
