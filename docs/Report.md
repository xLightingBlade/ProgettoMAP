# PROGETTO MAP 22_23
## 1. **Descrizione generale del caso di studio**

Benvenuti nell'avvincente mondo di "The last of us" (rivisitato), ambientato nel 2050, esattamente vent'anni dopo che un fungo noto come Cordyceps ha scatenato una pandemia globale che ha trasformato il 90% della popolazione mondiale in feroci creature assetate di sangue, simili a degli zombie. 

Mentre il mondo è stato devastato, gli ultimi sopravvissuti cercano rifugio nelle cosiddette ZQ (Zone Quarantena), aree murate in cui l'esercito impone una disciplina ferrea. 

In questa avventura, seguiremo Joel, un contrabbandiere che deve recuperare un prezioso carico di armi, che gli sono state sottratte da un truffatore di nome Robert, noto all'interno della ZQ di Boston;
Robert confessa a Joel che le sue armi sono state vendute alle luci. 

Chi sono le luci? Le luci sono un gruppo di resistenza
(da molti definiti terroristi) che si oppone all'oppressione dell'esercito e cerca di trovare una cura per la pandemia causata dal fungo. Saputo ciò, Joel
incontra il capo delle luci, una tale Marlene, e spiega l'accaduto per avere indietro le sue armi. Marlene propone a Joel un patto: deve portare fuori dalla ZQ,
in uno stabilimento delle luci a Nord, una bambina di 12 anni di nome Ellie. Joel decide, dopo un iniziale rifiuto, di accettare il patto e organizza così il viaggio..


### **Luoghi principali**:

1. ***Casa di Joel***: <br>Luogo iniziale del gioco, qui Joel deve cercare di costruire tutto l'armamento necessario per affrontare il viaggio con Ellie. 
La stanza principale è il soggiorno, che contiene oggetti come una pistole, bombe, coltello, torcia, bottiglie di vetro e cibo. Nella casa c'è anche un ripostiglio in cui si possono trovare munizioni per le armi e batterie(utili nel caso in cui durante il viaggio la torcia dovesse scaricarsi), mentre nel bagno ci sono alcool, garze e forbici, utili ad esempio per costruirsi dei kit medici.

2. ***Passaggio segreto sotto la ZQ***: <br>Joel e Ellie sono riusciti a uscire dalla ZQ attraverso un passaggio segreto, ma ora devono evitare le guardie. 
La stanza del passaggio segreto è un corridoio con nascondigli come rocce e casse. 
I due devono raggiungere l'uscita, la quale richiede l'uso di un tastierino elettronico che permette di aprire il cancello una volta indovinata la combinazione. 
Se però la corrente non è attivata, il tastierino non funziona. Infatti devono trovare una stanza in cui è possibile riattivare il quadro elettrico.

3. ***Metropolitana***: <br>Una volta usciti dal passaggio segreto, Joel ed Ellie vengono soppresi dalle guardie, dalle quali devono scappare.
Una volta fuggiti, i due devono attraversare una metropolitana allagata.
La sfortuna vuole però che Ellie non sappia nuotare, Joel deve dunque mettersi alla ricerca di un qualcosa su cui sia possibile trasportare Ellie.
All'uscita della metropolitana incontrano il fratello di Joel, Tommy, un ex soldato delle luci, che suggerisce loro di dirigersi al San Maries Hospital, dove si trova il quartier generale delle luci.

4. ***San Maries Hospital***: <br>All'entrata dell'ospedale Joel ed Ellie vengono attaccati dai soldati delle luci, che pattugliavano l'ingresso, poichè considerati invasori.
I due vengono tramortiti e svengono a terra. Dopo circa 2 ore Joel si risveglia nella sala d'attesa dell'ospedale insieme a Marlene, che comunica a Joel
di dover necessariamente operare Ellie al cervello per poter trovare una cura contro il fungo.<br>
Questa operazione comporterà la morte di Ellie, ovviamente Joel non vuole che ciò accada, ed inizia una colluttazione con Marlene.
Joel ha la meglio, e cercherà poi di trovare la stanza in cui stanno operando Ellie, per poter fuggire insieme a lei, prima però dovrà necessariamente curarsi.<br>
Una volta curatosi Joel sale al piano della sala operatoria, che però è pattugliata da tantissime guardie, quindi dovrà trovare un modo per aggirarle.<br>
Una volta oltrepassate le guardie Joel si dirige verso la sala operatoria dove trova Ellie completamente addormentata, circondata da dottori.<br>
Joel riesce a portare via Ellie dall'ospedale e grazie ad un'auto trovata nel parcheggio al piano terra Joel riesce a fuggire con Ellie salvandole la vita.
<br>
<br>

## 2. **Gameplay di gioco**
In questa avventuare testuale l'utente giocatore dovrà cercare di avanzare con la storia accedendo a delle stanze che si apriranno solo quando saranno risolti determinati enigmi.<br>
L'utente quindi deve cercare di raccogliere ed utilizzare tutti gli oggetti necessari per aprire le porte e poter avanzare con il gioco.<br>
Il tutto è contornato da una narrativa avvincente mostrata all'utente attraverso i dialoghi dei personaggi e da dei momenti di pericolo<br>
in cui si chiede al giocatore di intervenire tempestivamente per salvare la vita dei personaggi.<br>
Inoltre per rendere l'esperienza di gioco più comoda e divertente, l'utente ha a disposizione la possibilità di poter consultare un piccolo manuale utente,<br>
in cui è spiegato il funzionamente di tutti i comandi del gioco, e la possibilità di salvare la partita e riprenderla quando desidera. 
<br>
<br>

