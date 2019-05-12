USE user_info;
INSERT IGNORE INTO role VALUES (1,'ADMIN');
INSERT IGNORE INTO role VALUES (2,'USER');


INSERT IGNORE INTO `user_info`.`registereduser`
(`active`, `address`, `balance`, `email`, `first_name`, `last_name`, `password`, `personal_number`, `phone`, `rating`, `user_name`)
VALUES
( true, 'Skövde gatan', 100000, 'gm.samer@gmail.com', 'Ghaiath Altrabulsi', 'samer', '$2a$12$mYr/2SsQZhXBXCOXucFGvezxuTpsfw4OyZXoh0JLqQP.O3YK71N.q', 198101015858, 0762857457, 10, 'ghaiath');


INSERT IGNORE INTO `user_info`.`registereduser`
(`active`, `address`, `balance`, `email`, `first_name`, `last_name`, `password`, `personal_number`, `phone`, `rating`, `user_name`)
VALUES
( true, 'Stockholm gatan', 100000, 'max@gmail.com', 'Max', 'Max', '$2a$12$ugjpk0UjTA8FW3gdnAMptuBxAdgAMg8W7Wcp.mofY8cwxG5RQmAlm', 198505050505, 0768585857, 10, 'max');


INSERT IGNORE INTO user_role VALUES (1,1);
INSERT IGNORE INTO user_role VALUES (2,1);