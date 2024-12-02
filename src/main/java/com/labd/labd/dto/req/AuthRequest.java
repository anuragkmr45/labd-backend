package com.labd.labd.dto.req;

import lombok.Data;

@Data
public class AuthRequest  {
    private String email;
    private String password;
}
