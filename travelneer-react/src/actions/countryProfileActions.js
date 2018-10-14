export const FETCH_COUNTRY_INFO_BEGIN = 'FETCH_COUNTRY_INFO_BEGIN'
export const FETCH_COUNTRY_INFO_SUCCESS = 'FETCH_COUNTRY_INFO_SUCCESS'
export const FETCH_COUNTRY_INFO_FAILURE = 'FETCH_COUNTRY_INFO_FAILURE'


const fetchCountryInfoBegin = () => ({
  type: FETCH_COUNTRY_INFO_BEGIN
})

const fetchCountryInfoSuccess = (country, countryDetails, _links) => ({
  type: FETCH_COUNTRY_INFO_SUCCESS,
  payload: { country, countryDetails, _links }
})

const fetchCountryInfoFailure = (error) => ({
  type: FETCH_COUNTRY_INFO_FAILURE,
  payload: { error }
})

export const fetchCountryInfo = (countryId) => {
  let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
  return (dispatch) => {
    dispatch(fetchCountryInfoBegin())
    return fetch(`http://localhost:8080/api/countries/${countryId}`, {
      headers: {
        'Authorization': tokenBearer,        
      }
    })
      .then(handleErrors)
      .then((response) => {
        return response.json()
      })
      .then((data) => {
        dispatch(fetchCountryInfoSuccess(data.country, data.countryDetails, data._links))
        return data
      })
      .catch(error => dispatch(fetchCountryInfoFailure(error)))
  }
}


const handleErrors = (response) => {
  if (!response.ok) {
    throw Error(response.statusText)
  }
  return response
}