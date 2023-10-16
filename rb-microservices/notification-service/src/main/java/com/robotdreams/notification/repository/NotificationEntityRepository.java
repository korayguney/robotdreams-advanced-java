package com.robotdreams.notification.repository;

import com.robotdreams.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationEntityRepository extends JpaRepository<Notification, Integer> {
}