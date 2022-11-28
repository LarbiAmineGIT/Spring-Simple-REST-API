package TP3spring.controller;

import TP3spring.model.Game;
import TP3spring.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Games")
public class GameController {
    @Autowired
    private Dao dao;


    @GetMapping(value="")
    public List<Game> getAllGames(){

        ArrayList<Game> arrayList = new ArrayList<>();
        arrayList = (ArrayList<Game>) dao.findAll();

        System.out.print("[");

        for(int i =0; i < arrayList.size(); i++){
            System.out.print("\"");
            System.out.print(arrayList.get(i).getName()+","+arrayList.get(i).getGenre());
            System.out.print("\"");
        }

        System.out.print("]");

        return dao.findAll();
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Game> getGame(@PathVariable int id){

        Game game = dao.findById(id);

        if(game != null){
            System.out.println("Cas ou le get sur un id est réalisée : \n");

            System.out.print("[");
            System.out.print("\"");
            System.out.print("id: "+ game.getId()+", name: "+game.getName()+", genre: "+game.getGenre());
            System.out.print("\"");
            System.out.print("]");

            System.out.println("Affichage de la requête : "+ ResponseEntity.ok(game) +"\n");
            return ResponseEntity.ok(game);

        }else{
            System.out.println("Cas ou le GET sur un id n'est pas réalisée : \n");
            System.out.println("Affichage de la requête : "+ ResponseEntity.notFound().build() +"\n");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value="")
    public void addGame(@RequestBody Game g){
        dao.save(g);
    }


    @PutMapping(value="/{id}")
    public ResponseEntity<Game> addGame(@PathVariable int id, @RequestBody Game g){
       Game res = dao.update(id,g);
       if(res != null){
           System.out.println("Cas ou l'update est réalisée : \n");
           System.out.println("Affichage de la requête : "+ ResponseEntity.ok(res) +"\n");
           return ResponseEntity.ok(res);
       }else{
           System.out.println("Cas ou l'update n'est pas réalisée : \n");
           System.out.println("Affichage de la requête : "+ ResponseEntity.notFound().build() +"\n");
           return ResponseEntity.notFound().build();
       }
    }

    @DeleteMapping(value="/{id}")
    public void deleteGame(@PathVariable int id){

        System.out.print("Game that we try to delete :  ");
        Game game = dao.findById(id);

        if(game != null){
            System.out.print("[");
            System.out.print("\"");
            System.out.print("id: "+ game.getId()+", name: "+game.getName()+", genre: "+game.getGenre());
            System.out.print("\"");
            System.out.print("]");
            dao.deleteById(id);
        }else{
            System.out.println("Il n'est pas possible de supprimer cette id : "+ id +" il n'est pas dans la liste");
        }
    }

}
