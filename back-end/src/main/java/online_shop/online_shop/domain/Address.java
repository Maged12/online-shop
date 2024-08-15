package online_shop.online_shop.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
