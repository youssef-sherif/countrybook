export const CONTINUE_THREAD_BEGIN = 'CONTINUE_THREAD_BEGIN'
export const CONTINUE_THREAD_SUCCESS = 'CONTINUE_THREAD_SUCCESS'
export const CONTINUE_THREAD_FAILURE = 'CONTINUE_THREAD_FAILURE'


const continueThreadBegin = () => ({
  type: CONTINUE_THREAD_BEGIN
})

const continueThreadSuccess = (data) => ({
  type: CONTINUE_THREAD_SUCCESS,
  payload: { data }
})

const continueThreadFailure = (error) => ({
  type: CONTINUE_THREAD_FAILURE,
  payload: { error }
})

export const continueThread = (postId, commentId) => {
  let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
  return (dispatch) => {
    dispatch(continueThreadBegin());    
    return fetch(`http://localhost:8080/api/posts/${postId}/threads/${commentId}`, {
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
        dispatch(continueThreadSuccess(data.comments));                

        return data
      })
      .catch(error => dispatch(continueThreadFailure(error)))
  }
}

const handleErrors = (response) => {
  if (!response.ok) {
    throw Error(response.statusText)
  }
  return response
}