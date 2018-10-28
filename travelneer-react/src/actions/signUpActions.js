export const CREATE_USER_BEGIN = 'POST_USER_CREDENTIALS_BEGIN'
export const CREATE_USER_SUCCESS = 'POST_USER_CREDENTIALS_SUCCESS'
export const CREATE_USER_FAILURE = 'POST_USER_CREDENTIALS_FAILURE'

const handleErrors = (response) => {
  if (!response.ok) {
    throw Error(response.statusText)
  }
  return response
}


const createUserBegin = () => ({
  type: CREATE_USER_BEGIN
})

const createUserSuccess = () => ({
  type: CREATE_USER_SUCCESS  
})

const createUserFailure = (error) => ({
  type: CREATE_USER_FAILURE,
  payload: {error}
})


export const createUser = (userName, email, password) => {
  return (dispatch) => {
    dispatch(createUserBegin())
    fetch('http://localhost:8080/users', {
      method: 'post',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        name: userName,
        email: email,
        password: password
      })
    })
      .then(handleErrors)
      .then((response) => response.json())
      .then((data) => {
        dispatch(createUserSuccess())
        localStorage.setItem('token', data.token)
        localStorage.setItem('logged_in', true)
        return data
      })
      .catch((error) => dispatch(createUserFailure(error)))
  }
}
