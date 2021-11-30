package com.example.demo.config.firebase;

import java.io.FileInputStream;
import java.io.IOException;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class firebaseConfig {
    
    @Bean
    public void fireCon() {
        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("C:/Users/sesisoft/Desktop/demo/src/main/resources/tttt-cf9d9-firebase-adminsdk-1ilbf-18db1bff35.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
      
    }
}
