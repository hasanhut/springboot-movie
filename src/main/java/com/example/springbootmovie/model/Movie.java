package com.example.springbootmovie.model;


import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name",length = 45)
    private String name;
    @Column(name = "year")
    private Integer year;
    @Column(name = "genre")
    private String genre;
    @Column(name = "description")
    private String description;
    @Column(name = "movieImage",length = 64)
    private String movieImage;
    @Column(name = "languages")
    private String languages;
    @Column(name = "actors")
    private String actors;

    public Movie() {

    }

    public Movie(Integer id, String name, Integer year, String genre, String description, String movieImage, String languages, String actors) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.movieImage = movieImage;
        this.languages = languages;
        this.actors = actors;
    }

    public Movie(Integer id, String name, Integer year, String genre, String actors) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.actors = actors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @Transient
    public String getPhotosImagePath() {
        if (movieImage == null || id == null) return null;

        return "/movie-images/" + id + "/" + movieImage;
    }
}

