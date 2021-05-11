package com.example.springbootmovie.controller;

import com.example.springbootmovie.model.Movie;
import com.example.springbootmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;


@Controller
public class MovieController {
    private final MovieService movieService;

    private final String UPLOAD_DIR = "./uploads/";

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String showMovieList(Model model,String keyword){
        if (keyword != null){
            model.addAttribute("movies",movieService.findByKeyword(keyword));
        }
        else{
            model.addAttribute("movies",movieService.getMovies());
        }
        return "index";
    }

    @GetMapping("/addNewMovie")
    public String addNewMovie(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie",movie);
        return "new_movie";
    }

    @PostMapping("/saveMovie")
    public String saveMovie(@ModelAttribute("movie") Movie movie) throws IOException{
        movieService.saveMovie(movie);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model){
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie",movie);
        return "update_movie";
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable(value = "id") int id){
        movieService.deleteMovie(id);
        return "redirect:/";
    }
}
