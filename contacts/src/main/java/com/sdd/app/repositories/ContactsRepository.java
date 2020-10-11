package com.sdd.app.repositories;

import com.sdd.app.models.Contact;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface ContactsRepository extends CrudRepository<Contact, Long>{

  @Override
  @Cacheable("allContacts")
  Iterable<Contact> findAll();

  @Override
  @CacheEvict(value = "allContacts", allEntries = true)
  <S extends Contact> S save(S s);
}
