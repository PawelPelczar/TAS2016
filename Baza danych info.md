#UWAGA!
Poniższe założenia ulegną niedługo zmianie! (np. ankieta będzie reprezentowana przez zestaw rekordów w jednej tabeli)

#Jak uruchomić bazę danych w programie Microsoft SQL Server Management Studio?

Należy kolejno wykonać:
* Uruchomić program Microsoft SQL Server Management Studio
* Stworzyć nowe zapytanie (klikając w przycisk _New Query_)
* Skopiować kod z pliku _Baza-danych.sql_
* Uruchomić kod (klikając w przycisk _Execute_)

lub:
* Otworzyć plik _Baza-danych.sql_ za pomocą programu Microsoft SQL Server Management Studio
* Uruchomić kod (klikając w przycisk _Execute_)

#Założenia bazy danych.
Baza danych ma być uniwersalna, np. powinna zapewniać nieograniczoną liczbę pytań w sondzie. Ewentualne ograniczenia, wynikające
choćby ze względów bezpieczeństwa (przecież nikomu nie potrzebna jest sonda z milionem pytań), powinny zostać nałożone po stronie
warstwy biznesowej.  
Baza danych powinna zapewniać możliwość tworzenia sond zarówno jednokrotnego jak i wielokrotnego wyboru.  

#Jak w tej chwili działa baza danych?
**Uwaga!** Dla każdego uruchomienia tworzona jest nowa baza danych (stara jest usuwana)!  
W bazie danych dla każdej sondy jest tworzona osobna tabela. Aby ułatwić prowadzenie statystyk, w trakcie tworzenia sondy
do tabeli 'List_polls' wprowadzany jest rekord z numerem ID użytkownika tworzącego sondę i numerem ID samej sondy (czyli nazwy tabeli).  
Dzięki tworzeniu nowej tabeli dla każdej sondy spełniony jest warunek nieograniczonej liczby pytań.  
W tej chwili pytanie może posiadać **tylko sześć odpowiedzi**. Odpowiedzi na pojedyncze pytanie tworzą ciąg binarny (1 dla zaznaczenia
odpowiedzi, 0 dla braku zaznaczenia) i są zapisywane do zmiennej, dzięki czemu zapewniony jest warunek tworzenia pytań jednokrotnego
i wielokrotnego wyboru (przy wsparciu strony biznesowej).  
Tabele _Admins_ i _Users_ niczym się nie różnią. Ze względów bezpieczeństwa zostały one jednak podzielone.  
Procedura 'Create_poll' zwraca wartość 1 jeśli wszystko przebiegło pomyślnie. Jeśli istnieje już tabela o numerze '@id_poll'

#Kierunki rozwoju na przyszłość.
* Wprowadzenie tabel zapewniających zapis odpowiedzi użytkowników na pytania z sond.
* Wprowadzenie procedur i widoków zapewniających dane statystyczne.
