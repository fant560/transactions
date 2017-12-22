package ru.transaction.example.implementation;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import ru.transaction.example.entity.Contact;
import ru.transaction.example.repository.ContactRepository;
import ru.transaction.example.service.ContactService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("contactService")
@Repository
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Contact> findAll() {
        // CrudRepository не умеет кастовать к нужному типу, что до неприличия странно
         return Lists.newArrayList(contactRepository.findAll());
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    //Не хотим участие в транзакции, просто получаем счетчик и все
    @Override
    public long countAll() {
        return transactionTemplate.execute(status ->
                em.createNamedQuery("Contact.countAll", Long.class)
                        .getSingleResult());
    }
}
