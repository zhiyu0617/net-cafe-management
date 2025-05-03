package com.info.cybercafe.controller;

import com.info.cybercafe.entity.ProductOrder;
import com.info.cybercafe.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product-orders")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductOrderController {

    @Autowired
    private ProductOrderService orderService;

    @GetMapping
    public List<ProductOrder> getAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<ProductOrder>> getByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductOrder>> getOrdersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

    @GetMapping("/unnotified")
    public List<ProductOrder> getUnnotified() {
        return orderService.getUnnotifiedOrders();
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ProductOrder> create(@RequestBody Map<String, Object> data) {
        try {
            Long userId = Long.valueOf(data.get("userId").toString());
            Long productId = Long.valueOf(data.get("productId").toString());
            int quantity = Integer.parseInt(data.get("quantity").toString());
            return ResponseEntity.ok(orderService.createOrder(userId, productId, quantity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/notify/{id}")
    public ResponseEntity<String> notify(@PathVariable Long id) {
        orderService.markAsNotified(id);
        return ResponseEntity.ok("Order marked as notified.");
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok("Status updated to " + status);
    }
}
