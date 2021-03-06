package com.ubt.hospitalmanagement.dtos.response.mappers;

import com.ubt.hospitalmanagement.dtos.DoctorDto;
import com.ubt.hospitalmanagement.dtos.PatientDto;
import com.ubt.hospitalmanagement.dtos.UserDto;
import com.ubt.hospitalmanagement.entities.User;
import com.ubt.hospitalmanagement.enums.Roles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserMapper {

    public static User map(DoctorDto doctor) {
        return User.builder()
                .roles(Roles.DOCTOR.toString())
                .email(doctor.getEmail())
                .password(doctor.getPassword())
                .experiences(doctor.getExperiences())
                .gender(doctor.getGender())
                .build();
    }

    public static User map(PatientDto patientDto) {
        return User.builder()
                .roles(Roles.PATIENT.toString())
                .email(patientDto.getEmail())
                .password(patientDto.getPassword())
                .diseases(patientDto.getDiseases())
                .build();
    }

    public static List<UserDto> map(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : users) {
            userDtos.add(map(user));
        }
        return userDtos;
    }

    public static UserDto map(User user) {
        byte[] profilePicture = user.getProfilePicture();
        if ( profilePicture != null ) {
            profilePicture = Arrays.copyOf( profilePicture, profilePicture.length );
        }
        return UserDto.builder()
                .id(user.getId())
//                .uuid(user.getUuid())
                .email(user.getEmail())
                .diseases(Optional.ofNullable(user.getDiseases()).orElse(null))
                .experiences(Optional.ofNullable(user.getExperiences()).orElse(null))
                .active(user.isActive())
//                .workingDays(Optional.ofNullable(user.getWorkingDays()).map(WorkTimeMapper::map).orElse(null))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobileNumber(user.getMobileNumber())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .profilePicture(profilePicture)
                .build();
    }
}
