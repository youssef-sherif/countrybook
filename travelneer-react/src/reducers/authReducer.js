import {
  AUTHORIZE_USER_BEGIN,
  AUTHORIZE_USER_SUCCESS,
  AUTHORIZE_USER_FAILURE
} from '../actions/authActions'

const authState = {
  userId: null,
  successful: false,
  loading: false
}

export function authReducer(state = authState, action) {
  switch (action.type) {

    case AUTHORIZE_USER_BEGIN:
      return {
        ...state,
        loading: true
      }
    case AUTHORIZE_USER_SUCCESS:
      return {
        ...state,
        userId: action.payload.userId,
        successful: true,
        loading: false
      }
    case AUTHORIZE_USER_FAILURE:
      return {
        ...state,
        successful: false
      }

    default:
      return {
        ...state
      }
  }
}
