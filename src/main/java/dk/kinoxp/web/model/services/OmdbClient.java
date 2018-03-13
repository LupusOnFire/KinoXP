package dk.kinoxp.web.model.services;

import dk.kinoxp.web.config.Constants;
import org.jsoup.Jsoup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OmdbClient
{
    String url;
    String key;

    public OmdbClient() {
        this.url = Constants.OMDB_URL;
        this.key = Constants.OMDB_KEY;
    }

    public String getJsonBodyByMovieTitle(String title){

        try {

            URL requestUrl = new URL(url + "?apikey=" + key + "&t=" + title.replace(" ", "+"));

            HttpURLConnection con = (HttpURLConnection) requestUrl.openConnection();

            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;

            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();


            System.out.println(content);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
