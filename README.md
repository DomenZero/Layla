Layla - intellectual counter
============================

Счетчик и хранилище результатов предполагаемых [игр][1]. Предназначена для улучшения процесса ролевых игр за счет ведения статистик.

Idea
====

Программное приложение под Android Layla, дальше Layla, позволяет с помощью графического интерфейса 
производить операции с таблицами Players, Rating хранящимися в базе данных Layla.db, такими как:
- Добавление учетных записей пользователей;
- Удаление учетных записей пользователей;
- Изменение в заранее определенных полей (по предназначению Layla) в таблице Rating. 
Пользователь (Ведущий статистику игрок, далее Ведущий Игрок) может сохранить Рейтинг Игрока, который автоматически сохранится в таблицу Rating.
При отсутствии в ней текущего Победителя добавляется новый, иначе приплюсовывается победа за партию.
Победитель определяется правилами игры и Ведущим Игроком.

How used
========

Пример использования для игры "Манчкин".
1. Выбираем количество игроков.
![Image][2]
2. Добавляем игроков (Ввод имени).
![Image][3]
3. Игра.
![Image][4]
4. Посмотреть рейтинг.
![Image][5]

Developed By
============
* Maksim Merkulov - <wardomenmax@gmail.com>

License
=======

Copyright 2015 Maksim Merkulov (DomenZero)
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[1]: под игрой следует понимать любую игру где интересна статистика побед. (то есть, почти все с соперничеством)
// понимать процесс взаимодействия Пользователя с графическим интерфейсом счетчика приложения Layla.
[2]: url image1.png