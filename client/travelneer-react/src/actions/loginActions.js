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
    type: LOGIN_BEGIN
  })
  
  const loginSuccess = () => ({
    type: LOGIN_SUCCESS
  })
  
  const loginFailure = () => ({
    type: LOGIN_FAILURE
  })
  
  export const login = (username, email, password) => {
    return (dispatch) => {
      dispatch(loginBegin())
      fetch(`http://localhost:8080/access-token?username=${encodeURIComponent(username)}&email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`, {
        method: 'get',
        headers: {
          'Content-Type': 'application/json',
          'Access-Control-Allow-origin': 'http://localhost:8080'
        }
      })
        .then(handleErrors)
        .then((response) => response.json())
        .then((data) => {
          dispatch(loginSuccess())        
          localStorage.setItem('token', data.token)      
          localStorage.setItem('logged_in', true)
          window.location.reload()                   
          return data
        })
        .catch((error) => dispatch(loginFailure()) )
    }
  }