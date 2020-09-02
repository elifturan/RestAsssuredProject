package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Map;

//TODO Country POJO class that have same encapsulated field as Countries table
@JsonIgnoreProperties(ignoreUnknown = true)
public class Countries {

     private String country_id;
     private String country_name;
     private int region_id;
     //private List<Map<String,Object>

     @Override
    public String toString(){
         return "Countries{" +
                 "country_id:" + country_id +
                 "country_name:" +country_name +
                 "region_id:" +region_id +'\'' +
                 '}';
     }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }
}
