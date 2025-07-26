package com.om.the_java_om_bank.service.impl;

import com.om.the_java_om_bank.dto.TransactionDto;
import com.om.the_java_om_bank.entity.Transaction;

public interface TransactionService {

    void saveTransaction(TransactionDto transactionDto);

}
