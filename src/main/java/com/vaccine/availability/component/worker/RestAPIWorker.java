package com.vaccine.availability.component.worker;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jdi.connect.Connector.BooleanArgument;
import com.vaccine.availability.builder.CowinRequestBuilder;
import com.vaccine.availability.model.Notification;
import com.vaccine.availability.service.EmailService;
import com.vaccine.availability.service.NotificationService;
import com.vaccine.availability.service.UserService;

@Component
@Scope("prototype")
public class RestAPIWorker implements Runnable {

	private Logger log = LoggerFactory.getLogger(RestAPIWorker.class);
	private volatile Vector<Notification> notifications;

//	@Autowired
//	private RestRequestBuilder restRequestBuilder;

	@Autowired
	private CowinRequestBuilder cowinRequestBuilder;

	@Autowired
	EmailService emailService;

	@Autowired
	NotificationService notificationService;

	private boolean isEmailSentForUser = false;

	private static final ZoneId zoneId = ZoneId.of("Asia/Kolkata");

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

	private Date userDate = null;

	@Override
	public synchronized void run() {

		log.info("Rest API Worker thread start.");
		synchronized (notifications) {
			notifications.stream().forEach(notification -> {
				log.info("Notification :" + notification);
				getCowinDetails(notification);

			});
		}

		log.info("Rest API Worker thread ends.");

	}

	public void setNotifications(Vector<Notification> notifications) {
		log.info("Notification details set to Call External API");
		this.notifications = notifications;

	}

	public void getCowinDetails(Notification notification) {

		synchronized (notification) {

			log.info("Getting cowin details  for " + notification.getEmail() + " ID : " + notification.getId());

			String date = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
					.format(LocalDateTime.now().atZone(zoneId)).toString();
			
			
			notificationService.updateNotification(notification);

			try {

				// To check the conditions
				if (!notification.getEmailSendDate().isEmpty()) {
					userDate = simpleDateFormat.parse(notification.getEmailSendDate());
				} else {
					userDate = simpleDateFormat.parse(DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
							.format(LocalDateTime.now().atZone(zoneId).plusDays(1)).toString());
				}

				Date presentDate = simpleDateFormat.parse(date);

				cowinRequestBuilder.getCowinDetails(notification.getPincode(), date).stream().forEach(center -> {
					log.info("Fetched cowin details going to check vaccine available  " + notification.getPincode()
							+ " center +" + center.getName());
					log.info("User Date " + userDate + " Today date " + presentDate);
					center.getSessions().stream()
							.filter(session -> (session.getAvailable_capacity() > 0
									&& session.getMin_age_limit() == notification.getAgeGroup())
									&& (notification.getEmailSendDate() == null || userDate.equals(presentDate)
											|| userDate.before(presentDate)
											|| notification.getEmailSendDate().isEmpty()))
							.forEach(session -> {

								log.info("Going to send mail :" + notification.getEmail() + " " + center.getName()
										+ " on " + session.getDate());
								emailService.buildContent(notification, center, session).notifyUser();
								log.error("ID " + notification.getId() + " Email Sent " + notification.getEmail()
										+ " Pin : " + notification.getPincode() + "Place : " + center.getName());

								notification.setEmailSent(true);
								isEmailSentForUser = true;

							});

				});

				if (isEmailSentForUser) {

					log.info("Email sent count increase " + notification.getEmailCount());
					notification.setEmailCount(notification.getEmailCount() + 1);

					if (notification.getEmailCount() == 5) {
						String nextDate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
								.format(LocalDateTime.now().atZone(zoneId).plusDays(1)).toString();
						/*
						 * if (!user.getEmail().equals("ysakhpr@gmail.com")) { log.info("Omit ysakh");
						 * user.setEmailSendDate(nextDate); }
						 */
						notification.setEmailSendDate(nextDate);
						notification.setEmailCount(0);
						notification.setEmailSent(false);

						log.info("User email sending details setting default");
						log.info("Removed User " + notification.getEmail());
//						users.remove(user);

					}
					log.info("Updating user after sending mail");
					notificationService.updateNotification(notification);
					isEmailSentForUser = false;
				}

			} catch (IOException | InterruptedException | ParseException e) {
				log.info("Exception occured " + e.getMessage());
				e.printStackTrace();
			}
		}

	}

}
