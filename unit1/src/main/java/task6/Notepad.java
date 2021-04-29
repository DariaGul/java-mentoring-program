package task6;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class Notepad {

    private static final int LENGTH = 48;
    private NotepadEntry[] entries;

    public Notepad() {
        this.entries = new NotepadEntry[LENGTH];
    }

    /**
     * Метод для просмотра всех записей блокнота.
     *
     * @return список записей
     */
    public NotepadEntry[] showAllEntries() {
        NotepadEntry[] copyEntries = Arrays.copyOf(entries, entries.length);
        return Arrays.stream(copyEntries)
            .filter(Objects::nonNull)
            .toArray(NotepadEntry[]::new);
    }

    /**
     * Метод для удаления записи из блокнота
     *
     * @param deletedText Текст уаляемой записи
     * @return сообщение о результате операции
     */
    public String delete(String deletedText) {
        Optional<NotepadEntry> needEntry = Arrays.stream(entries)
            .filter(Objects::nonNull)
            .filter(current -> deletedText.equals(current.getEntry()))
            .findFirst();

        if (needEntry.isPresent()) {
            NotepadEntry entry = needEntry.get();
            entry.setEntry(null);
            int indexEntryDeleted = Arrays.asList(entries).indexOf(entry);
            entries[indexEntryDeleted] = null;
        } else {
            return "entry to delete not found";
        }
        return "entry deleted";
    }

    /**
     * Метод добавления записи в блокнот
     *
     * @param text Текст новой записи
     * @return сообщение о результате операции
     */
    public String add(String text) {
        int emptyEntry = Arrays.asList(entries).indexOf(null);
        if (emptyEntry == -1) {
            int currentLength = entries.length;
            entries = Arrays.copyOf(entries, currentLength + LENGTH);
            entries[currentLength] = new NotepadEntry(text);
        } else {
            entries[emptyEntry] = new NotepadEntry(text);
        }
        return "entry created";
    }

    /**
     * Метод изменения записи в блокноте
     *
     * @param oldText Текст старой записи
     * @param newText Текст новой записи
     * @return сообщение о результате операции
     */
    public String edit(String oldText, String newText) {
        Optional<NotepadEntry> needEntry = Arrays.stream(entries)
            .filter(Objects::nonNull)
            .filter(current -> current.getEntry().equals(oldText))
            .findFirst();

        if (needEntry.isPresent()) {
            needEntry.get().setEntry(newText);
        } else {
            return "entry to change not found";
        }

        return "entry changed";
    }
}
