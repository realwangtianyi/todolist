## 1.简介

todolist应用是笔者开发的一款简易的待办事件记录应用。

项目在线预览体验地址（2023年3月1日前有效）：

http://152.136.189.18:8111/

GitHub仓库为：

https://github.com/realwangtianyi/todolist.git

DockerHub仓库为：

https://hub.docker.com/repository/docker/taitaicomedy/todo_mysql

https://hub.docker.com/repository/docker/taitaicomedy/todo_app

本文以Linux系统I(尤其是CentOS 8系统)为例，介绍了通过docker容器部署todolist应用的步骤。

## 2. 环境准备：安装docker、docker-compose、git和npm14

备注：docker和docker-compose必须安装，git和npm可以不安装，通过文件传输等其他方式获取源码和前端dist文件。

### 2.1 安装docker

执行下列命令：

```shell
yum install -y docker
docker --version # 若显示docker版本信息，说明安装成功。
systemctl start docker # 启动docker，否则后续执行docker-compose up会报错
systemctl enable docker # 启动docker，否则后续执行docker-compose up会报错
```

### 2.2 安装docker-compose

docker-compose是一种容器编排工具，可以通过执行compose脚本，让前端、后端、数据库的三个docker容器建立某种依赖关系，一键启动。本文采用这种简单便捷的工具部署todolist应用。这里推荐两种安装docker-compose工具的方式。

方式1：

```shell
yum -y install python3-pip
pip3 install --upgrade pip
pip3 install docker-compose
docker-compose --version  # 若显示docker-compose版本信息，说明安装成功。
```

方式2：

```shell
curl -L "https://github.com/docker/compose/releases/download/1.29.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose # 从GitHub下载docker-compose，如果不方便访问GitHub，也可以通过pip3安装compose
chmod +x /usr/local/bin/docker-compose # 分配执行权限，否则会报错
docker-compose --version # 若显示docker-compose版本信息，说明安装成功。
```

如果安装不成功，请参考：

通过GitHub安装docker-compose: https://blog.csdn.net/m0_67391907/article/details/126327295

通过pip3安装docker-compose: https://blog.csdn.net/csdn_life18/article/details/126635511

### 2.3 安装git

执行下列命令：

```shell
yum install -y git # 通过yum安装git
git --version # 若显示git版本信息，说明安装成功。
```

### 2.4 安装npm14

执行下列命令：

```shell
yum remove -y npm # 要求node和npm版本为14或16，因此先卸载可能存在的旧版本
curl --silent --location https://rpm.nodesource.com/setup_14.x | bash - # 下载npm镜像
yum install -y nodejs # 通过yum安装npm和node
node --version
npm --version
wget https://dl.yarnpkg.com/rpm/yarn.repo -O /etc/yum.repos.d/yarn.repo # 下载yarn镜像（这一步和下一步的install yarn也可以不做，不安装yarn的话可能会报警告，但不会报错）
yum install -y yarn # 通过yum安装yarn
```

## 3. 拉取todo项目的docker镜像

备注：这一步也可以直接省略不做。后面docker-compose如果检测到我们没有手动拉取用到的镜像，则会帮我们自动拉取。

拉取todo_app(Spring Boot程序)镜像：

```shell
docker pull taitaicomedy/todo_app
```

拉取todo_mysql(数据库及表)镜像：

```shell
docker pull taitaicomedy/todo_mysql
```

拉取nginx镜像：docker pull nginx

## 4. 准备nginx配置文件和前端静态文件

### 4.1 拉取GitHub源码和准备nginx配置文件

依次执行下列命令：

```shell
mkdir /myprojects # 我们在此目录下操作，为方便直接复制shell代码，后续描述均采用以此路径为基础的绝对路径
cd /myprojects # 切换到/myprojects目录
git clone https://github.com/realwangtianyi/todolist.git # 国内访问GitHub不稳定，如果失败，请多重试几次
cd /myprojects/todolist # 切换到/myprojects/todolist目录
ls # 查看todolist目录中有哪些子目录
```

执行ls后，应显示以下内容，说明成功：

```
README.md # README文件
pictures # 存放README.md中图片的目录
todo_frontend # 前端程序的源代码目录，Vue2编写
todo_backend # 后端程序的源代码目录，Spring Boot编写
todo_docker_image # 镜像制作文件目录，含Dockerfile等
todo_nginx_conf # nginx服务器配置文件相关内容目录
```

