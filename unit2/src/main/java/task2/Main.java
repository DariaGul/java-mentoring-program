package task2;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import task1.Stationery;

public class Main {

    public static void main(String[] args) {

        Map<String, Stationery> mapStationery = NovicePack.collectNovicePackMap();
        Notebook notebook = (Notebook) getStationery(mapStationery, "Notebook");
        Pen pen1 = (Pen) getStationery(mapStationery, "Pen");
        CorrectionPencil correctionPencil = new CorrectionPencil(6);

        pen1.write("kono dio da", notebook);
        UUID recordId3 = pen1.write("za warudo", notebook);
        writeRecords(notebook);

        notebook.deleteRecord(recordId3, correctionPencil);
        writeRecords(notebook);

        Pencil pencil1 = (Pencil) getStationery(mapStationery, "Pencil");
        UUID recordId1 = pencil1.write("arrivederci", notebook);

        notebook.deleteRecord(recordId1, correctionPencil);
        writeRecords(notebook);

        Eraser eraser = (Eraser) getStationery(mapStationery, "Eraser");
        UUID recordId2 = pen1.write("indelible", notebook);
        notebook.deleteRecord(recordId2, eraser);
        writeRecords(notebook);
    }

    private static void writeRecords(PaperProduct paper1) {
        paper1.getRecords().forEach(c -> System.out.println(c.getText()));
        System.out.println();
    }

    private static Stationery getStationery(Map<String, Stationery> mapStationery, String typeStationery) {

        Optional<Entry<String, Stationery>> stationery = mapStationery
            .entrySet()
            .stream()
            .filter(c -> c.getValue().toString().contains(typeStationery))
            .findAny();
        return stationery.map(Entry::getValue).orElse(null);
    }
}
