package com.lufecrx.spring.app.redesocial.controller.rsa;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RsaController {

    @GetMapping("/gerachaversa")
    public List<Key> gerachaversa() {
        RSAGenerator rsa = new RSAGenerator(128);

        Key publicKey = rsa.getPublickey();
        Key privateKey = rsa.getPrivatekey();

        return List.of(publicKey, privateKey);
    }
}
