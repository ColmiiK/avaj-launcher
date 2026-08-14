package es.fortytwo.avaj.simulator;

// class Balloon
// {
// +Balloon(long p_id, string p_name, Coordinates p_coordinate)
// +void updateConditions()
// }
public class Balloon extends Aircraft {
  public Balloon(long p_id, String p_name, Coordinates p_coordinate) {
    // call the constructor of the Aircraft,
    // otherwise Java will call the default constructor of the superclass
    super(p_id, p_name, p_coordinate);
  }

  public void updateConditions() {
    final String weather = weatherTower.getWeather(this.coordinates);
    int currentLongitude, currentLatitude, currentHeight;
    switch (weather) {
      case "SUN":
        currentLatitude = this.coordinates.getLatitude();
        currentHeight = this.coordinates.getHeight();
        this.coordinates.setLatitude(currentLatitude + 2);
        this.coordinates.setHeight(currentHeight + 4);
        System.out.println(this.getLogInfo() + ": It's always sunny in Malaga.");
        checkHeight();
        break;
      case "RAIN":
        currentHeight = this.coordinates.getHeight();
        this.coordinates.setHeight(currentHeight - 5);
        System.out.println(this.getLogInfo() + ": I love the smell of rain in the morning.");
        checkHeight();
        break;
      case "FOG":
        currentHeight = this.coordinates.getHeight();
        this.coordinates.setHeight(currentHeight - 3);
        System.out.println(this.getLogInfo() + ": Silent Hill vibes.");
        checkHeight();
        break;
      case "SNOW":
        currentHeight = this.coordinates.getHeight();
        this.coordinates.setHeight(currentHeight - 15);
        System.out.println(this.getLogInfo() + ": Don't eat the yellow snow.");
        checkHeight();
        break;
      default:
        break;
    }
  }
}
