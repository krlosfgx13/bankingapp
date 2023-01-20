package com.project.banking.service.impl;

import com.project.banking.model.Bank;
import com.project.banking.model.BankCurrency;
import com.project.banking.repository.BankRepository;
import com.project.banking.response.ApiResponse;
import com.project.banking.response.BankCurrencyData;
import com.project.banking.response.BankCurrencyResponse;
import com.project.banking.service.BankCurrencyService;
import com.project.banking.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository repository;

    @Autowired
    private BankCurrencyService bankCurrencyService;

    ApiResponse response = new ApiResponse();

    private static final int CURRENCY_Q = 1;
    private static final int CURRENCY_$ = 2;
    private static final int CURRENCY_€ = 3;

    @Override
    public ApiResponse createBank(Bank bank, List<Integer> bankCurrencies) {
        try {
            if(bank.getAddress() == "" || bank.getName() == ""){
                response.setMessage("Fill out mandatory fields.");
                response.setCode(500);
                return response;
            }
            List<Bank> listOfBanks = getAllBanks();
            if (!bankCurrencies.isEmpty()) {
                if (listOfBanks.size() < 10) {
                    repository.save(bank);
                    BankCurrency bankCurrency = null;
                    for (Integer currencyId : bankCurrencies) {
                        bankCurrency = new BankCurrency();
                        bankCurrency.setBankId(bank.getBankId());
                        bankCurrency.setCurrencyId(currencyId);
                        bankCurrencyService.createBankCurrency(bankCurrency);
                    }
                    response.setMessage("Operation performed successfully.");
                    response.setCode(200);
                } else {
                    response.setMessage("There can't be more than 10 banks..");
                    response.setCode(500);
                }
            } else {
                response.setMessage("Select at least one currency.");
                response.setCode(500);
            }
            response.setStatus("success");
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public ApiResponse updateBank(Integer id, double amount) {
        try {
            Bank bankObj = repository.findByBankId(id);
            if (bankObj != null) {
                bankObj.setCashAvailable(amount);
                repository.save(bankObj);
            } else {
                return new ApiResponse(500, "Something went wrong.", "error");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
    }

    @Override
    public ApiResponse deleteBank(Integer id) {
        try {
            Bank bank = repository.findByBankId(id);
            if (bank != null) {
                repository.delete(bank);
            } else {
                return new ApiResponse(500, "Something went wrong.", "error");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new ApiResponse(200, "Operation performed successfully.", "success");
    }

    @Override
    public Bank getBankById(Integer id) {
        try {
            return repository.findByBankId(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Bank getBankByName(String name) {
        try {
            return repository.findByBankName(name);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<Bank> getAllBanks() {
        List<Bank> list = new ArrayList<>();
        try {
            list = repository.findAll();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }

    public BankCurrencyResponse getAllBankCurrencies() throws SQLException, ClassNotFoundException {
        List<BankCurrencyData> bankCurrencyDataList = new ArrayList<>();
        BankCurrencyResponse response = new BankCurrencyResponse();
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bankingapp", "root", "12345678");
            String selectStatement = "select b.idbanco, b.nombre, b.direccion, group_concat(m.descripcion separator ', ') as myList \n" +
                    "from banco b \n" +
                    "join monedabanco mb on b.idbanco = mb.idbanco\n" +
                    "join moneda m on m.idmoneda = mb.idmoneda \n" +
                    "group by b.idbanco, b.nombre, b.direccion\n" +
                    "order by b.nombre;";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(selectStatement);
            while (rs.next()) {
                BankCurrencyData bankCurrencyData = new BankCurrencyData();
                bankCurrencyData.setBankId(rs.getInt(1));
                bankCurrencyData.setBankName(rs.getString(2));
                bankCurrencyData.setBankAddress(rs.getString(3));
                bankCurrencyData.setCurrencyDescription(rs.getString(4));
                bankCurrencyDataList.add(bankCurrencyData);
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
            throw ex;
        } finally {
            conn.close();
        }
        response.setCode(200);
        response.setMessage("Operation performed successfully.");
        response.setStatus("success");
        response.setBankCurrencyDataList(bankCurrencyDataList);
        return response;
    }
}
