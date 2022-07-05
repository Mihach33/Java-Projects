/**
 * @author Smilianets Mykhailo S20511
 */

package zad1;


import com.neovisionaries.i18n.CountryCode;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Service {
    private String country;

    public Service(String country) {
        this.country = country;
    }

    public String getWeather(String city) {
        URL linkToWeather = null;
        String currentWeather = "";
        try {
            String code = String.valueOf((CountryCode.findByName(country).get(0)));
            linkToWeather = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + code + "&appid=04c8bb809a4a1c383906bb50aae40265");
            URLConnection connection = linkToWeather.openConnection();
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            int a;
            while ((a = bf.read()) != -1) {
                currentWeather += bf.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentWeather;
    }


    public Double getRateFor(String kod_waluty) {
        String result = "{";
        String apiK = "ebe94ea5a96ebf42e782ec339784c0ee";
        String query = null;
        String currencyCode = getCurrency(country);
        try {
            query = "http://api.exchangeratesapi.io/v1/latest?access_key="
                    + apiK + "&base=" +
                    "&symbols=" + currencyCode + "," + kod_waluty;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            URL url = new URL(query);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            int a;
            while ((a = bf.read()) != -1) {
                result += bf.readLine();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsObject = new JSONObject(result);
        JSONObject money1 = jsObject.getJSONObject("rates");
        Double kurs = Double.parseDouble(String.valueOf(money1.get(kod_waluty))) / Double.parseDouble(String.valueOf(money1.get(currencyCode)));
        return Double.parseDouble(String.format("%.6f",kurs));
    }

    public String getWeather(String country,String city,String jsonObject){
        JSONObject json=new JSONObject("{"+jsonObject);
        return country+" "+city+" temp is: "+String.format("%.1f",(Double.parseDouble(String.valueOf(json.getJSONObject("main").get("temp")))-273.15))+" speed of wind is: "
                +json.getJSONObject("wind").get("speed")+" m/s";
    }

    public String getCountryCode(String country) {
        Map<String, String> list = new HashMap<>();
        Locale.setDefault(Locale.US);
        for (String isoCountry : Locale.getISOCountries()) {
            Locale l = new Locale("", isoCountry);
            list.put(l.getDisplayCountry(), isoCountry);
        }
        return list.get(country);
    }


    public Double getNBPRate() {
        Document doc = null;
        Document doc1 = null;
        try {
            doc = Jsoup.connect("http://www.nbp.pl/kursy/kursya.html").get();
            doc1 = Jsoup.connect("http://www.nbp.pl/kursy/kursyb.html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> firstList = new ArrayList<>();
        List<String> secondList = new ArrayList<>();
        getValutes(doc,firstList,secondList);
        getValutes(doc1,firstList,secondList);


        int idx=0;
        if(country.equals("Poland")){return 1d;}
        for (String s : firstList) {
            if(s.split(" ")[1].equals(getCurrency(country))){
                break;
            }else{idx++;}
        }

        double result=Double.parseDouble(secondList.get(idx).split(",")[0]+"."+secondList.get(idx).split(",")[1]);
        if(!firstList.get(idx).split(" ")[0].equals(1)){
            result=result/Double.parseDouble(firstList.get(idx).split(" ")[0]);
        }
        return 1/Double.valueOf(String.format("%.4f",result));
    }

    public void getValutes(Document doc,List<String> firstList,List<String> secondList){
        int i=2;
        while(doc.select("body > table:nth-child(2) > tbody > tr > td > center " +
                "> table.pad5 > tbody > tr:nth-child("+i+") > td:nth-child(2)").size()!=0){
            firstList.add(doc.select("body > table:nth-child(2) > tbody > tr > td > center " +
                    "> table.pad5 > tbody > tr:nth-child("+i+") > td:nth-child(2)").get(0).text());
            secondList.add(doc.select("body > table:nth-child(2) > tbody > tr > td > center > table.pad5 " +
                    "> tbody > tr:nth-child("+i+") > td:nth-child(3)").get(0).text());
            i++;
        }
    }

    public String getCurrency(String country){
        return  String.valueOf(Currency.getInstance(new Locale("", getCountryCode(country))));
    }

}
