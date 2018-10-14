import {
  AUTHORIZE_USER_BEGIN,
  AUTHORIZE_USER_SUCCESS,
  AUTHORIZE_USER_FAILURE
} from '../actions/authActions'

const authState = {
  userId: null
}

export function authReducer(state = authState, action) {
  switch (action.type) {

    case AUTHORIZE_USER_BEGIN:
      return {
        ...state
      }
    case AUTHORIZE_USER_SUCCESS:
      return {
        ...state,
        userId: action.payload

      }
    case AUTHORIZE_USER_FAILURE:
      return {
        ...state
      }

    default:
      return {
        ...state
      }
  }
}
