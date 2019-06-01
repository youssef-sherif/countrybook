FROM node:11.8.0
WORKDIR /usr/src/app

COPY package*.json ./
RUN yarn install
RUN yarn global add serve

COPY . .

EXPOSE 3000
CMD [ "serve", "-s", "build"]