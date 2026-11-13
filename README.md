# Testcontainers + MySQL demo

Небольшой production-style Spring Boot проект с JPA, One-to-Many связью, MySQL для приложения и отдельной тестовой MySQL через Testcontainers.

Принцип проекта:

- Основное приложение работает с MySQL (`app_demo`).
- Для интеграционных тестов используется отдельная MySQL, запускаемая Testcontainers.
- В корневом пакете есть сущности `Customer` и `Order`, связанные `@OneToMany` / `@ManyToOne`.
- В момент запуска `CommandLineRunner` добавляет исходные данные в приложение и пишет их в логи.
- В `start.txt` описаны все шаги запуска, проверки и инструкция по Testcontainers.

Полная инструк��ия и команды запуска — в `start.txt`.
