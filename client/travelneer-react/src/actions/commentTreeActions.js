export const CONTINUE_THREAD_BEGIN = 'CONTINUE_THREAD_BEGIN'
export const CONTINUE_THREAD_SUCCESS = 'CONTINUE_THREAD_SUCCESS'
export const CONTINUE_THREAD_FAILURE = 'CONTINUE_THREAD_FAILURE'


const continueCommentTreeBegin = () => ({
  type: CONTINUE_THREAD_BEGIN
})

const continueCommentTreeSuccess = (data) => ({
  type: CONTINUE_THREAD_SUCCESS,
  payload: { data }
})

const continueCommentTreeFailure = (error) => ({
  type: CONTINUE_THREAD_FAILURE,
  payload: { error }
})

export const continueCommentTree = (postId, commentId) => {
  let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
  return (dispatch) => {
    dispatch(continueCommentTreeBegin());    
    return fetch(`http://localhost:8080/api/posts/${postId}/comments/${commentId}/tree`, {
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
        dispatch(continueCommentTreeSuccess(data.comments));                

        return data
      })
      .catch(error => dispatch(continueCommentTreeFailure(error)))
  }
}

const handleErrors = (response) => {
  if (!response.ok) {
    throw Error(response.statusText)
  }
  return response
}