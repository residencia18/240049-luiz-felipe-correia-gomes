package br.com.lufecrx.crudexercise.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.lufecrx.crudexercise.audit.model.AuditLog;
import br.com.lufecrx.crudexercise.audit.repository.AuditLogRepository;

@DataJpaTest
public class AuditLogRepositoryTest {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private TestEntityManager entityManager;

    private AuditLog auditLog;

    @BeforeEach
    public void setUp() {
        auditLog = new AuditLog();
        auditLog.setEventName("LogoutAttempt");
        auditLog.setEventDescription("User logout attempt");
        auditLog.setTimestamp(new Date());
        auditLog.setUserId("user2");
        auditLog.setAffectedResource("LogoutService");
        auditLog.setOrigin("127.0.0.1");
        entityManager.persistAndFlush(auditLog);
    }

    @Test
    public void testFindAllAuditLogs() {
        List<AuditLog> logs = auditLogRepository.findAll();
        assertEquals(1, logs.size(), "Expected to find 1 audit log in the database");
    }

    @Test
    public void testFindAuditLogById() {
        Optional<AuditLog> optionalLog = auditLogRepository.findById(auditLog.getId());
        assertTrue(optionalLog.isPresent(), "Expected to find an audit log with the given id");
        assertEquals(auditLog.getEventName(), optionalLog.get().getEventName(), "Event names should match");
    }

    @Test
    public void testSaveAuditLog() {
        AuditLog newLog = new AuditLog();
        newLog.setEventName("LogoutAttempt");
        newLog.setEventDescription("User logout attempt");
        newLog.setTimestamp(new Date());
        newLog.setUserId("user2");
        newLog.setAffectedResource("LogoutService");
        newLog.setOrigin("127.0.0.1");

        AuditLog savedLog = auditLogRepository.save(newLog);

        assertNotNull(savedLog.getId(), "The generated value for id should not be null");
        assertEquals(newLog.getEventName(), savedLog.getEventName(), "Event names should match");
    }

    @Test
    public void testDeleteAuditLog() {
        auditLogRepository.delete(auditLog);
        Optional<AuditLog> optionalLog = auditLogRepository.findById(auditLog.getId());
        assertTrue(optionalLog.isEmpty(), "Expected not to find an audit log with the given id");
    }
}
