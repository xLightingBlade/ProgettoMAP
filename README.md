# ProgettoMAP

## Idee per implementare quello che chiede Pippo:

1)Uso dei DB: Creare una tabella di DB che contiene stanze, oggetti e comandi caricandone i dati da file csv: **FATTO**

2)Uso dei file: file di dialoghi, file csv sopraccitati, serializzazione per salvare lo stato della partita: **IN CORSO**

3)Uso dei thread: Nella sezione del passsaggio segreto, una volta che ti nascondi dalla guardia, si può usare un thread che faccia sparire la guardia dopo tot secondi: **IN CORSO**
Inoltre, uso dei thread per inizializzazre dal database mentre scorrono i dialoghi: **FATTO**

4)Uso dei socket/REST APIs: Possibili chiamate api per raccogliere dati come dati sulla zona attuale(meteo,data,ora ecc..). Pensare ad altri possibili usi: **TO DO**

5)Uso di SWING: menù principale, finestra per inserire i comandi, finestre per i dialoghi, vediamo un po' cosa fare poi.
Si è detto anche di usare swing per mostrare il contenuto di qualche documento che verrà trovato: **IN CORSO**

6)Lamba expressions: pensarci


'Enigmi' del gioco:
1) Nell'ospedale quando Joel accoltella Marlene rimane ferito e deve trovare un tesserino per poter aprire l'infermeria.
2) Nella metropolitana allagata bisogna trovare la zattera di legno per far attraversare Ellie
3) Nel passaggio segreto bisogna arrivare al cancello senza farsi vedere dalle guardie
4) Per aprire il cancello bisogna ripristinare la corrente e inserire un codice sul tastierino
5) Una volta ripristinata la corrente bisogna risolvere un'enigma numerico per avere il codice del tastierino (il testo dell'enigma si può mostrare come finestra swing)
