package com.example.demo.service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class firebaseService {
    private Logger logger=LoggerFactory.getLogger(firebaseService.class);

    public Firestore callFirebase() {
        logger.info("callFirebase");
        return FirestoreClient.getFirestore();
    }
}
