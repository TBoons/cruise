/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruise;

/**
 *
 * @author Boonstra
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class cruiseControl extends JFrame{
    private JTextField item1;
    private JTextField item2;
    private JTextField currentSpeed;
    private JLabel currentSpeedLabel;
    private JButton ignitionButton;
    private JLabel engineStatus;
    
    public cruiseControl(){
        super("Java Cruise Control");
        setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        
        systemStatus ss = new systemStatus();
        
        //item1 = new JTextField(10);
        //add(item1);
        
        //item2 = new JTextField("Enter text here");
        //add(item2);
        
        c.gridx = 0;
        c.gridy = 0;
        currentSpeedLabel = new JLabel("Current Speed:");
        add(currentSpeedLabel,c);
                
        c.gridx = 1;
        c.gridy = 0;
        
        currentSpeed = new JTextField(String.valueOf(ss.getCurrentSpeed()),5);
        currentSpeed.setEditable(false);
        add(currentSpeed,c);
        
        ignitionButton = new JButton("Ignition");
        c.gridx = 0;
        c.gridy = 1;
        add(ignitionButton,c);
        
        engineStatus = new JLabel("off");
        engineStatus.setForeground(Color.red);
        c.gridx = 1;
        c.gridy = 1;
        add(engineStatus,c);
        
        thehandler handler = new thehandler();
        
        //item1.addActionListener(handler);
        //item2.addActionListener(handler);
        currentSpeed.addActionListener(handler);
        
    }
    
    private class thehandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String string = "";
            
            if(event.getSource()==item1){
                //System.out.println(item1.getText());
                //string=String.format("field 1: %s", event.getActionCommand());
            } else if (event.getSource()==item2){
                //string=String.format("field 2: %s", event.getActionCommand());
            } else if (event.getSource()==currentSpeed){
                string=String.format("field 3: %s", event.getActionCommand());
            } 
            
            JOptionPane.showMessageDialog(null, string);
        }
    }
    
    private class systemStatus{
        private String ignitionStatus;
        private String cruiseControlStatus;
        private int currentSpeed = 60;
        private int cruiseControlSpeedSetting;
        
        //Igntion Status
        public void setIgnitionStatus(String i){
            ignitionStatus = i;
        }
        public String getIgnitionStatus(){
            return ignitionStatus;
        }
        
        //Current Speed
        public void setCurrentSpeed(int i){
            currentSpeed = i;
        }
        public int getCurrentSpeed(){
            return currentSpeed;
        }
        
    }
    
}