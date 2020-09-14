package com.noisyapple;

// App Class.
public class App {

  // Main method.
  public static void main(String[] args) {

    // Args handling.
    if (args.length > 0)
      switch (args[0]) {
        case "static":
          new StaticDots(500, 500);
          break;
        case "moving":
          new MovingDots(500, 500);
          break;
      }
    else
      System.out.println("No arguments entered, available: \"static\", \"moving\"");

  }

}
