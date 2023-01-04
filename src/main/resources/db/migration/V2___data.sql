INSERT INTO news.source (id, name)
VALUES (1, 'irbis.plus'),
       (2, 'praktika.irbis.plus');
INSERT INTO news.subject (id, name)
VALUES (1, 'Помощь юр. лицам'),
       (2, 'Помощь физ. лицам'),
       (3, 'О нас'),
       (4, 'Обновления сервиса');
INSERT INTO news.flow (id, textflow, source_id, subject_id)
VALUES (1, 'Обновления законодательства в 2022 году.', 1, 1),
       (2, 'Обновления законодательства в 2023 г.', 1, 1),
       (3, 'Рассказываем о том, как обезопасить себя от мошенников', 1, 2),
       (4, 'Рассказываем о том, как отдыхают наши работники', 1, 3),
       (5, 'Знакомим с нашими клиентами. Часть 1', 1, 3),
       (6, 'Знакомим с нашими клиентами. Часть 2', 1, 3),
       (7, 'Знакомство с сервисом', 2, 4),
       (8, 'Нововведения во вкладке "Суды"', 2, 4);