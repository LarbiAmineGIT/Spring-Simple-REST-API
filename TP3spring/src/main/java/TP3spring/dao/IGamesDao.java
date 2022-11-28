package TP3spring.dao;

import TP3spring.model.Game;

import java.util.List;
import java.util.Optional;

public interface IGamesDao {


    public List<Game> findAll();
    public Game findById(int id);
    public Game save(Game g);

    public Game update(int id, Game g);

    public void deleteById(int id);
}
