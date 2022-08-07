package com.ticketco.ticketconotification.Repository;

import com.ticketco.ticketconotification.Model.Sms;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends MongoRepository<Sms, Long> {
}
