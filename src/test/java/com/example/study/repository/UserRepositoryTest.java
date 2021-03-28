package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setAccount("TestUser01");
        user.setPassword("aaaa");
        user.setStatus("REGISTERED");
        user.setEmail("aaaa@gmail.com ");
        user.setPhoneNumber("010-1111-2222");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user);
        System.out.println("newUser"+newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-0001");

        //System.out.println(user);

        user.getOrderGroupList().stream().forEach(orderGroup->{
            System.out.println("------------주문묶음--------------");
            System.out.println("수령인 : "+orderGroup.getRevName());
            System.out.println("수령 : "+orderGroup.getRevAddress());
            System.out.println("총금액 : "+orderGroup.getTotalPrice());
            System.out.println("총수량 : "+orderGroup.getTotalQuantity());
            System.out.println("-----------주문상세--------------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름 : "+orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테코리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품 : "+orderDetail.getItem().getName());
                System.out.println("고객센터 번호: "+orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태:"+orderDetail.getStatus());
                System.out.println("도착예정일자:"+orderDetail.getArrivalDate());

            });


        });




    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("updated method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);
        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(2L);

        Assertions.assertFalse(deleteUser.isPresent());

        if(deleteUser.isPresent()){
            System.out.println("데이터 존재 : "+deleteUser.get());
        }else{
            System.out.println("데이터 삭제 데이터 없음");
        }
    }



}