import {
    REQUEST_PASSWORD_RESET_TOKEN_BEGIN,
    REQUEST_PASSWORD_RESET_TOKEN_SUCCESS,
    REQUEST_PASSWORD_RESET_TOKEN_FAILURE,

    CHANGE_PASSWORD_BEGIN,
    CHANGE_PASSWORD_SUCCESS,
    CHANGE_PASSWORD_FAILURE
  } from '../actions/passwordsActions'

  const authState = {    
    requestEmailSuccessful: false,
    requestEmailLoading: false,
    
    changePasswordSuccessful: false,
    changePasswordLoading: false,
    changePasswordError: false
  }
  
  export function passwordsReducer(state = authState, action) {
    switch (action.type) {
  
      case REQUEST_PASSWORD_RESET_TOKEN_BEGIN:
        return {
          ...state,
          requestEmailLoading: true
        }
      case REQUEST_PASSWORD_RESET_TOKEN_SUCCESS:
        return {
          ...state,                
          requestEmailSuccessful: true,
          requestEmailLoading: false
        }
      case REQUEST_PASSWORD_RESET_TOKEN_FAILURE:
        return {
          ...state,
          requestEmailSuccessful: false
        }
      case CHANGE_PASSWORD_BEGIN:
        return {
          ...state,
          changePasswordLoading: true
        }
      case CHANGE_PASSWORD_SUCCESS:
        return {
          ...state,
          changePasswordSuccessful: true,
          changePasswordLoading: false
        }
      case CHANGE_PASSWORD_FAILURE:
        return {
          ...state,
          changePasswordSuccessful: false,
          changePasswordError: true
        }
        
      default:
        return {
          ...state
        }
    }
  }
  