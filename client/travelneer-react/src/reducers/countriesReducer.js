import {
  FETCH_COUNTRIES_BEGIN,
  FETCH_COUNTRIES_SUCCESS,
  FETCH_COUNTRIES_FAILURE,
  SELECT_COUNTRY,
  FETCH_FOLLOWED_COUNTRIES_SUCCESS,
  SEARCH_COUNTRIES_SUCCESS,  
} from '../actions/countriesActions'


const getCountriesState = {    
  error: "",
  loading: false,
  successful: false,
  countries: [{name: "", id: 0}],
  searchCountriesResource: "",
  followedCountriesResource: "",
  selectedCountryCode: "",
  selectedCountryName: ""
}

export function fetchCountriesReducer(state = getCountriesState,
  action) {
  switch (action.type) {

    case FETCH_COUNTRIES_BEGIN:

      return {
        ...state,
        loading: true,
        successful: false,
        error: null
      }

    case FETCH_COUNTRIES_SUCCESS:

      return {
        ...state,
        loading: false,
        successful: true,
        countries: action.payload.data.countries,
        searchCountriesResource: action.payload.data._links.searchCountries.href,
        followedCountriesResource: action.payload.data._links.followedCountries.href
      }

    case FETCH_COUNTRIES_FAILURE:

      return {
        ...state,
        loading: false,
        error: action.payload.error
      }

    case SEARCH_COUNTRIES_SUCCESS:

      return {
        ...state,
        loading: false,
        successful: true,                
        countries: action.payload.data.countries                 
      }

    case FETCH_FOLLOWED_COUNTRIES_SUCCESS:
      
      return {
        ...state,
        loading: false,
        successful: true,
        countries: action.payload.data.countries
      }
      
    case SELECT_COUNTRY:            

      let country = state.countries.find( country => country.code  === action.payload.countryCode)
      let name = country != null ? country.name : "(choose a country)"

      return {
          ...state,          
          selectedCountryCode: action.payload.countryCode,
          selectedCountryName: name
      }

    default:
      return { ...state }
  }
}
