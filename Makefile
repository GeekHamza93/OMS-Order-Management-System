.PHONY: dev build test db-up db-down

dev:
	./mvnw quarkus:dev

build:
	./mvnw -DskipTests clean package

test:
	./mvnw -DskipTests=false test

db-up:
	docker compose up -d db

db-down:
	docker compose down
