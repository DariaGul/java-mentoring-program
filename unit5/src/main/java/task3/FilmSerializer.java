package task3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FilmSerializer {

    public void serialize(String filename, List<Film> collectionFilm) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(collectionFilm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Film> deserialize(String filename) {
        List<Film> listFilm = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            listFilm = (List<Film>) objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listFilm;
    }

}
