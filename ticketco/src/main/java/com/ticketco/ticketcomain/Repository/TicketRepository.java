package com.ticketco.ticketcomain.Repository;

import com.ticketco.ticketcomain.Model.Ticket;
import com.ticketco.ticketcomain.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Set<Ticket> findAllByUser(User user);
}
