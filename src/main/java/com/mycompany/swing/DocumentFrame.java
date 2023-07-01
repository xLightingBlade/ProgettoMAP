/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author santo
 */
public class DocumentFrame extends JFrame {

    private JLabel textLabel;
    private JScrollPane scrollPane;
    
    public DocumentFrame(String title, String text) {
        super(title);
        System.out.println("Sono entrato nel costruttore");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,500);         
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        

        String processText = text.replaceAll("[.;]", "$0<br>");
        
        textLabel = new JLabel("<html>"+processText+"</html>");
        textLabel.setHorizontalAlignment(JLabel.LEFT);
        textLabel.setVerticalAlignment(JLabel.TOP);
        textLabel.setForeground(Color.WHITE);
        //textLabel.setFont(new Font("Bodoni MT", Font.BOLD, 16));
        textLabel.setFont(new Font("Kristen ITC", Font.BOLD, 14));
        
        int spacing = 20;
        Border emptyBorder = new EmptyBorder(spacing, spacing, spacing, spacing);
        
        Color borderColor = Color.GRAY;
        int borderWidth = 2;
        Border lineBorder = BorderFactory.createLineBorder(borderColor, borderWidth);
        Border compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, lineBorder);
        textLabel.setBorder(compoundBorder);        
  
        
        JScrollPane scrollPane = new JScrollPane(textLabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.BLACK);
        
        
        add(scrollPane, BorderLayout.CENTER);    
        pack();
        System.out.println("Sto uscnedo dal costruttore");
        setVisible(true);
    }
   
    
    
    
    
     
    public static void main(String[] args) {
        String testo =  "Lionel Andrés Messi Cuccittini, detto Leo (pronuncia spagnola [ljoˈnɛl ˈmesi]; Rosario, 24 giugno 1987), è un calciatore argentino, centrocampista o attaccante del Paris Saint-Germain e della nazionale argentina, di cui è capitano, con la quale si è laureato campione del Sud America nel 2021 e campione del mondo nel 2022.\n" +
                        "\n" +
                        "Soprannominato la Pulga[2][3] (in italiano \"la Pulce\") per via della bassa statura e spesso paragonato al connazionale Diego Armando Maradona,[4][5][6] è ritenuto uno dei più forti calciatori di tutti i tempi[7][8][9][10] nonché da alcuni il migliore in assoluto.[11][12][13][14] È il secondo marcatore più prolifico della storia del calcio e il miglior realizzatore sudamericano delle nazionali di calcio.[15][16][17] I 43 trofei ufficiali vinti in carriera lo rendono il calciatore più titolato di sempre, a pari merito con Dani Alves.[18] Rientra, inoltre, nella lista dei calciatori con almeno 1000 presenze in carriera. Detiene il record di gol segnati in competizioni ufficiali sia in una singola stagione (82 nel 2011-2012) sia in un anno solare (91 nel 2012)[19] ed è il calciatore che detiene i record di gol (34) e di assist (15) in finali in competizioni ufficiali.[20] Figura inoltre al primo posto come miglior rifinitore della storia del calcio con più di 363 assist all'attivo.[21]\n" +
                        "\n" +
                        "Con 35 trofei vinti è il calciatore più decorato nella storia del Barcellona,[22] club del quale detiene anche il record di presenze, gol e assist.[23][24] Insieme a Luis Suárez e Neymar ha fatto parte del tridente (chiamato MSN dalle iniziali dei cognomi) più prolifico della storia del club[25] e del calcio spagnolo.[26][27] È primatista di gol e assist nella Liga.[28] Detiene il record assoluto di gol (50 nel 2011-2012)[29] e assist (21 nel 2019-2020)[30] realizzati in una singola stagione nei cinque principali campionati europei.\n" +
                        "\n" +
                        "Con la nazionale argentina, di cui è primatista di presenze e reti, ha partecipato alle edizioni del campionato del mondo comprese tra il 2006 e il 2022, – record –[31][32] e a sei della Copa América, edizioni comprese tra il 2007 e il 2021.\n" +
                        "\n"+
                        "A livello individuale si è aggiudicato sette volte il Pallone d'oro[33], sei volte la Scarpa d'oro, sette volte il premio come migliore giocatore al mondo FIFA[34] e tre volte quello di miglior giocatore UEFA.[35] La IFFHS lo ha eletto miglior calciatore del decennio 2011-2020 e per tre volte miglior marcatore internazionale dell'anno. Inoltre la rivista inglese World Soccer lo ha eletto per 6 anni World Player of The Year.";
        
        new DocumentFrame("Messi is the best player of all time", testo);
        
    }
}
