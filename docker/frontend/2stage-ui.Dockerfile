# Stage 1
FROM node:12 as react-build
WORKDIR /app
COPY ./app ./
RUN yarn
RUN yarn build

# Stage 2 - the production environment
FROM nginx:alpine

RUN apk add --no-cache curl

COPY --from=react-build /app/build /usr/share/nginx/html
COPY ./docker/frontend/nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
