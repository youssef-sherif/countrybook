export const AUTHORIZE_USER_BEGIN = 'AUTHORIZE_USER_BEGIN'
export const AUTHORIZE_USER_SUCCESS = 'AUTHORIZE_USER_SUCCESS'
export const AUTHORIZE_USER_FAILURE = 'AUTHORIZE_USER_FAILURE'

const handleErrors = (response) => {
    if (!response.ok) {
        throw Error(response.statusText)
    }
    return response
}

const authorizeUserBegin = () => ({
    type: AUTHORIZE_USER_BEGIN
})

const authorizeUserSuccess = (userId) => ({
    type: AUTHORIZE_USER_SUCCESS,
    payload: userId
})

const authorizeUserFailure = () => ({
    type: AUTHORIZE_USER_FAILURE
})


export const authorizeUser = () => {
    let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
    return (dispatch) => {
        dispatch(authorizeUserBegin())
        fetch('http://localhost:8080/api/v1/user', {
            method: 'get',
            headers: {
                'Authorization': tokenBearer,
                'Content-Type': 'application/json'
            }
        })
            .then(handleErrors)
            .then((response) => response.json())
            .then((data) => {
                dispatch(authorizeUserSuccess(data.userId))
                localStorage.setItem('logged_in', 'true')
                return data
            })
            .catch((error) => { 
                dispatch(authorizeUserFailure())                
                localStorage.setItem('logged_in', 'false')                                                
            })
    }
}
