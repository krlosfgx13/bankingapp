package com.project.banking;

import com.project.banking.model.*;
import com.project.banking.repository.UserRepository;
import com.project.banking.repository.UserRoleRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.response.BalanceInquiryResponse;
import com.project.banking.service.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BankingApplicationTests {
    @Autowired
    AtmService atmService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    BankCurrencyService bankCurrencyService;

    @Autowired
    BankService bankService;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    PersonService personService;

    @Autowired
    TransactionCategoryService transactionCategoryService;

    @Autowired
    TransactionHistoryService transactionHistoryService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionTypeService transactionTypeService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    Pbkdf2PasswordEncoder pbkdf2PasswordEncoder;

    private static final int CURRENCY_Q = 1;
    private static final int CURRENCY_$ = 2;
    private static final int CURRENCY_€ = 3;

    /*
     * create currency
     * create bank
     * create bank currency
     * create atm
     * create person
     * create user
     * create bankaccount
     * deposit money to that account
     * check account balance
     * withdraw money from the account
     * deposit money to that account with a paycheck
     * */

    //@Test
    void testConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/bankingapp","root","12345678");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from cajero");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }



    void createBanks() {
        ApiResponse mockResponse = new ApiResponse();
        ApiResponse mockResponseError = new ApiResponse();
        ApiResponse response = new ApiResponse();
        mockResponse.setMessage("Operation performed successfully.");
        mockResponse.setCode(200);
        mockResponse.setStatus("success");
        mockResponseError.setMessage("An error has occurred.");
        mockResponseError.setCode(500);
        mockResponseError.setStatus("error");

        List<Integer> listOfCurrencies1 = new ArrayList<>();
        BankCurrency bankCurrency = new BankCurrency();
        listOfCurrencies1.add(CURRENCY_Q);
        listOfCurrencies1.add(CURRENCY_$);
        listOfCurrencies1.add(CURRENCY_€);

        Bank bank = new Bank();
        bank.setName("Banco de America Central");
        bank.setAddress("Diagonal 6, 01010, Ciudad de Guatemala");
        bank.setCashAvailable(10000000.00);
        response = bankService.createBank(bank, listOfCurrencies1);
        assertEquals(mockResponse.getCode(), response.getCode());

        List<Integer> listOfCurrencies2 = new ArrayList<>();
        BankCurrency bankCurrency2 = new BankCurrency();
        listOfCurrencies2.add(CURRENCY_Q);
        listOfCurrencies2.add(CURRENCY_$);

        Bank bank2 = new Bank();
        bank2.setName("Banco de Guatemala");
        bank2.setAddress("Centro Comercial 1, Ciudad de Guatemala");
        bank2.setCashAvailable(10000000.00);
        response = bankService.createBank(bank2, listOfCurrencies2);
        assertEquals(mockResponse.getCode(), response.getCode());
    }

    //@Test
    public void createAtms() {
        ApiResponse mockResponse = new ApiResponse();
        ApiResponse mockResponseError = new ApiResponse();
        ApiResponse response = new ApiResponse();
        mockResponse.setMessage("Operation performed successfully.");
        mockResponse.setCode(200);
        mockResponse.setStatus("success");
        mockResponseError.setMessage("An error has occurred.");
        mockResponseError.setCode(500);
        mockResponseError.setStatus("error");

        Atm atm = new Atm();
        atm.setBankId(20);
        atm.setAddress("Centro Comercial Oakland Mall");
        atm.setCashAvailable(200000.00f);
        response = atmService.createAtm(atm);
        assertEquals(mockResponse.getCode(), response.getCode());
    }

    //@Test
    public void createBankAccounts() {
        ApiResponse mockResponse = new ApiResponse();
        ApiResponse mockResponseError = new ApiResponse();
        ApiResponse response = new ApiResponse();
        mockResponse.setMessage("Operation performed successfully.");
        mockResponse.setCode(200);
        mockResponse.setStatus("success");
        mockResponseError.setMessage("An error has occurred.");
        mockResponseError.setCode(500);
        mockResponseError.setStatus("error");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setPersonId(1);
        bankAccount.setBankId(20);
        bankAccount.setBalance(1000.00f);
        response = bankAccountService.createBankAccount(bankAccount);
        assertEquals(mockResponse.getCode(), response.getCode());

        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setPersonId(2);
        bankAccount2.setBankId(20);
        bankAccount2.setBalance(150.00f);
        response = bankAccountService.createBankAccount(bankAccount2);
        assertEquals(mockResponseError.getCode(), response.getCode());

        BankAccount bankAccount3 = new BankAccount();
        bankAccount3.setPersonId(3);
        bankAccount3.setBankId(20);
        bankAccount3.setBalance(750.00f);
        response = bankAccountService.createBankAccount(bankAccount3);
        assertEquals(mockResponse.getCode(), response.getCode());

//        BankAccount bankAccount4 = new BankAccount();
//        bankAccount4.setDpi(2145874589654780L);
//        bankAccount4.setBankId(21);
//        bankAccount4.setBalance(500.00f);
//        response = bankAccountService.createBankAccount(bankAccount4);
//        assertEquals(mockResponse.getCode(), response.getCode());
//
//        BankAccount bankAccount5 = new BankAccount();
//        bankAccount5.setDpi(1485789645785410l);
//        bankAccount5.setBankId(21);
//        bankAccount5.setBalance(500.00f);
//        response = bankAccountService.createBankAccount(bankAccount5);
//        assertEquals(mockResponse.getCode(), response.getCode());
    }

    public void validateUserCredentials() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String userName = "ocortez";
        String password = "hola1234";
        assertEquals(true, userService.authenticateUser(userName, password));
        assertEquals(false, userService.authenticateUser(userName, "12345678"));
        assertEquals(true, userService.authenticateUser("jlopez", "12345678"));
        assertEquals(false, userService.authenticateUser("jlopez", "holahola"));
        assertEquals(true, userService.authenticateUser("carlosgc", "holahola"));
        assertEquals(false, userService.authenticateUser("carlosgc", "hola"));
    }

    public void testTransactionsAndCurrencies() throws NoSuchAlgorithmException, InvalidKeySpecException {
        ApiResponse mockResponse = new ApiResponse();
        ApiResponse mockResponseError = new ApiResponse();
        ApiResponse response = new ApiResponse();
        mockResponse.setMessage("Operation performed successfully.");
        mockResponse.setCode(200);
        mockResponse.setStatus("success");
        mockResponseError.setMessage("An error has occurred.");
        mockResponseError.setCode(500);
        mockResponseError.setStatus("error");
        String userName = "carlosgc";
        String password = "holahola";

//        response = transactionService.depositMoney(1128, CURRENCY_€, 250.00F);
//        assertEquals(mockResponse.getCode(), response.getCode());
        BalanceInquiryResponse balance = transactionService.balanceInquiry(6, userName, password);
        assertEquals(3500.00f, balance.getBankAccount().getBalance());

        response = transactionService.withdrawMoney(6, userName, password, 2500.00f);
        assertEquals(mockResponseError.getCode(), response.getCode());
        balance = transactionService.balanceInquiry(6, userName, password);
        assertEquals(3500.00f, balance.getBankAccount().getBalance());
    }


    public void testTransactions() throws NoSuchAlgorithmException, InvalidKeySpecException {
        ApiResponse mockResponse = new ApiResponse();
        ApiResponse mockResponseError = new ApiResponse();
        ApiResponse response = new ApiResponse();
        mockResponse.setMessage("Operation performed successfully.");
        mockResponse.setCode(200);
        mockResponse.setStatus("success");
        mockResponseError.setMessage("An error has occurred.");
        mockResponseError.setCode(500);
        mockResponseError.setStatus("error");
        String userName = "carlosgc";
        String password = "holahola";

        response = transactionService.depositMoney(1128, CURRENCY_Q, 250.00F);
        assertEquals(mockResponse.getCode(), response.getCode());
        BalanceInquiryResponse balance = transactionService.balanceInquiry(6, userName, password);
        assertEquals(1250.00f, balance.getBankAccount().getBalance());

        response = transactionService.withdrawMoney(6, userName, password, 1200.00f);
        balance = transactionService.balanceInquiry(6, userName, password);
        assertEquals(50.00f, balance.getBankAccount().getBalance());
        //BankAccount balance = new BankAccount();

        Atm atm = atmService.getAtmById(6);
        double cashAvailable = atm.getCashAvailable();
        assertEquals(98799.00f, cashAvailable);

        Bank bank = bankService.getBankById(20);
        cashAvailable = bank.getCashAvailable();
        assertEquals(998800.00f, cashAvailable);

        response = transactionService.monetaryInvestment(21, CURRENCY_Q, 100000.00F);
        bank = bankService.getBankById(21);
        cashAvailable = bank.getCashAvailable();
        assertEquals(200000.00, cashAvailable);

        response = transactionService.rechargeAtm(7, CURRENCY_Q, 13000.00F);
        atm = atmService.getAtmById(7);
        cashAvailable = atm.getCashAvailable();
        assertEquals(13000.00f, cashAvailable);

        response = transactionService.depositMoneyWithPayCheck(6, userName, password, "Banco de America Central",
                CURRENCY_Q, 25.00f, 1129);
        balance = transactionService.balanceInquiry(6, "ocortez", "hola1234");
        assertEquals(775.00f, balance.getBankAccount().getBalance());

        bank = bankService.getBankById(20);
        cashAvailable = bank.getCashAvailable();
        assertEquals(998800.00f, cashAvailable);
    }

    //@Test
    void createPersonsAndUsers() throws NoSuchAlgorithmException, InvalidKeySpecException {
        ApiResponse mockResponse = new ApiResponse();
        ApiResponse mockResponseError = new ApiResponse();
        ApiResponse response = new ApiResponse();
        mockResponse.setMessage("Operation performed successfully.");
        mockResponse.setCode(200);
        mockResponse.setStatus("success");
        mockResponseError.setMessage("An error has occurred.");
        mockResponseError.setCode(500);
        mockResponseError.setStatus("error");

//        Currency currency = new Currency();
//        currency.setDescription(CurrencyEnum.EURO_€);
//        response = currencyService.createCurrency(currency);
//        assertEquals(mockResponse.getCode(), response.getCode());


//        TransactionCategory transactionCategory = new TransactionCategory();
//        transactionCategory.setDescription(TransactionCategoryEnum.BASIC_TRANSACTIONS);
//        response = transactionCategoryService.createTransactionCategory(transactionCategory);
//        assertEquals(mockResponse.getCode(), response.getCode());
//
//        TransactionType transactionType = new TransactionType();
//        transactionType.setDescription(TransactionTypeEnum.DEPOSIT_MONEY);
//        transactionType.setTransactionCategoryId(5);
//        response = transactionTypeService.createTransactionType(transactionType);
//        assertEquals(mockResponse.getCode(), response.getCode());

//        Person person = new Person();
//        person.setDpi("3457896540125");
//        person.setFirstName("Carlos");
//        person.setSecondName("Antonio");
//        person.setFirstLastName("Gonzalez");
//        person.setSecondLastName("Caceres");
//        person.setAddress("Ciudad de Guatemala");
//        person.setPhoneNumber("34578965");
//        person.setEmailAddress("carlosgonzalez@host.net");
//        response = personService.createPerson(person);
//        assertEquals(mockResponse.getCode(), response.getCode());
//
//        Person person2 = new Person();
//        person2.setDpi("2145874589654");
//        person2.setFirstName("Juan");
//        person2.setSecondName("Edgar");
//        person2.setThirdName("Manuel");
//        person2.setFirstLastName("Lopez");
//        person2.setAddress("Ciudad de Guatemala");
//        response = personService.createPerson(person2);
//        assertEquals(mockResponse.getCode(), response.getCode());
//
//        Person person3 = new Person();
//        person3.setDpi("3335554787874");
//        person3.setFirstName("Olivia");
//        person3.setFirstLastName("Cortez");
//        person3.setAddress("Ciudad de Guatemala");
//        person3.setPhoneNumber("54192348");
//        person3.setEmailAddress("ocortez@host.net");
//        response = personService.createPerson(person3);
//        assertEquals(mockResponse.getCode(), response.getCode());

        User user = new User();
        user.setPersonId(1);
        user.setUserName("carlosgc");
        user.setPassword("holahola");
        response = userService.createUser(user);
        assertEquals(mockResponse.getCode(), response.getCode());

        UserRole userRole = new UserRole();
        userRole.setUserAccountId(1);
        userRole.setRoleId(1);
        userRoleService.createUserRole(userRole);

        userRole = new UserRole();
        userRole.setUserAccountId(1);
        userRole.setRoleId(2);
        userRoleService.createUserRole(userRole);

        User user2 = new User();
        user2.setPersonId(2);
        user2.setUserName("jlopez");
        user2.setPassword("12345678");
        response = userService.createUser(user2);
        assertEquals(mockResponse.getCode(), response.getCode());

        UserRole userRole2 = new UserRole();
        userRole2.setUserAccountId(2);
        userRole2.setRoleId(1);
        userRoleService.createUserRole(userRole2);

        User user3 = new User();
        user3.setPersonId(3);
        user3.setUserName("ocortez");
        user3.setPassword("hola1234");
        response = userService.createUser(user3);
        assertEquals(mockResponse.getCode(), response.getCode());

        UserRole userRole3 = new UserRole();
        userRole3.setUserAccountId(3);
        userRole3.setRoleId(1);
        userRoleService.createUserRole(userRole3);
    }
}
