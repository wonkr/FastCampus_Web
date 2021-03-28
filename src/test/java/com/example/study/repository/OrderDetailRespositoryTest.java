package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailRespositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRespository orderDetailRespository;

    @Test
    @Transactional
    public void create(){
         OrderDetail orderDetail = new OrderDetail();

         //orderDetail.setUserId(7L);
         //orderDetail.setItemId(1L);

         OrderDetail newOrderDetail = orderDetailRespository.save(orderDetail);

        Assertions.assertNotNull(newOrderDetail);
    }

    public void read(){


    }
}