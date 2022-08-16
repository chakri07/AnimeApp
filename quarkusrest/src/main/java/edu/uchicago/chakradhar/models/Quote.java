package edu.uchicago.chakradhar.models;

public class Quote {

    public Quote() {
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id='" + id + '\'' +
                ", quote='" + quote + '\'' +
                ", anime='" + anime + '\'' +
                ", character='" + character + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

    public Quote(String gender, String character, String breed, String id, String userEmail) {
        this.quote = breed;
        this.anime = gender;
        this.character = character;
        this.userEmail = userEmail;
        this.id = id;
    }

    private String id;
    private String quote;
    private String anime;
    private String character;
    private String userEmail;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
