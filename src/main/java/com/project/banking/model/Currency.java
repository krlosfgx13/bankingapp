package com.project.banking.model;

import javax.persistence.*;

@Entity
@Table(name = "currency", schema = "bankingapp")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "currency_id")
    private Long currencyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "description")
    private CurrencyEnum description;

    public Long getCurrencyId() {
        return currencyId;
    }

    public CurrencyEnum getDescription() {
        return description;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public void setDescription(CurrencyEnum description) {
        this.description = description;
    }
}
