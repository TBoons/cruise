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
    private final int pedalIncrement = 5;
    
    private JButton ignitionButton;
    public JLabel engineStatus;
    
    private JButton cruiseOnOffButton;
    public JLabel cruiseOnOffStatus;
    
    private JButton gasPedal;
    private JButton brakePedal;
    
    private systemStatus ss = new systemStatus();
    
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
        currentSpeed = new JTextField( String.valueOf(ss.getCurrentSpeed()), 5);
        currentSpeed.setEditable(false);
        add(currentSpeed,c);
        
        c.gridx = 2;
        c.gridy = 0;
        gasPedal = new JButton("GAS");
        add(gasPedal);
        
        c.gridx = 3;
        c.gridy = 0;
        brakePedal = new JButton("BRAKE");
        add(brakePedal);
        
        ignitionButton = new JButton("Ignition");
        c.gridx = 0;
        c.gridy = 1;
        add(ignitionButton,c);
        
        engineStatus = new JLabel( "off" ); //start off
        engineStatus.setForeground(Color.red);
        c.gridx = 1;
        c.gridy = 1;
        add(engineStatus,c);
        
        cruiseOnOffButton = new JButton("Cruise");
        c.gridx = 0;
        c.gridy = 2;
        add(cruiseOnOffButton,c);
        
        cruiseOnOffStatus = new JLabel( "off" ); //start off
        cruiseOnOffStatus.setForeground(Color.red);
        c.gridx = 1;
        c.gridy = 2;
        add(cruiseOnOffStatus,c);
        
        thehandler handler = new thehandler();
        
        //item1.addActionListener(handler);
        //item2.addActionListener(handler);
        //currentSpeed.addActionListener(handler);
        ignitionButton.addActionListener(handler);
        cruiseOnOffButton.addActionListener(handler);
        gasPedal.addActionListener(handler);
        brakePedal.addActionListener(handler);
    }
    
    private class thehandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            
            if(event.getSource()==cruiseOnOffButton){
                cruiseOnOff( false );
            } else if (event.getSource()==ignitionButton){
                ignition();
            } else if (event.getSource()==gasPedal){
                gasPedalPressed();
            } else if (event.getSource()==brakePedal){
                brakePedalPressed();
            } 
        }
    }
    
    public class systemStatus{
        private String ignitionStatus = "off";
        private String cruiseControlStatus = "off";
        private int currentSpeed = 0;
        private int cruiseControlSpeedSetting;
        
        //Igntion Status
        public void setIgnitionStatus(String i){
            ignitionStatus = i;
            updateStatusDisplay();
        }
        public String getIgnitionStatus(){
            return ignitionStatus;
        }
        
        //Current Speed
        public void setCurrentSpeed(int i){
            currentSpeed+= i;
            if( currentSpeed < 0 )
                currentSpeed = 0;
            
            if( currentSpeed > 100 )
                currentSpeed = 100;
            updateStatusDisplay();
        }
        public int getCurrentSpeed(){
            return currentSpeed;
        }
        
        //Cruise Control Button
        public void setCruiseOnOffStatus(String i){
            cruiseControlStatus = i;
            updateStatusDisplay();
        }
        public String getCruiseOnOffStatus(){
            return cruiseControlStatus;
        }
    }
    
    public void updateStatusDisplay(){
        //Engine
        String engineState = ss.getIgnitionStatus();
        engineStatus.setText( engineState );
        if( engineState == "on" )
             engineStatus.setForeground(Color.green);
        else
            engineStatus.setForeground(Color.red);
        
        //Cruise
        String cruiseState = ss.getCruiseOnOffStatus();
        cruiseOnOffStatus.setText(cruiseState);
        if( cruiseState == "on" )
             cruiseOnOffStatus.setForeground(Color.green);
        else
            cruiseOnOffStatus.setForeground(Color.red);
        
        //Speed
        int speedState = ss.getCurrentSpeed();
        currentSpeed.setText( String.valueOf(speedState) );
    }
   
    public void ignition(){
        if( ss.getIgnitionStatus() == "off" ){
            //Turn on
            ss.setIgnitionStatus("on");
           
        } else {
            //Turn off
            ss.setIgnitionStatus("off");
            cruiseOnOff( true );//Car is off... Turn off Cruise Control
        }
    }
    
    public void cruiseOnOff(boolean overrideStatus){
        if( ss.getCruiseOnOffStatus() == "off" && ss.getIgnitionStatus() == "on" && !overrideStatus ){ //only turn on when engine is on
            //Turn on
            ss.setCruiseOnOffStatus("on");
        } else {
            //Turn off
            ss.setCruiseOnOffStatus("off");
        }
    }
    
    public void gasPedalPressed(){
        ss.setCurrentSpeed(pedalIncrement);
    }
    public void brakePedalPressed(){
       ss.setCurrentSpeed(-pedalIncrement);
    }
}