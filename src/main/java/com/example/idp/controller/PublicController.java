package com.example.idp.controller;

import com.example.idp.entity.Key;
import com.example.idp.service.key.KeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/key")
public class PublicController {
    private final KeyService keyService;

    @PostMapping("/generate")
    public ResponseEntity<Void> addKeys() throws NoSuchAlgorithmException {
        keyService.addKey();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/public")
    public ResponseEntity<?> getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Optional<Key> keyPair = keyService.getAllKeys();
        if (keyPair.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] publicKeyBytes = Base64.getDecoder().decode(keyPair.get().getPublicKey());

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        //RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);

        return new ResponseEntity<>("publicKey", HttpStatus.OK);
    }
}
