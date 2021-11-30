package com.example.demo.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.testDto;
import com.example.demo.service.firebaseService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restController {
    
    private Logger logger=LoggerFactory.getLogger(restController.class);
    private final String collectionName="testTable";
    
    @Autowired
    private firebaseService firebaseService;

    @RequestMapping(value = "/test/**",method = RequestMethod.GET)
    public void test(HttpServletRequest request,HttpServletResponse response) {
        logger.info("testController");
        Firestore firestore=firebaseService.callFirebase();
        String action=request.getParameter("action");
        if(action.equals("select")){
            logger.info("select 요청");
            ApiFuture<QuerySnapshot>select=firestore.collection(collectionName).get();
            try {
                List<QueryDocumentSnapshot> documents = select.get().getDocuments();
                for(QueryDocumentSnapshot q:documents){
                    System.out.println(q.toObject(testDto.class));
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }    
        }else if(action.equals("insert")){
            logger.info("insert 요청");
            testDto dto=testDto.builder().name("testName2").build();
            ApiFuture<WriteResult> apiFuture = firestore.collection(collectionName).document("1").set(dto);
            try {
                logger.info(apiFuture.get().getUpdateTime().toString());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
       
    }
}
