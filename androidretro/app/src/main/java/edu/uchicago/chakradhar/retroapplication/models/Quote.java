package edu.uchicago.chakradhar.retroapplication.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName("anime")
    @Expose
    private String anime;
    @SerializedName("character")
    @Expose
    private String character;
    @SerializedName("quote")
    @Expose
    private String quote;

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "QuoteResponse{" +
                "anime='" + anime + '\'' +
                ", character='" + character + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }
}
