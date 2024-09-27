package com.contact.contact.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sales-service")
public class SalesServiceProperties {
    private Api api;
    private Order order;
    private Payment payment;

    // Getters and Setters todo

    public static class Api {
        private String version;
        // Getters and Setters todo
    }

    public static class Order {
        private boolean emailNotification;
        // Getters and Setters todo
    }

    public static class Payment {
        private String gateway;
        private String currency;
        // Getters and Setters todo
    }
}