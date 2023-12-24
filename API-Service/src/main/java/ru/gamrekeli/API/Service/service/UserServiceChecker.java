package ru.gamrekeli.API.Service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserServiceChecker {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user-service.health-endpoint}")
    private String userServiceHealthEndpoint;

    @Autowired
    private DiscoveryClient discoveryClient;

    public boolean isUserServiceAvailable() {
        String userServiceStatus = discoveryClient.getInstances("user-service").toString();
        String statusKey = "status =";
        String status = "";

        int indexStatus = userServiceStatus.indexOf(statusKey);
        if (indexStatus != -1) {
            int commaIndex = userServiceStatus.indexOf(",", indexStatus);
            if (commaIndex != -1) {
                status = userServiceStatus.substring(indexStatus + statusKey.length(), commaIndex).trim();
                // Проверка статуса и обработка ошибок
                if (!status.equalsIgnoreCase("UP")) {
                    log.info("Статус не UP: " + status);
                    return false;
                } else {
                    log.info("Статус UP");
                    return true;
                }
            }
        } else {
            log.info("Статус не найден, возможно сервис не дееспособный!");
        }
        return false;
    }
}
