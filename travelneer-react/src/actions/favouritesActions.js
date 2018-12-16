export const FAVOURITE_POST_BEGIN = 'FAVOURITE_POST_BEGIN'
export const FAVOURITE_POST_SUCCESS = 'FAVOURITE_POST_SUCCESS'
export const FAVOURITE_POST_FAILURE = 'FAVOURITE_POST_FAILURE'

const favouritePostBegin = () => ({
    type: FAVOURITE_POST_BEGIN
})

const favouritePostSuccess = (data) => ({
    type: FAVOURITE_POST_SUCCESS,
    payload: {data}
})

const favouritePostFailure = (error) => ({
    type: FAVOURITE_POST_FAILURE,
    payload: { error }
})

export const favouritePost = (resource, method) => {
    let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
    return (dispatch) => {
        dispatch(favouritePostBegin())
        return fetch(resource, {
            method: method,
            headers: {
                'Authorization': tokenBearer,
                'Content-Type': 'application/json'
            }
        })
            .then(handleErrors)
            .then((response) => {
                return response.json()
            })
            .then((data) => {
                dispatch(favouritePostSuccess(data))
                return data
            })
            .catch(error => {
                dispatch(favouritePostFailure(error));
            })
    }
}

const handleErrors = (response) => {
    if (!response.ok) {
        throw Error(response.statusText)
    }
    return response
}