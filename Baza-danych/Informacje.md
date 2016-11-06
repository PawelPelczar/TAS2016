**UWAGA! Uruchomienie całego kodu z 'Baza danych.sql' powoduje usunięcie i stworzenie na nowo całej bazy danych! Dlatego proszę uruchamiać kod rozważnie!**

Baza danych stoi już na Amazonie. Dane do logowania wszystkim wysyłałem w mailu.

Ogólne zasady działania:
* Lista rekordów w tabeli 'Pytania' o takim samym ID sondy tworzy pełną sondę.
* Analogicznie lista rekordów o takim samym ID pytania w tabeli 'Odpowiedzi' tworzy listę wszystkim możliwych odpowiedzi na dane pytanie.
* Tabela 'Wyniki' jest tabelą transakcyjną i przechowuje odpowiedzi użytkowników.

Założenia:
* Sonda może posiadać nieograniczoną liczbę pytań (ewentualne ograniczenia mogą istnieć po stronie warstwy biznesowej)
* Każde pytanie może mieć nieograniczoną liczbę możliwych odpowiedzi (ewentualne ograniczenia mogą istnieć po stronie warstwy biznesowej)
* **Nie istnieje mechanizm sprawdzający czy użytkownik odpowiedział na wszystkie pytania w sondzie**. Dlatego przed zapisem do bazy danych należy się upewnić po stronie innej warstwy.
* Przy dodatkowym założeniu, że rekordy w tabeli 'Wyniki' o ID użytkownika równym 0 (lub inną liczbą <=0) reprezentują niezalogowanych użytkowników, spełnione jest założenie, że zarówno anonimowi jak i zarejestrowani użytkownicy mogą odpowiadać na pytania w sondzie.

Kolejne obszary rozwoju:
* Wszystko to o czym pisałem w mailu.
* Wykorzystanie widoków.
* Stworzenie procedur, dzięki którym możliwe będzie wyświetlanie statystyk.

Kilka przykładowych poleceń:
```sql
SELECT * FROM Wyniki WHERE id_user=3

SELECT Sondy.id_sondy AS 'ID sondy', Users.name AS 'Imię autora sondy', Users.surname AS 'Nazwisko autora sondy',
Sondy.title AS 'Tytuł sondy', Sondy.liczba_pytan AS 'Liczba pytań sondy' FROM Sondy JOIN Users ON Users.id_user=Sondy.id_autor
```
