package com.example.springbootmovie.service;

import com.example.springbootmovie.model.Actor;
import com.example.springbootmovie.model.Movie;
import com.example.springbootmovie.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getActors(){
        return actorRepository.findAll();
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteActor(int id){
        actorRepository.deleteById(id);
    }
}
