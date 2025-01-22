# **Zadanie:**

Zadaním je vytvoriť jednoduchú aplikáciu, ktorá bude počítať poistné podľa zadaných parametrov:
Label 	Nadobúda hodnoty 	Povinný výber 	 	 	 	 
Variant poistenia 	krátkodobé poistenie, celoročné poistenie 	Áno 	 	 	 	 
Začiatok poistenia 	 	Áno 	 	 	 	 
Koniec poistenia 	 	áno pre krátkodobé poistenie 	 	 	 	 
Balík 	základný, rozšírený, extra 	Áno 	 	 	 	 
Pripoistenia 	storno cesty, športové aktivity 	Nie 	 	 	 	 
Počet osôb 	1 až 3 	Áno

 	Sadzby 	 	 	 	 	 
 	krátkodobé poistenie 	celoročné poistenie 	 	 	 	 
základný balík 	1,2 € / deň 	39 € 	 	 	 	 
rozšírený balík 	1,8 € / deň 	49 € 	 	 	 	 
extra balík 	2,4 € / deň 	59 € 	 	 	 	 
storno cesty 	50 % prirážka k sadzbe 	20 % prirážka k sadzbe 	 	 	 	 
športové aktivity 	30 % prirážka k sadzbe 	10 % prirážka k sadzbe

Výstupom je Cena poistenia

Príklad 	 	 	 	 	 	 
krátkodobé poistenie na tri kalendárne dni pre dve osoby, balík rozšírený s dojednanými športovými  
aktivitami stojí 14,04 € (3 * 1,8 * 1,3 * 2)



Požiadavky na použité technológie a postupy BE:
•	Vyber microservice framework: micronaut, spring boot, helidon, quarkus
•	DB - ideálne H2 DB for Java – ukladanie výpočtu + vybrané parametre (napr. Sadzby balíkov)
•	API - JSON komunikácia medzi FE – BE
•	Publikovanie swagger / openapi dokumentacie
•	Použitie application properties – napr. Parametre prirážka k sadzbe
•	zadefinovanie java objektov pre API a pouzitie enumov (napr. Variant poistenia)

Vo výsledku očakávame:
•	Zdrojové kódy aplikácie BE  
•	Plusom je spustiteľné JAR-ko  

# **Task solution:**

Java Platform: Java 21

Microservice framework: Spring Boot  + libs

Database: H2 In Memory

Build Tool: Maven

Tests: JUnit, SpringBootTest, Mockito

Task is implemented as complete E2E solution for BE from REST controller layer to DB.

Main functionality: application calculates insurance based on input params and persist result into DB.

Additional functionality: retrieving calculated results from DB

Beside implementing functionality there has been also effort to demonstrate usage optimal BE software development practices and guidelines:

1, layering - controller, service, model, domain, repository

2, domain design - domain objects flows directly from DB to REST (e.g. no value objects or special db entities) 

3, clean code

4, design patterns 

5, JAVA coding rules, practices, features, code extensibility and readiness for future changes

6, input validation + exception handling 

7, documentation (java doc + REST API)

8, automated tests - junit tests without spring context; integration tests with spring context 

Other notes:
 * main test case calculation from task example (14.04 EUR) is implemented in 
   InsuranceControllerIntegrationTests.testCalculationSuccess   
 * base rates are stored in DB and loaded on application startup, additional rates are stored in application.properties file (could be also in DB)
 * as much as possible used spring autoconfiguration
 * no need to use complex JPA  - used only simple spring JDBC repository default implementation
 * instead lombok alternative, used java record for immutability and spring logging for standard log output
 * used declarative spring + jakarta validation for input, also with conditional constraints (e.g. CustomValidationConstraint)
 * swagger documentation based on open api annotations in controller

#### **Build project:**
_mvn clean install_

#### **Start application with maven:**
_mvn spring-boot:run_

#### **Start application with jar:**
_cd target_

_java -jar insurance-0.0.1-SNAPSHOT.jar_

#### **Run tests:**
_mvn test_


#### **Open API URL:**
http://localhost:8080/swagger-ui/index.html#/




