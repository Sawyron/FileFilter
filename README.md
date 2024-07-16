# Утилита фильтрации содержимого файлов

## Используемые технологии

- Java 22
- Maven 3.9.6
- Apache Commons CLI 1.8.0
- Junit 5.10.3 

## Аргументы командной строки

- -a, --append - включить режим добавления в существующие файлы
- -f, --full - включить режим вывода полной статистики
- -s, --simple - включить режим вывода краткой статистики
- -o, --output - задать путь для файлов результатов
- -p, --prefix - задать префик имён файлов результатов
- Пути файлов входных данных передаются последними агрументами

Для сборки выполнить команду "mvn package", будет создан файл
для запуска утилиты
"file-filter-1.0-SNAPSHOT-jar-with-dependencies.jar".
