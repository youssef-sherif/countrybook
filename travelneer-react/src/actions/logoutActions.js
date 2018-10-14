export const LOGOUT_BEGIN = 'LOGOUT_BEGIN'
export const LOGOUT_SUCCESS = 'LOGOUT_SUCCESS'
export const LOGOUT_FAILURE = 'LOGOUT_FAILURE'

const handleErrors = (response) => {
    if (!response.ok) {
        throw Error(response.statusText)
    }
    return response
}


const logoutBegin = () => ({
    type: LOGOUT_BEGIN
})

const logoutSuccess = () => ({
    type: LOGOUT_SUCCESS
})

const logoutFailure = (error) => ({
    type: LOGOUT_FAILURE,
    payload: { error }
})

export const logout = (userId, userName) => {
    return (dispatch) => {
        dispatch(logoutBegin())

        dispatch(logoutSuccess())
        localStorage.removeItem('token')

    }
}
