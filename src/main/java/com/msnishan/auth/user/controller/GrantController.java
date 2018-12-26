package com.msnishan.auth.user.controller;

import com.msnishan.auth.dto.GrantDTO;
import com.msnishan.auth.dto.GroupDTO;
import com.msnishan.auth.user.service.GrantService;
import com.msnishan.auth.user.service.GroupService;
import com.msnishan.auth.user.util.DtoFactory;
import com.msnishan.gen.converter.EnvelopeConverter;
import com.msnishan.gen.type.request.RequestEnvelope;
import com.msnishan.gen.type.response.ResponseEnvelope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/grants")
public class GrantController {

    private GrantService grantService;

    public GrantController(GrantService grantService) {
        this.grantService = grantService;
    }

    @PostMapping
    public ResponseEntity<ResponseEnvelope> createGrant(@RequestBody RequestEnvelope request) {

        GrantDTO grant = EnvelopeConverter.extractFromEnvelop(request, GrantDTO.class);
        grant = DtoFactory.createGrantDTO(grantService.createGroup(request.getContext(), grant));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(request.getContext(), null, "1200", grant);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

    @GetMapping("/{grantId}")
    public ResponseEntity<ResponseEnvelope> findGroup(RequestEnvelope request, @PathVariable("grantId") String grandId) {
        GrantDTO grant = DtoFactory.createGrantDTO(grantService.findGrantById(request.getContext(), grandId));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(request.getContext(), null, "1200", grant);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }
}
