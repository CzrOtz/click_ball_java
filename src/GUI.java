import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GUI {
    private int frame_width = 800;
    private int frame_height = 500;

    private CirclePanel circlePanel;

    public void display() {
        JFrame frame = new JFrame("Swing GUI Example");
        circlePanel = new CirclePanel();

        frame.add(circlePanel);
        frame.setVisible(true);
        frame.setSize(frame_width, frame_height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //event listener
        circlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point mousePoint = e.getPoint();
                if (circlePanel.contains(mousePoint)) {
                    circlePanel.moveCircle();
                }
            }
        });



        // Start the timer to update the circle position
        Timer timer = new Timer(5, e -> {
            circlePanel.repaint();
        });
        timer.start();
    }

    private class CirclePanel extends JPanel {
        private static final int CIRCLE_SIZE = 50;
        private Point circleLocation;

        public CirclePanel() {
            setPreferredSize(new Dimension(frame_width, frame_height));
            setBackground(new Color(155, 155, 155));
            circleLocation = new Point(frame_width / 2, frame_height / 2); // Initial circle location
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(circleLocation.x, circleLocation.y, CIRCLE_SIZE, CIRCLE_SIZE);
        }

        public void moveCircle() { // this sets a new position for circle
            int newX = (int) (Math.random() * (getWidth() - CIRCLE_SIZE));
            int newY = (int) (Math.random() * (getHeight() - CIRCLE_SIZE));
            circleLocation.setLocation(newX, newY);
            repaint(); // Repaint the panel to show updated circle position
        }

        @Override
        public boolean contains(Point p) {
            int circleX = circleLocation.x + CIRCLE_SIZE / 2;
            int circleY = circleLocation.y + CIRCLE_SIZE / 2;
            double distance = Math.sqrt(Math.pow(p.getX() - circleX, 2) + Math.pow(p.getY() - circleY, 2));
            return distance <= CIRCLE_SIZE / 2;
        }
    }
}