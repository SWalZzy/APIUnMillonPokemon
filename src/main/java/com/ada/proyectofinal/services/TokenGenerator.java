package com.ada.proyectofinal.services;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class TokenGenerator {

    protected static SecureRandom random = new SecureRandom();

    public synchronized String generateToken( String username ) {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 16 );
        return ( username + ":" + random );
    }
}