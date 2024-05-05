-- This script updates the users table to add the columns from the OneTimePassword record and the is_enabled column
-- The columns are otp and otp_generation_time

ALTER TABLE users add COLUMN is_enabled BOOLEAN DEFAULT false;
ALTER TABLE users ADD COLUMN otp TEXT;
ALTER TABLE users ADD COLUMN otp_generation_time TIMESTAMP;
