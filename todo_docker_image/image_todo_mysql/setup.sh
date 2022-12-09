#!/bin/bash
set -e

#查看mysql服务的状态，方便调试，这条语句可以删除
echo `service mysql status`

echo '1.启动mysql....'
#启动mysql
service mysql start
sleep 3
echo `service mysql status`

echo '2.开始导入数据....'
#导入数据
mysql < /mysql/schema.sql
echo '3.导入数据完毕....'

sleep 3
echo `service mysql status`

#设置mysql权限 跨域
echo '4.开始设置mysql跨主机权限'
mysql < /mysql/privileges.sql
echo '5.修改mysql跨主机权限完成'

#sleep 3
echo `service mysql status`
echo `mysql容器启动完毕,且数据导入成功`

tail -f /dev/null
