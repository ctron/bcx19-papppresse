package de.dentrassi.bcx19;

import java.security.Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.dentrassi.crypto.pem.PemKeyStoreProvider;

@SpringBootApplication
public class Application {

    public static void main(final String[] args) throws InterruptedException {
        Security.addProvider(new PemKeyStoreProvider());
        SpringApplication.run(Application.class, args);
        Thread.sleep(Long.MAX_VALUE);
    }

}