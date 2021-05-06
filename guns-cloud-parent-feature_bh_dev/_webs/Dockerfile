FROM node:13.12.0 as npmBuilder

#拷贝项目到工作目录
WORKDIR /apps
COPY auth-web /apps/auth-web
COPY sys-web /apps/sys-web
COPY workflow-web /apps/workflow-web

#解决node-sass安装慢
RUN mkdir -p /apps/node-sass
COPY linux-x64-79_binding.node /apps/node-sass/binding.node
ENV SASS_BINARY_PATH /apps/node-sass/binding.node

#注册淘宝镜像，加快install
RUN npm config set registry=https://registry.npm.taobao.org

#install and build
WORKDIR /apps/auth-web
RUN npm install
RUN npm run build:prod

WORKDIR /apps/sys-web
RUN npm install
RUN npm run build:prod

WORKDIR /apps/workflow-web
RUN npm install
RUN npm run build:prod

#开始构建nginx
FROM nginx:1.17 as nginxServer

#准备nginx配置文件
COPY _nginx/nginx.conf /etc/nginx/nginx.conf
COPY _nginx/conf.d /etc/nginx/conf.d

#创建一些项目目录，存放可运行的html静态文件
RUN mkdir -p /front-apps/auth
RUN mkdir -p /front-apps/sys
RUN mkdir -p /front-apps/workflow

#从上一个镜像拷贝dist相关文件
COPY --from=npmBuilder /apps/auth-web/dist /front-apps/auth
COPY --from=npmBuilder /apps/sys-web/dist /front-apps/sys
COPY --from=npmBuilder /apps/workflow-web/dist /front-apps/workflow

CMD ["nginx", "-g", "daemon off;"]