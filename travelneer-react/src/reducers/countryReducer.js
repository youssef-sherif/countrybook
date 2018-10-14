import {
  FETCH_COUNTRIES_BEGIN,
  FETCH_COUNTRIES_SUCCESS,
  FETCH_COUNTRIES_FAILURE,

  FETCH_FOLLOWED_COUNTRIES_SUCCESS,
  SEARCH_COUNTRIES_SUCCESS,  
} from '../actions/countryActions'


const getCountriesState = {    
  error: "",
  loading: false,
  successful: false,
  countries: [],
  searchCountriesResource: "",
  followedCountriesResource: ""
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
        countries: action.payload.countries,
        searchCountriesResource: action.payload._links.searchCountries.href,
        followedCountriesResource: action.payload._links.followedCountries.href
      }

    case FETCH_COUNTRIES_FAILURE:

      return {
        ...state,
        loading: false,
        successful: false,
        error: action.payload.error
      }

    case SEARCH_COUNTRIES_SUCCESS:

      return {
        ...state,
        loading: false,
        successful: true,                
        countries: action.payload.countries                 
      }

    case FETCH_FOLLOWED_COUNTRIES_SUCCESS:
      
      return {
        ...state,
        loading: false,
        successful: true,
        countries: action.payload.countries
      }
      

    default:
      return { ...state }
  }
}
