package com.ticketco.ticketconotification.Repository;

import com.ticketco.ticketconotification.Model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, Long> {
}
