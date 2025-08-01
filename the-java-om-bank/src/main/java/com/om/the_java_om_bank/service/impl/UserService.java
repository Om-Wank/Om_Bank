package com.om.the_java_om_bank.service.impl;

import com.om.the_java_om_bank.dto.*;

public interface UserService {

   BankResponse createAccount(UserRequest userRequest);

   BankResponse balanceEnquiry(EnquiryRequest request);

   String nameEnquiry(EnquiryRequest request);

   BankResponse creditAccount(CreditDebitRequest request);

   BankResponse debitAccount(CreditDebitRequest request);

   BankResponse transfer(TransferRequest request);

   BankResponse login(LoginDto loginDto);
}

