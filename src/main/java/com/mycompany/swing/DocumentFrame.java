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

public class DocumentFrame extends JFrame 
{
    private final JLabel textLabel;
    private JScrollPane scrollPane;
    
    public DocumentFrame(String title, String text) 
    {
        super(title);
        //System.out.println("Sono entrato nel costruttore");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900,500);         
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        

        String processText = text.replaceAll("[.;:]", "$0<br>");
        
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
        
        scrollPane = new JScrollPane(textLabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(Color.BLACK);
        
        add(scrollPane, BorderLayout.CENTER);    
        pack();
        //System.out.println("Sto uscnedo dal costruttore");
        setVisible(true);
    }
}
