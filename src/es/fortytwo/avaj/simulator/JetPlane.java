package es.fortytwo.avaj.simulator;

// class JetPlane
// {
// +JetPlane(long p_id, string p_name, Coordinates p_coordinate)
// +void updateConditions()
// }
public class JetPlane extends Aircraft {
  public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
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
        this.coordinates.setLatitude(currentLatitude + 10);
        this.coordinates.setHeight(currentHeight + 2);
        System.out.println(this.getLogInfo() + ": Flying close to the sun");
        checkHeight();
        break;
      case "RAIN":
        currentLatitude = this.coordinates.getLatitude();
        this.coordinates.setLatitude(currentLatitude + 5);
        System.out.println(this.getLogInfo() + ": Raining sideways");
        break;
      case "FOG":
        currentLatitude = this.coordinates.getLatitude();
        this.coordinates.setLatitude(currentLatitude + 1);
        System.out.println(this.getLogInfo() + ": Is that a building?");
        break;
      case "SNOW":
        currentHeight = this.coordinates.getHeight();
        this.coordinates.setHeight(currentHeight - 7);
        System.out.println(this.getLogInfo() + ": It's all white?");
        checkHeight();
        break;
      default:
        break;
    }
  }
}
