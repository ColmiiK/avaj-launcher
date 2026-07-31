package es.fortytwo.avaj.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulator {

  public static List<Flyable> parser(Scanner reader) throws Exception {
    List<Flyable> flyables = new ArrayList<>();
    AircraftFactory factory = AircraftFactory.getFactory();
    while (reader.hasNextLine()) {
      String line = reader.nextLine();
      String[] words = line.split(" ");
      Coordinates coordinates = new Coordinates(Integer.parseInt(words[2]), Integer.parseInt(words[3]),
          Integer.parseInt(words[4]));
      flyables.add(factory.newAircraft(words[0], words[1], coordinates));
    }
    return flyables;
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Incorrect usage: only one scenario.txt file allowed");
      return;
    }
    try {
      System.setOut(new PrintStream(new FileOutputStream("simulation.txt")));
      File scenario = new File(args[0]);
      Scanner reader = new Scanner(scenario);
      List<Flyable> flyables = new ArrayList<>();
      final int iterations = Integer.parseInt(reader.nextLine());
      try {
        if (iterations <= 0)
          throw new Exception("Number of iterations can't be negative");
        flyables = parser(reader);
      } catch (Exception e) {
        System.err.println("ERROR: " + e.getMessage());
      }
      reader.close();
      WeatherTower weatherTower = new WeatherTower();
      for (Flyable flyable : flyables) {
        weatherTower.register(flyable);
        flyable.registerTower(weatherTower);
      }
      for (int i = 0; i < iterations; i++) {
        weatherTower.changeWeather();
      }

    } catch (FileNotFoundException e) {
      System.err.println("File not found");
      e.printStackTrace();
    }
  }
}
