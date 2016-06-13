package com.becomejavasenior.hibernate;

import com.becomejavasenior.entity.Contact;
import com.becomejavasenior.entity.Deal;
import com.becomejavasenior.entity.Stage;

import java.util.List;
import java.util.Map;

public interface DealHibernateDAO extends GenericHibernateDAO<Deal> {
    public List<Deal> getDealsByStage(String stage);
    public List<Contact> getContactsByDealId(int dealId);
    public List<Stage> getAllStage();
    public List<Deal> getDealsForList();
    void addContactToDeal(Deal deal, Contact contact);
    Map<Integer, String> getStageDealsList();
}