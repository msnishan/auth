package com.msnishan.auth.user.util;

import com.msnishan.auth.dto.AddressDTO;
import com.msnishan.auth.dto.FeatureAccessDTO;
import com.msnishan.auth.dto.UserDTO;
import com.msnishan.auth.user.domain.Address;
import com.msnishan.auth.user.domain.FeatureAccess;
import com.msnishan.auth.user.domain.User;

import java.util.Set;
import java.util.stream.Collectors;

public class DtoFactory {

//    public static GroupDTO createGroupDTO(Group group) {
//        GroupDTO groupDTO = new GroupDTO();
//        groupDTO.setGroupId(group.getGroupId());
//        groupDTO.setDescription(group.getDescription());
//        groupDTO.setId(group.getId());
//        groupDTO.setCompanyId(group.getPosId());
//        return groupDTO;
//    }
//
//    public static GrantDTO createGrantDTO(Grant grant) {
//        GrantDTO grantDTO = new GrantDTO();
//        grantDTO.setGrantId(grant.getGrantId());
//        grantDTO.setGroup(createGroupDTO(grant.getGroup()));
//        grantDTO.setGroupId(grant.getGrantId());
//        grantDTO.setDescription(grant.getDescription());
//        grantDTO.setId(grant.getId());
//        grantDTO.setCompanyId(grant.getPosId());
//        return grantDTO;
//    }
    public static FeatureAccessDTO createFeatureAccessDTO(FeatureAccess featureAccess) {
        FeatureAccessDTO featureAccessDTO = new FeatureAccessDTO();
        featureAccessDTO.setFeatureAccessId(featureAccess.getFeatureId());
        featureAccessDTO.setFeatureName(featureAccess.getFeatureName());
        featureAccessDTO.setFeatureUrl(featureAccess.getFeatureUrl());
        featureAccessDTO.setMethod(featureAccess.getMethod());
        featureAccessDTO.setId(featureAccess.getId());
        return featureAccessDTO;
    }

    public static UserDTO createUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setMailId(user.getEmail());
        userDTO.setEmployeeId(user.getEmployeeId());
        userDTO.setDesignation(user.getDesignation());
        userDTO.setAddresses(user.getAddresses().stream()
                .map(DtoFactory::createAddressDTO).collect(Collectors.toSet()));
        userDTO.setFeatureAccess(user.getFeatureAccesses().stream()
                .map(DtoFactory::createFeatureAccessDTO).collect(Collectors.toSet()));
        return userDTO;
    }

    public static AddressDTO createAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1(address.getAddressLine1());
        addressDTO.setAddressLine2(address.getAddressLine1());
        addressDTO.setCity(address.getCity());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setState(address.getState());
        addressDTO.setCounty(address.getCounty());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setIsoCountry(address.getIsoCountry());
        addressDTO.setPrimary(true);
        return addressDTO;
    }
}
