package com.nnk.springboot.poseidon.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
@Getter
@Setter
@NoArgsConstructor
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private Integer curveId;
    private Timestamp asOfDate;
    private Double term;
    private Double value;
    private Timestamp creationDate;

    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }
}
