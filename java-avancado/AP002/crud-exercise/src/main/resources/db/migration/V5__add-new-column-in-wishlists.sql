-- This script update the wishlists table to add the columns from the user_id record
-- This is needed to link the wishlist to the user

ALTER TABLE wishlists add COLUMN user_id BIGINT NOT NULL;
