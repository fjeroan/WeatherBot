import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import com.google.gson.*;

public class Kanye {

  //creating a string to store the content
  String content;

  //creating an HTTPURLConnection variable
  private static HttpURLConnection connection;

  //creatking a link variable
  private String link = "https://api.kanye.rest/";

  //creating a quote variable
  private String quote;

  Kanye()
  {
    //creating all the necessary things to get the data
     BufferedReader reader;

     String line;
     
     StringBuffer responseContent = new StringBuffer();
     
     

    //testing if the link works
     System.out.println(link.toString());
     
     //converting link to linkStr
     String linkStr = link.toString();

    //acutally opening the link and everythgin    
    try{

      URL url = new URL(linkStr);
      connection= (HttpURLConnection) url.openConnection();
     
      connection.setRequestMethod("GET");
     
      connection.setConnectTimeout(5000);
     
      connection.setReadTimeout(5000);
     
      int status = connection.getResponseCode();
     
     
      if(status > 299)
      {
       //creating a new bufferedreader
        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream() ) );
     
        while((line = reader.readLine()) != null)
        {
          responseContent.append(line+ "\n");

        }//end of loop
     
        reader.close();

      }//end of if
      //else if link actually works!!
      else
      {
        //creating a buffered reader
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
     
                         
        while((line = reader.readLine()) != null)
        {
          responseContent.append(line+ "\n");
        }
      
        reader.close();
     
      }

      //assigning the content from the stringBuilder
      content=  responseContent.toString();

      //outputting for debugging
      System.out.println(responseContent);

      //splitting the string
      //String [] tokenizedStr = content.split("[{}:\"]");

      KanyeParse(responseContent.toString());

      System.err.println(this.toString());

      
     
    }//end of try
     //catching statements
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


  }




  public String getQuote() {
    return this.quote;
  }

 // @Override
  public String toString() {
    // TODO Auto-generated method stub
    System.out.println("The wonderful and amazing Kanye quote you've gotten is: " + this.quote + ". Enjoy!!");
    return "The wonderful and amazing Kanye quote you've gotten is: " + this.quote + ". Enjoy!!";

  }


  public void KanyeParse(String json)
  {
    //parsing the Json string and converting it to a JsonObject
    JsonObject object = new JsonParser().parse(json).getAsJsonObject();
   
    // Get the location from object
    quote = object.get("quote").getAsString();



    


  }

    


}
  

