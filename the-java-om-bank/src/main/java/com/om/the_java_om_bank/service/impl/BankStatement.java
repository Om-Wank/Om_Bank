package com.om.the_java_om_bank.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.om.the_java_om_bank.dto.EmailDetails;
import com.om.the_java_om_bank.entity.Transaction;
import com.om.the_java_om_bank.entity.User;
import com.om.the_java_om_bank.repository.TransactionRepository;
import com.om.the_java_om_bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class BankStatement {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public List<Transaction> generateStatement(String accountNumber, String startDate, String endDate) throws DocumentException, FileNotFoundException {
        //LocalDate start = LocalDate.parse(startDate.trim(), DateTimeFormatter.ISO_DATE);
        //LocalDate end = LocalDate.parse(endDate.trim(), DateTimeFormatter.ISO_DATE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate.trim(), formatter);
        LocalDate end = LocalDate.parse(endDate.trim(), formatter);

        // Filter transactions
        List<Transaction> transactionList = transactionRepository.findAll().stream()
                .filter(transaction -> accountNumber.equals(transaction.getAccountNumber()))
                .filter(transaction -> {
                    LocalDate createdDate = transaction.getCreatedAt();
                    return createdDate != null && (
                            (createdDate.isEqual(start) || createdDate.isAfter(start)) &&
                                    (createdDate.isEqual(end) || createdDate.isBefore(end))
                    );
                })
                .toList();

        User user = userRepository.findByAccountNumber(accountNumber);
        String customerName = String.join(" ",
                user.getFirstName(),
                user.getLastName() != null ? user.getLastName() : "",
                user.getOtherName() != null ? user.getOtherName() : "").trim();

        String filePath = "C:\\Users\\wankh\\Documents\\" + accountNumber + "_Statement_" + LocalDate.now() + ".pdf";

        // Create PDF
        Rectangle statementSize = new Rectangle(PageSize.A4);
        Document document = new Document(statementSize);
        OutputStream outputStream = new FileOutputStream(filePath);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Bank info
        PdfPTable bankInfoTable = new PdfPTable(1);
        PdfPCell bankName = new PdfPCell(new Phrase("Om Bank"));
        bankName.setBorder(0);
        bankName.setBackgroundColor(BaseColor.BLUE);
        bankName.setPadding(20f);
        PdfPCell bankAddress = new PdfPCell(new Phrase("72, Some Address, Pune, Maharashtra, India"));
        bankAddress.setBorder(0);
        bankInfoTable.addCell(bankName);
        bankInfoTable.addCell(bankAddress);

        // Statement info
        PdfPTable statementInfo = new PdfPTable(2);
        statementInfo.setWidthPercentage(100);

        statementInfo.addCell(createBorderlessCell("Start Date: " + startDate));
        statementInfo.addCell(createBorderlessCell("STATEMENT OF ACCOUNT"));
        statementInfo.addCell(createBorderlessCell("End Date: " + endDate));
        statementInfo.addCell(createBorderlessCell("Customer Name: " + customerName));
        statementInfo.addCell(createBorderlessCell(""));
        statementInfo.addCell(createBorderlessCell("Customer Address: " + user.getAddress()));

        // Transactions table
        PdfPTable transactionsTable = new PdfPTable(4);
        transactionsTable.setWidthPercentage(100);

        transactionsTable.addCell(createHeaderCell("DATE"));
        transactionsTable.addCell(createHeaderCell("TRANSACTION TYPE"));
        transactionsTable.addCell(createHeaderCell("TRANSACTION AMOUNT"));
        transactionsTable.addCell(createHeaderCell("STATUS"));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        transactionList.forEach(tx -> {
            transactionsTable.addCell(new Phrase(tx.getCreatedAt() != null ? tx.getCreatedAt().format(dateFormatter) : "N/A"));
            transactionsTable.addCell(new Phrase(tx.getTransactionType() != null ? tx.getTransactionType() : "N/A"));

            String amountText = tx.getAmount() != null ? "₹ " + String.format("%.2f", tx.getAmount()) : "₹ 0.00";
            transactionsTable.addCell(new Phrase(amountText));

            transactionsTable.addCell(new Phrase(tx.getStatus() != null ? tx.getStatus() : "N/A"));
        });


        document.add(bankInfoTable);
        document.add(statementInfo);
        document.add(transactionsTable);
        document.close();

        // Send email
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("STATEMENT OF ACCOUNT")
                .messageBody("Kindly find your requested account statement attached!")
                .attachment(filePath)
                .build();

        emailService.sendEmailWithattachment(emailDetails);

        return transactionList;
    }

    private PdfPCell createBorderlessCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setBorder(0);
        return cell;
    }

    private PdfPCell createHeaderCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setBorder(0);
        return cell;
    }
}
