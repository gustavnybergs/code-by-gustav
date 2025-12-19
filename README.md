# Code by Gustav - Fullstack Developer Portfolio

En professionell portfolio-webbplats som visar min kompetens som fullstack-utvecklare. Byggd som examensarbete för Javautvecklare-programmet (JAVA24) på Stockholms Tekniska Institut.

## Om Projektet

Detta är min digitala portfolio där jag presenterar mig själv, mina projekt och min tekniska kompetens för potentiella arbetsgivare och rekryterare. Portfolion är byggd som en fullstack-applikation för att visa att jag behärskar både frontend och backend-utveckling.

Min bakgrund från sälj, kundtjänst och undervisning ger mig en unik kombination av teknisk förmåga och kommunikationsskills. Jag kan översätta mellan affärsbehov och tekniska lösningar, vilket gör mig till en utvecklare som förstår både kod och användaren bakom skärmen.

## Tech Stack

### Frontend
- **React** - Komponentbaserat UI-bibliotek
- **React Router** - Navigation mellan sidor
- **Axios** - HTTP-klient för API-kommunikation
- **React Icons** - Ikonbibliotek
- **CSS3** - Styling med CSS variables och responsiv design

### Backend
- **Java 17** - Programmeringsspråk
- **Spring Boot 3.2.0** - Backend-ramverk
- **Spring Data JPA** - Databashantering
- **MySQL** - Relationsdatabas
- **Lombok** - Reducerar boilerplate-kod
- **Maven** - Build-verktyg

### Utvecklingsverktyg
- **Git & GitHub** - Versionshantering
- **GitHub Projects** - Kanban-board för projektstyrning
- **Visual Studio Code** - Frontend-utveckling
- **IntelliJ IDEA** - Backend-utveckling

## Funktioner

### Besökare kan:
- Se en professionell presentation av mig som utvecklare
- Bläddra genom mina projekt med detaljerade beskrivningar
- Navigera mellan projekt med intuititva pilar
- Se mina tekniska färdigheter kategoriserade och visualiserade
- Läsa om min bakgrund och karriärväg
- Kontakta mig via ett fungerande kontaktformulär
- Uppleva en responsiv design som fungerar på alla enheter

### Tekniskt:
- Fullstack-arkitektur med separation mellan frontend och backend
- RESTful API med CRUD-operationer
- Databas-driven innehåll (projekt, skills, kontaktmeddelanden)
- Form-validering både på frontend och backend
- Error handling och loading states
- CORS-konfiguration för säker kommunikation

## Utvecklingsprocess

### Vecka 44-48: Foundation och Prototyp

När jag påbörjade projektet kastade jag mig direkt in i kodandet. Jag ville snabbt få upp en fungerande prototyp för att se om min tekniska stack fungerade tillsammans och för att få en känsla för hur portfolion skulle se ut.

Under dessa veckor byggde jag:
- Grundstrukturen för både React-frontend och Spring Boot-backend
- Alla JPA entities (Project, Skill, ContactMessage) med repositories
- REST API-controllers för alla endpoints
- React-komponenter (Header, Footer, ProjectCard, SkillCard, ContactForm)
- Alla sidor (Home, Projects, ProjectDetail, Skills, About, Contact)
- Styling med ett monokromt färgschema och responsiv design
- Integration mellan frontend och backend via Axios

Jag jobbade intensivt under dessa veckor, ungefär 4 hela dagar där jag itererade snabbt mellan frontend och backend för att få allt att fungera tillsammans.

### Vecka 51: Omstrukturering och Projektstyrning

Efter att ha byggt prototypen insåg jag att jag behövde strukturera upp arbetet bättre för att möta kursens krav på projektstyrning. Även om koden var färdig hade jag inte arbetat med GitHub Issues eller Kanban-board från start.

Jag beslutade att göra en "clean start" med git-historiken:
1. Skapade 8 issues som representerade olika utvecklingsfaser
2. Skapade ett Kanban-board i GitHub Projects
3. Raderade den gamla git-historiken
4. Gjorde 8 nya commits där varje commit refererade till ett specifikt issue
5. Pushade allt till GitHub med en strukturerad historik

Detta var en lärorik process. Nästa gång jag startar ett projekt kommer jag att:
- Skapa issues och Kanban-board FÖRE jag börjar koda
- Committa oftare med tydliga meddelanden
- Använda branching-strategi för större features
- Dokumentera löpande istället för i efterhand

