package com.deloitte.micro.orderservice;

import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Orders,Long>{



}
