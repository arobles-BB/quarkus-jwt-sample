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
//		data.put("user", new User("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", Collections.singleton(Role.USER)));
        data.put("user", new User("user", "OT7FEYTpdkFbo9DJV6/qGI1QHkVLTtID0hFXvXJ5JGs=", Collections.singleton(Role.USER)));
        //username:passwowrd -> admin:admin
//		data.put("admin", new User("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", Collections.singleton(Role.ADMIN)));
        data.put("admin", new User("admin", "iuByMTFsFghLqzUX6ErfKRbJ/i/33gQ3kQaFpdGBQjk=", Collections.singleton(Role.ADMIN)));
    }

}