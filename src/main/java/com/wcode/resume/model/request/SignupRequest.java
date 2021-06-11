package com.wcode.resume.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @Size(min = 10, max = 40)
    @NotBlank
    private String fullname;

    @Size(min = 10, max = 60)
    @NotBlank
    private String address;

    @Size(min = 3, max = 10)
    @NotBlank
    private String zip;

    @Size(min = 10, max = 20)
    @NotBlank
    private String phone;

    @Size(min = 10, max = 500)
    @NotBlank
    private String aboutme;

}
