# WebApplication-lab1

Un’applicazione web prevede una parte pubblica, accessibile a tutti, ed un privata soggetta a
verifica delle credenziali.
Nella parte pubblica è presente una sezione (che risponde alla URL /register) all’interno della
quale un utente può registrarsi, fornendo un insieme di dati quali nome, cognome, indirizzo email,
password prescelta (inserita due volte per verifica) e consenso di accettazione delle
norme sulla privacy. 

I vari campi sono soggetti a vincoli di lunghezza minima e. massima e a
correttezza formale nel caso dell’indirizzo e-mail. Compilando tale modulo, i dati sono inviati
al server che verifica il rispetto dei vincoli, l’uguaglianza delle due password, l’espressione del
consenso sulla privacy, l’assenza di utenti con la stessa password già registrati sul sistema. In
caso positivo aggiunge alla lista degli utenti che tiene internamente (in una mappa singleton
contenuta in memoria – senza per il momento usare una base dati) un record con i dettagli
dell’utente e comunica che la registrazione è andata a buon fine. In caso di errore, rimanda
alla pagina di registrazione, popolando i diversi campi del modulo con i valori
precedentemente riempiti dall’utente, indicando i diversi tipi di errore che si sono verificati.

Nella parte pubblica è presente anche una pagina di accesso (URL: /login) alla parte privata.
Compilando correttamente il modulo con l’indirizzo e-mail e la password scelti in fase di
registrazione, il server abilita l’accesso alla parte privata (ci si limiti a verificare la presenza
delle credenziali nella mappa degli utenti, senza preoccuparsi di ricordare, lato server, l’esito
della verifica). Se le credenziali sono errate, si rimane sulla pagina di login.
