package com.vaccine.availability.component.worker;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vaccine.availability.model.Notification;
import com.vaccine.availability.service.NotificationService;

@Component
@Scope("prototype")
public class NotificationWorker implements Callable {

	Logger log = LoggerFactory.getLogger(NotificationWorker.class);

	@Autowired
	NotificationService notificationService;

	@Autowired
	RestAPIWorker restAPIWorker;

	@Override
	public Vector<Notification> call() throws Exception {
		log.info("Notification Data Fetching start from db in NotificationWorker Worker");
		Vector<Notification> notifications = new Vector<Notification>(notificationService.getNotifications());
		return notifications;
	}

	

}