### 4.2 生成前端静态文件

```
vim /myprojects/todolist/todo_frontend/src/main.js # 编辑main.js文件
```

通过vim等方式编辑main.js文件，该文件如下图所示，请在提示位置修改后端服务器的ip地址和端口。其中，服务器IP地址一般应为后端服务器的公网地址，端口号为后端服务器暴露的端口号，建议设置成8234，如果设置成别的，需要在《5.1 修改docker-compose.yml文件》中也进行相应修改。

```
import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import './assets/css/global.css'
import axios from 'axios'

Vue.config.productionTip = false
axios.defaults.baseURL = 'http://服务器IP地址:端口号'
Vue.prototype.$http = axios

new Vue({
  render: h => h(App)
}).$mount('#app')
```

修改结束后，按:wq保存文件并退出。

接下来，执行以下命令，生成前端的dist文件。

```
cd /myprojects/todolist/todo_frontend # 进入前端源码根目录
npm install # 安装依赖
npm run build # 打包生成dist文件，执行完成后，即可生成dist目录
```

接下来将dist目录拷贝到nginx配置文件路径下。

```
cd /myprojects/todolist/todo_frontend # 进入前端源码根目录
cp -r dist /myprojects/todolist/todo_nginx_conf/html/ # 将dist文件复制到nginx配置文件路径下
```



## 5.开启前后端和数据库

### 5.1 修改docker-compose.yml文件

备注：如果前面4.3中端口号设置的是8234，这里可以不用改。否则的话，需要将container_todo_app的ports左边的8234改成4.3中设置的端口号。其他地方一般不需要修改。

```shell
vim /myprojects/todolist/todo_docker_image/docker-compose.yml # 编辑docker-compose.yml文件
```

文件打开后，请根据以下注释进行修改：

```bash
version: "3"
services:
  container_todo_mysql:
    image: taitaicomedy/todo_mysql:1.0 # MySQL数据库镜像，含todo表和wangtianyi用户
    ports:
      - "13306:3306" # 如果执行后提示13306端口被占用或其他原因，左边那个13306端口也可以改成其它未被占用的端口，右边那个3306不要修改。
    environment:
      MYSQL_DATABASE: todo
      MYSQL_USER: wangtianyi # MySQL用户和密码维持这样就可以，不需要修改，不会干扰宿主机的数据库。
      MYSQL_PASSWORD: 123456

  container_todo_app:
    image: taitaicomedy/todo_app:1.0 # 后端程序镜像
    ports:
      - "8234:8234" # 如果执行后提示8234端口被占用或其他原因，左边那个8234端口也可以改成其它未被占用的端口，但是一定要去/myprojects/todolist/todo_frontend/src/main,js里把端口号改成与此处一致，并且重新npm run build和cp dist。右边那个8234不要修改。
    depends_on:
      - container_todo_mysql

  container_todo_nginx:
    image: nginx:1.23.2  # nginx http服务器镜像，用于部署vue
    ports:
      - "8111:80" # 如果执行后提示8111端口被占用或其他原因，左边那个8111端口也可以改成其它未被占用的端口，这里是几，在浏览器地址栏输入的端口就是几。右边那个80是nginx的监听端口，不要修改。
    volumes:
      - /myprojects/todolist/todo_nginx_conf/html:/usr/share/nginx/html
      - /myprojects/todolist/todo_nginx_conf/nginx.conf:/etc/nginx/nginx.conf
      - /myprojects/todolist/todo_nginx_conf/logs:/var/log/nginx
      - /myprojects/todolist/todo_nginx_conf/conf:/etc/nginx/conf.d
```

如果修改端口，要注意修改成没有被占用的端口。

Cent OS下查看端口是否被占用请参考https://blog.csdn.net/qq_38766930/article/details/123707354。

### 5.2 执行docker-compose.yml文件

```bash
cd /myprojects/todolist/todo_docker_image # 切换到容器编排脚本所在目录下
docker-compose up # 执行docker-compose.yml编排脚本，启动前端、后端、数据库的容器。compose会到DockerHub里自动拉取用到的镜像
```

如果执行docker-compose up的过程中Java报错，请执行以下命令后重新docker-compose up：

```shell
docker system prune -f
```



### 6. 访问和使用todolist应用

在浏览器地址栏中输入nginx容器所在宿主机的ip:nginx暴露外部端口号，对todolist应用进行访问。

附：界面截图

![](pictures\main.png)

