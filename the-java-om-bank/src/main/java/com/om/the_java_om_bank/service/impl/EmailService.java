package com.om.the_java_om_bank.service.impl;

import com.om.the_java_om_bank.dto.EmailDetails;

public interface EmailService {

    void sendEamilAlert(EmailDetails emailDetails);
    void sendEmailWithattachment(EmailDetails emailDetails);
}
