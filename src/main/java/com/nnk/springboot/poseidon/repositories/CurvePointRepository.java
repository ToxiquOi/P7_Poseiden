package com.nnk.springboot.poseidon.repositories;

import com.nnk.springboot.poseidon.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
