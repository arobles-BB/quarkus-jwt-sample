package com.bloobirds.quarkusjwt.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode(callSuper = false)
public class User {

    public static Map<String, User> data = null;
    private static String pass1= "1UnQZzBd3GqcFWT8PYECsGBgUHe64wCk72hqUwI4EEo=";
    private static String pass2= "pTBwKGZTsDpjJg47vFmi8X3fxcnHEiQobOgvM9QBHts=";
    public String username;
    public String password;


    public Set<Role> roles;

    public static User findByUsername(String username) {
        if (data == null) init();
        return data.get(username);
    }

    public static void init(){
        data= new HashMap<>();
        //username:passwowrd -> user:user
        data.put("user", new User("user", pass2, Collections.singleton(Role.USER)));
        //username:passwowrd -> admin:admin
        data.put("admin", new User("admin", pass1, Collections.singleton(Role.ADMIN)));
    }

}