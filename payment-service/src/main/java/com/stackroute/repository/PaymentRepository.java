package com.stackroute.repository;

import com.stackroute.entity.PaymentInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PaymentRepository extends JpaRepository<PaymentInfoModel, Integer> {

}
