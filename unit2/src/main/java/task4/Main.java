package task4;

import java.util.Comparator;
import java.util.List;
import task1.Stationery;
import task2.Notebook;
import task2.NovicePack;
import task2.Pencil;

public class Main {

    public static void main(String[] args) {
        List<Stationery> listStationery = NovicePack.collectNovicePack();

        listStationery.sort(Comparator.comparing(Stationery::getCost));
        System.out.println(listStationery);

        listStationery.sort(Comparator.comparing(c -> c.getClass().getSimpleName()));
        System.out.println(listStationery);

        Notebook notebook = new Notebook(true);
        notebook.setCost(250);
        listStationery.add(notebook);

        Pencil pencil = new Pencil("HB");
        pencil.setCost(100);
        listStationery.add(pencil);
        listStationery.sort(Comparator.comparing(Stationery::getCost).reversed()
                                .thenComparing(c -> c.getClass().getSimpleName()));
        System.out.println(listStationery);
    }
}