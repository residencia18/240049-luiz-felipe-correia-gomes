package br.com.lufecrx.crudexercise.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lufecrx.crudexercise.audit.model.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    
}
