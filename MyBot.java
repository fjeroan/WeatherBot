import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.lang.model.util.ElementScanner14;

import org.jibble.pircbot.*;

public class MyBot extends PircBot {
    
    public MyBot() {
        this.setName("KanyeBot");
    }

    HttpURLConnection connection;


    //send message to user
    public void onMessage(String channel, String sender, String login, String hostname, String message) {

    //if user enters time
    if (message.equalsIgnoreCase("Time")) {
      String time = new java.util.Date().toString();
      sendMessage(channel, sender + ": The time is now " + time);
    }
    //if user enters Hello
    else if(message.contains("Hello"))
    {
      sendMessage(channel, "Wassup " + sender + "!" );
      sendMessage(channel, "Enter Help to see what I can do!");
    }
    //if user contains Help
    else if (message.contains("Help"))
    {
      sendMessage(channel, "Hiya! I see you requested help: Here's everything I can do");
      sendMessage(channel, "Time- I can output the time" );
      sendMessage(channel, "Any city name - I can output the weather for that city");
      sendMessage(channel, "Kanye - I output a random Kanye quote");
    }
    //if user enters Kanye
    else if (message.contains("Kanye"))
    {

      Kanye k = new Kanye();

      sendMessage(channel, k.toString());



    }
    //else if user enters a city name
    else 
    {

      Link weatherLink = new Link(message);

      Weather weather = weatherLink.parseJson(weatherLink.getData());

      sendMessage(channel, weather.toString());
    }
  }










   


    

}