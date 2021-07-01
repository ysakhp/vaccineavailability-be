package com.vaccine.availability.component.scheduler;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.vaccine.availability.component.worker.RestAPIWorker;
import com.vaccine.availability.component.worker.NotificationWorker;
import com.vaccine.availability.model.Notification;


@Component
public class NotificationScheduler {

	@Autowired
	ThreadPoolTaskExecutor executorService;

	@Autowired
	RestAPIWorker restAPIWorker;

	@Autowired
	NotificationWorker userWorker;

	

	private volatile Vector<Notification> notifications;

	Logger log = LoggerFactory.getLogger(NotificationScheduler.class);

	@Scheduled(initialDelay = 500, fixedDelay = 1000 * 60 * 30)
	public synchronized void scheduleGetUsers() {
		log.info("Notification Details fetch scheduler start");
		Future userFuture = executorService.submit(userWorker);
		try {

			notifications = (Vector<Notification>) userFuture.get();

			log.info("Notification details Fetched completely in future");

			restAPIWorker.setNotifications(notifications);

			log.info("Details Fetch scheduler ends");

		} catch (InterruptedException e) {
			log.info("Interrupted");
			e.printStackTrace();
		} catch (ExecutionException e) {
			log.info("Exception occured");
			e.printStackTrace();
		}
	}

	@Scheduled(initialDelay = 1000 * 15, fixedDelay = 1000 * 60 * 2 )
	public void scheduleResAPICallForUser() {

		System.gc();
		
		log.info("External Cowin Rest API CALL Scheduler starts. ");

		executorService.submit(restAPIWorker);
		
		log.info("External Cowin Rest API CALL Scheduler ends. ");
		
		
		System.gc();

	}

}
