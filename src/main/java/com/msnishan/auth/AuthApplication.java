package com.msnishan.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.msnishan.auth.base.domain.RequestEnvelopeTest;
import com.msnishan.gen.type.request.RequestEnvelope;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import java.io.IOException;

@EnableAuthorizationServer
@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);

//        String ss = "{\n" +
//                "  \"context\": {\n" +
//                "    \"clientId\": \"1\",\n" +
//                "    \"posId\": \"1\",\n" +
//                "    \"userId\": \"ksaleh\"\n" +
//                "  },\n" +
//                "  \"featureAccessId\": \"TRAINER_REMOVE\",\n" +
//                "  \"featureName\": \"TRAINER_REMOVE\",\n" +
//                "  \"featureUrl\": \"TRAINER_REMOVE\",\n" +
//                "  \"method\": \"POST\",\n" +
//                "  \"requestDateTime\": \"2019-04-09T19:04:44.872Z\"\n" +
//                "}";
//        ObjectMapper om = new ObjectMapper().findAndRegisterModules();
//        try {
//            RequestEnvelopeTest srr = om.readValue(ss, RequestEnvelopeTest.class);
//            System.out.print(srr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
