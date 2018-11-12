export const FETCH_COUNTRY_INFO_BEGIN = 'FETCH_COUNTRY_INFO_BEGIN'
export const FETCH_COUNTRY_INFO_SUCCESS = 'FETCH_COUNTRY_INFO_SUCCESS'
export const FETCH_COUNTRY_INFO_FAILURE = 'FETCH_COUNTRY_INFO_FAILURE'

export const FETCH_POSTS_COUNT_BEGIN = 'FETCH_POSTS_COUNT_BEGIN'
export const FETCH_POSTS_COUNT_SUCCESS = 'FETCH_POSTS_COUNT_SUCCESS'
export const FETCH_POSTS_COUNT_FAILURE = 'FETCH_POSTS_COUNT_FAILURE'
export const TOGGLE_FOLLOWED = 'TOGGLE_FOLLOWED'


export const toggleFollowed = (followed) => ({
    type: TOGGLE_FOLLOWED,
    payload: {followed}
})



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



export const fetchFollowersCount = (resource) => ({

})


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
        
        dispatch(fetchCountryInfoSuccess(data))
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