package com.controller;


import com.entity.ShopHatOrder;
import com.repo.Orders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    private final Orders orderRepo;

    public BookingController(Orders orderRepo) {
        this.orderRepo = orderRepo;
    }

    // ✅ Get all orders for a particular user by mobileNo
    @GetMapping("/user/{mobileNo}")
    public ResponseEntity<List<?>> getUserOrders(@PathVariable Long mobileNo) {
        List<ShopHatOrder> orders = orderRepo.findByMoNO(mobileNo);

        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<?> response = orders.stream().map(order -> new Object() {
            public final Long roomNo = order.getRoomNo();
            public final String roomType = order.getRoomType();
            public final String name = order.getUserNm();
            public final Long mobileNo = order.getMoNO();
            public final int noOfPerson = order.getNoOfPerson();
            public final String checkIn = order.getCheckIn();
            public final String checkOut = order.getCheckout();
            public final String paymentStatus = order.getStatus();
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}

