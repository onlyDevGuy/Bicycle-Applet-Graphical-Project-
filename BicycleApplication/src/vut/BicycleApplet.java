package vut;

/*
 * Initials: Sizwe
 * Surname: Ramokhali
 * Student Number: 224058967
 * Class Group: C5
 *
 *
 * Testing coordinates used:
 * Initial bike position: x = 90, y = 200
 * Test move to: x = 120, y = 210
 * Test move to: x = 150, y = 180
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class BicycleApplet extends Applet {

    private Bicycle bicycle;
    private boolean showBike;
    private int startX;
    private int startY;

    // CONSTRUCTOR — input and instantiation happens here
    public BicycleApplet() {
        showBike = false;
        startX = Integer.parseInt(JOptionPane.showInputDialog("Enter bicycle rear wheel centre X coordinate (recommended: 90):"));
        startY = Integer.parseInt(JOptionPane.showInputDialog("Enter bicycle rear wheel centre Y coordinate (recommended: 200):"));
        bicycle = new Bicycle(startX, startY);

        // REGISTER EVENT
        this.addMouseListener(new MyEventHandler());
    }

    @Override
    public void init() {
        setSize(500, 300);
        setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (showBike) {
            bicycle.drawBike(g2);
        }
    }

    // INNER CLASS — mouse event handler
    private class MyEventHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {

            // MIDDLE button — change bike color to magenta
            if (me.getButton() == MouseEvent.BUTTON2) {
                bicycle.setBodyColor(Color.MAGENTA);
                repaint();
            }

            // RIGHT button — move bike to new coordinates
            if (me.getButton() == MouseEvent.BUTTON3) {
                int newX = Integer.parseInt(JOptionPane.showInputDialog("Enter new X coordinate (recommended: 120-150):"));
                int newY = Integer.parseInt(JOptionPane.showInputDialog("Enter new Y coordinate (recommended: 180-210):"));
                bicycle.setPosition(newX, newY);
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
            // Show bike on ANY mouse button press
            if (!showBike) {
                showBike = true;
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) { }

        @Override
        public void mouseEntered(MouseEvent me)  { }

        @Override
        public void mouseExited(MouseEvent me)   { }
    }
}