package com.nnk.springboot.poseidon.services;

import com.nnk.springboot.poseidon.domain.CurvePoint;
import com.nnk.springboot.poseidon.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

@Service
public class CurvePointService extends ACrudService<CurvePointRepository, CurvePoint, Integer> {

    public CurvePointService(CurvePointRepository repository) {
        super(repository);
    }

}
