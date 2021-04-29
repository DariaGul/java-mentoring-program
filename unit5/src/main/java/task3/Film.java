package task3;

import java.io.Serializable;

public class Film implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private Actor mainActor;

    public Film(String name, Actor mainActor) {
        this.mainActor = mainActor;
        this.title = name;
    }

    public String getTitle() {
        return title;
    }

    public Actor getMainActor() {
        return mainActor;
    }

    @Override
    public String toString() {
        return "Film{" +
            "title='" + title + '\'' +
            ", mainActor=" + mainActor +
            '}';
    }
}
