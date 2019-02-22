package com.joada.idioma;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by janisharali on 29/08/16.
 */
public class Profile implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String imageUrl;

    @SerializedName("age")
    @Expose
    private Integer age;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("languages")
    @Expose
    private List<Language<String, Integer, Integer>> languages;


    public Profile(String name, Integer age, String location, String profileURL, List<Language<String, Integer, Integer>> languages){
        this.name = name;
        this.age = age;
        this.location = location;
        this.imageUrl = profileURL;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Language<String, Integer, Integer>> getLanguages() { return languages; }

    public void setLanguages(List<Language<String, Integer, Integer>> languages) { this.languages = languages; }

    public String getLevel(Language lang){
        switch (lang.getR().intValue()){
            case 1: return "Beginner";
            case 2: return "Intermediate";
            case 3: return "Advanced";
            case 4: return "Fluent";
            case 5: return "Native";
            default: return "";
        }
    }

}
