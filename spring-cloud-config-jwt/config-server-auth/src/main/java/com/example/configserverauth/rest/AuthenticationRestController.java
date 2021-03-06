package com.example.configserverauth.rest;

import com.example.configserverauth.models.JwtAuthenticationRequest;
import com.example.configserverauth.models.JwtAuthenticationResponse;
import com.example.configserverauth.models.JwtUser;
import com.example.configserverauth.security.JwtTokenUtil;
import com.example.configserverauth.security.MemberServiceImpl;
import com.example.configserverauth.security.WebAuthenticationDetailsSourceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zzf
 * @date: 2018/6/30
 * @time: 22:20
 * @description : 客户端获取token
 */
@RestController
public class AuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MemberServiceImpl userDetailsService;

    @Autowired
    private WebAuthenticationDetailsSourceImpl webAuthenticationDetailsSource;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(HttpServletRequest request){

        JwtAuthenticationRequest jwtAuthenticationRequest = webAuthenticationDetailsSource.buildDetails(request);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(jwtAuthenticationRequest.getUsername(), jwtAuthenticationRequest.getPassword());
        authToken.setDetails(jwtAuthenticationRequest);
        Authentication authenticate = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);


        JwtUser userDetails = userDetailsService.loadUserByUsername(jwtAuthenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
}