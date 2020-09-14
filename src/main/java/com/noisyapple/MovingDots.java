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
public class MovingDots extends JFrame {

  // Keeps track of width and height from constructor.
  private int width, height;

  // Configuration constants
  private int UPDATE_RATE = 60;
  private final int DOT_AMOUNT = 100;
  private final int DOT_MAX_RADIUS = 20;
  private final float SPEED_LIMIT = 2.5f;

  // Dots array;
  private Dot[] dots = new Dot[DOT_AMOUNT];

  // Class constructor.
  public MovingDots(int width, int height) {
    this.width = width;
    this.height = height;

    // Dots inside dots array are initialized.
    for (int i = 0; i < dots.length; i++)
      dots[i] = new Dot();

    // Frame configuration.
    this.setTitle("Moving Dots");
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);

    // A new instance of inner class CustomCanvas is added to the frame.
    this.add(new CustomCanvas());

    // JFrame fits size to JPanel dimensions.
    this.pack();
    this.setLocationRelativeTo(null);

    // Starts draw loop.
    startLoop();
  }

  // Draw loop gets started in a new Thread.
  public void startLoop() {
    Thread drawLoop = new Thread() {
      public void run() {
        while (true) { // Infinite loop.
          repaint(); // Components inside JFrame gets repainted.
          try {
            Thread.sleep(1000 / UPDATE_RATE); // Delay between iterations.
          } catch (InterruptedException ex) {
          }
        }
      }
    };

    drawLoop.start();
  }

  // Inner class, models a custom canvas where to draw.
  class CustomCanvas extends JPanel {

    // paint method inherited from Canvas, YOUR CAN DRAW HERE.
    public void paintComponent(Graphics g) {

      // Graphics context g is then casted to Graphics2D.
      Graphics2D g2 = (Graphics2D) g;

      // Context configuration, antialiasing enabled for smoother graphics.
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Canvas background.
      g2.setColor(Color.decode("#000000"));
      g2.fillRect(0, 0, width, height);

      for (Dot dot : dots) {
        dot.draw(g2); // Dot gets displayed.
        dot.update(); // Dot position gets updated.
      }

    }

    // getPreferredSize gets overriden to make JFrame fit JPanel size;
    public Dimension getPreferredSize() {
      return (new Dimension(width, height));
    }

  }

  // Dot class.
  public class Dot {

    private int x, y;
    private int radius;
    private float xSpeed = 0.5f;
    private float ySpeed = 0.1f;
    private float xAcceleration;
    private float yAcceleration;
    private Color color;

    // Class constructor.
    public Dot() {
      this.x = (int) ((Math.random() * width)); // Generates a x-coordinate value.
      this.y = (int) ((Math.random() * height)); // Generates a y-coordinate value.
      this.radius = (int) ((Math.random() * DOT_MAX_RADIUS) + 1); // Generates a radius value.

      float hue = (float) Math.random() * 360; // Generates a random hue value;

      this.color = Color.getHSBColor(hue, 0.4f, 1f);
    }

    public void update() {
      // Random acceleration between -1 and 1.
      xAcceleration = ((float) Math.random() * 2) - 1;
      yAcceleration = ((float) Math.random() * 2) - 1;

      // New Speed.
      xSpeed += xAcceleration;
      ySpeed += yAcceleration;

      // Speed gets limited.
      if (xSpeed > SPEED_LIMIT)
        xSpeed = SPEED_LIMIT;
      if (xSpeed < -SPEED_LIMIT)
        xSpeed = -SPEED_LIMIT;
      if (ySpeed > SPEED_LIMIT)
        ySpeed = SPEED_LIMIT;
      if (ySpeed < -SPEED_LIMIT)
        ySpeed = -SPEED_LIMIT;

      // New position.
      x += xSpeed;
      y += ySpeed;

      // Wall collitions handler.
      if (x >= width)
        x = 0;
      if (y >= height)
        y = 0;
      if (x < 0)
        x = width - 1;
      if (y < 0)
        y = height - 1;
    }

    // Draw function.
    public void draw(Graphics2D g2) {
      g2.setColor(color);
      g2.fillOval(x, y, radius, radius);
    }

    // Printable data.
    @Override
    public String toString() {
      return "X => " + x + " Y => " + y + " R => " + radius;
    }

  }

}
