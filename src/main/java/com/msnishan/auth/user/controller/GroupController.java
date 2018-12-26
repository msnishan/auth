package com.msnishan.auth.user.controller;

import com.msnishan.auth.dto.GroupDTO;
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
@RequestMapping("/auth/groups")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<ResponseEnvelope> createGroup(@RequestBody RequestEnvelope request) {

        GroupDTO group = EnvelopeConverter.extractFromEnvelop(request, GroupDTO.class);
        group = DtoFactory.createGroupDTO(groupService.createGroup(request.getContext(), group));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(request.getContext(), null, "1200", group);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<ResponseEnvelope> findGroup(RequestEnvelope request, @PathVariable("groupId") String groupId) {
        GroupDTO group = DtoFactory.createGroupDTO(groupService.findGroupById(request.getContext(), groupId));
        ResponseEnvelope responseEnvelope = EnvelopeConverter
                .createResponseEnvelop(request.getContext(), null, "1200", group);
        return new ResponseEntity<>(responseEnvelope, HttpStatus.CREATED);
    }
}
