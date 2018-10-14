package com.travelneer.domain.country;

import com.travelneer.hateoas.CountryResource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Countries {

    private List<Country> countries;

    public Countries() {
        this.countries = new ArrayList<>();
    }

}
