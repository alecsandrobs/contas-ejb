package com.stolk.alecsandro.obra.security;

import org.jboss.security.Base64Encoder;

import java.security.MessageDigest;

public class PassGenerator {

    public String generate(String senha) {
        try {
            byte[] digest = MessageDigest.getInstance("sha-256").digest(senha.getBytes());
            return Base64Encoder.encode(digest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
