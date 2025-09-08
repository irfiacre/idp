package com.example.idp.service.key;

import com.example.idp.entity.Key;
import com.example.idp.repository.KeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class KeyService {
    private final KeyRepository keyRepository;

    public void addKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) pair.getPublic();
//
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        System.out.println("-----BEGIN PUBLIC KEY-----\n"
                + publicKeyStr + "\n-----END PUBLIC KEY-----");
//
//        Key keyPairGenerated = Key.builder()
//                .publicKey(privateKey.getFormat())
//                .privateKey(privateKey.getFormat())
//                .build();
      //  keyRepository.deleteAll();

//        keyRepository.save(keyPairGenerated);
    }

    public Optional<Key> getAllKeys() {
        return keyRepository.findAll().stream().findFirst();
    }
}
