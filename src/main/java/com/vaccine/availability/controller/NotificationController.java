package com.vaccine.availability.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaccine.availability.model.Notification;
import com.vaccine.availability.model.User;
import com.vaccine.availability.service.NotificationService;
import com.vaccine.availability.service.SequenceService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class NotificationController {

	Logger log = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private NotificationService notificationService;

	@PostMapping(value = { "/createnotify" })
	public Notification createNotification(@RequestBody Notification notification) {

//		notification.setId(sequenceService.getSequenceNumber(Notification.SEQUENCE_NAME));
		log.info("Notification call");
		return notificationService.createNotify(notification);
	}
	
	@GetMapping(value = {"/notify"})
	public List<Notification> getNotificationDetails(){
		
		log.info("Fetching notificatin details");
		return notificationService.getNotifications();
	}
	
	@GetMapping(value = {"/notifyByUserId/{userId}"})
	public List<Notification> getNotificationDetails(@PathVariable(value = "userId") String userId){
		
		log.info("Fetching notificatin details");
		return notificationService.getNotificationsByUserId(userId);
	}

}
