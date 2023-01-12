package com.nnk.springboot.poseidon.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationModel {
    private String username;
    private String password;
    private String fullname;
}
