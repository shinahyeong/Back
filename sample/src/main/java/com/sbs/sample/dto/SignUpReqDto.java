package com.sbs.sample.dto;
// data transport object -> 프론트엔드와 연결하기 위해 필요, 정보를 주고받는 부분

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SignUpReqDto {
    private String email;
    private String pwd;
    private String name;
}
