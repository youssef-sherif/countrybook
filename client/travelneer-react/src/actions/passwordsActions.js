import { toast } from 'react-toastify';

export const REQUEST_PASSWORD_RESET_TOKEN_BEGIN = 'REQUEST_PASSWORD_RESET_TOKEN_BEGIN'
export const REQUEST_PASSWORD_RESET_TOKEN_SUCCESS = 'REQUEST_PASSWORD_RESET_TOKEN_SUCCESS'
export const REQUEST_PASSWORD_RESET_TOKEN_FAILURE = 'REQUEST_PASSWORD_RESET_TOKEN_FAILURE'
export const CHANGE_PASSWORD_BEGIN = 'CHANGE_PASSWORD_BEGIN'
export const CHANGE_PASSWORD_SUCCESS = 'CHANGE_PASSWORD_SUCCESS'
export const CHANGE_PASSWORD_FAILURE = 'CHANGE_PASSWORD_FAILURE'

const handleErrors = (response) => {
    if (!response.ok) {
        throw Error(response.statusText)
    }
    return response
}

const requestPasswordResetTokenBegin = () => ({
    type: REQUEST_PASSWORD_RESET_TOKEN_BEGIN
})

const requestPasswordResetTokenSuccess = (data) => ({
    type: REQUEST_PASSWORD_RESET_TOKEN_SUCCESS,
    payload: { data }
})

const requestPasswordResetTokenFailure = () => ({
    type: REQUEST_PASSWORD_RESET_TOKEN_FAILURE
})

export const requestPasswordResetToken = () => {
    return (dispatch) => {
        dispatch(requestPasswordResetTokenBegin())
        fetch('http://localhost:8080/password-reset-token', {
            method: 'get',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-origin': 'http://localhost:8080'
            }
        })
            .then(handleErrors)
            .then((response) => response.json())
            .then((data) => {
                dispatch(requestPasswordResetTokenSuccess(data))
                return data
            })
            .catch((error) => {
                dispatch(requestPasswordResetTokenFailure())
            })
    }
}

const changePasswordBegin = () => ({
    type: CHANGE_PASSWORD_BEGIN
})

const changePasswordSuccess = (data) => ({
    type: CHANGE_PASSWORD_SUCCESS,
    payload: { data }
})

const changePasswordFailure = () => ({
    type: CHANGE_PASSWORD_FAILURE
})


export const changePassword = (oldPassword, newPassword) => {
    let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
    return (dispatch) => {
        dispatch(changePasswordBegin())
        fetch('http://localhost:8080/auth/me/password', {
            method: 'put',
            headers: {                
                'Authorization': tokenBearer,
                'Content-Type': 'application/json',
                'Access-Control-Allow-origin': 'http://localhost:8080'
            },
            body: JSON.stringify({
                oldPassword: oldPassword,                
                newPassword: newPassword
            })
        })
            .then(handleErrors)
            .then((response) => response.json())
            .then((data) => {
                dispatch(changePasswordSuccess(data))
                toast('success', {containerId: 'passwordChangeToast'});
                return data
            })
            .catch((error) => {
                dispatch(changePasswordFailure())
                toast('error', {containerId: 'passwordChangeToast'});
            })
    }
}