package com.cifolio.cifolio.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public final class RsaKeyGenerator {
    private RsaKeyGenerator() {}

    public static KeyPair generateRsaKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception exception) {
            throw new IllegalStateException(exception);
        }
    }
}