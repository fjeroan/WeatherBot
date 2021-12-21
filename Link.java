import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import com.google.gson.*;

//creating the link class
public class Link
{
  private static HttpURLConnection connection;

  StringBuilder link;


  //variables for data

  //consturtoor
  Link(String city)
  {
    //creating the string
    link = new StringBuilder( "https://api.openweathermap.org/data/2.5/weather?q={city name}&appid=2590a00b9735652f65016c3698f4cc5c");
    
    //replacing the cityName w/ the user's city
    link.replace(50, 61, city); 

  }

  //getting the link
  public StringBuilder getLink() {
    return link;
  }

  //getting the acftual data
  public String getData()
  {

    BufferedReader reader;

    String line;
    
    StringBuffer responseContent = new StringBuffer();
    
    

    //for debugging
    System.out.println(link.toString());
    
    //converting the link to a string
    String linkStr = link.toString();

    //opening the url
    try{

        URL url = new URL(linkStr);
        connection= (HttpURLConnection) url.openConnection();
    
        connection.setRequestMethod("GET");
    
        connection.setConnectTimeout(5000);
    
        connection.setReadTimeout(5000);
    
        int status = connection.getResponseCode();
    
        //System.out.println(status);
    
        if(status > 299)
        {
          reader = new BufferedReader(new InputStreamReader(connection.getErrorStream() ) );
    
          while((line = reader.readLine()) != null)
          {
            responseContent.append(line+ "\n");
    
    
          }
    
          reader.close();
        }
        else
        {
          reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    
                        
            while((line = reader.readLine()) != null)
            {
               responseContent.append(line+ "\n");
            }
    
            reader.close();
    
        }
    
        
         return responseContent.toString();
    
      }
      catch(MalformedURLException e)
      {
        e.printStackTrace();
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
      finally{
        connection.disconnect();
      }

      return responseContent.toString();
  }

    //parsing function
    public Weather parseJson( String Json)
    {
        //parsing the Json string and converting it to a JsonObject
        JsonObject object = new JsonParser().parse(Json).getAsJsonObject();

      
        JsonObject main = object.getAsJsonObject("main");

        // Get the temperatures from main
        double temp = main.get("temp").getAsDouble();
        double high = main.get("temp_max").getAsDouble();
        double low = main.get("temp_min").getAsDouble();

        // Get weather from the object
        String weather = object.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();

        // Get the location from object
        String location = object.get("name").getAsString();

        // Return Weather object
        return new Weather(temp, high, low, weather, location);
    }



  
}