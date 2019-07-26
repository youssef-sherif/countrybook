package com.travelneer.country;

public class Country {

    private Short   id;
    private String  code;
    private String  name;
    private String  profileImageUrl;
    private String  flagUrl;


    public Short getId() {
        return this.id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return this.profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getFlagUrl() {
        return this.flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public CountryResource toResource() {
        var countryResource = new CountryResource(this);

        return countryResource;
    }

    public CountryDetailsResource toDetailsResource() {
        var countryDetailsResource = new CountryDetailsResource(this);

        return countryDetailsResource;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Country (");

        sb.append(id);
        sb.append(", ").append(code);
        sb.append(", ").append(name);
        sb.append(", ").append(profileImageUrl);
        sb.append(", ").append(flagUrl);

        sb.append(")");
        return sb.toString();
    }
}
