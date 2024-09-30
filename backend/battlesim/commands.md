https://chatgpt.com/c/66eadc39-a870-8010-ba9b-fb79a511a1f4

sudo service mysql start


CREATE DATABASE mydb;
CREATE USER 'myuser'@'%' IDENTIFIED BY 'mypassword';
GRANT ALL PRIVILEGES ON mydb.* TO 'myuser'@'%';
FLUSH PRIVILEGES;


mysql -h 3306-<your-codespace-id>.githubpreview.dev -u myuser -p mydb

PORT 3306 MYSQL



