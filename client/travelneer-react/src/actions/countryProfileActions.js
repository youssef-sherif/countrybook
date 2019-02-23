import { fetchCountryPosts, fetchPostsBegin } from "./postsActions";

export const FETCH_COUNTRY_INFO_BEGIN = 'FETCH_COUNTRY_INFO_BEGIN'
export const FETCH_COUNTRY_INFO_SUCCESS = 'FETCH_COUNTRY_INFO_SUCCESS'
export const FETCH_COUNTRY_INFO_FAILURE = 'FETCH_COUNTRY_INFO_FAILURE'

export const FETCH_POSTS_COUNT_BEGIN = 'FETCH_POSTS_COUNT_BEGIN'
export const FETCH_POSTS_COUNT_SUCCESS = 'FETCH_POSTS_COUNT_SUCCESS'
export const FETCH_POSTS_COUNT_FAILURE = 'FETCH_POSTS_COUNT_FAILURE'

export const FOLLOW_COUNTRY_BEGIN = 'FOLLOW_COUNTRY_BEGIN'
export const FOLLOW_COUNTRY_SUCCESS = 'FOLLOW_COUNTRY_SUCCESS'
export const FOLLOW_COUNTRY_FAILURE = 'FOLLOW_COUNTRY_FAILURE'
export const TOGGLE_FOLLOWED = 'TOGGLE_FOLLOWED'


const fetchPostsCountBegin = () => ({
  type: FETCH_POSTS_COUNT_BEGIN
})

const fetchPostsCountSuccess = (count) => ({
  type: FETCH_POSTS_COUNT_SUCCESS,
  payload: { count }
})

const fetchPostsCountFailure = (error) => ({
  type: FETCH_POSTS_COUNT_FAILURE,
  payload: { error }
})

export const fetchPostsCount = (resource) => {
  console.log(resource)
  let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
  return (dispatch) => {
    dispatch(fetchPostsCountBegin())
    return fetch(resource, {
      headers: {        
        'Authorization': tokenBearer,
        'Access-Control-Allow-origin': 'http://localhost:8080'
      }
    })
      .then(handleErrors)
      .then((response) => {
        return response.json()
      })
      .then((data) => {
        dispatch(fetchPostsCountSuccess(data.postsCount))
        return data
      })
      .catch(error => dispatch(fetchPostsCountFailure(error)))
  }
}


const fetchCountryInfoBegin = () => ({
  type: FETCH_COUNTRY_INFO_BEGIN
})

const fetchCountryInfoSuccess = (data) => ({
  type: FETCH_COUNTRY_INFO_SUCCESS,
  payload: { data }
})

const fetchCountryInfoFailure = (error) => ({
  type: FETCH_COUNTRY_INFO_FAILURE,
  payload: { error }
})

export const fetchCountryInfo = (countryId) => {
  let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
  return (dispatch) => {
    dispatch(fetchCountryInfoBegin());
    dispatch(fetchPostsBegin());
    return fetch(`http://localhost:8080/api/countries/${countryId}`, {
      headers: {
        'Authorization': tokenBearer,
        'Access-Control-Allow-origin': 'http://localhost:8080'
      }
    })
      .then(handleErrors)
      .then((response) => {
        return response.json()
      })
      .then((data) => {        
        dispatch(fetchCountryInfoSuccess(data));
        dispatch(fetchCountryPosts(data._links.countryPosts.href));
        dispatch(fetchPostsCount(data._links.postsCount.href));

        return data
      })
      .catch(error => dispatch(fetchCountryInfoFailure(error)))
  }
}

const followCountryBegin = () => ({
  type: FOLLOW_COUNTRY_BEGIN
})

const followCountrySuccess = (data) => ({
  type: FOLLOW_COUNTRY_SUCCESS,
  payload: {data}
})

const followCountryFailure = (error) => ({
  type: FOLLOW_COUNTRY_FAILURE,
  payload: { error }
})


export const toggleFollowed = (followed) => ({
  type: TOGGLE_FOLLOWED,
  payload: {followed}
})


export const followCountry = (resource, method) => {
  let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
  return (dispatch) => {
      dispatch(followCountryBegin())
      return fetch(resource, {
          method: method,
          headers: {
              'Authorization': tokenBearer,
              'Content-Type': 'application/json',
              'Access-Control-Allow-origin': 'http://localhost:8080'
          }
      })
          .then(handleErrors)
          .then((response) => {
              return response.json()
          })
          .then((data) => {
              dispatch(followCountrySuccess(data))
              return data
          })
          .catch(error => {
              dispatch(followCountryFailure(error));
          })
  }
}


const handleErrors = (response) => {
  if (!response.ok) {
    throw Error(response.statusText)
  }
  return response
}