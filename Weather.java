import javax.lang.model.util.ElementScanner14;

public class Weather {

  private double temp;
  private double high;
  private double low;
  private String weather;
  private String location;

  
  //if user enters weather
  Weather( double temp_, double high_, double low_, String weather_, String location_)
  {


    //assigning all the data to the variables
    temp = temp_;

    high = high_;

    low = low_;

    weather = weather_;

    location = location_;
    

  }

  //converts kelvins to faherenheit
  public double convertToF(double temp)
  {
    return (temp - 273.15) * (9.0/5.0) + 32;
  }

  //getter functions
  public double getHigh() {
    return high;
  }

  public double getLow() {
    return low;
  }

  public double getTemp() {
    return temp;
  }

  public String getLocation() {
    return location;
  }
  public String getWeather() {
    return weather;
  }


  //setter functions
  public void setHigh(double high) {
    this.high = high;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setLow(double low) {
    this.low = low;
  }

  public void setTemp(double temp) {
    this.temp = temp;
  }

  public void setWeather(String weather) {
    this.weather = weather;
  }

  
  //toString function
  public String toString()
  {
    if(this.weather.equals("Rain"))
    {
      return String.format("You ask and you shall recieve!! The weather in " + this.location + " is " + this.weather + ", You should probably pack an umbrella! The temperature is %.2f, with a high of %.2f and a low of %.2f.", this.convertToF(temp), this.convertToF(high), this.convertToF(low));
    }
    else if (this.weather.equals("Snow"))
    {
      return String.format("You ask and you shall recieve!! The weather in " + this.location + " is " + this.weather + ". Brrrr, it's cold outside, you should pack a jacket! The temperature is %.2f, with a high of %.2f and a low of %.2f.", this.convertToF(temp), this.convertToF(high), this.convertToF(low));

    }
    else 
    {
      return String.format("You ask and you shall recieve!! The weather in " + this.location + " is " + this.weather + ". The temperature is %.2f, with a high of %.2f and a low of %.2f.", this.convertToF(temp), this.convertToF(high), this.convertToF(low));

    }
  }

  
  
}
