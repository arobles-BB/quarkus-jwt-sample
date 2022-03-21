package com.bloobirds.quarkusjwt.model;

import lombok.Data;

import java.util.ArrayList;
@Data
public class JSONWebKEy {
    private ArrayList<JWKKey> keys= new ArrayList<JWKKey>();
}
