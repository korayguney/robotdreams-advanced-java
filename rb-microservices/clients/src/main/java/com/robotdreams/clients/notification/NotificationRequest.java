package com.robotdreams.clients.notification;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {
    private Long toCustomerId;
    private String toCustomerPhone;
    private String message;
}
