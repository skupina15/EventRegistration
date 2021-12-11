# EventRegistration

docker build -t ime .

docker run -d --name climb-db -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=climb-db -p 5432:5432 postgres:13