export const FETCH_COUNTRIES_BEGIN = 'FETCH_COUNTRIES_BEGIN'
export const FETCH_COUNTRIES_SUCCESS = 'FETCH_COUNTRIES_SUCCESS'
export const FETCH_COUNTRIES_FAILURE = 'FETCH_COUNTRIES_FAILURE'

export const SEARCH_COUNTRIES_SUCCESS = 'SEARCH_COUNTRIES_SUCCESS'
export const FETCH_FOLLOWED_COUNTRIES_SUCCESS = 'FETCH_FOLLOWED_COUNTRIES_SUCCESS'
export const SELECT_COUNTRY = 'SELECT_COUNTRY'


const fetchCountriesBegin = () => ({
  type: FETCH_COUNTRIES_BEGIN
})

const fetchCountriesSuccess = (data) => ({
  type: FETCH_COUNTRIES_SUCCESS,
  payload: { data }
})

const fetchCountriesFailure = (error) => ({
  type: FETCH_COUNTRIES_FAILURE,
  payload: { error }
})


const searchCountriesSuccess = (data) => ({
  type: SEARCH_COUNTRIES_SUCCESS,
  payload: { data }
})

const fetchFollowedCountriesSuccess = (data) => ({
  type: FETCH_FOLLOWED_COUNTRIES_SUCCESS,
  payload: { data }
})

export const fetchCountries = () => {
  let tokenBearer = `Bearer ${localStorage.getItem('token')}`

  return (dispatch) => {    
      dispatch(fetchCountriesBegin())
      fetch('http://localhost:8080/api/countries', {
          method: 'get',
          headers: {
              'Authorization': tokenBearer,
              'Content-Type': 'application/json',
              'Access-Control-Allow-origin': 'http://localhost:8080'
            }
      })
          .then(handleErrors)
          .then((response) => response.json())
          .then((data) => {
              dispatch(fetchCountriesSuccess(data))
              return data
          })
          .catch((error) => { 
              dispatch(fetchCountriesFailure(error))                              
          })
  }
}


export const searchCountries = (resource, searchParam) => {  
  let tokenBearer = `Bearer ${localStorage.getItem('token')}`  

  return (dispatch) => {
      dispatch(fetchCountriesBegin())
      fetch(`${resource}${searchParam}`, {
          method: 'get',
          headers: {
              'Authorization': tokenBearer,
              'Content-Type': 'application/json',
              'Access-Control-Allow-origin': 'http://localhost:8080'
            }
      })
          .then(handleErrors)
          .then((response) => response.json())
          .then((data) => {
              dispatch(searchCountriesSuccess(data))
              return data
          })
          .catch((error) => { 
              dispatch(fetchCountriesFailure(error))                                                                       
          })
  }
}

export const fetchFollowedCountries = (resource) => {  
  let tokenBearer = `Bearer ${localStorage.getItem('token')}`  

  return (dispatch) => {
      dispatch(fetchCountriesBegin())
      fetch(resource, {
          method: 'get',
          headers: {
              'Authorization': tokenBearer,
              'Content-Type': 'application/json',
              'Access-Control-Allow-origin': 'http://localhost:8080'
          }
      })
          .then(handleErrors)
          .then((response) => response.json())
          .then((data) => {
              dispatch(fetchFollowedCountriesSuccess(data))
              return data
          })
          .catch((error) => { 
              dispatch(fetchCountriesFailure(error))                                                                       
          })
  }
}


export const selectCountry = (countryId) => ({
  type: SELECT_COUNTRY,
  payload: {countryId}  
})

// Handle HTTP errors since fetch won't.
const handleErrors = (response) => {
  if (!response.ok) {
    throw Error(response.statusText)
  }
  return response
}