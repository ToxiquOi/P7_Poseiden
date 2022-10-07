package com.nnk.springboot.poseidon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "trade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer tradeId;
    private String account;
    private String type;
    private Double buyQuantity;

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }
}
