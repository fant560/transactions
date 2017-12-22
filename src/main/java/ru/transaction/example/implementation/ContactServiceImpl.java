package ru.transaction.example.implementation;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.transaction.example.entity.Contact;
import ru.transaction.example.repository.ContactRepository;
import ru.transaction.example.service.ContactService;

import java.util.List;

@Service("contactService")
@Repository
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

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
        return contactRepository.countAllContacts();
    }
}
