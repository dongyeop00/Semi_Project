package com.gml.semi_project.DTO;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String memberId;
    private String password;
}