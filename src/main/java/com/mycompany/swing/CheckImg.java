/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swing;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author santo
 */
public class CheckImg 
{
    public static boolean isImage(String path) 
    { 
        try 
        { 
            return ImageIO.read(new File(path))!=null;   
        } 
        catch(IOException ex) 
        {                        
            return false;
        }
    }
}
