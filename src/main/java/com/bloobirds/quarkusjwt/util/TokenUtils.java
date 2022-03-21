package com.bloobirds.quarkusjwt.util;

import com.bloobirds.quarkusjwt.model.JSONWebKEy;
import com.bloobirds.quarkusjwt.model.JWKKey;
import com.bloobirds.quarkusjwt.model.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.jwt.algorithm.SignatureAlgorithm;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.RequestScoped;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@RequestScoped
public class TokenUtils {

    public static String generateToken(String username, Set<Role> roles, Long duration, String issuer, String secret) {
        JwtClaimsBuilder claimsBuilder = Jwt.claims();

        Set<String> groups = new HashSet<>();
        for (Role role : roles) groups.add(role.toString());
        claimsBuilder.issuer(issuer);
        claimsBuilder.subject(username);
        claimsBuilder.issuedAt(System.currentTimeMillis());
        claimsBuilder.expiresIn(duration);
        claimsBuilder.groups(groups);

        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey= new SecretKeySpec(secretBytes, "HS256");

        return claimsBuilder.jws().algorithm(SignatureAlgorithm.HS256).sign(secretKey);

    }

    public static void writeSecret64(String secret) throws IOException {
        JSONWebKEy key= new JSONWebKEy();
        JWKKey data= new JWKKey();
        data.setK(secret);
        ArrayList<JWKKey> keys= new ArrayList<>();
        keys.add(data);
        key.setKeys(keys);

        ObjectMapper mapper = new ObjectMapper();
        String jsonSecret=mapper.writeValueAsString(key);

        Base64.Encoder encoder= Base64.getEncoder();
        byte[] jsonSecret64 = encoder.encode(jsonSecret.getBytes(StandardCharsets.UTF_8));

        FileOutputStream fio= new FileOutputStream("secret");
        fio.write(jsonSecret64);
        fio.close();
    }

}