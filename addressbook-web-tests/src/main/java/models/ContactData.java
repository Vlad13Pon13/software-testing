package models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ContactData {
    @NonNull
    public String firstName;
    @NonNull
    public String middleName;
    @NonNull
    public String lastName;
    @NonNull
    public String nickName;
    @NonNull
    public String title;
    @NonNull
    public String company;
    @NonNull
    public String address;
    public String home ="";
    @NonNull
    public String mobile;
    public String work="";
    public String fax="";
    @NonNull
    public String mail;
    public String mailTwo="";
    public String mailThree="";
    public String homepage="";
    public String birthDay="0";
    public String birthMonth="-";
    public String birthYear="";
    public String anniversaryDay="0";
    public String anniversaryMonth="-";
    public String anniversaryYear="";

}
