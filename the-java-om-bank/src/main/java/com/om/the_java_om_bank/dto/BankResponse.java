package com.om.the_java_om_bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankResponse {

    private  String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;


}
