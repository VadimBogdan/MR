package Homework;

import java.io.Serializable;

public class Subscriber implements Serializable {
    private final String FirstName;
    private final String MiddleName;
    private final String LastName;
    private final String Address;
    private final int IntercitySpeakTime;
    private final int UrbanSpeakTime;

    public Subscriber(String firstName,
                      String middleName,
                      String lastName,
                      String address,
                      int intercitySpeakTime,
                      int urbanSpeakTime) {
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
        Address = address;
        IntercitySpeakTime = intercitySpeakTime;
        UrbanSpeakTime = urbanSpeakTime;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s | Адреса: %s | Тривалість розмови між містами %d у місті %d",
                FirstName,
                MiddleName,
                LastName,
                Address,
                IntercitySpeakTime,
                UrbanSpeakTime);
    }
}
