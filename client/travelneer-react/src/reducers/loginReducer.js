import {
    LOGIN_BEGIN,
    LOGIN_SUCCESS,
    LOGIN_FAILURE,
  } from '../actions/loginActions'
  
  const initialState = {
    login: {
      usernameOrEmail: "",
      password: "",
      loading: false,
      successful: false,
      error: false
    }
  }
  
  export function loginReducer(state = initialState, action) {
    switch (action.type) {
      case LOGIN_BEGIN:
        return {
          ...state,
          login: {
            loading: true
          }
        }
      case LOGIN_SUCCESS:
        return {
          ...state,
          login: {
            loading: false,
            successful: true,
            usernameOrEmail: "",
            password: ""
          }        
        }
      case LOGIN_FAILURE:
        return {
          ...state,
          login: {
            successful: false,
            error: true
          }
        }

      default:
        return {
          ...state
        }
    }
  }
  