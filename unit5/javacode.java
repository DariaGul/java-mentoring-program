import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task6.Notepad;
import task6.NotepadEntry;

import java.util.Arrays;

public class NotepadTest {

    private static final String FIRST = "first";
    private Notepad notepad;

    @BeforeEach
    public void createNotepad() {
        notepad = new Notepad();
        notepad.add(FIRST);
        notepad.add("second");
        notepad.add("third");
    }

    @Test
    public void whenShowAllEntriesThenReturnNotepadEntryTest() {
        NotepadEntry[] entries = notepad.showAllEntries();
        NotepadEntry[] needArray = {
            new NotepadEntry(FIRST),
            new NotepadEntry("second"),
            new NotepadEntry("third"),
        };
        Assertions.assertArrayEquals(needArray, entries);
    }

    @Test
    public void whenDeleteEntryNotInNotebookThenEntryNotFoundTest() {
        int beforeDelete = notepad.showAllEntries().length;
        String message = notepad.delete("entry from another notepad");
        Assertions.assertEquals("entry to delete not found", message);
        int afterDelete = notepad.showAllEntries().length;
        Assertions.assertEquals(beforeDelete, afterDelete);
    }

    @Test
    public void whenDeleteEntryThenEntryDeletedTest() {
        int beforeDelete = notepad.showAllEntries().length;
        String message = notepad.delete(FIRST);
        Assertions.assertEquals("entry deleted", message);
        NotepadEntry[] afterDelete = notepad.showAllEntries();
        Assertions.assertNotEquals(beforeDelete, afterDelete.length);
        Arrays.stream(afterDelete)
            .forEach(c -> Assertions.assertNotEquals(FIRST, c.getEntry()));
    }

    @Test
    public void whenAddNewEntryThenEntryCreatedTest() {
        String entryForAdd = "new entry";
        int beforeAdd = notepad.showAllEntries().length;
        String message = notepad.add(entryForAdd);
        Assertions.assertEquals("entry created", message);
        NotepadEntry[] afterAdd = notepad.showAllEntries();
        Assertions.assertNotEquals(beforeAdd, afterAdd.length);

        long count = Arrays.stream(afterAdd)
            .filter(c -> entryForAdd.equals(c.getEntry()))
            .count();
        Assertions.assertEquals(count, 1);
    }

    @Test
    public void whenAddIdenticalEntryThenEntryCreatedTest() {
        int beforeAdd = notepad.showAllEntries().length;
        String message = notepad.add(FIRST);
        Assertions.assertEquals("entry created", message);
        NotepadEntry[] afterAdd = notepad.showAllEntries();
        Assertions.assertNotEquals(beforeAdd, afterAdd.length);
        long count = Arrays.stream(afterAdd)
            .filter(c -> FIRST.equals(c.getEntry()))
            .count();
        Assertions.assertEquals(count, 2);
    }

    @Test
    public void whenEditEntryThenEntryChangedTest() {
        String newEntry = "edit";
        String message = notepad.edit(FIRST, newEntry);
        Assertions.assertEquals("entry changed", message);
        NotepadEntry[] afterEdit = notepad.showAllEntries();
        long countNewEntry = Arrays.stream(afterEdit)
            .filter(c -> newEntry.equals(c.getEntry()))
            .count();
        Assertions.assertEquals(countNewEntry, 1);
        Arrays.stream(afterEdit)
            .forEach(c -> Assertions.assertNotEquals(FIRST, c.getEntry()));
    }

    @Test
    public void whenEditEntryNotInNotebookThenEntryNotFoundTest() {
        int beforeEdit = notepad.showAllEntries().length;
        String entryForEdit = "entry from another notebook";
        String newEntry = "edit";
        String message = notepad.edit(entryForEdit, newEntry);
        Assertions.assertEquals("entry to change not found", message);
        int afterEdit = notepad.showAllEntries().length;
        Assertions.assertEquals(beforeEdit, afterEdit);
    }

}