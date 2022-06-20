package com.ubt.hospitalmanagement.controllers;

import com.ubt.hospitalmanagement.dtos.DisseaseDto;
import com.ubt.hospitalmanagement.dtos.DoctorDto;
import com.ubt.hospitalmanagement.dtos.PatientDto;
import com.ubt.hospitalmanagement.dtos.requests.AuthenticationRequest;
import com.ubt.hospitalmanagement.dtos.requests.SignUpRequest;
import com.ubt.hospitalmanagement.dtos.response.AuthenticationResponse;
import com.ubt.hospitalmanagement.services.JwtUtil;
import com.ubt.hospitalmanagement.services.MyUserDetailsService;
import com.ubt.hospitalmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtTokenUtil;

    private final MyUserDetailsService userDetailsService;

    private final UserService userService;

    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(authenticationRequest.getEmail());

        List<String> roles = userDetails.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
        if(!roles.contains("ROLE_" + authenticationRequest.getRole().toUpperCase())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not a " + authenticationRequest.getRole());
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping(path = "/signup")
    public void signUp(@RequestBody SignUpRequest request) {
        userService.createUser(request);
    }

    @PostMapping(path = "/doctors")
    public void addDoctor(@RequestBody DoctorDto doctor) {
        userService.addDoctor(doctor);
    }

    @PutMapping(path = "/doctors/{doctorId}")
    public void updateDoctor(@PathVariable Integer doctorId, @RequestBody DoctorDto doctorDto) {
        userService.updateDoctor(doctorId, doctorDto);
    }

    @PostMapping(path = "/patients")
    @RolesAllowed({"ROLE_DOCTOR"})
    public void createPatient(@RequestBody PatientDto patient) {
        userService.addPatient(patient);
    }

    @PutMapping(path = "/patients/")
    public void addDisseas(@RequestParam DisseaseDto dissease) {
        userService.addDissease(dissease);
    }



} 