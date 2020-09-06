package com.calldaraja.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MpesaRepo extends JpaRepository<C2BPaymentDetails,Long> {
}
