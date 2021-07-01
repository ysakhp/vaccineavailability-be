package com.vaccine.availability.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vaccine.availability.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, Integer> {

}
