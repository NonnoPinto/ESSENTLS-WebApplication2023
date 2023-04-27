package com.essentls.resource;

import java.io.InputStream;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.essentls.mail.MailManager;
import jakarta.mail.MessagingException;
import org.json.JSONObject;

public class User {
    private int id;
    private String email;
    private String password;
    private String cardId;
    private int tier;
    private Date registrationDate;
    private String name;
    private String surname;
    private String sex;
    private Date dateOfBirth;
    private String nationality;
    private JSONObject homeCountryAddress;
    private String homeCountryUniversity;
    private int periodOfStay;
    private String phoneNumber;
    private JSONObject paduaAddress;
    private String documentType;
    private String documentNumber;
    private String documentFile;
    private String dietType;
    private String[] allergies;
    private String emailHash;
    private boolean emailConfirmed;
    private byte[] documentBytes;

    public User(){}
    public User(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.cardId = user.getCardId();
        this.tier = user.getTier();
        this.registrationDate = user.getRegistrationDate();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.sex = user.getSex();
        this.dateOfBirth = user.getDateOfBirth();
        this.nationality = user.getNationality();
        this.homeCountryAddress = user.getHomeCountryAddress();
        this.homeCountryUniversity = user.getHomeCountryUniversity();
        this.periodOfStay = user.getPeriodOfStay();
        this.phoneNumber = user.getPhoneNumber();
        this.paduaAddress = user.getPaduaAddress();
        this.documentType = user.getDocumentType();
        this.documentNumber = user.getDocumentNumber();
        this.documentFile = user.getDocumentFile();
        this.dietType = user.getDietType();
        this.allergies = user.getAllergies();
        this.emailHash = user.getEmailHash();
        this.emailConfirmed = user.getEmailConfirmed();
    }

    public User(int id, String email, String password, String cardId, int tier, Date registrationDate, String name,
            String surname, String sex, Date dateOfBirth, String nationality, JSONObject homeCountryAddress,
            String homeCountryUniversity, int periodOfStay, String phoneNumber, JSONObject paduaAddress,
            String documentType, String documentNumber, String documentFile, String dietType, String[] allergies, String emailHash, boolean emailConfirmed) {
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
        this.emailHash = emailHash;
        this.emailConfirmed = emailConfirmed;
        this.documentBytes = null;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCardId() {
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

    public JSONObject getHomeCountryAddress() {
        return homeCountryAddress;
    }

    public String getHomeCountryUniversity() {
        return homeCountryUniversity;
    }

    public int getPeriodOfStay() {
        return periodOfStay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public JSONObject getPaduaAddress() {
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

    public String[] getAllergies() {
        return allergies;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public boolean getEmailConfirmed(){
        return emailConfirmed;
    }

    public byte[] getDocumentBytes() {
        return documentBytes;
    }

    //Mail and password modifers
    //Mail regExpr
    final static Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    //Mail validation helper method
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    public void setMail(String _mail) {
        this.setMailAndHash(_mail);
    }

    public void setEmail(String _mail) {
        this.setMailAndHash(_mail);
    }

    //in case of email change (not allowed by now)
    public void setMailAndHash(String _mail) {
        if (_mail.isEmpty() || _mail == null)
        {
            this.email= null;
            this.emailHash= null;
        }
        else if (validate(_mail)) {
            this.email = _mail;
            this.emailHash = _mail.hashCode()+"";
        }
        else {
            //send some kind of error message
        }
        
    }

    public void setPassword(String _password) {
        //password inter than 5 char
        if(_password.length() < 5) {
            //send some kind of error message
        }
        //not the same password
        else if(_password.equals(password)) {
            //send some kind of error message
        }
        else {
            password = _password;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setHomeCountryAddress(JSONObject homeCountryAddress) {
        this.homeCountryAddress = homeCountryAddress;
    }

    public void setHomeCountryUniversity(String homeCountryUniversity) {
        this.homeCountryUniversity = homeCountryUniversity;
    }

    public void setPeriodOfStay(int periodOfStay) {
        this.periodOfStay = periodOfStay;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPaduaAddress(JSONObject paduaAddress) {
        this.paduaAddress = paduaAddress;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setDocumentFile(String documentFile) {
        this.documentFile = documentFile;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public void setAllergies(String allergies[]) {
        this.allergies = allergies;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public void setDocumentBytes(byte[] documentBytes) {
        this.documentBytes = documentBytes;
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
        uJson.put("emailHash", emailHash);
        uJson.put("emailConfirmed", emailConfirmed);
        return uJson;
    }
}
