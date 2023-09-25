package com.project.banking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Value {
    private final AtmServiceImpl atmService;

    public Value(AtmServiceImpl atmService) {
        this.atmService = atmService;
    }

    public String read(){
        return atmService.test();
    }
}
