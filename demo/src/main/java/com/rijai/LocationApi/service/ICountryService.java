package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Country;

import java.util.List;

public interface ICountryService {
    List<Country> getCountries();
    Country addCountry(Country country);
    Country updateCountry(long id, Country country);
    Country getCountry(long id);
    Country deleteCountry(long id);
}