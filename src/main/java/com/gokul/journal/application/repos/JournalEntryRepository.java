package com.gokul.journal.application.repos;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gokul.journal.application.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId>{

}