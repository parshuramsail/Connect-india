package com.stackroute.slotservice.repository;

import com.stackroute.slotservice.modal.Slot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends MongoRepository<Slot, Long> {

}
