package task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import task1.Stationery;

public class NovicePack {

    public static Map<String, Stationery> collectNovicePackMap() {
        Map<String, Stationery> mapStationery = new HashMap<>();
        mapStationery.put(Notebook.class.getSimpleName(), new Notebook(false));
        mapStationery.put(Pen.class.getSimpleName(), new Pen("Шариковая"));
        mapStationery.put(Pencil.class.getSimpleName(), new Pencil("2H"));
        mapStationery.put(Eraser.class.getSimpleName(), new Eraser(3));
        return mapStationery;
    }

    public static List<Stationery> collectNovicePack() {
        List<Stationery> listStationery = new ArrayList<>();
        listStationery.add(new Notebook(false));
        listStationery.add(new Pen("Шариковая"));
        listStationery.add(new Pencil("2H"));
        listStationery.add(new Eraser(3));
        listStationery.add(new CorrectionPencil(6));
        listStationery.add(new Sticker("Желтый"));
        listStationery.add(new PaperA4(2));
        return listStationery;
    }

}
