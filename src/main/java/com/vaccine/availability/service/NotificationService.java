package com.vaccine.availability.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccine.availability.model.Notification;
import com.vaccine.availability.repository.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	public Notification createNotify(Notification notification) {

		return notificationRepository.save(notification);
	}

	public List<Notification> getNotifications() {

		return notificationRepository.findAll();
	}

	public List<Notification> getNotificationsByUserId(String userId) {
		// TODO Auto-generated method stub
		return notificationRepository.findAll().stream()
				.filter(notification -> notification.getUser().getId().equals(userId)).collect(Collectors.toList());
	}

	public void updateNotification(Notification notification) {

		notificationRepository.save(notification);

	}

}
