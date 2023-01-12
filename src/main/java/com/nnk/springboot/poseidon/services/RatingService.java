package com.nnk.springboot.poseidon.services;

import com.nnk.springboot.poseidon.domain.Rating;
import com.nnk.springboot.poseidon.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService extends ACrudService<RatingRepository, Rating, Integer> {

    @Autowired
    public RatingService(RatingRepository repository) {
        super(repository);
    }
}
