package com.info.cybercafe.controller;

import com.info.cybercafe.entity.Product;
import com.info.cybercafe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Map<String, Object> data) {
        try {
            String name = (String) data.get("name");
            double price = Double.parseDouble(data.get("price").toString());
            int quantity = Integer.parseInt(data.get("quantity").toString());
            String category = (String) data.get("category");

            Product product = new Product(name, price, quantity, category);
            Product saved = productService.createProduct(product);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.status(400).body("Invalid product data: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> data) {
        try {
            String name = (String) data.get("name");
            double price = Double.parseDouble(data.get("price").toString());
            int quantity = Integer.parseInt(data.get("quantity").toString());
            String category = (String) data.get("category");

            Product updated = new Product(name, price, quantity, category);
            Product saved = productService.updateProduct(id, updated);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.status(400).body("Invalid update data: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
