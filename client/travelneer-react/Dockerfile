FROM node:11.8.0

ENTRYPOINT [ "serve", "-s", "build"]

WORKDIR /usr/src/app
COPY . .
WORKDIR /usr/src/app

RUN yarn build
RUN yarn global add serve