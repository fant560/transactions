package ru.transaction.example.implementation;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.transaction.example.entity.Contact;
import ru.transaction.example.repository.ContactRepository;
import ru.transaction.example.service.ContactService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
    @PersistenceContext(unitName = "emfA")
    private EntityManager emA;
    @PersistenceContext(unitName = "emfB")
    private EntityManager emB;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
         return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        return null;
    }

    @Override
    public Contact save(Contact contact) {
        Contact contactB = new Contact();
        contactB.setFirstName(contact.getFirstName());
        contactB.setLastName(contact.getLastName());
        if (contact.getId() == null){
            emA.persist(contact);
            emB.persist(contactB);
        } else {
            emA.merge(contact);
            emB.merge(contactB);
        }

        return contact;

    }

    @Override
    public long countAll() {
        return 0;
    }
}
