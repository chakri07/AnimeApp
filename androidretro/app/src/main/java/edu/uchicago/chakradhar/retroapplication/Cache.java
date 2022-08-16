package edu.uchicago.chakradhar.retroapplication;


//singleton cache
public class Cache {

    private String keyword;
    private String userEmail = "mobileTest@gmail.com";

    private static Cache cache;

    private Cache() {
    }

    public static Cache getInstance() {
        if (null == cache) {
            cache = new Cache();
            return cache;
        } else {
            return cache;
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;

    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
