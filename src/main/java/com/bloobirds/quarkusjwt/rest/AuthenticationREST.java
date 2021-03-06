package com.bloobirds.quarkusjwt.rest;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bloobirds.quarkusjwt.model.AuthRequest;
import com.bloobirds.quarkusjwt.model.AuthResponse;
import com.bloobirds.quarkusjwt.model.User;
import com.bloobirds.quarkusjwt.util.PBKDF2Encoder;
import com.bloobirds.quarkusjwt.util.TokenUtils;

import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Log
@Path("/auth")
public class AuthenticationREST {

    @Inject
    PBKDF2Encoder passwordEncoder;

    @ConfigProperty(name = "com.bloobirds.quarkusjwt.password.secret")  private String secret;
    @ConfigProperty(name = "com.bloobirds.quarkusjwt.jwt.duration") public Long duration;
    @ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

    @PermitAll
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AuthRequest authRequest) {
        User u = User.findByUsername(authRequest.username);
        log.info("u:"+u);
        if (u != null && u.password.equals(passwordEncoder.encode(authRequest.password))) {
            try {
                AuthResponse response=new AuthResponse(TokenUtils.generateToken(u.username, u.roles, duration, issuer, secret));
                return Response.ok(response).build();
            } catch (Exception e) {
                log.warning(e.getLocalizedMessage());
                return Response.status(Status.UNAUTHORIZED).build();
            }
        } else {
            log.warning("p:"+passwordEncoder.encode(authRequest.password));
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }

}