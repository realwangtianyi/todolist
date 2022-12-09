from mysql:5.7

#设置免密登录
env mysql_allow_empty_password yes

#将所需文件放到容器中
copy setup.sh /mysql/setup.sh
copy schema.sql /mysql/schema.sql
copy privileges.sql /mysql/privileges.sql

#设置容器启动时执行的命令
cmd ["sh", "/mysql/setup.sh"]