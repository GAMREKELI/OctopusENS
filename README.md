# OctopusENS

Этот проект представляет собой программу для мгновенного оповещения всех друзей 
авторизованного пользователя о возможной опасности для него. 
Пользователь может инициировать оповещение, нажав на кнопку, после чего все его друзья получат уведомление 
на их электронную почту.

## Технологии
- [Java](https://www.java.com/ru/)
- [Spring](https://spring.io/)
- [Apache Kafka](https://kafka.apache.org/)
- [Apache Zookeeper](https://zookeeper.apache.org/)
- [Spring Security](https://spring.io/projects/spring-security)
- [Zipkin](https://zipkin.io/)
- [Docker](https://www.docker.com/)
- [MySql](https://www.mysql.com/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Eureka](https://spring.io/guides/gs/service-registration-and-discovery/)
- [Google SMTP](https://about.google/intl/gmail/)

## Разработка

В этом проекте создана функция небольшой социальной сети, в которой есть возможность подписки и добавления в друзья. 
При отправке запроса происходит уведомление всех друзей пользователя. Информация о всех пользователях хранится в базе данных, 
в другой базе данных сохраняются сгенерированные сообщения о возможных опасностях. 
Доступ к сервисам имеют только зарегистрированные пользователи.

#### Описание сервисов:

1) config-service:
    - Сервис, в котором находятся все конфигурации каждого сервиса.
2) authentication-service:
    - Сервис отвечающий за регистрацию и авторизацию пользователей, чтобы не получилось такого, что неавторизованный пользователь мог манипулировать с вашими данными.
3) API-Gateway:
    - Вокруг него построена вся архитектура, он получает все запросы от клиентов, обрабатывает и рассылает разным сервисам.
4) user-service:
    - Сервис отвечающий за пользователей. Он отвечает за функции отправки запроса в друзья, а также добавления в друзья, предоставляет информацию
о пользователях, которая необходима для рассылки в дальнейшем.
5) API-Service:
    - Данный сервис генерирует сообщение, копию сохраняет в базе данных, далее этот сервис отправляет по Kafka в сервис notification-email-service.
6) notification-email-service:
    - Сервис, который принимает сообщения, парсит их, достаёт всю необходимую информацию (например mail'ы по котором отправлять сообщения),
далее с помощью Google SMTP делает рассылку по почте.
7) registry-service:
    - > Eureka Server — это service discovery (обнаружение сервисов) для ваших микросервисов. Клиентские приложения могут самостоятельно регистрироваться в нем, а другие микросервисы могут обращаться к Eureka Server для поиска необходимых им микросервисов.
    - Проще, этот сервис является грубо говоря контейнером, в котором лежат все сервисы, каждый сервис регистрируется в нём.

### Наглядная схема работы проекта:

![Схема](./images/Снимок%20экрана%202023-11-12%20в%2019.33.09.png)


