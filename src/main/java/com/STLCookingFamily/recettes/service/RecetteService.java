package com.STLCookingFamily.recettes.service;

import com.STLCookingFamily.recettes.entity.Recette;
import com.STLCookingFamily.recettes.repository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecetteService {

    @Autowired
    RecetteRepository recetteRepository;

    public ResponseEntity<List<Recette>> retrieveAllRecette(String title){
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

    public ResponseEntity<Recette> retrieveRecetteById( long id) {
        Optional<Recette> recetteData = recetteRepository.findById(id);

        if (recetteData.isPresent()) {
            return new ResponseEntity<>(recetteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Recette> createRecetteService(Recette recette) {
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


    public ResponseEntity<Recette> updateRecetteService(long id, Recette recette) {
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

    public ResponseEntity<HttpStatus> deleteRecetteService(long id) {
        try {
            recetteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllRecettesService() {
        try {
            recetteRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Recette>> findByPublishedService() {
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


    public ResponseEntity<List<Recette>>  retrieveRecettesByUserId(int id){
        List<Recette> recettes = recetteRepository.listRecette(id);
        try {
            if (recettes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recettes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
