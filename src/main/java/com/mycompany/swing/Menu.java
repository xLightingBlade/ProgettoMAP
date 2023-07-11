/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.swing;
import com.mycompany.avventura.CaricamentoSalvataggioPartita;
import com.mycompany.avventura.Engine;
import com.mycompany.avventura.LoaderPrinterCharacterStream;
import com.mycompany.exception.ImgException;
import com.mycompany.gioco.Avventura;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author santo
 */
public class Menu extends javax.swing.JFrame 
{
    private OpzioniMenu sceltaUtente;
    //private JDialog manualeDialog;
    private DocumentFrame manualeUtente;
    
   
    public Menu(String pathImg, OpzioniMenu sceltaUtente) throws ImgException, IOException 
    {
        if(CheckImg.isImage(pathImg))
        {
            ImageIcon imgIcon = new ImageIcon(pathImg);
            initComponents();
            this.sceltaUtente = sceltaUtente;
            myInit(imgIcon);
        } 
        else 
        {
            throw new ImgException();           
        }
    }

    
    private void myInit(ImageIcon imgIcon) throws IOException 
    {
        setSize(1000, 600);

        jPanel1.setBorder(BorderFactory.createEmptyBorder());       // Rimuovi il bordo del pannello
        jPanel1.setOpaque(false);                                // Rendi il pannello trasparente
        jPanel1.setBackground(new Color(0, 0, 0, 0));            // Imposta il colore di sfondo del pannello su trasparente
    
        Image img = imgIcon.getImage();
        img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(img); 
    
        jPanel1.setLayout(new BorderLayout());
        JLabel backgroundLabel = new JLabel(imgIcon);
        jPanel1.add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.setBounds(0, 0, imgIcon.getIconWidth(), imgIcon.getIconHeight());
               
        setResizable(false);    // non è possibile ridimensionare il frame
        setLocationRelativeTo(null);  // il frame appare al centro del desktop, quindi non bisogna trascinarlo al centro con il cursore
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);   // centro il testo contenuto nella lable al centro della lable stessa
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
            
        initManualeUtente();
    }
    
    
    private void initManualeUtente() throws IOException
    {
        LoaderPrinterCharacterStream loader = new LoaderPrinterCharacterStream();
        manualeUtente = new DocumentFrame("Manuale utente",loader.ottieniComeTesto(".//resources//istruzioniGioco.txt"));
        manualeUtente.getTextLabel().setFont(new Font("Press Gothic", Font.BOLD, 15));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        avviaPartitaButton = new javax.swing.JButton();
        indietroButton = new javax.swing.JButton();
        caricaPartitaButton = new javax.swing.JButton();
        manualeUtenteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menù di gioco");
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));

        titleLabel.setBackground(new java.awt.Color(0, 0, 0));
        titleLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("The Last of Us");

        avviaPartitaButton.setBackground(new java.awt.Color(0, 0, 0));
        avviaPartitaButton.setForeground(new java.awt.Color(255, 255, 255));
        avviaPartitaButton.setText("Avvia partita");
        avviaPartitaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avviaPartitaButtonActionPerformed(evt);
            }
        });

        indietroButton.setBackground(new java.awt.Color(0, 0, 0));
        indietroButton.setForeground(new java.awt.Color(255, 255, 255));
        indietroButton.setText("Indietro");
        indietroButton.setToolTipText("Premendo questo tasto chiuderai l'applicazione.");
        indietroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indietroButtonActionPerformed(evt);
            }
        });

        caricaPartitaButton.setBackground(new java.awt.Color(0, 0, 0));
        caricaPartitaButton.setForeground(new java.awt.Color(255, 255, 255));
        caricaPartitaButton.setText("Carica partita");
        caricaPartitaButton.setToolTipText("Cliccando qui riprenderai a giocare dall'ultimo salvataggio effettuato");
        caricaPartitaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caricaPartitaButtonActionPerformed(evt);
            }
        });

        manualeUtenteButton.setBackground(new java.awt.Color(0, 0, 0));
        manualeUtenteButton.setForeground(new java.awt.Color(255, 255, 255));
        manualeUtenteButton.setText("Manuale utente");
        manualeUtenteButton.setToolTipText("cliccando qui si aprirà una finestra contenente le istruzioni presenti nel gioco con la relativa descrizione");
        manualeUtenteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualeUtenteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(398, 398, 398)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(423, 423, 423)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(indietroButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(avviaPartitaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(caricaPartitaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(manualeUtenteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(329, 329, 329))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176)
                .addComponent(avviaPartitaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(caricaPartitaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(manualeUtenteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(indietroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void avviaPartitaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avviaPartitaButtonActionPerformed
        sceltaUtente.setStatoCorrente(OpzioniMenu.Stato.AVVIA);
        dispose();
    }//GEN-LAST:event_avviaPartitaButtonActionPerformed

    private void indietroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indietroButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_indietroButtonActionPerformed

    
    private void caricaPartitaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caricaPartitaButtonActionPerformed
        // qui ci sarà la chiamata al metodo per ricaricare la partita
        
        CaricamentoSalvataggioPartita caricamento = new CaricamentoSalvataggioPartita();
        
        switch (caricamento.carica()) 
        {    
            //file partita salvata non trovato
            case 1 -> JOptionPane.showMessageDialog(null, "Nessuna partita salvata. Avviare una nuova partita.", "Partita non salvata",JOptionPane.ERROR_MESSAGE);
            
            //file partita salvata vuoto
            case 2 -> JOptionPane.showMessageDialog(null, "La partita salvata è vuota. Avviare una nuova partita.", "Partita salvata vuota",JOptionPane.ERROR_MESSAGE);
                
            //Errore nel caricamento del file della partita salvata    
            case 3 -> JOptionPane.showMessageDialog(null, "Errore nel caricamento della partita salvata.\n Riprova oppure avvia una nuova partita.", "Errore nel caricamento della partita salvata",JOptionPane.ERROR_MESSAGE);
        
            //partita caricata correttamente
            case 4 -> 
            {
                    sceltaUtente.setStatoCorrente(OpzioniMenu.Stato.CARICA);
                    dispose();                
            }
        }
    }//GEN-LAST:event_caricaPartitaButtonActionPerformed

    private void manualeUtenteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualeUtenteButtonActionPerformed
        manualeUtente.setVisible(true);
    }//GEN-LAST:event_manualeUtenteButtonActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        OpzioniMenu sceltaUtente = new OpzioniMenu();
        
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run()
            {
                try 
                {
                    new Menu(".//resources//img//sfondoMenuTheLastofUS.jpg", sceltaUtente).setVisible(true);
                } 
                catch (ImgException ex)
                {
                    System.out.println("errore immagine");
                } catch (IOException ex) {
                    System.out.println("eeerrr");
                }
            }
        });
        
        while(sceltaUtente.getStatoCorrente()==null) 
        {
        }
        
        switch(sceltaUtente.getStatoCorrente()) 
        {
            case AVVIA -> new Engine(new Avventura()).esegui();
                
            case CARICA -> 
            {
                CaricamentoSalvataggioPartita avvioPartitaSalvata = new CaricamentoSalvataggioPartita();
                avvioPartitaSalvata.carica();
                avvioPartitaSalvata.avviaPartitaSalvata();
            }
        }
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton avviaPartitaButton;
    private javax.swing.JButton caricaPartitaButton;
    private javax.swing.JButton indietroButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton manualeUtenteButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}