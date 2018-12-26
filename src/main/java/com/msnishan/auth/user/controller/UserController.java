package com.msnishan.auth.user.controller;

import com.msnishan.auth.annotation.HttpRequestContext;
import com.msnishan.auth.dto.UserDTO;
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

@RestController
@RequestMapping("/auth/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "post a user", response = UserDTO.class, nickname = "getUser")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "User", required = true, value = "User Details", dataType = "User",
                    paramType = "body", type = "body")
    )
    public ResponseEntity<ResponseEnvelope> createUser(@ApiParam(name = "User") @RequestBody RequestEnvelope request) {

        UserDTO user = EnvelopeConverter.extractFromEnvelop(request, UserDTO.class);
        user = DtoFactory.createUserDTO(userService.createUser(request.getContext(), user));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(request.getContext(), null, "1200", user);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseEnvelope> findGroup(@HttpRequestContext RequestContext context, @PathVariable("userId") String userId) {
        UserDTO user = DtoFactory.createUserDTO(userService.findUserById(context, userId));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(context, null, "1200", user);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }
}
