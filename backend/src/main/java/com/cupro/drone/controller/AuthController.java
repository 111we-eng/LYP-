package com.cupro.drone.controller;

import com.cupro.drone.config.JwtUtil;
import com.cupro.drone.dto.LoginRequest;
import com.cupro.drone.dto.LoginResponse;
import com.cupro.drone.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 简单用户校验（admin / admin123）
        if ("admin".equals(loginRequest.getUsername()) && "admin123".equals(loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            return Result.ok("登录成功", new LoginResponse(token));
        }
        return Result.fail(401, "用户名或密码错误");
    }
}
