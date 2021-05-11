package com.example.springbootmovie.controller;


import com.example.springbootmovie.model.Movie;
import com.example.springbootmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


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
        return "movies";
    }

    @GetMapping("/addNewMovie")
    public String addNewMovie(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie",movie);
        return "new_movie";
    }

    @PostMapping("/saveMovie")
    public String saveMovie(@ModelAttribute("movie")Movie movie, @RequestParam("image") MultipartFile multipartFile) throws IOException{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        movie.setMovieImage(fileName);
        Movie savedMovie = movieService.saveMovie(movie);
        String uploadDir = "./movie-images/" + savedMovie.getId();
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ioe){
            throw new IOException("Could not save image file: " + fileName,ioe);
        }
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
