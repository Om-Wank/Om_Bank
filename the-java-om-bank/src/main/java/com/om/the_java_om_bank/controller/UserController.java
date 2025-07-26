package com.om.the_java_om_bank.controller;

import com.om.the_java_om_bank.dto.*;
import com.om.the_java_om_bank.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Account Management APIs")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(
            summary = "Create New User Account",
            description = "Creating a new user and assigning an account ID"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"

    )
    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }



    @Operation(
            summary = "Balance Enquiry",
            description = "Given an account number, check how much the user has"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCUSS"

    )
    @GetMapping("balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request){
        return  userService.balanceEnquiry(request);
    }



    @Operation(
            summary = "New Enquiry",
            description = "Given an account number,check the details"
    )
    @ApiResponse(
            responseCode = "202",
            description = "Http status 202 SUCCUSS"

    )
    @GetMapping("nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request){
        return  userService.nameEnquiry(request);
    }




    @Operation(
            summary = "Credit",
            description = "Given an account number and amount,Add the amount to the account"
    )
    @ApiResponse(
            responseCode = "203",
            description = "Http status 203 SUCCUSS"

    )
    @PostMapping("credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest request){
        return userService.creditAccount(request);
    }



    @Operation(
            summary = "Debit",
            description = "Given an account number and amount,subtract the amount from account"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Http status 204 SUCCUSS"

    )
    @PostMapping("debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest request){
        return userService.debitAccount(request);
    }

    @PostMapping("/login")
    public BankResponse login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }


    @Operation(
            summary = "Transfer",
            description = "Given an sourceAccountNumber and destinationAccountNumber transfer the amount from source account to destination account"
    )
    @ApiResponse(
            responseCode = "205",
            description = "Http status 205 SUCCUSS"

    )
    @PostMapping("transfer")
    public BankResponse transfer(@RequestBody TransferRequest request){
        return userService.transfer(request);
    }


}
