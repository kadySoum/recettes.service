package com.STLCookingFamily.recettes.entity;


import javax.persistence.Column;

        import lombok.AllArgsConstructor;
        import lombok.NoArgsConstructor;

        import javax.persistence.*;
        import java.util.ArrayList;

@Entity //The @Entity annotation specifies that the class is an entity and is mapped to a database table.
@Table(name = "recettes") //The @Table annotation specifies the name of the database table to be used for mapping.
//@NoArgsConstructor
//@AllArgsConstructor
public class Recette {

    @Id //The @Id annotation specifies the primary key of an entity


    @SequenceGenerator(
            name= "recette_sequence",
            sequenceName = "recette_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "recette_sequence")

   // @GeneratedValue(strategy = GenerationType.AUTO) //the @GeneratedValue provides for the specification of generation strategies for the values of primary keys
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    @Column(name = "ingredients")
    private ArrayList<String> ingredients;

    @Column(name = "auteurId")
    private int auteurId;

    @Column(name = "photoId")
    private ArrayList<String> photoId;


    public Recette() {

    }
    public Recette(String title, String description, ArrayList<String> ingredient, int auteurId,ArrayList<String> photoId, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.ingredients = ingredients;
        this.auteurId = auteurId;
        this.photoId = photoId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }

    //add ingredient
    //remove ingredient
    public void setIngredient(ArrayList<String> ingredient) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getIngredients() {
        return ingredients ;
    }

    public ArrayList<String> getPhotoId() {
        return photoId ;
    }
    public ArrayList<String> setPhotoId(ArrayList<String> photos) {
        return photoId=photos;
    }
    public int getAuteurId() {
        return auteurId;
    }
    public int setAuteurId(int auteur) {
        return auteurId=auteur;
    }
    @Override
    public String toString() {
        return "Recette [id=" + id + ", title=" + title + ", desc=" + description + ", ingredients=" + ingredients+", auteurId=" + auteurId + ", photoId=" + photoId+  ", published=" + published + "]";
    }
}

/*

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Entity //The @Entity annotation specifies that the class is an entity and is mapped to a database table.
@Table(name = "recettes") //The @Table annotation specifies the name of the database table to be used for mapping.
//@NoArgsConstructor
//@AllArgsConstructor
public class Recette {

    @Id //The @Id annotation specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.AUTO) //the @GeneratedValue provides for the specification of generation strategies for the values of primary keys
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    @Column(name = "ingredients")
    private ArrayList<String> ingredients;


    public Recette() {

    }
    public Recette(String title, String description, ArrayList<String> ingredient, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.ingredients = ingredients;

    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }

    //add ingredient
    //remove ingredient
    public void setIngredient(ArrayList<String> ingredient) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getIngredients() {
        return ingredients ;
    }

    public ArrayList<String> getUsers() {
        return ingredients ;
    }
    @Override
    public String toString() {
        return "Recette [id=" + id + ", title=" + title + ", desc=" + description + ", ingredients=" + ingredients+ ", published=" + published + "]";
    }
}
*/