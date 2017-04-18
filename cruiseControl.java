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
    public JLabel engineStatus;
    
    private JButton cruiseOnOffButton;
    public JLabel cruiseOnOffStatus;
    
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
        currentSpeed.addActionListener(handler);
        ignitionButton.addActionListener(handler);
        cruiseOnOffButton.addActionListener(handler);
        
    }
    
    private class thehandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            
            systemStatus ss = new systemStatus();
           
            if(event.getSource()==cruiseOnOffButton){
                cruiseOnOff( false );
            } else if (event.getSource()==ignitionButton){
                ignition();
            } else if (event.getSource()==currentSpeed){
                //string=String.format("field 3: %s", event.getActionCommand());
            } 
            //JOptionPane.showMessageDialog(null, string);
        }
    }
    
    public class systemStatus{
        private String ignitionStatus = "off";
        private String cruiseControlStatus = "off";
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
        
        //Cruise Control Button
        public void setCruiseOnOffStatus(String i){
            cruiseControlStatus = i;
        }
        public String getCruiseOnOffStatus(){
            return cruiseControlStatus;
        }
    }
   
    public void ignition(){
        if( ss.getIgnitionStatus() == "off" ){
            //Turn on
            ss.setIgnitionStatus("on");
            engineStatus.setText( "on" );
            engineStatus.setForeground(Color.green);
        } else {
            //Turn off
            ss.setIgnitionStatus("off");
            engineStatus.setText( "off" );
            engineStatus.setForeground(Color.red);
            cruiseOnOff( true );
        }
    }
    
    public void cruiseOnOff(boolean overrideStatus){
        if( ss.getCruiseOnOffStatus() == "off" && ss.getIgnitionStatus() == "on" && !overrideStatus ){ //only turn on when engine is on
            //Turn on
            ss.setCruiseOnOffStatus("on");
            cruiseOnOffStatus.setText( "on" );
            cruiseOnOffStatus.setForeground(Color.green);
        } else {
            //Turn off
            ss.setCruiseOnOffStatus("off");
            cruiseOnOffStatus.setText( "off" );
            cruiseOnOffStatus.setForeground(Color.red);
        }
    }
    
}