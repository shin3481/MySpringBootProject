package com.rookies4.myspringboot.repository;

import com.rookies4.myspringboot.entity.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
//assertj 라이브러리의 Assertions 클래스
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
//@Transactional
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Rollback(value = false) //롤백 처리하지 마라!
    void testUpdateCustomer(){
        //수정은 찾은다음 저장하는 방법으로 해야함
        Customer customer = customerRepository.findByCustomerId("AC003").orElseThrow(()->new RuntimeException("Customer Not Found"));
        customer.setCustomerName("마이둘리"); //save가 없어도 @Transactional때문에 자동 수정됨.
        customerRepository.save(customer); //@Transactional가 없으면 save가 있어야 저장됨.
    }

    @Test @Disabled
        //Customer 조회 존재하지 않으면 예외발생
    void testNotFoundCustomer(){
        Customer notFoundCustomer = customerRepository.findByCustomerId("AC004").orElseThrow(()->new RuntimeException("Customer Not Found"));

    }

    @Test
    //Customer 조회
    void testFindCustomer(){
        //findBuId()호출
        Optional<Customer> customerById = customerRepository.findById(1L);
        //assertThat(customerById).isNotEmpty();
        if(customerById.isPresent()){
            Customer existCustomer = customerById.get();
            assertThat(existCustomer.getId()).isEqualTo(1L);
        }
        //Optional의 T orElseGet(Supplier) 고객번호(AC001)가 존재하는 경우
        // Supplier추상메서드 T get()
        Optional<Customer> customerByCustomerId = customerRepository.findByCustomerId("AC001");
        Customer ac001Customer = customerByCustomerId.orElseGet(()-> new Customer());
        assertThat(ac001Customer.getCustomerName()).isEqualTo("스프링부트");

        //Optional의 T orElseGet(Supplier) 고객번호(AC004)가 존재하지 않는 경우
        Customer notFoundCustomer = customerRepository.findByCustomerId("AC004").orElseGet(() -> new Customer());
        assertThat(notFoundCustomer.getCustomerName()).isNull();

    }

    @Test
    @Transactional
    @Rollback(value = false) //롤백 처리하지 마라!
    //@Disabled
    void testSaveCustomer(){
        //Given (준비단계)
        Customer customer = new Customer();
        customer.setCustomerId("AC005");
        customer.setCustomerName("스프링FW3");
        //When (실행단계)
        Customer saveCustomer = customerRepository.save(customer);
        //Them (검증단계)
        //assertEquals(expected, actual)
        //등록된 Customer 엔티티객체가 Null이 아닌지를 검증하기
        assertThat(saveCustomer).isNotNull();
        //등록된 Customer Name값이 동일한지 검증하기
        assertThat(saveCustomer.getCustomerName()).isEqualTo("스프링FW3");
    }


}