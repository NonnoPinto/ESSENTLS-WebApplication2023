package com.essentls.resource;

import java.util.Date;

import org.json.JSONObject;

public class User {
    private long id;
    private String email;
    private String password;
    private int cardId;
    private int tier;
    private Date registrationDate;
    private String name;
    private String surname;
    private String sex;
    private Date dateOfBirth;
    private String nationality;
    private String homeCountryAddress;
    private String homeCountryUniversity;
    private String periodOfStay;
    private int phoneNumber;
    private String paduaAddress;
    private String documentType;
    private String documentNumber;
    private String documentFile;
    private String dietType;
    private String allergies;

    public User(long id, String email, String password, int cardId, int tier, Date registrationDate, String name,
            String surname, String sex, Date dateOfBirth, String nationality, String homeCountryAddress,
            String homeCountryUniversity, String periodOfStay, int phoneNumber, String paduaAddress,
            String documentType, String documentNumber, String documentFile, String dietType, String allergies) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.cardId = cardId;
        this.tier = tier;
        this.registrationDate = registrationDate;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.homeCountryAddress = homeCountryAddress;
        this.homeCountryUniversity = homeCountryUniversity;
        this.periodOfStay = periodOfStay;
        this.phoneNumber = phoneNumber;
        this.paduaAddress = paduaAddress;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.documentFile = documentFile;
        this.dietType = dietType;
        this.allergies = allergies;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getCardId() {
        return cardId;
    }

    public int getTier() {
        return tier;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSex() {
        return sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public String getHomeCountryAddress() {
        return homeCountryAddress;
    }

    public String getHomeCountryUniversity() {
        return homeCountryUniversity;
    }

    public String getPeriodOfStay() {
        return periodOfStay;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getPaduaAddress() {
        return paduaAddress;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getDocumentFile() {
        return documentFile;
    }

    public String getDietType() {
        return dietType;
    }

    public String getAllergies() {
        return allergies;
    }

    public JSONObject toJSON() {
        JSONObject uJson = new JSONObject();
        uJson.put("id", id);
        uJson.put("email", email);
        uJson.put("password", password);
        uJson.put("cardId", cardId);
        uJson.put("tier", tier);
        uJson.put("registrationDate", registrationDate);
        uJson.put("name", name);
        uJson.put("surname", surname);
        uJson.put("sex", sex);
        uJson.put("dateOfBirth", dateOfBirth);
        uJson.put("nationality", nationality);
        uJson.put("homeCountryAddress", homeCountryAddress);
        uJson.put("homeCountryUniversity", homeCountryUniversity);
        uJson.put("periodOfStay", periodOfStay);
        uJson.put("phoneNumber", phoneNumber);
        uJson.put("paduaAddress", paduaAddress);
        uJson.put("documentType", documentType);
        uJson.put("documentNumber", documentNumber);
        uJson.put("documentFile", documentFile);
        uJson.put("dietType", dietType);
        uJson.put("allergies", allergies);
        return uJson;
    }
}