### Vecka 52-1: Polish och Färdigställande

Nu arbetar jag med att:
- Seeda databasen med riktiga projekt och skills
- Kommentera koden på svenska enligt clean code-principer
- Deploya frontend till Vercel och backend till Railway
- Skriva denna README med reflektioner
- Förbereda för presentation och examination

## Tekniska Val och Reflektioner

### Varför React?

React är det frontend-ramverk jag känner mig mest bekväm med efter utbildningen. Vi har jobbat mycket med React och jag uppskattar:
- Komponentbaserad arkitektur som gör koden återanvändbar
- JSX som kombinerar HTML och JavaScript på ett intuitivt sätt
- Stark community och bra dokumentation
- Hooks (useState, useEffect) som gör state management enkelt

För en portfolio behöver jag inte den extra komplexiteten som Angular erbjuder, och React kändes som det naturliga valet.

### Varför Spring Boot?

Spring Boot är det backend-ramverk vi fokuserat mest på under utbildningen. Jag valde det eftersom:
- Jag känner mig bekväm med Java och Spring ekosystemet
- Spring Data JPA förenklar databashantering enormt
- Inbyggt stöd för REST API:er med minimal konfiguration
- Lombok reducerar boilerplate-kod (getters, setters, constructors)
- Professionell standard inom Java-utveckling

Spring Boot gav mig också möjlighet att visa att jag behärskar fullstack-utveckling med Java, vilket är relevant för många arbetsgivare.

### Varför MySQL?

Jag valde MySQL framför PostgreSQL av en praktisk anledning - jag har tidigare haft tekniska problem med PostgreSQL på min dator. MySQL har fungerat stabilt för mig och:
- Är väletablerat och väldokumenterat
- Fungerar utmärkt med Spring Data JPA
- Är enkelt att sätta upp både lokalt och i molnet
- Har god prestanda för denna typ av applikation

För en portfolio med relativt lite data är skillnaden mellan MySQL och PostgreSQL försumbar. Det viktiga var att välja en databas jag kunde arbeta effektivt med.

## Design och UX

En av de största utmaningarna med att bygga en portfolio var designen. Även om det tekniskt inte är svårt att bygga en enkel CRUD-applikation, så är det mycket svårare att skapa något som ser professionellt och unikt ut.

### Designprocess

Jag studerade många olika developer portfolios och insåg att de flesta följer samma mönster:
- Mörk bakgrund (ofta svart eller mörkblå)
- Neon-färger eller starka accenter
- Mycket "tech-estetik"

Jag ville sticka ut och valde därför:
- Monokromt färgschema (svart, vitt, grått)
- Minimalistisk och ren design
- Fokus på läsbarhet och användbarhet framför "coola" effekter
- Professionell ton som passar både startup-kulturen och mer traditionella företag

### Responsiv Design

Portfolion är byggd mobile-first, vilket innebär att jag först designade för mobila enheter och sedan skalade upp för större skärmar. Detta säkerställer att portfolion fungerar bra oavsett enhet.

### Navigering

För projektdetaljsidor implementerade jag side-navigation arrows (höger/vänster pilar) istället för att bara ha en "tillbaka"-knapp. Detta gör det enkelt att bläddra mellan projekt utan att behöva gå tillbaka till projektlistan varje gång.

## Utmaningar och Lärdomar

### Största Utmaningen: Design

Som nämnt ovan var designen den största utmaningen. Det är lätt att bygga funktionalitet, men mycket svårare att skapa något visuellt tilltalande och professionellt. Jag lärde mig att:
- Mindre är ofta mer - för många effekter kan göra sidan rörig
- Konsistens är viktigare än att "sticka ut" på varje sida
- Whitespace och spacing är lika viktigt som innehållet
- Inspiration från andras portfolios är okej, men kopiera aldrig

### Lärdom: Planering är Viktigt

Även om jag lyckades bygga en fungerande portfolio relativt snabbt, insåg jag i efterhand att jag borde ha:
1. Planerat bättre med wireframes innan jag började koda
2. Använt GitHub Issues från dag 1
3. Committat oftare med mindre ändringar
4. Dokumenterat tekniska val löpande istället för i efterhand

Detta är lärdomar jag tar med mig till nästa projekt.

