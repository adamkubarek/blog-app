# blog-app
Prosta aplikacja bloga z wykorzystaniem frameworka Spring.
## Mikro dokumentacja
### Instalacja
Projekt należy sklonować i zaimportować w Eclipse albo InteliJ
Projekt należy uruchomić jako 'Spring boot app', Spring Boot powinien sobie poradzić z konfiguracją (zawiera wszystkie 'startery' w pliku pom.xml). (Wymagany Maven) 
Aplikacja zostanie uruchomiona pod adresem http://localhost:8080/, pod adresem http://localhost:8080/h2-console dostęp do bazy danych
Aby dostać się do funkcjonalności admina należy się zalogować
* login:'admin'
* hasło:'password'

### Wykorzystane technologie
* Spring Boot
* Spring Security
* Spring Data
* Hibernate
* H2 Database
* Thymeleaf
* HTML
* CSS
* Bootstrap
* JavaScript(CkEditor)
### Use Case
![alt text](https://github.com/adamkubarek/blog-app/blob/master/UseCaseDiagram.jpg)
### Krótki opis struktury projektu
Aplikacja została napisana zgodnie z wzorcem  MVC, dzięki czemu jest wysoce modularna i zapewnia łatwość
jej ewentualnej rozbudowy. Głównymi elementami aplikacji są klasy Javy zaimplementowane w pakiecie
hawk.springframework.blogapp, oraz pliki html w folderze resources/templates. Do budowy projektu został
wykorzystany Maven. W pakiecie hawk.springframework.blogapp znajdują się kolejne pakiety odpowiadające za
poszczególne funkcje aplikacji. 
* W pakiecie controller zostały zdefiniowane 3 klasy kontrolerów. Kontrolery są podzielone na kontroler odpowiedzialny
za obsługiwanie adresów URL admina, kontroler odpowiedzialny za obsługiwanie adresów URL użytkownika oraz kontroler
odpowiedzialny za obsługiwanie wyjątków.
* W pakiecie domain zostały zdefiniowane obiekty domenowe tj. Artykuł, Komentarz oraz Tag. Obiekty te są ze sobą powiązane
za pomocą adnotacji z pakietu javax.persistence, a także zostały zastosowane adnotacje odpowiadające za podstawową walidację
z pakietu javax.validation. Gettery oraz Settery generowane są za pomocą adnotacji z wykorzystaniem biblioteki Lombok.
* W pakiecie exceptions została zdefiniowana klasa NotFoundException, błąd ten jest wywoływany przy odwoływaniu się do
ID(poprzez URL) które nie istnieją.
* W pakiecie repository zostały zdefiniowane interfejsy repozytoriów dla każdego obiektu domenowego. Interfejsy te dziedziczą
z interfejsu CrudRepository, który jest implementowany przez Spring Data. Dodatkowo obiekt domenowy Artykuł posiada
interfejs, który dziedziczy interfejs PagingAndSortingRepository, który zapewnia paginację.   
* W pakiecie samples została zdefiniowana klasa, która dodaje do bazy danych niewielką liczbę danych początkowych.
* W pakiecie security została zdefiniowana klasa, która odpowiada za konfigurację Spring Security. W konfiguracji tej zostały
wydzielone adresy url dostępne tylko dla admina po zalogowaniu się. Na chwilę obecną jest dodany 1 admin za pomocą
inMemoryAuthentication().
* W pakiecie service zostały zdefiniowane interfejsy usług dla każdego obiektu domenowego wraz z pakietem impl, w którym
są klasy implementujące te interfejsy.
* W pakiecie util zostały zdefiniowane 2 klasy, klasa CurrentTime, która posiada statyczną metodę, która zwraca obecny
czas obiektu LocalDateTime w odpowiednim formacie. Druga klasa (pager) jest klasą pomocniczą odpowiadającą za ustawienia paginacji. 
* W głównym pakiecie znajduje się również główna klasa BlogAppApplication, odpowiadająca za całą konfigurację projektu jaką
zapewnia Spring Boot.     

