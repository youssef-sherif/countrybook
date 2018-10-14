export const FETCH_COUNTRIES_BEGIN = 'FETCH_COUNTRIES_BEGIN'
export const FETCH_COUNTRIES_SUCCESS = 'FETCH_COUNTRIES_SUCCESS'
export const FETCH_COUNTRIES_FAILURE = 'FETCH_COUNTRIES_FAILURE'

export const SEARCH_COUNTRIES_FAILURE = 'SEARCH_COUNTRIES_FAILURE'
export const SEARCH_COUNTRIES_SUCCESS = 'SEARCH_COUNTRIES_SUCCESS'
export const FETCH_FOLLOWED_COUNTRIES_SUCCESS = 'FETCH_FOLLOWED_COUNTRIES_SUCCESS'


const fetchCountriesBegin = () => ({
  type: FETCH_COUNTRIES_BEGIN
})

const fetchCountriesSuccess = (countries, _links) => ({
  type: FETCH_COUNTRIES_SUCCESS,
  payload: { countries, _links }
})

const fetchCountriesFailure = (error) => ({
  type: FETCH_COUNTRIES_FAILURE,
  payload: { error }
})


const searchCountriesSuccess = (countries) => ({
  type: SEARCH_COUNTRIES_SUCCESS,
  payload: { countries }
})

const fetchFollowedCountriesSuccess = (countries) => ({
  type: FETCH_FOLLOWED_COUNTRIES_SUCCESS,
  payload: { countries }
})

export const fetchCountries = () => {
  let tokenBearer = `Bearer ${localStorage.getItem('token')}`

  return (dispatch) => {    
      dispatch(fetchCountriesBegin())
      fetch('http://localhost:8080/api/countries', {
          method: 'get',
          headers: {
              'Authorization': tokenBearer,
              'Content-Type': 'application/hal+json'
          }
      })
          .then(handleErrors)
          .then((response) => response.json())
          .then((data) => {
              dispatch(fetchCountriesSuccess(data.countries, data._links))
              localStorage.setItem('logged_in', 'true')
              return data
          })
          .catch((error) => { 
              dispatch(fetchCountriesFailure())                              
          })
  }
}


export const searchCountries = (resource, searchParam) => {  
  let tokenBearer = `Bearer ${localStorage.getItem('token')}`  

  return (dispatch) => {
      dispatch(fetchCountriesBegin())
      fetch(resource+searchParam, {
          method: 'get',
          headers: {
              'Authorization': tokenBearer,
              'Content-Type': 'application/hal+json'
          }
      })
          .then(handleErrors)
          .then((response) => response.json())
          .then((data) => {
              dispatch(searchCountriesSuccess(data.countries))
              localStorage.setItem('logged_in', 'true')
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
              'Content-Type': 'application/hal+json'
          }
      })
          .then(handleErrors)
          .then((response) => response.json())
          .then((data) => {
              dispatch(fetchFollowedCountriesSuccess(data.countries))
              localStorage.setItem('logged_in', 'true')
              return data
          })
          .catch((error) => { 
              dispatch(fetchCountriesFailure(error))                                                                       
          })
  }
}


// Handle HTTP errors since fetch won't.
const handleErrors = (response) => {
  if (!response.ok) {
    throw Error(response.statusText)
  }
  return response
}