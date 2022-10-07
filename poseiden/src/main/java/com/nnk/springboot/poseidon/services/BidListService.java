package com.nnk.springboot.poseidon.services;

import com.nnk.springboot.poseidon.domain.BidList;
import com.nnk.springboot.poseidon.repositories.BidListRepository;
import org.springframework.stereotype.Service;

@Service
public class BidListService extends ACrudService<BidListRepository, BidList, Integer> {

    public BidListService(BidListRepository repository) {
        super(repository);
    }
}
