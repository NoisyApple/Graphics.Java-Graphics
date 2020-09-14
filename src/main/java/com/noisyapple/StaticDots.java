package com.noisyapple;

import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Program's Frame.
@SuppressWarnings("serial")
public class StaticDots extends JFrame {

  // Keeps track of width and height from constructor.
  private int width, height;

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

    // paint method inherited from Canvas, YOUR CAN DRAW HERE.
    public void paintComponent(Graphics g) {
    }

    // getPreferredSize gets overriden to make JFrame fit JPanel size;
    public Dimension getPreferredSize() {
      return (new Dimension(width, height));
    }

  }

}