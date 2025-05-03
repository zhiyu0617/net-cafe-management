package com.info.cybercafe.service;

import com.info.cybercafe.entity.Product;
import com.info.cybercafe.entity.ProductOrder;
import com.info.cybercafe.entity.User;
import com.info.cybercafe.repository.ProductOrderRepository;
import com.info.cybercafe.repository.ProductRepository;
import com.info.cybercafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductOrder createOrder(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductOrder order = new ProductOrder();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setNotified(false);

        return productOrderRepository.save(order);
    }

    public List<ProductOrder> getAllOrders() {
        return productOrderRepository.findAll();
    }

    public List<ProductOrder> getOrdersByUser(Long userId) {
        return productOrderRepository.findByUserId(userId);
    }

    public List<ProductOrder> getUnnotifiedOrders() {
        return productOrderRepository.findByNotifiedFalse();
    }

    public void markAsNotified(Long orderId) {
        ProductOrder order = productOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setNotified(true);
        productOrderRepository.save(order);
    }

    public void updateOrderStatus(Long orderId, String status) {
        ProductOrder order = productOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if ("COMPLETED".equalsIgnoreCase(status) && !"COMPLETED".equalsIgnoreCase(order.getStatus())) {
            Product product = order.getProduct();
            int currentStock = product.getQuantity();
            int orderQty = order.getQuantity();

            if (currentStock < orderQty) {
                throw new RuntimeException("Not enough stock to complete the order.");
            }

            product.setQuantity(currentStock - orderQty);
            productRepository.save(product);
        }

        order.setStatus(status);
        productOrderRepository.save(order);
    }
}
