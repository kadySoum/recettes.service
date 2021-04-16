package com.STLCookingFamily.recettes.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.STLCookingFamily.recettes.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.STLCookingFamily.recettes.entity.Recette;
import com.STLCookingFamily.recettes.repository.RecetteRepository;

/**
 * Cette classe s'occupe de repondre aux requetes et de faire les operations necessaires
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController //permet de designer une classe comme controleur, lui conferant la capacite de traiter les requêtes de type GET, POST, etc.  repondre directement sans passer par une vue.
@RequestMapping("/cookapis")
public class RecetteController {

   @Autowired
   RecetteService recetteService;

    @GetMapping("/")
    public String showIndex(){
        return "Bonjour, comment allez-vous les cocos ! ";
    }

   @GetMapping("/recettes")
    public ResponseEntity<List<Recette>> getAllRecettes(@RequestParam(required = false) String title) {
        return recetteService.retrieveAllRecette(title);
    }


    //@PathVariable permet de recuperer {id}  sous le nom id
    //si on a  @GetMapping("/recettes/{coco}")   et @PathVariable("id") on va recuperer la variable coco et le recuperer dans id
    @GetMapping("/recettes/{id}")
    public ResponseEntity<Recette> getRecetteById(@PathVariable("id") long id) {
        return recetteService.retrieveRecetteById( id);
    }


    @GetMapping("/recettesUser/{id}")
    public ResponseEntity<List<Recette>> getRecetteByUserId(@PathVariable("id") int id) {
        return recetteService.retrieveRecettesByUserId( id);
    }

    /**
     *
     * @param recette
     * @return
     */
    //RequestBody le parametre fait parti du body
    @PostMapping("/newRecette")
    //public ResponseEntity<Recette> createRecette(@RequestBody Recette recette) {
    public ResponseEntity<Recette> createRecette(@RequestBody Recette recette) {
      return recetteService.createRecetteService(recette);
    }

    /**
     * trouver une recette grace a son id
     * @param id
     * @param recette
     * @return
     */
    @PutMapping("/recettes/{id}")
    public ResponseEntity<Recette> updateRecette(@PathVariable("id") int id, @RequestBody Recette recette) {
        return recetteService.updateRecetteService(id,recette);
    }

    /**
     * supprimer une recette
     * @param id
     * @return
     */
    @DeleteMapping("/recettes/{id}")
    public ResponseEntity<HttpStatus> deleteRecette(@PathVariable("id") int id) {
        return recetteService.deleteRecetteService(id);
    }

    /**
     * tout supprimer
     * @return
     */
    @DeleteMapping("/recettes")
    public ResponseEntity<HttpStatus> deleteAllRecettes() {
        return recetteService.deleteAllRecettesService();
    }

    /**
     * afficher la liste des recettes publiéees
     * @return
     */
    @GetMapping("/recettes/published")
    public ResponseEntity<List<Recette>> findByPublished() {
        return recetteService.findByPublishedService();
    }

}

/*
package com.STLCookingFamily.recettes.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.STLCookingFamily.recettes.entity.Recette;
import com.STLCookingFamily.recettes.repository.RecetteRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController //permet de designer une classe comme controleur, lui conferant la capacite de traiter les requêtes de type GET, POST, etc.  repondre directement sans passer par une vue.
@RequestMapping("/cookapis")
//public class RecetteController {

    @Autowired
    RecetteRepository recetteRepository;

    @GetMapping("/")
    public String showIndex(){
        return "Bonjour, comment allez-vous les cocos ! ";
    }

    @GetMapping("/recettes")
    public ResponseEntity<List<Recette>> getAllRecettes(@RequestParam(required = false) String title) {
        try {
            List<Recette> recettes = new ArrayList<Recette>();

            if (title == null)
                recetteRepository.findAll().forEach(recettes::add);
            else
                recetteRepository.findByTitleContaining(title).forEach(recettes::add);

            if (recettes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(recettes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //@PathVariable permet de recuperer {id}  sous le nom id
    //si on a  @GetMapping("/recettes/{coco}")   et @PathVariable("id") on va recuperer la variable coco et le recuperer dans id
    @GetMapping("/recettes/{id}")
    public ResponseEntity<Recette> getRecetteById(@PathVariable("id") long id) {
        Optional<Recette> recetteData = recetteRepository.findById(id);

        if (recetteData.isPresent()) {
            return new ResponseEntity<>(recetteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //RequestBody le parametre fait parti du body
    @PostMapping("/newRecette")
    //public ResponseEntity<Recette> createRecette(@RequestBody Recette recette) {
    public ResponseEntity<Recette> createRecette(@RequestBody Recette recette) {
        // if (recette!= null) {

        try {
            Recette _recette = recetteRepository
                    .save(new Recette(recette.getTitle(), recette.getDescription(), recette.getIngredients(), recette.getAuteurId(),recette.getPhotoId(), false));
            return new ResponseEntity<>(_recette, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //  }
    }


    @PutMapping("/recettes/{id}")
    public ResponseEntity<Recette> updateRecette(@PathVariable("id") long id, @RequestBody Recette recette) {
        Optional<Recette> recetteData = recetteRepository.findById(id);

        if (recetteData.isPresent()) {
            Recette _recette = recetteData.get();
            _recette.setTitle(recette.getTitle());
            _recette.setDescription(recette.getDescription());
            _recette.setPublished(recette.isPublished());
            return new ResponseEntity<>(recetteRepository.save(_recette), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/recettes/{id}")
    public ResponseEntity<HttpStatus> deleteRecette(@PathVariable("id") long id) {
        try {
            recetteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/recettes")
    public ResponseEntity<HttpStatus> deleteAllRecettes() {
        try {
            recetteRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/recettes/published")
    public ResponseEntity<List<Recette>> findByPublished() {
        try {
            List<Recette> recettes = recetteRepository.findByPublished(true);

            if (recettes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recettes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
 */