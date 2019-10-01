USE UserBankList;
SET sql_notes = 0;
CREATE TABLE IF NOT EXISTS user (userId INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), sureName VARCHAR(45));
CREATE TABLE IF NOT EXISTS account (accountId INT NOT NULL, account INT, userId INT, CONSTRAINT FK_account_user FOREIGN KEY (userId) REFERENCES user (userId) ON DELETE CASCADE );
INSERT INTO user (name,sureName) VALUES ("Andrew1","Orlov1"),("Andrew2","Orlov2"),("Andrew3","Orlov3"),("Andrew4","Orlov4"),("Andrew5","Orlov5"),("Andrew6","Orlov6"),("Andrew7","Orlov7"),("Andrew8","Orlov8"),("Andrew9","Orlov9"),("Andrew10","Orlov10");
INSERT INTO account(accountId, account, userId) VALUES (1111,1000,1),(2222,2000,2),(3333,3000,3),(4444,4000,4),(5555,5000,5),(6666,6000,6),(7777,7000,7),(8888,8000,8),(9999,9000,9),(10000,10000,10);
SET sql_notes = 1;
-- SELECT u.*, a.accountId, a.account FROM user u JOIN account a ON u.userId = a.userId;
