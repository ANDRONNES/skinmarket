package com.skinmarket.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skinmarket.project.model.entity.BillingAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {

}
