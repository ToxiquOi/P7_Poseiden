package com.nnk.springboot.poseidon.services;

import com.nnk.springboot.poseidon.domain.Trade;
import com.nnk.springboot.poseidon.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService extends ACrudService<TradeRepository, Trade, Integer> {

    @Autowired
    public TradeService(TradeRepository repo) {
        super(repo);
    }

}