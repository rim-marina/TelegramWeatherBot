import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    //88d3c470b46106e76538b04be229a33d
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=88d3c470b46106e76538b04be229a33d");
        // для извлечения содержимого
        Scanner in = new Scanner((InputStream) url.getContent());    //читаем содержимое
        String result = "";         //переменная результата
        while(in.hasNext()) {        //циклом проходим по сканеру
            result += in.nextLine();
        }
        JSONObject jsonObject = new JSONObject(result);     //создаем объект из файла json
        model.setName(jsonObject.getString("name"));

        JSONObject main = jsonObject.getJSONObject("main"); //маленький объект с данными main
        model.setTemperatureInTheCity(main.getDouble("temp"));
        model.setHumidityInTheCity(main.getDouble("humidity"));

        JSONArray jsonArray = jsonObject.getJSONArray("weather");       //создаем объект с данными массива
        for (int i=0; i<jsonArray.length();i++){        //проходим по массиву weather
            JSONObject obj = jsonArray.getJSONObject(i);
            model.setUrlToTheWeatherIcon((String) obj.get("icon"));
            model.setJsonWithWeatherData((String) obj.get("main"));
        }
        return "Город: " + model.getName() + "\n" +
                "Температура: " + model.getTemperatureInTheCity() + "C" + "\n" +
                "Влажность: " + model.getHumidityInTheCity() + "%" + "\n"
//                "https://openweathermap.org/img/wn/" + model.getUrlToTheWeatherIcon() + ".png"
                ;
    }
}
