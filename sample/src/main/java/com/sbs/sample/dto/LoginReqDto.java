package com.sbs.sample.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LoginReqDto {
    private String email;
    private String pwd;
}
