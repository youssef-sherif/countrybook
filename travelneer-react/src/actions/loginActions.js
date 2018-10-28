export const LOGIN_BEGIN = 'LOGIN_BEGIN'
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS'
export const LOGIN_FAILURE = 'LOGIN_FAILURE'

const handleErrors = (response) =>{
    if (!response.ok) {
      throw Error(response.statusText)
    }
    return response
  }
  
  
  const loginBegin = () => ({
    type: CREATE_USER_BEGIN
  })
  
  const loginSuccess = (userId) => ({
    type: CREATE_USER_SUCCESS,
    payload: { userId }
  })
  
  const loginFailure = () => ({
    type: CREATE_USER_FAILURE
  })
  
  export const login = (userName, email, password) => {
    return (dispatch) => {
      dispatch(loginBegin())
      fetch('http://localhost:8080/access-token', {
        method: 'post',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          name: userName,
          email: email,
          password: password
          })
        })
        .then(handleErrors)
        .then((response) => response.json())
        .then((data) => {
          dispatch(loginSuccess(data.userId))        
          localStorage.setItem('token', data.token)        
          return data
        })
        .catch((error) => dispatch(loginFailure()) )
    }
  }