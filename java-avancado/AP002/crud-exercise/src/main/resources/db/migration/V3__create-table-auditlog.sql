-- This script creates the table audit_log in the database
-- The table is used to store audit logs for all the events that occur in the application

CREATE TABLE audit_log (
    id SERIAL PRIMARY KEY,
    event_name VARCHAR(50) NOT NULL,
    event_description TEXT,
    timestamp TIMESTAMP,
    user_id VARCHAR(50),
    affected_resource VARCHAR(50),
    origin VARCHAR(50)
);