package edu.uchicago.chakradhar.retroapplication.screens.favs.models;

public class FavQuoteResponse {

    private String quote;
    private String anime;
    private String character;
    private String userEmail;
    private String id;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FavQuoteResponse(String quote, String anime, String character, String userEmail, String id) {
        this.quote = quote;
        this.anime = anime;
        this.character = character;
        this.userEmail = userEmail;
        this.id = id;
    }

    public FavQuoteResponse() {
    }

    @Override
    public String toString() {
        return "FavaQuoteResponse{" +
                "quote='" + quote + '\'' +
                ", anime='" + anime + '\'' +
                ", character='" + character + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
