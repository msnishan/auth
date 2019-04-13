package com.msnishan.auth.user.controller;

import com.msnishan.auth.annotation.HttpRequestContext;
import com.msnishan.auth.dto.FeatureAccessDTO;
import com.msnishan.auth.dto.UserDTO;
import com.msnishan.auth.user.domain.FeatureAccess;
import com.msnishan.auth.user.service.FeatureAccessService;
import com.msnishan.auth.user.service.UserService;
import com.msnishan.auth.user.util.DtoFactory;
import com.msnishan.gen.converter.EnvelopeConverter;
import com.msnishan.gen.type.request.RequestContext;
import com.msnishan.gen.type.request.RequestEnvelope;
import com.msnishan.gen.type.response.ResponseEnvelope;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

@RestController
@RequestMapping("/auth/features")
public class FeatureController {

    private FeatureAccessService featureAccessService;

    public FeatureController(FeatureAccessService featureAccessService) {
        this.featureAccessService = featureAccessService;
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "post a Feature", response = FeatureAccessDTO.class, nickname = "createFeature")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "FeatureAccess", required = true, value = "Feature Details", dataType = "FeatureAccess",
                    paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> createFeatureAccess(@ApiParam(name = "FeatureAccess") @RequestBody RequestEnvelope request) {
        addRequestId(request);
        FeatureAccessDTO featureAccessDTO = EnvelopeConverter.extractFromEnvelop(request, FeatureAccessDTO.class);
        FeatureAccess featureAccess = featureAccessService.saveFeatureAccess(request.getContext(), featureAccessDTO);
        featureAccessDTO.setId(featureAccess.getId());
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(request.getContext(), null, "1200", featureAccessDTO);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

    private void addRequestId(RequestEnvelope request) {
        if (request.getContext().getRequestId() == null) {
            request.getContext().setRequestId(UUID.randomUUID().toString());
        }
    }

    @GetMapping("/{featureAccessId}")
    @ApiOperation(value = "Get FeatureAccess", notes = "Get FeatureAccess.", response=FeatureAccessDTO.class)
    public ResponseEntity<ResponseEnvelope> findFeatureAccess(@ApiIgnore @HttpRequestContext RequestContext context, @PathVariable("featureAccessId") String featureAccessId) {
        FeatureAccessDTO featureAccessDTO = DtoFactory.createFeatureAccessDTO(featureAccessService.findFeatureAccessById(context, featureAccessId));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(context, null, "1200", featureAccessDTO);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.OK);
    }


}
