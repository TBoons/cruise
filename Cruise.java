/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruise;

/**
 *
 * @author Tim Boonstra
 */

import javax.swing.JFrame;

public class Cruise {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cruiseControl cruiseControlDisplay = new cruiseControl();
        cruiseControlDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cruiseControlDisplay.setSize(600,400);
        cruiseControlDisplay.setVisible(true);
        cruiseControlDisplay.setLocationRelativeTo(null);
    }
}

