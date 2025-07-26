package com.om.the_java_om_bank.repository;

import com.om.the_java_om_bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {


}
