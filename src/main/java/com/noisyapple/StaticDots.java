package com.noisyapple;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Program's Frame.
@SuppressWarnings("serial")
public class StaticDots extends JFrame {

  // Keeps track of width and height from constructor.
  private int width, height;

  // Class constructor.
  public StaticDots(int width, int height) {

  }

  // Inner class, models a custom canvas where to draw.
  class CustomCanvas extends JPanel {

    // paint method inherited from Canvas, YOUR CAN DRAW HERE.
    public void paintComponent(Graphics g) {

    }

  }

}