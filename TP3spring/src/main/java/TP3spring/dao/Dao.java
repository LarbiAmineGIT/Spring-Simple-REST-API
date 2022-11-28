package TP3spring.dao;

import TP3spring.model.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class Dao implements IGamesDao {

    private Game game = new Game("Warcraft3","rts");
    private Game game2 = new Game("Dota2","moba");
    private Game game3 = new Game("Counter-strike","fps");
    private List<Game> gamelist = new ArrayList<Game>();

    public Dao(){
        gamelist.add(game);
        gamelist.add(game2);
        gamelist.add(game3);
    }




    public List<Game> findAll(){
        return this.gamelist;
    }

    public Game findById(int id){

        Game gameretured = this.gamelist.stream().filter(game1 -> game1.getId() == id).findAny().orElse(null);

        if(gameretured != null){
            System.out.println("Found it ! ");
            return gameretured;
        }
        return null;
    }

    /*
    * Pour sauvegarder, on doit fournir le jeu sous format JSON en spécifiant un id, qui ne doit
    * pas exister dans notre liste
    * */
    public Game save(Game g){
        boolean unique = true;

        for (Game game : this.gamelist) {
            if (game.getId() == g.getId()) {
                unique = false;
                System.out.println("Can't create game with the same id ");
            }
        }
        if(unique){
            this.gamelist.add(g);
            System.out.println("New game successfully added");
            return g;
        }
        return null;
    }
    /*
    * Pour update un jeu, on doit fournir l'id d'un jeu qui existe
    * */
    public Game update(int id, Game game){

        Game gameupdate = this.gamelist.stream().filter(game1 -> game1.getId() == id).findAny().orElse(null);

        if(gameupdate != null){
            gameupdate.setName(game.getName());
            gameupdate.setGenre(game.getGenre());
            System.out.println("GAME MODIFIED !");
            return gameupdate;
        }
        return null;
    }


    public void deleteById(int id) {
        Game gamedelete = this.gamelist.stream().filter(game1 -> game1.getId() == id).findAny().orElse(null);
        if(gamedelete != null){
            this.gamelist.remove(game);
            System.out.println(" was deleted");
        }else{
            System.out.println("Il n'est pas possible de supprimer cette id : "+ id +" il n'est pas dans la liste");
        }
    }
}
