import {
    LOGIN_BEGIN,
    LOGIN_SUCCESS,
    LOGIN_FAILURE,
  } from '../actions/loginActions'
  
  const initialState = {
      loading: false,
      successful: false,
      error: false
  }
  
  export function loginReducer(state = initialState, action) {
    switch (action.type) {
      case LOGIN_BEGIN:
        return {
          ...state,          
            loading: true          
        }
      case LOGIN_SUCCESS:
        return {
          ...state,
            loading: false,
            successful: true
        }
      case LOGIN_FAILURE:
        return {
          ...state,          
            loading: false,
            successful: false,
            error: true          
        }

      default:
        return {
          ...state
        }
    }
  }
  