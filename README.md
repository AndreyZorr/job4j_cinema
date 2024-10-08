**job4j_cinema**

В этом проекте нужно разработать сайт по покупке билетов в кинотеатре.

Сервис позволяет:
1.Регистрацию и вход пользователям.
2.Просмотр киносеансов и фильмов.
3.Покупку билетов.

Для реализации нужно использовать: 
* Spring Boot 2.7.6
* Postgresql 42.5.1 
* Sql2o 1.6.0 
* commons-dbcp2 2.9.0 
* H2database 2.1.214
* Mockito
* Liquibase
* Junit5
* AssertJ

Требования к окружению:
* Java19
* Maven 4
* PostgreSQL 16

Запуск проекта:
Создать бд PostgreSQL:
create database cinema;

Клонировать репозиторий:
git clone https://github.com/AndreyZorr/job4j_cinema.git

Собрать проект с помощью Maven:
mvn clean install

Запуск проекта:
mvn clean install

После запуска проект доступен по адресу: http://localhost:8080

Взаимодействие с приложением:

Главная страница.


Регистрация/вход


