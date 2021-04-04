package src.Connect;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.image.BufferedImage;


public class test_Geocode {
    private static String googleAPIURL = "https://maps.googleapis.com/maps/api/";
    private static String APIKey = "AIzaSyAIWO9sXdB28-df7Kb4MlwWF3UoP1HQohU";

    public static double[] Geocode(String country, String city, String address){

        String country_format = "+" + country.replace(" ", "+");

        String city_format = "+" + city.replace(" ","+");

        String address_format = address.replace(" ", "+");
        String full_address = address_format + "," + city_format + "," + country_format;

        String full_URL = googleAPIURL + "geocode/json?address=" + full_address + "&key="+ APIKey;

        String latitude = "", longitude = "";
        double lat_fl = 0, long_fl = 0;
        //System.out.println(full_address);
        URL url_google;
        HttpsURLConnection conn;

        try{
            url_google = new URL(full_URL);
        } catch(MalformedURLException e){
            e.printStackTrace();
            return null;
        }

        //System.out.println("Response");
        try{
            conn = (HttpsURLConnection)url_google.openConnection();
            InputStream is = conn.getInputStream();

            
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String inputLine;

            while((inputLine = br.readLine()) != null) {
                if(inputLine.contains("\"location\"")){
                    latitude = br.readLine();
                    longitude = br.readLine();
                }
                // System.out.println(inputLine);
            }
            
            latitude = latitude.split(":")[1];
            latitude = latitude.split(",")[0];
            lat_fl = Double.parseDouble(latitude);

            longitude = longitude.split(":")[1];
            long_fl = Double.parseDouble(longitude);
            //System.out.println(lat_fl+"\n"+long_fl);
            return new double[] {lat_fl, long_fl};
            
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage staticMap(String country, String city, String address){
        String country_format = "+" + country.replace(" ", "+");

        String city_format = "+" + city.replace(" ","+");

        String address_format = address.replace(" ", "+");
        String full_address = address_format + "," + city_format + "," + country_format;

        String full_URL = googleAPIURL + "staticmap?center=" + full_address + "&markers=" + full_address +"&zoom=13&maptype=roadmap&size=500x400&key="+ APIKey;

        URL url_google;
        HttpsURLConnection conn;

        try{
            url_google = new URL(full_URL);
        } catch(MalformedURLException e){
            e.printStackTrace();
            return null;
        }

        try{
            conn = (HttpsURLConnection)url_google.openConnection();
            InputStream is = conn.getInputStream();
            ByteArrayInputStream baos = new ByteArrayInputStream(is.readAllBytes());
            BufferedImage bi = ImageIO.read(baos);
            // System.out.println("Returning Image");
            return bi;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage staticMap(double lat_1, double long_1, double lat_2, double long_2){

        double lat_long[] = new double[] {0,0};
        lat_long[0] = (lat_1 + lat_2) / 2;
        lat_long[1] = (long_1 + long_2) / 2; 

        String address1 = lat_1 + "," + long_1;
        String address2 = lat_2 + "," + long_2; 

        String full_URL = googleAPIURL + "staticmap?markers=" + address1 + "|" + address2 + "&maptype=roadmap&size=500x400&key="+ APIKey;

        URL url_google;
        HttpsURLConnection conn;

        try{
            url_google = new URL(full_URL);
        } catch(MalformedURLException e){
            e.printStackTrace();
            return null;
        }

        try{
            conn = (HttpsURLConnection)url_google.openConnection();
            InputStream is = conn.getInputStream();
            ByteArrayInputStream baos = new ByteArrayInputStream(is.readAllBytes());
            BufferedImage bi = ImageIO.read(baos);
            System.out.println("Returning Image");
            return bi;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage staticMap(double[] destination1, double[] destination2, ArrayList<Double> pathPoints){
        if(destination1.length != 2){
            System.out.println("Destination 1 is not valid.");
            return null;
        }
        if(destination2.length != 2){
            System.out.println("Destination 2 is not valid.");
            return null;
        }
        if(pathPoints.size() % 2 != 0){
            System.out.println("Path points are not valid.");
            return null;
        }

        
        String address1 = destination1[0] + "," + destination1[1];
        String address2 = destination2[0] + "," + destination2[1]; 

        String path = "";

        for(int i = 0; i < pathPoints.size(); i += 2){
            path += "|"+pathPoints.get(i)+","+pathPoints.get(i+1);
        }

        String full_URL = googleAPIURL + "staticmap?markers=" + address1 + "|" + address2 + "&maptype=roadmap&size=500x400&path=color:0x0000ff|weight:5"+path+"&key="+ APIKey;

        URL url_google;
        HttpsURLConnection conn;

        try{
            url_google = new URL(full_URL);
        } catch(MalformedURLException e){
            e.printStackTrace();
            return null;
        }

        try{
            conn = (HttpsURLConnection)url_google.openConnection();
            InputStream is = conn.getInputStream();
            ByteArrayInputStream baos = new ByteArrayInputStream(is.readAllBytes());
            BufferedImage bi = ImageIO.read(baos);
            System.out.println("Returning Image");
            return bi;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
