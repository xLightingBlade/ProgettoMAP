/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.swing;
import com.mycompany.exception.ImgException;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author santo
 */
public class ImgJFrame extends javax.swing.JFrame {
    
    public ImgJFrame(String pathImg) throws ImgException {       
        this(pathImg, "");
    }
    
    
    public ImgJFrame(String pathImg, String toolTipText)  throws ImgException { 
        if(CheckImg.isImage(pathImg)) {
            ImageIcon imgIcon = new ImageIcon(pathImg);  
            initComponents();
            myInit(imgIcon, toolTipText);
        } else {
            throw new ImgException();
        }
    }

    
    private void myInit(ImageIcon imgIcon, String toolTipText) {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);     //DISPOSE_ON_CLOSE chiude solo il frame corrente e libera le risorse associate ad esso, ma l'esecuzione del programma continua normalmente.      
        setSize(1200, 800);
        setResizable(false);    
        setLocationRelativeTo(null);
        
        JLabel imgLabel = new JLabel(imgIcon);
        imgLabel.setSize(getWidth(), getHeight());
        if(!toolTipText.isEmpty())
            imgLabel.setToolTipText(toolTipText);
        getContentPane().add(imgLabel, BorderLayout.CENTER);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ImgJFrame(".//resources//img//fotoSoggiorno960x660.jpg", "").setVisible(true);
                } catch (ImgException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });                
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

