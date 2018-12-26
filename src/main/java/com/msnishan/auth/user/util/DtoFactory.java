package com.msnishan.auth.user.util;

import com.msnishan.auth.dto.GrantDTO;
import com.msnishan.auth.dto.GroupDTO;
import com.msnishan.auth.dto.UserDTO;
import com.msnishan.auth.user.domain.Grant;
import com.msnishan.auth.user.domain.Group;
import com.msnishan.auth.user.domain.User;
import com.msnishan.auth.user.domain.UserGrant;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DtoFactory {

    public static GroupDTO createGroupDTO(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupId(group.getGroupId());
        groupDTO.setDescription(group.getDescription());
        groupDTO.setId(group.getId());
        groupDTO.setCompanyId(group.getCompanyId());
        return groupDTO;
    }

    public static GrantDTO createGrantDTO(Grant grant) {
        GrantDTO grantDTO = new GrantDTO();
        grantDTO.setGrantId(grant.getGrantId());
        grantDTO.setGroup(createGroupDTO(grant.getGroup()));
        grantDTO.setGroupId(grant.getGrantId());
        grantDTO.setDescription(grant.getDescription());
        grantDTO.setId(grant.getId());
        grantDTO.setCompanyId(grant.getCompanyId());
        return grantDTO;
    }

    public static UserDTO createUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setCompanyId(user.getCompanyId());
        userDTO.setMailId(user.getEmail());
        Set<String> grants = user.getUserGrants().stream()
                                                .map(userGrant -> userGrant.getGrant().getGrantId())
                                                .collect(Collectors.toSet());
        userDTO.setGrants(grants);
        return userDTO;
    }
}
