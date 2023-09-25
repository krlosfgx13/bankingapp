package com.project.banking.service.impl;

public class Example {

    public String test() {
        try {
            Value value = new Value(new AtmServiceImpl(null, null));
            return value.read();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

}
