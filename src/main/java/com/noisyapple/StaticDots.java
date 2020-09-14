package com.noisyapple;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Program's Frame.
@SuppressWarnings("serial")
public class StaticDots extends JFrame {

  // Keeps track of width and height from constructor.
  private int width, height;

  // Control constants.
  private final int DOT_AMOUNT = 100;
  private final int DOT_MAX_RADIUS = 20;

  // Class constructor.
  public StaticDots(int width, int height) {
    this.width = width;
    this.height = height;

    // Frame configuration.
    this.setTitle("Static Dots");
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);

    // A new instance of inner class CustomCanvas is added to the frame.
    this.add(new CustomCanvas());

    // JFrame fits size to JPanel dimensions.
    this.pack();
    this.setLocationRelativeTo(null);
  }

  // Inner class, models a custom canvas where to draw.
  class CustomCanvas extends JPanel {

    // paintComponent method inherited from Canvas, YOUR CAN DRAW HERE.
    public void paintComponent(Graphics g) {

      // Graphics context g is then casted to Graphics2D.
      Graphics2D g2 = (Graphics2D) g;

      // Context configuration, antialiasing enabled for smoother graphics.
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Canvas background.
      g2.setColor(Color.decode("#000000"));
      g2.fillRect(0, 0, width, height);

      // Dots color.
      g2.setColor(Color.decode("#ffffff"));

      for (int i = 0; i < DOT_AMOUNT; i++) {
        int x = (int) ((Math.random() * width)); // Generates a x-coordinate value.
        int y = (int) ((Math.random() * height)); // Generates a y-coordinate value.
        int radius = (int) ((Math.random() * DOT_MAX_RADIUS) + 1); // Generates a radius value.

        // A dot is drawn.
        g2.fillOval(x, y, radius, radius);
      }

    }

    // getPreferredSize gets overriden to make JFrame fit JPanel size;
    public Dimension getPreferredSize() {
      return (new Dimension(width, height));
    }

  }

}