### Lärdom: Portfolios är Levande Dokument

En portfolio är aldrig "färdig". Det finns alltid nya projekt att lägga till, nya tekniker att lära sig, och design att förbättra. Det viktiga är att ha något att visa, och sedan iterera och förbättra över tid.

## Installation och Körning

### Förutsättningar
- Node.js (v18 eller senare)
- Java 17
- Maven 3.6+
- MySQL 8.0+

### Backend Setup

1. Skapa MySQL-databas:
```sql
CREATE DATABASE portfolio_db;
CREATE USER 'portfolio_user'@'localhost' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON portfolio_db.* TO 'portfolio_user'@'localhost';
FLUSH PRIVILEGES;
```

2. Navigera till backend-mappen:
```bash
cd backend
```

3. Kör applikationen:
```bash
mvn spring-boot:run
```

Backend körs på: http://localhost:8080

### Frontend Setup

1. Navigera till frontend-mappen:
```bash
cd frontend
```

2. Installera dependencies:
```bash
npm install
```

3. Starta development server:
```bash
npm start
```

Frontend körs på: http://localhost:3000

### API Endpoints

**Projects:**
- GET `/api/projects` - Hämta alla projekt
- GET `/api/projects/{id}` - Hämta specifikt projekt
- GET `/api/projects/featured` - Hämta featured projekt

**Skills:**
- GET `/api/skills` - Hämta alla skills
- GET `/api/skills/category/{category}` - Hämta skills per kategori

**Contact:**
- POST `/api/contact` - Skicka kontaktmeddelande

## Framtida Förbättringar

Även om portfolion är funktionell finns det många saker jag skulle vilja lägga till framöver:

### Kortsiktigt (närmaste månaderna)
- **Deployment** - Publicera portfolion live på Vercel/Railway
- **Riktiga projekt** - Lägga till fler projekt från utbildningen
- **Blog-funktion** - Skriva tekniska artiklar och dela lärdomar
- **CV-download** - Möjlighet att ladda ner mitt CV som PDF

### Långsiktigt (under första året som utvecklare)
- **Admin-panel** - Dashboard för att hantera projekt och skills
- **Dark mode** - Toggle mellan ljust och mörkt tema
- **Animations** - Smooth scroll-animationer och transitions
- **Testing** - Unit tests och integration tests
- **CI/CD** - Automatisk deployment via GitHub Actions
- **Analytics** - Se hur många som besöker portfolion
- **Internationalisering** - Stöd för flera språk (svenska/engelska)

## Projektstyrning

### GitHub Issues och Kanban

Projektet är organiserat med GitHub Issues som kopplas till en Kanban-board. Varje feature eller bug representeras av ett issue som flyttas genom kolumnerna:
- **Backlog** - Framtida features
- **Ready** - Redo att påbörjas
- **In Progress** - Pågående arbete
- **Done** - Färdigt

### Commit-meddelanden

Alla commits följer formatet:
```
Typ: Kort beskrivning (#issue-nummer)

Längre beskrivning av vad som gjorts
Varför det gjordes
Eventuella tekniska detaljer

Löser #issue-nummer
```

Detta gör det enkelt att följa projektets utveckling och förstå varför vissa beslut togs.

## Reflektion

Att bygga denna portfolio har varit en lärorik resa. Jag har inte bara förbättrat mina tekniska färdigheter inom React och Spring Boot, utan också lärt mig mycket om design, användarupplevelse, och vikten av god projektstyrning.

Den största lärdomen är att utveckling handlar om mer än att skriva kod. Det handlar om att:
- Förstå användaren och deras behov
- Göra genomtänkta tekniska val
- Dokumentera sitt arbete
- Kunna kommunicera sina beslut
- Vara ödmjuk inför att man alltid kan lära sig mer

Jag ser fram emot att fortsätta utveckla denna portfolio och använda den som en levande showcase för min utveckling som programmerare.

## Kontakt

**Gustav Nyberg**
- GitHub: [github.com/gustavnybergs](https://github.com/gustavnybergs)
- LinkedIn: [linkedin.com/in/gustavnyberg](https://linkedin.com/in/gustavnyberg)
- Email: gustavnybergs@gmail.com

---

**Examensarbete - JAVA24**  
**Stockholms Tekniska Institut**  
**Hösten 2024 - Våren 2025**
