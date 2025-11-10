package com.dev.order_service.repository;

import com.dev.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author DELL
 **/
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
