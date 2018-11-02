export const VALIDATE_USER_NAME_BEGIN = 'VALIDATE_USER_NAME_BEGIN'
export const VALIDATE_USER_NAME_SUCCESS = 'VALIDATE_USER_NAME_SUCCESS'
export const VALIDATE_USER_NAME_FAILURE = 'VALIDATE_USER_NAME_FAILURE'

export const VALIDATE_EMAIL_BEGIN = 'VALIDATE_EMAIL_BEGIN'
export const VALIDATE_EMAIL_SUCCESS = 'VALIDATE_EMAIL_SUCCESS'
export const VALIDATE_EMAIL_FAILURE = 'VALIDATE_EMAIL_FAILURE'

export const VALIDATE_PASSWORD_BEGIN = 'VALIDATE_PASSWORD_BEGIN'
export const VALIDATE_PASSWORD_SUCCESS = 'VALIDATE_PASSWORD_SUCCESS'
export const VALIDATE_PASSWORD_FAILURE = 'VALIDATE_PASSWORD_FAILURE'

export const USERNAME_OR_EMAIL = 'USERNAME_OR_EMAIL'
export const PASSWORD = 'PASSWORD'

const handleErrors = (response) =>{
    if (!response.ok || !response.created) {
      throw Error(response.statusText)
    }
    return response
  }
  
  const validateUsernameBegin = () => ({
    type: VALIDATE_USER_NAME_BEGIN
  })
  
  const validateUsernameSuccess = (isValid, username) => ({
    type: VALIDATE_USER_NAME_SUCCESS,
    payload: {isValid, username}
  })
  
  const validateUsernameFailure = (error) => ({
    type: VALIDATE_USER_NAME_FAILURE,
    payload: {error}
  })
  
  export const validateUsername = (username) => {
    return (dispatch) => {      
      dispatch(validateUsernameBegin())
      return fetch(`http://localhost:8080/validations?username=${encodeURIComponent(username)}`, {
      method: 'get'})
      .then( handleErrors)
      .then( (response) => {return response.json()})
      .then( (data) => {
        dispatch(validateUsernameSuccess(data.isValid, username))
        return data})
      .catch(error => dispatch(validateUsernameFailure(error)))
    }
  }

  
  const validateEmailBegin = () => ({
    type: VALIDATE_EMAIL_BEGIN
  })
  
  const validateEmailSuccess = (isValid, email) => ({
    type: VALIDATE_EMAIL_SUCCESS,
    payload: {isValid, email}
  })
  
  const validateEmailFailure = (error) => ({
    type: VALIDATE_EMAIL_FAILURE,
    payload: {error}
  })
  
  export const validateEmail = (email) => {
    return (dispatch) => {
      dispatch(validateEmailBegin())
      return fetch(`http://localhost:8080/validations?email=${encodeURIComponent(email)}`, {
        method: 'get'})
      .then( handleErrors)
      .then( (response) => {return response.json()})
      .then( (data) => {
        dispatch(validateEmailSuccess(data.isValid, email))
        return data})
      .catch(error => dispatch(validateEmailFailure(error)))
    }
  }
  
  const validatePasswordBegin = () => ({
    type: VALIDATE_PASSWORD_BEGIN
  })
  
  const validatePasswordSuccess = (passwordStrength, password) => ({
    type: VALIDATE_PASSWORD_SUCCESS,
    payload: {passwordStrength, password}
  })
  
  const validatePasswordFailure = (error) => ({
    type: VALIDATE_PASSWORD_FAILURE,
    payload: {error}
  })
  
  export const validatePassword = (password) => {    
    return (dispatch) => {
      dispatch(validatePasswordBegin())
      return fetch(`http://localhost:8080/validations?password=${encodeURIComponent(password)}`, {
        method: 'get'})
      .then( handleErrors)
      .then( (response) => {return response.json()})
      .then( (data) => {
        dispatch(validatePasswordSuccess(data.passwordStrength, password))
        return data})
      .catch(error => dispatch(validatePasswordFailure(error)))
    }
  }

  export const changeUsernameOrEmail = (usernameOrEmail) => ({
    type: USERNAME_OR_EMAIL,
    payload : {usernameOrEmail}
  })

  export const changePassword = (password) => ({
    type: PASSWORD,
    payload : {password}
  })