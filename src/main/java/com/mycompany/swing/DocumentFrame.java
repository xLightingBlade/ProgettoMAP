/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swing;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900,500);         
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 77, 0));
        

        String processText = text.replaceAll("\n", "<br>");
        
        textLabel = new JLabel("<html><pre style='Press Gothic'>"+processText+"</pre></html>");
        textLabel.setHorizontalAlignment(JLabel.LEFT);
        textLabel.setVerticalAlignment(JLabel.TOP);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Press Gothic", Font.BOLD, 17));
        
        scrollPane = new JScrollPane(textLabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(new Color(0, 77, 0));
        
        add(scrollPane, BorderLayout.CENTER);    
        pack();
    }

    
    public JLabel getTextLabel() 
    {
        return textLabel;
    }  
}
