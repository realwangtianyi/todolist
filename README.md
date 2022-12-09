## 1.简介

todolist应用是笔者开发的一款简易的待办事件记录应用。

GitHub仓库为：

DockerHub仓库为：

本文介绍了通过docker容器部署todolist应用的步骤。

## 2. 环境准备：安装docker、docker-compose、git和npm

备注：docker和docker-compose必须安装，git和npm可以不安装，通过文件传输等其他方式获取源码和前端dist文件。

### 2.1 安装docker

执行下列命令：

yum install -y docker

执行结束后，运行docker --version，如果能显示docker版本信息，说明安装成功。

### 2.2 安装docker-compose

依次执行下列命令：

curl -L "https://github.com/docker/compose/releases/download/1.29.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

chmod +x /usr/local/bin/docker-compose

执行结束后，运行docker-compose --version，如果能显示dockers版本信息，说明安装成功。

若不成功，可参考：

通过GitHub安装docker-compose: https://blog.csdn.net/m0_67391907/article/details/126327295

通过pip3安装docker-compose: https://blog.csdn.net/csdn_life18/article/details/126635511

### 2.3 安装git

执行下列命令：

yum install -y git

执行结束后，运行git --version，如果能显示git版本信息，说明安装成功。

### 2.4 安装npm

执行下列命令：

yum install -y npm

执行结束后，运行npm --version，如果能显示npm版本信息，说明安装成功。

## 3. 拉取todo项目的docker镜像

拉取todo_app(Spring Boot程序)镜像：docker pull taitaicomedy/todo_app

拉取todo_mysql(数据库及表)镜像：docker pull taitaicomedy/todo_mysql

拉取nginx镜像：docker pull nginx

## 4. 准备nginx配置文件和前端静态文件

### 4.1 准备nginx配置文件

将

### 4.2 生成前端静态文件

从GitHub上拉取todo项目源码。并在todo_frontend/src/main.js中的“axios.defaults.baseURL = 'http://服务器IP地址:端口号'”处填写你要将后端部署的服务器的IP地址和端口号。端口号需要跟下文的docker-compose.yml一致。

修改后，在todo_frontend目录下执行npm run build，即可生成dist目录。

将生成的dist目录放置到4.1中的/data/nginx/html中去，执行后的文件目录结构形如/data/nginx/html/dist/......

的