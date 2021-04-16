package com.STLCookingFamily.recettes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.STLCookingFamily.recettes.entity.Recette;
import org.springframework.data.jpa.repository.Query;

public interface RecetteRepository extends JpaRepository<Recette, Long> {
    List<Recette> findByPublished(boolean published);
    List<Recette> findByTitleContaining(String title);

    @Query("select n from Recette n where n.auteurId=?1")
    List<Recette> listRecette(int aId);
}
