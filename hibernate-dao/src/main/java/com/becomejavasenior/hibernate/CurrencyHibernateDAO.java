package com.becomejavasenior.hibernate;

import com.becomejavasenior.entity.Currency;

public interface CurrencyHibernateDAO extends GenericHibernateDAO<Currency> {
    Currency getActiveCurrency();
}
