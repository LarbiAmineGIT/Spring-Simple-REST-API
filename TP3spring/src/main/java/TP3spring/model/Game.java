package TP3spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class Game{

    private String name;
    private String genre;

    private int id;

    static int counter = 0;

    public Game() {
    }

    public Game(String name, String genre){
        this.name = name;
        this.genre = genre;
        this.id = counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

}
