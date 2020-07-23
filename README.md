# AlgorithmBooks
Плагин, позволяющий программировать прямо в книжке! В майнкрафт! Пишите свои алгоритмы для своего игрока! / A plugin that allows you to program right in the book! Minecraft! Write your own algorithms for your player!

## ENG
# Description
Plugin capabilities:
1. start, end - start algorithm and end algorithm
2. forward, back, left, right, run, jump - walking and jump
3. if 1 == 1 - conditions
4. rotate - turn the player's head (requires input: rotate = degX, degY)
5. write - displays a message in the chat (requires input: write = input)
6. &testVariable = 1 - variable declarations (example of using a variable: write = &testVariable&)
7. #testMetka - label to goto it (jump to label: goto testMetka)
8. timeout - suspends the process for the specified amount of time (requires input: timeout milliseconds)
9. slot - switches slot to player (requires input: slot num)
10. click left - breaks the block the player is hovering over
11. click right - puts the block you have in your hands
12. lookToBlock - a function that returns the type of block the player is looking at (example: &testTargetBlock = lookToBlock)
13. send - sends a message to the chat from the player, can also call commands (example: send = /bot start)
14. drop - throws the item in the player's hand under him

# Examples in pictures
![alt text](/screenshots/2020-07-23_15.56.08.PNG "")
![alt text](/screenshots/2020-07-23_15.56.12.PNG "")
![alt text](/screenshots/2020-07-23_15.56.28.PNG "")
![alt text](/screenshots/2020-07-23_15.56.34.PNG "")
![alt text](/screenshots/2020-07-23_15.56.41.PNG "")

Video about the plugin (in Russian) - URL.

# Compile
1. Download the source code and upload it to Eclipse for example.
2. Add External JARs: [Server Core](https://getbukkit.org/download/craftbukkit) 1.12.2.
3. Now you have the code that you can edit!

# Install for Server
1. Download the compiled [AlgorithmBooks](https://github.com/Dseym/AlgorithmBooks/releases/download/AlgorithmBooks/AlgorithmBooks.jar) and upload it to your server.
2. Have fun!

If you need to translate the plugin into English, then write to Issues.
If you need a version for 1.14.4 or higher, then write to Issues.

## RUS
# Описание
1. start, end - начало и конец алгоритма
2. forward, back, left, right, run, jump - движения игрока и прыжок
3. if 1 == 1 - условие
4. rotate - поворот головы игрока (требуется вход: rotate = градусX, градусY)
5. write - вывести в чат собщение (требуется вход: write = 1)
6. &testVariable = 1 - создание переменной (пример использования: write = &testVariable&)
7. #testMetka - метка для перехода к ней (пример перехода: goto #testMetka)
8. timeout - приостанавливает выполнение аглоритма (пример: timeout миллисекунды)
9. slot - меняет слот игроку (пример: slot 5)
10. click left - ломает блок на который смотри игрок
11. click right - ставит блок который у игрока в руках
12. lookToBlock - функция, возвращающая тип блока на который смотрит игрок (пример: &testTargetBlock = lookToBlock)
13. send - отправляет в чат сообщение от имени игрока, также отправляет и команды (пример: send /bot start)
14. drop - выбрасывает предмет из руки игрока под себя

# Примеры в картинках
URLs

Видео о плагине (на русском) - URL.

# Компиляция
1. Скачайте исходный код и загрузите, к примеру, в Eclipse.
2. Добавьте External Jars в проект: [Серверное ядро](https://getbukkit.org/download/craftbukkit) 1.12.2.
3. Теперь у Вас есть код для редактирования!

# Установка на сервер
1. Скачайте скомпилированный [AlgorithmBooks](https://github.com/Dseym/AlgorithmBooks/releases/download/AlgorithmBooks/AlgorithmBooks.jar) и загрузите на свой сервер.
2. Веселитесь!

Eсли понадобиться версия для 1.14.4 или выше, пишите в Issues.

Для свободного использование.