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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(path = "/doctors/{doctorUuid}")
    public void updateDoctor(@PathVariable String doctorUuid, @RequestBody DoctorDto doctorDto) {
        userService.updateDoctor(doctorUuid, doctorDto);
    }

    @PostMapping(path = "/patients/")
    public void createPatient(@RequestBody PatientDto patient) {
        userService.addPatient(patient);
    }

    @PutMapping(path = "/patients/")
    public void addDisseas(@RequestParam DisseaseDto dissease) {
        userService.addDissease(dissease);
    }



} 