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

    USERNAME_OR_EMAIL,
    PASSWORD
} from '../actions/validationActions'


const initialState = {
    loginUsername: "",
    loginPassword: "",
    username: { 
        value: "",
        loading: false,
        errorMessage: null,
        error: null,
        isValid: false,
        message: null
    },
    email: {
        value: "",
        loading: false,
        errorMessage: null,
        error: null,
        isValid: false,
        message: null
    },
    password: {
        value: "",
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
                username: {
                    loading: true,
                    isEmpty: false
                }
            }
        case VALIDATE_USER_NAME_SUCCESS:
            return {
                ...state,
                username: {
                    value: action.payload.username,
                    loading: false,
                    isValid: action.payload.isValid,
                    isEmpty: false
                }
            }

        case VALIDATE_USER_NAME_FAILURE:
            return {
                ...state,
                username: {
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

        case USERNAME_OR_EMAIL:
            return {
              ...state,
              loginUsername: action.payload.usernameOrEmail   
            }

        case PASSWORD:
            return {
              ...state,
              loginPassword: action.payload.password
            }            

        default:
            return {
                ...state
            }
    }
}
