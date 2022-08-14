FROM node:12.8.0-alpine
WORKDIR /app
COPY ./package.json .
RUN npm install
COPY . .
EXPOSE 4200
CMD npm start
