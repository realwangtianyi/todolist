use mysql;
create user wangtianyi identified by '123456';
select host, user from user;
update user set host='%' where user='root';
update user set host='%' where user='wangtianyi';
grant all on todo.* to wangtianyi@'%' identified by '123456' with grant option;
grant all privileges on *.* to root@'%' with grant option;
-- 这一条命令一定要有：
flush privileges;
