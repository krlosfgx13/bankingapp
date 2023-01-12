package com.project.banking.service.impl;

import com.project.banking.model.*;
import com.project.banking.repository.TransactionRepository;
import com.project.banking.response.*;
import com.project.banking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankService bankService;

    @Autowired
    private AtmService atmService;

    @Autowired
    private UserService userService;

    @Autowired
    private BankCurrencyService bankCurrencyService;

    ApiResponse response = new ApiResponse();

    private static final int DEPOSITO_MONETARIO_BANCARIO = 5;
    private static final int CONSULTA_SALDO = 6;
    private static final int RETIRO_MONETARIO = 7;
    private static final int DEPOSITO_DOCUMENTO = 8;
    private static final int INVERSION_MONETARIA = 9;
    private static final int RECARCAR_CAJERO = 10;
    private static final int CURRENCY_Q = 1;
    private static final int CURRENCY_$ = 2;
    private static final int CURRENCY_€ = 3;

    @Override
    public ApiResponse depositMoney(Integer bankAccountId, Integer currencyId, float amount) {
        try {
            BankAccount bankAccount = bankAccountService.getBankAccountById(bankAccountId);
            if (currencyId > 3) {
                currencyId = 1;
            }
            if (bankAccount != null) {
                BankCurrency bankCurrency = bankCurrencyService.getByBankAndCurrency(bankAccount.getBankId(), currencyId);
                if (bankCurrency != null) {
                    float balance = bankAccount.getBalance();
                    switch (currencyId) {
                        case CURRENCY_Q:
                            amount = amount;
                            break;

                        case CURRENCY_$:
                            amount *= 8;
                            break;

                        case CURRENCY_€:
                            amount *= 10;
                            break;
                    }
                    bankAccountService.updateBankAccount(bankAccountId, balance + amount);
                    Transaction transaction = new Transaction();
                    transaction.setTransactionTypeId(DEPOSITO_MONETARIO_BANCARIO);
                    transaction.setBankId(bankAccount.getBankId());
                    transaction.setBankAccountId(bankAccountId);
                    transaction.setAmount(amount);
                    transaction.setCurrencyId(currencyId);
                    repository.save(transaction);

                    response.setMessage("Operation performed successfully.");
                    response.setCode(200);
                    response.setStatus("success");
                } else {
                    return new ApiResponse(500, "Currency not valid for this bank.", "error");
                }
            } else {
                response.setMessage("Account number is not valid.");
                response.setCode(500);
                response.setStatus("error");
            }
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public BalanceInquiryResponse balanceInquiry(Integer atmId, String userName, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            Atm atm = atmService.getAtmById(atmId);
            if (atm == null) {
                return new BalanceInquiryResponse(500, "Atm Id is not valid.", "error");
            }
            User user = userService.getUserByUserName(userName);
            BalanceInquiryResponse response = new BalanceInquiryResponse();
            boolean isUserValid = userService.authenticateUser(userName, password);
            if (isUserValid) {
                BankAccount bankAccount = bankAccountService.getBankAccountByDpi(user.getUserAccountId());
                if (bankAccount != null) {
                    if (atm.getBankId() == bankAccount.getBankId()) {
                        Transaction transaction = new Transaction();
                        transaction.setTransactionTypeId(CONSULTA_SALDO);
                        transaction.setBankAccountId(bankAccount.getBankAccountId());
                        transaction.setCurrencyId(CURRENCY_Q);
                        transaction.setAtmId(atmId);
                        repository.save(transaction);
                        response.setBankAccount(bankAccount);
                        response.setCode(200);
                        response.setMessage("Operation performed successfully.");
                        response.setStatus("success");
                    } else {
                        return new BalanceInquiryResponse(500, "Atm and Account Number do not belong to the same bank.", "error");
                    }
                    return response;
                } else {
                    return new BalanceInquiryResponse(500, "Account number is not valid.", "error");
                }
            } else {
                return new BalanceInquiryResponse(500, "Invalid username or password.", "error");
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public ApiResponse withdrawMoney(Integer atmId, String userName, String password, float amount) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            Atm atm = atmService.getAtmById(atmId);
            if (atm == null) {
                return new ApiResponse(500, "Atm Id is not valid.", "error");
            }
            User user = userService.getUserByUserName(userName);
            if (user != null) {
                boolean isUserValid = userService.authenticateUser(userName, password);
                if (isUserValid) {
                    BankAccount bankAccount = bankAccountService.getBankAccountByDpi(user.getUserAccountId());
                    if (bankAccount != null) {
                        if (bankAccount.getBalance() >= amount) {
                            float balance = bankAccount.getBalance();
                            if (bankAccount.getBankId() == atm.getBankId()) {
                                float atmCashAvailable = atm.getCashAvailable();
                                if (amount <= 2000.0f) {
                                    Transaction transaction = new Transaction();
                                    transaction.setTransactionTypeId(RETIRO_MONETARIO);
                                    transaction.setAtmId(atmId);
                                    transaction.setBankAccountId(bankAccount.getBankAccountId());
                                    transaction.setAmount(amount);
                                    transaction.setCurrencyId(CURRENCY_Q);
                                    repository.save(transaction);
                                    bankAccountService.updateBankAccount(bankAccount.getBankAccountId(), balance - amount);
                                    atmService.updateAtm(atmId, atmCashAvailable - amount);
                                    response.setMessage("Operation performed successfully.");
                                    response.setCode(200);
                                    response.setStatus("success");
                                } else {
                                    response.setMessage("Error, is greater than limit.");
                                    response.setCode(500);
                                    response.setStatus("error");
                                }
                            } else {
                                return new ApiResponse(500, "Atm and Account Number do not belong to the same bank.", "error");
                            }
                        } else {
                            return new ApiResponse(500, "Insufficient funds.", "error");
                        }
                    } else {
                        response.setMessage("Account number is not valid.");
                        response.setCode(500);
                        response.setStatus("error");
                    }
                } else {
                    response.setMessage("Invalid credentials.");
                    response.setCode(500);
                    response.setStatus("error");
                }
            } else {
                response.setMessage("Invalid user.");
                response.setCode(500);
                response.setStatus("error");
            }
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public ApiResponse depositMoneyWithPayCheck(Integer atmId, String userName, String password, String issuingBankName,
                                                Integer currencyId, float amount, Integer bankAccountId) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            User user = userService.getUserByUserName(userName);
            boolean isUserValid = userService.authenticateUser(userName, password);
            if (user != null && isUserValid) {
                Bank issuingBank = bankService.getBankByName(issuingBankName);
                if (issuingBank != null) {
                    switch (currencyId) {
                        case CURRENCY_Q:
                            amount = amount;
                            break;

                        case CURRENCY_$:
                            amount *= 8;
                            break;

                        case CURRENCY_€:
                            amount *= 10;
                            break;
                    }
                    float bankCashAvailable = issuingBank.getCashAvailable();
                    BankAccount bankAccount = bankAccountService.getBankAccountById(bankAccountId);
                    float balance = bankAccount.getBalance();
                    Bank receivingBank = bankService.getBankById(bankAccount.getBankId());
                    BankCurrency bankCurrency = bankCurrencyService.getByBankAndCurrency(receivingBank.getBankId(), currencyId);
                    if (bankCurrency != null) {
                        float cashAvailableReceivingBank = receivingBank.getCashAvailable();
                        bankService.updateBank(issuingBank.getBankId(), bankCashAvailable - amount);
                        bankAccountService.updateBankAccount(bankAccountId, balance + amount);
                        bankService.updateBank(bankAccount.getBankId(), cashAvailableReceivingBank + amount);
                        Transaction transaction = new Transaction();
                        transaction.setTransactionTypeId(DEPOSITO_DOCUMENTO);
                        transaction.setAtmId(atmId);
                        transaction.setBankAccountId(bankAccountId);
                        transaction.setBankId(receivingBank.getBankId());
                        transaction.setAmount(amount);
                        transaction.setCurrencyId(currencyId);
                        repository.save(transaction);
                    } else {
                        return new ApiResponse(500, "Currency not valid for this bank.", "error");
                    }
                } else {
                    return new ApiResponse(500, "Bank is not valid.", "error");
                }
            } else {
                return new ApiResponse(500, "Invalid credentials.", "error");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
    }

    @Override
    public ApiResponse monetaryInvestment(Integer bankId, Integer currencyId, float amount) {
        try {
            Bank bank = bankService.getBankById(bankId);
            if (bank != null) {
                BankCurrency bankCurrency = bankCurrencyService.getByBankAndCurrency(bank.getBankId(), currencyId);
                if (bankCurrency != null) {
                    float bankCashAvailable = bank.getCashAvailable();
                    switch (currencyId) {
                        case CURRENCY_Q:
                            amount = amount;
                            break;

                        case CURRENCY_$:
                            amount *= 8;
                            break;

                        case CURRENCY_€:
                            amount *= 10;
                            break;
                    }

                    bankService.updateBank(bankId, bankCashAvailable + amount);
                    Transaction transaction = new Transaction();
                    transaction.setTransactionTypeId(INVERSION_MONETARIA);
                    transaction.setBankId(bankId);
                    transaction.setAmount(amount);
                    transaction.setCurrencyId(currencyId);
                    repository.save(transaction);
                } else {
                    return new ApiResponse(500, "Currency not valid for this bank..", "error");
                }
            } else {
                return new ApiResponse(500, "Something went wrong.", "error");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
    }

    @Override
    public ApiResponse rechargeAtm(Integer atmId, Integer currencyId, float amount) {
        try {
            Atm atm = atmService.getAtmById(atmId);
            if (atm != null) {
                Bank bank = bankService.getBankById(atm.getBankId());
                BankCurrency bankCurrency = bankCurrencyService.getByBankAndCurrency(bank.getBankId(), currencyId);
                if (bankCurrency != null) {
                    float atmCashAvailable = atm.getCashAvailable();
                    float bankCashAvailable = bank.getCashAvailable();
                    switch (currencyId) {
                        case CURRENCY_Q:
                            amount = amount;
                            break;

                        case CURRENCY_$:
                            amount *= 8;
                            break;

                        case CURRENCY_€:
                            amount *= 10;
                            break;
                    }
                    if(bankCashAvailable > amount){
                        bankService.updateBank(atm.getBankId(), bankCashAvailable - amount);
                        atmService.updateAtm(atmId, atmCashAvailable + amount);
                        Transaction transaction = new Transaction();
                        transaction.setTransactionTypeId(RECARCAR_CAJERO);
                        transaction.setBankId(bank.getBankId());
                        transaction.setAtmId(atmId);
                        transaction.setAmount(amount);
                        transaction.setCurrencyId(currencyId);
                        repository.save(transaction);
                    }else{
                        return new ApiResponse(500, "Bank does not have sufficient funds for this transaction.", "error");
                    }
                } else {
                    return new ApiResponse(500, "Invalid currency for this bank.", "error");
                }

            } else {
                return new ApiResponse(500, "Atm Id is not valid.", "error");
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong.");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
            response.setMessage("Something went wrong.");
            response.setCode(500);
            response.setStatus("error");
            return response;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
    }

    @Override
    public TransactionLogResponse getTransactionLog() throws SQLException, ClassNotFoundException {
        List<TransactionLog> transactionLogList = new ArrayList<>();
        TransactionLogResponse response = new TransactionLogResponse();
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bankingapp", "root", "12345678");
            String selectStatement = "select t.idtransaccion, tt.descripcion, t.monto, t.idcajero, t.idbanco, t.idcuentabancaria,\n" +
                    "u.nombreusuario, m.descripcion, t.fechatransaccion, ct.descripcion \n" +
                    "from transaccion t\n" +
                    "join tipotransaccion tt on t.idtipotransaccion = tt.idtipotransaccion\n" +
                    "join categoriatransaccion ct on tt.idcategoriatransaccion = ct.idcategoriatransaccion\n" +
                    "join moneda m on t.idmoneda = m.idmoneda\n" +
                    "left join cuentabancaria cb on t.idcuentabancaria = cb.idcuentabancaria\n" +
                    "left join usuario u on cb.dpi = u.dpi\n" +
                    "order by t.fechatransaccion desc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectStatement);
            while (rs.next()) {
                TransactionLog log = new TransactionLog();
                log.setTransactionId(rs.getInt(1));
                log.setTransactionDescription(rs.getString(2));
                log.setAmount(rs.getFloat(3));
                log.setAtmId(rs.getInt(4));
                log.setBankId(rs.getInt(5));
                log.setBankAccountId(rs.getInt(6));
                log.setUserName(rs.getString(7));
                log.setCurrencyDescription(rs.getString(8));
                log.setTransactionDate(rs.getTimestamp(9));
                log.setTransactionCategory(rs.getString(10));
                transactionLogList.add(log);
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong." + ex.getMessage());
            throw ex;
        } finally {
            conn.close();
        }
        response.setCode(200);
        response.setMessage("Operation performed successfully.");
        response.setStatus("success");
        response.setTransactionLogList(transactionLogList);
        return response;
    }

    @Override
    public Page<Transaction> getTransactionPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
