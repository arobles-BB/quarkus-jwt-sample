package com.bloobirds.quarkusjwt.model;

import lombok.Data;

@Data
public class JWKKey {
    final String kty="oct";
    final String kid="secretKey";
    private String k;
}
