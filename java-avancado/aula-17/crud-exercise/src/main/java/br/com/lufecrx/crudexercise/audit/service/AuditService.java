package br.com.lufecrx.crudexercise.audit.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.audit.model.AuditLog;
import br.com.lufecrx.crudexercise.audit.repository.AuditLogRepository;

@Service
public class AuditService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public void logEvent(String eventName, String description, String userId, String resource, String origin) {
        AuditLog log = new AuditLog();
        log.setEventName(eventName);
        log.setEventDescription(description);
        log.setTimestamp(new Date()); // Register the current date and time
        log.setUserId(userId); // // User ID that triggered the event
        log.setAffectedResource(resource); // Resource affected by the event
        log.setOrigin(origin); // Origin of the event
        auditLogRepository.save(log);
    }
}