package task3;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MovieManager manager = new MovieManager(new FilmSerializer());

        List<Film> newFilms = new ArrayList<>();
        newFilms.add(new Film("Акулий Торнадо", new Actor("Тара Рид")));
        manager.modifyCollectionFilms(newFilms);
        newFilms.clear();

        Actor newActor = new Actor("Тим Карри");
        newFilms.add(new Film("Улика", newActor));
        newFilms.add(new Film("Шоу ужасов Рокки Хоррора", newActor));
        manager.modifyCollectionFilms(newFilms);

    }


}
