package com.example.backend.entity.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class AutorizeVo {
    String username;
    String role;
    String token;
    Date expires;
}
