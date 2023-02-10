package com.nnk.springboot.poseidon.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationModel {
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Pattern(regexp = "^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[!@#&()–{}:;',?/*~$^+=<>]+).{8,20}$", message = "Enter valid password")
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    @NotBlank(message = "Role is mandatory")
    private String role;
}
