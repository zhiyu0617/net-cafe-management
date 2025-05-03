package com.info.cybercafe.controller;

import com.info.cybercafe.entity.User;
import com.info.cybercafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String role = request.get("role");
        String adminPassword = request.get("adminPassword"); // 额外的管理员密码

        if ("ADMIN".equalsIgnoreCase(role) && !"123456".equals(adminPassword)) {
            return ResponseEntity.status(403).body("Invalid admin password.");
        }

        User user = new User(username, password, role);
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully with role: " + role);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username,
                                                     @RequestParam String password,
                                                     @RequestParam String role) {
        Optional<User> user = userService.authenticateUser(username, password);
        if (user.isPresent()) {
            if (!user.get().getRole().equalsIgnoreCase(role)) {
                return ResponseEntity.status(403).body(Map.of("message", "Incorrect role for this user."));
            }
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("role", user.get().getRole());
            response.put("userId", user.get().getId().toString());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(value -> ResponseEntity.ok(value.getBalance()))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/recharge")
    public ResponseEntity<String> recharge(@RequestParam Long userId, @RequestParam Double amount) {
        userService.updateBalance(userId, amount);
        return ResponseEntity.ok("Recharge successful");
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> request) {
        Long userId = Long.parseLong(request.get("userId"));
        String newPassword = request.get("newPassword");
        userService.changePassword(userId, newPassword);
        return ResponseEntity.ok("Password updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }
}