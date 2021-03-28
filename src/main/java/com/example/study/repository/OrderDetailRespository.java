package com.example.study.repository;

import com.example.study.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRespository extends JpaRepository<OrderDetail, Long> {
}
