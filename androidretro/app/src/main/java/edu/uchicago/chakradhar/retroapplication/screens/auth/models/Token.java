package edu.uchicago.chakradhar.retroapplication.screens.auth.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Token {

    @SerializedName("sub")
    @Expose
    private String sub;
    @SerializedName("email_verified")
    @Expose
    private Boolean emailVerified;
    @SerializedName("iss")
    @Expose
    private String iss;
    @SerializedName("phone_number_verified")
    @Expose
    private Boolean phoneNumberVerified;
    @SerializedName("cognito:username")
    @Expose
    private String cognitoUsername;
    @SerializedName("given_name")
    @Expose
    private String givenName;
    @SerializedName("origin_jti")
    @Expose
    private String originJti;
    @SerializedName("aud")
    @Expose
    private String aud;
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("token_use")
    @Expose
    private String tokenUse;
    @SerializedName("auth_time")
    @Expose
    private Integer authTime;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("exp")
    @Expose
    private Integer exp;
    @SerializedName("iat")
    @Expose
    private Integer iat;
    @SerializedName("jti")
    @Expose
    private String jti;
    @SerializedName("email")
    @Expose
    private String email;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public Boolean getPhoneNumberVerified() {
        return phoneNumberVerified;
    }

    public void setPhoneNumberVerified(Boolean phoneNumberVerified) {
        this.phoneNumberVerified = phoneNumberVerified;
    }

    public String getCognitoUsername() {
        return cognitoUsername;
    }

    public void setCognitoUsername(String cognitoUsername) {
        this.cognitoUsername = cognitoUsername;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getOriginJti() {
        return originJti;
    }

    public void setOriginJti(String originJti) {
        this.originJti = originJti;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTokenUse() {
        return tokenUse;
    }

    public void setTokenUse(String tokenUse) {
        this.tokenUse = tokenUse;
    }

    public Integer getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Integer authTime) {
        this.authTime = authTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getIat() {
        return iat;
    }

    public void setIat(Integer iat) {
        this.iat = iat;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}