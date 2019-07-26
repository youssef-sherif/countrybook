export const FETCH_POST_INFO_BEGIN = 'FETCH_POST_INFO_BEGIN'
export const FETCH_POST_INFO_SUCCESS = 'FETCH_POST_INFO_SUCCESS'
export const FETCH_POST_INFO_FAILURE = 'FETCH_POST_INFO_FAILURE'

export const FETCH_FAVOURITES_COUNT_BEGIN = 'FETCH_FAVOURITES_COUNT_BEGIN'
export const FETCH_FAVOURITES_COUNT_SUCCESS = 'FETCH_FAVOURITES_COUNT_SUCCESS'
export const FETCH_FAVOURITES_COUNT_FAILURE = 'FETCH_FAVOURITES_COUNT_FAILURE'

export const FETCH_COMMENTS_COUNT_BEGIN = 'FETCH_COMMENTS_COUNT_BEGIN'
export const FETCH_COMMENTS_COUNT_SUCCESS = 'FETCH_COMMENTS_COUNT_SUCCESS'
export const FETCH_COMMENTS_COUNT_FAILURE = 'FETCH_COMMENTS_COUNT_FAILURE'


export const FETCH_COMMENTS_BEGIN = 'FETCH_COMMENTS_BEGIN'
export const FETCH_COMMENTS_SUCCESS = 'FETCH_COMMENTS_SUCCESS'
export const FETCH_COMMENTS_FAILURE = 'FETCH_COMMENTS_FAILURE'

export const TOGGLE_FAVOURITE = 'TOGGLE_FAVOURITE'


export const toggleFavourite = () => ({
    type: TOGGLE_FAVOURITE
  })

const fetchFavouritesCountBegin = () => ({
  type: FETCH_FAVOURITES_COUNT_BEGIN
})

const fetchFavouritesCountSuccess = (count) => ({
  type: FETCH_FAVOURITES_COUNT_SUCCESS,
  payload: { count }
})

const fetchFavouritesCountFailure = (error) => ({
  type: FETCH_FAVOURITES_COUNT_FAILURE,
  payload: { error }
})

export const fetchFavouritesCount = (resource) => {  
  let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
  return (dispatch) => {
    dispatch(fetchFavouritesCountBegin())
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
        dispatch(fetchFavouritesCountSuccess(data.favouritesCount))
        return data
      })
      .catch(error => dispatch(fetchFavouritesCountFailure(error)))
  }
}

const fetchCommentsCountBegin = () => ({
    type: FETCH_COMMENTS_COUNT_BEGIN
  })
  
  const fetchCommentsCountSuccess = (count) => ({
    type: FETCH_COMMENTS_COUNT_SUCCESS,
    payload: { count }
  })
  
  const fetchCommentsCountFailure = (error) => ({
    type: FETCH_COMMENTS_COUNT_FAILURE,
    payload: { error }
  })
  
export const fetchCommentsCount = (resource) => {    
    let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
    return (dispatch) => {
      dispatch(fetchCommentsCountBegin())
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
          dispatch(fetchCommentsCountSuccess(data.commentsCount))
          return data
        })
        .catch(error => dispatch(fetchCommentsCountFailure(error)))
    }
  }


const fetchPostInfoBegin = () => ({
  type: FETCH_POST_INFO_BEGIN
})

const fetchPostInfoSuccess = (data) => ({
  type: FETCH_POST_INFO_SUCCESS,
  payload: { data }
})

const fetchPostInfoFailure = (error) => ({
  type: FETCH_POST_INFO_FAILURE,
  payload: { error }
})

export const fetchPostInfo = (postId) => {
  let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
  let resource = `http://localhost:8080/posts/${postId}`
  if(localStorage.getItem('logged_in') === 'true') {
    resource = `http://localhost:8080/auth/posts/${postId}`
  }
  return (dispatch) => {
    dispatch(fetchPostInfoBegin());    
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
        dispatch(fetchPostInfoSuccess(data));        
        dispatch(fetchFavouritesCount(data._links.favouritesCount.href));
        dispatch(fetchCommentsCount(data._links.commentsCount.href));
        dispatch(fetchComments(data._links.comments.href));

        return data
      })
      .catch(error => dispatch(fetchPostInfoFailure(error)))
  }
}

const fetchCommentsBegin = () => ({
    type: FETCH_COMMENTS_BEGIN
})

const fetchCommentsSuccess = (data, loadMore) => ({
    type: FETCH_COMMENTS_SUCCESS,    
    payload: {data, loadMore}
})

const fetchCommentsFailure = (error) => ({
    type: FETCH_COMMENTS_FAILURE,
    payload: {error}
})

export const fetchComments = (resource, loadMore=false) => {
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`
    return (dispatch) => {
        dispatch(fetchCommentsBegin())
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
                dispatch(fetchCommentsSuccess(data, loadMore))                
                return data
            })
            .catch((error) => { 
                dispatch(fetchCommentsFailure(error))                                                                             
            })
    }
}


const handleErrors = (response) => {
  if (!response.ok) {
    throw Error(response.statusText)
  }
  return response
}