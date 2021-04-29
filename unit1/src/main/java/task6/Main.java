package task6;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Notepad notepad = new Notepad();
        notepad.add("add new record");
        notepad.add("add");
        notepad.add("new");
        notepad.add("record");
        notepad.add("delete");
        notepad.add("add record throw null");

        notepad.delete("delete");
        notepad.edit("add record throw null", "edit record");

        Arrays.stream(notepad.showAllEntries())
            .forEach(cur -> System.out.println(cur.getEntry()));
    }
}
