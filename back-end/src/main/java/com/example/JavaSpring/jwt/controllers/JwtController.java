package com.example.JavaSpring.jwt.controllers;

import com.example.JavaSpring.jwt.models.AuthenticationResponse;
import com.example.JavaSpring.jwt.models.UserModel;
import com.example.JavaSpring.jwt.repository.UserRepository;
import com.example.JavaSpring.jwt.services.UserDetailsImpl;
import com.example.JavaSpring.jwt.services.UserDetailsServiceImpl;
import com.example.JavaSpring.jwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins ="http://localhost:4200")
public class JwtController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailsServiceImpl myUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/public")
    public String publicSite(){
        return "Public site!";
    }
    @GetMapping("/user")
    public String userSite(){
        return "USER!";
    }
    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody UserModel newUser){
        if(userRepository.existsByUsername(newUser.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");

        }
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        String password = new BCryptPasswordEncoder().encode(newUser.getPassword());
        UserModel tmp = new UserModel("21", newUser.getUsername(), password, newUser.getRoles() );
        userRepository.save(newUser);
        return ResponseEntity.ok("ok");
    }
    @GetMapping("/admin")
    public List<UserModel> adminSite(){
        return userRepository.findAll();
    }

    //localhost:8080/auth
    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody UserModel loginRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password !");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

         return ResponseEntity.ok(new AuthenticationResponse(jwt,loginRequest.getUsername(),roles)); // return jwt

    }
}
