package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.ShopHatOrder;

public interface Orders extends JpaRepository<ShopHatOrder, Long> {
         ShopHatOrder findByOrderId(String orderId);
         // ✅ Custom method for Specific users all orders
         List<ShopHatOrder> findByMoNO(Long mobileNo);
}