## 3. **Applicazione degli argomenti del corso**
Di seguito, una descrizione di come i vari argomenti del corso sono stati applicati a questo progetto:
1. ***File***: Innanzitutto, vengono usati dei file csv, presenti nella cartella `resources`, per caricare dati con cui creare delle tabelle in un database H2.
   Nella stessa cartella è anche presente un file denominato `stopwords`, usato nel progetto dal parser per ignorare le stopwords all'interno dei comandi.
   Inoltre, vi sono due cartelle: `the_last_of_us(storia)/Dialoghi` e `the_last_of_us(storia)/documenti_gioco(da raccogliere)`.
   La prima cartella presenta tutti i file di testo contenenti i dialoghi mostrati al primo ingresso in una stanza, al verificarsi di certe attività o in base alle condizioni climatiche
   di alcune stanze in particolare.
   La seconda cartella contiene file di testo che racchiudono il contenuto di alcuni documenti leggibili all'interno del gioco.

2. ***Database***: Per questo progetto è stato usato H2 in modalità embedded (tant'è che il database 'test' viene creato nella user home directory).
   Per il progetto, sono state create delle tabelle all'interno del database, denominate "STANZE", "OGGETTI", e "COMANDI", popolate con il contenuto dei sopraccitati
   file csv, usando la funzione csvread fornita da H2.
   Queste tabelle sono poi state interrogate ed il contenuto dei ResultSet è stato usato per popolare le liste di stanze, oggetti e comandi usate nel gioco.

3. ***Threads***: La classe `src/main/java/com/mycompany/avventura/CaricamentoDati.java` prende in input un BufferedStream e lo passa alla classe `src/main/java/com/mycompany/avventura/LoaderPrinterCharacterStream.java`
   deputata a leggere il contenuto del file di testo. In tutto ciò, CaricamentoDati estende Thread e nel suo metodo 'run' effettua la stampa del contenuto dei file di testo, in
   contemporanea all'inizializzazione dell'avventura.
   Oltre a questo, un metodo `gestioneTimer` all'interno di `Avventura.java` fa uso della classe TimerTask per creare una sorta di "quick time event", dove il giocatore deve inserire un certo
   comando entro un certo periodo di tempo per non finire in game over.

4. ***REST API***: Il package `src/main/java/com/mycompany/openweatherAPI` è creato allo scopo di poter effettuare chiamate API all'endpoint [api.openweathermap.org](api.openweathermap.org).
   Al suo interno si trovano le seguenti classi:
     1. La classe `MeteoForecastDTO`, classe che rappresenta l'oggetto contenente l'output di una chiamata API. Infatti, questa classe ha un metodo `parseJSON`, che prende in input il json
        ricevuto dalla chiamata API e lo trasforma in un oggetto MeteoForecastDTO.
     2. La classe `MeteoAPIController` possiede due metodi, uno per recuperare il meteo di una zona specificandone le coordinate e uno dove invece si specifica il nome di una città.
        In entrambi i casi si fa uso delle classi `URI` (per creare appunto l'URI da usare nella chiamata), `HTTPRequest` (per impostare i parametri della richiesta http), `HTTPClient`
        (il client che eseguirà la richiesta) e `HTTPResponse` (per ricevere la risposta http e prenderne poi il body).
        Questi metodi passano infine il body della risposta al metodo `parseJson`
     3. La classe `MeteoAPI` contiene un paio di metodi attualmente non usati che servono a mostrare la temperatura di una città o di coordinate. In più, possiede un metodo `getMeteoID` che
        effettua una chiamata API tramite il metodo `getMeteoCitta` di `MeteoAPIController` e dall'oggetto risultante prende l'attributo id.
        Questo attributo, che indica essenzialmente che tempo fa in quella località, viene usato poi, in alcune stanze, per stampare il contenuto di un file di testo, diverso a seconda dell'id meteo.

5. ***SWING***:Si è fatto uso delle funzionalità di SWING per alcuni aspetti del progetto:
     1. All'avvio del gioco, si apre il frame `Menu.java`. La schermata di menù permette al giocatore, tramite una serie di bottoni, di scegliere se iniziare una nuova partita, caricarne una esistente, consultare
     il manuale utente oppure chiudere il gioco.
     2. Quando si raccoglie un oggetto di tipo `Documento` oppure si chiede di visualizzare il manuale utente, viene attivato il frame `DocumentFrame.java`, visualizzandone il contenuto testuale in una finestra.
     3. Allo stesso modo, quando si raccoglie un oggetto di tipo `OggettoImmagine` (una foto), la foto viene visualizzata in una finestra, che è un frame `ImgJFrame.java`.
     4. Quando bisogna inserire la combinazione giusta in un tastierino per poter aprire un cancello, viene aperta una finestra, frame `TastierinoJFrame.java`, che ha dei campi JTextField dove inserire
        quella che si crede essere la combinazione di cifre giusta

6. ***Lambda expressions***: Al momento vi è un solo utilizzo delle lambda expressions: In `.//gioco/BehaviourController` il metodo `checkOggettoInventario`, per controllare se nell'inventario del
   giocatore compare un certo oggetto, apre uno stream di Oggetti su cui applica un'operazione terminale anyMatch, per controllare se nell'inventario esiste l'oggetto cercato

