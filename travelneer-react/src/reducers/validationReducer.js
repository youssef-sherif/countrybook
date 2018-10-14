import {
    VALIDATE_USER_NAME_FAILURE,
    VALIDATE_USER_NAME_BEGIN,
    VALIDATE_USER_NAME_SUCCESS,

    VALIDATE_EMAIL_BEGIN,
    VALIDATE_EMAIL_SUCCESS,
    VALIDATE_EMAIL_FAILURE,

    VALIDATE_PASSWORD_BEGIN,
    VALIDATE_PASSWORD_SUCCESS,
    VALIDATE_PASSWORD_FAILURE,
} from '../actions/validationActions'


const initialState = {
    userName: {
        value: null,
        loading: false,
        errorMessage: null,
        error: null,
        isValid: false,
        message: null
    },
    email: {
        value: null,
        loading: false,
        errorMessage: null,
        error: null,
        isValid: false,
        message: null
    },
    password: {
        value: null,
        loading: false,
        errorMessage: null,
        error: null,
        passwordStrength: 3
    }
}


export function validationReducer(state = initialState, action) {
    switch (action.type) {
        case VALIDATE_USER_NAME_BEGIN:
            return {
                ...state,
                userName: {
                    loading: true,
                    isEmpty: false
                }
            }
        case VALIDATE_USER_NAME_SUCCESS:
            return {
                ...state,
                userName: {
                    value: action.payload.userName,
                    loading: false,
                    isValid: action.payload.isValid,
                    isEmpty: false
                }
            }

        case VALIDATE_USER_NAME_FAILURE:
            return {
                ...state,
                userName: {
                    errorMessage: action.payload.error,
                    error: action.payload.error,
                    isValid: false,
                    isEmpty: false
                }
            }

        case VALIDATE_EMAIL_BEGIN:
            return {
                ...state,
                email: {
                    loading: true,
                    isEmpty: false
                }
            }
        case VALIDATE_EMAIL_SUCCESS:
            return {
                ...state,
                email: {
                    value: action.payload.email,
                    loading: false,
                    isValid: action.payload.isValid,
                    isEmpty: false
                }
            }

        case VALIDATE_EMAIL_FAILURE:
            return {
                ...state,
                email: {
                    errorMessage: action.payload.error,
                    error: action.payload.error,
                    isValid: false,
                    isEmpty: false
                }
            }

        case VALIDATE_PASSWORD_BEGIN:
            return {
                ...state,
                password: {
                    loading: true,
                    isEmpty: false
                }
            }

        case VALIDATE_PASSWORD_SUCCESS:
            return {
                ...state,
                password: {
                    value: action.payload.password,
                    loading: false,
                    passwordStrength: action.payload.passwordStrength,
                    isEmpty: false
                }
            }

        case VALIDATE_PASSWORD_FAILURE:
            return {
                ...state,
                password: {
                    errorMessage: action.payload.error,
                    passwordStrength: 3,                    
                    error: action.payload.error,
                    isEmpty: false
                }
            }

        default:
            return {
                ...state
            }
    }
}
