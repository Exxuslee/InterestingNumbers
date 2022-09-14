Тестове завдання для кандидата на позицію Android Developer

Розробити додаток для Android, цікаві факти про числа, на
Java/Kotlin. Додаток повинен дати можливість користувачу ввести число,
про яке він хоче дізнатись цікавий факт, а також - випадково згенерувати
число і отримати про нього факт. Додаток повинен містити 2 екрана.

Головний екран
Повинен бути розділений на дві частини, верхня частина - поле для
введення числа, кнопка “Отримати факт” і кнопка “Отримати факт про
випадкове число”, нижня частина - відображати історію запитів
користувача, кожен елемент історії повинен показувати число, яке
користувач раніше шукав і прев’ю факту( все, що поміщається в один
рядок), по кліку на елемент повинен відкриватись 2й екран.
2й екран
Повинен відображати користувачу число і повний текст факту про
вибране попередньо число. Також повинна бути можливість повернутись
на попередній екран.

Опис завдання
Для отримання інформації про число - використовуємо api
http://numbersapi.com. Наприклад, для числа “10” - запит http://numbersapi.com/10
Для отримання факту про випадкове число - http://numbersapi.com/random/math
Для “http” запитів не забудьте додати android:usesCleartextTraffic="true" в
Manifest файл.

Основні вимоги
- МП - Java/Kotlin, IDE - Android Studio;
- Запити до api повинні відбуватись асинхронно(наприклад Coroutine, Rx);
- Для зберігання даних(історії пошуку фактів) використовувати Room;
- UI додатку - довільний, це не є критерієм оцінки тестового завдання;
