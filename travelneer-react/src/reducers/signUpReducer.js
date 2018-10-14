import {
  CREATE_USER_BEGIN,
  CREATE_USER_SUCCESS,
  CREATE_USER_FAILURE
} from '../actions/signUpActions'

const initialState = {
  signUp: {
    loading: false,
    successful: false,
    error: false
  }
}

export function signUpReducer(state = initialState, action) {
  switch (action.type) {
    case CREATE_USER_BEGIN:
      return {
        ...state,
        signUp: {
          loading: true
        }
      }
    case CREATE_USER_SUCCESS:
      return {
        ...state,
        signUp: {
          loading: false,
          successful: true
        }        
      }
    case CREATE_USER_FAILURE:
      return {
        ...state,
        signUp: {
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
