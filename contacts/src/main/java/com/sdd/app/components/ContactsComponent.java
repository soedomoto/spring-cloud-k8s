package com.sdd.app.components;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sdd.app.models.Contact;
import com.sdd.app.repositories.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class ContactsComponent {

  @Autowired
  ContactsRepository repository;

  @HystrixCommand(fallbackMethod = "getContactsFallback")
  public Iterable<Contact> getContacts() throws IOException {
    return repository.findAll();
  }

  public Iterable<Contact> getContactsFallback() {
    return Arrays.asList(new Contact(0L, "Fallback user", "0000"));
  }
}
