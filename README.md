# Scanpost

## 1. О проекте
Scanpost - это простое консольное Java приложение, которое позволяет отфильтровать базу данных, 
содежащую отображение Map (Пользователь - Множество email адресов, этого пользователя).
После работы приложения в базе остаются только уникальные пользователи. Пользователь считается уникальным, если его email адреса не присутствуют у других пользователей.
Если в базе содержатся дубликаты пользователей, то их множества адресов будут объеденены и сохранены под одним пользователем. 

## 2. Сборка 
Сборка производится с помощью Maven

## 3. Как использовать
Необходимо создать экземпляр класса Scanpost и передать в качестве аргумента исходную базу данных.
Далее у созданного объекта вызвать метод *deleteCopy()*
Данный метод вернет отфильтрованную базу данных, того же типа, что и исходная

## 4. Контакты
email: tribuna87@mail.ru
skype: tribuna87
telegram: @kutiavinvladimir

[![Build Status](https://travis-ci.com/kva-devops/scanpost.svg?branch=master)](https://travis-ci.com/kva-devops/scanpost)
[![codecov](https://codecov.io/gh/kva-devops/scanpost/branch/master/graph/badge.svg?token=OWRIMGNY31)](https://codecov.io/gh/kva-devops/scanpost)