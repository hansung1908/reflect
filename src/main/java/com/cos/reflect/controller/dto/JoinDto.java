package com.cos.reflect.controller.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinDto {

    private String username;
    private String password;
    private String email;
}
