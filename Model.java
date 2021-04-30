public class Model {

    private String city;
    private Double temperatureInTheCity;
    private Double humidityInTheCity;
    private String urlToTheWeatherIcon;
    private String jsonWithWeatherData;

    public String getName() {return city;}

    public void setName(String name) {this.city = name;}

    public Double getTemperatureInTheCity() {return temperatureInTheCity;}

    public void setTemperatureInTheCity(Double temperatureInTheCity) {this.temperatureInTheCity = temperatureInTheCity;}

    public Double getHumidityInTheCity() {return humidityInTheCity;}

    public void setHumidityInTheCity(Double humidityInTheCity) {this.humidityInTheCity = humidityInTheCity;}

    public String getUrlToTheWeatherIcon() {return urlToTheWeatherIcon;}

    public void setUrlToTheWeatherIcon(String urlToTheWeatherIcon) {this.urlToTheWeatherIcon = urlToTheWeatherIcon;}

    public String getJsonWithWeatherData() {return jsonWithWeatherData;}

    public void setJsonWithWeatherData(String jsonWithWeatherData) {this.jsonWithWeatherData = jsonWithWeatherData;}
}
