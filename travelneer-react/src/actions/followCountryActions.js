export const FOLLOW_COUNTRY_BEGIN = 'FOLLOW_COUNTRY_BEGIN'
export const FOLLOW_COUNTRY_SUCCESS = 'FOLLOW_COUNTRY_SUCCESS'
export const FOLLOW_COUNTRY_FAILURE = 'FOLLOW_COUNTRY_FAILURE'


const followCountryBegin = () => ({
    type: FOLLOW_COUNTRY_BEGIN
})

const followCountrySuccess = (data) => ({
    type: FOLLOW_COUNTRY_SUCCESS,
    payload: {data}
})

const followCountryFailure = (error) => ({
    type: FOLLOW_COUNTRY_FAILURE,
    payload: { error }
})

export const followCountry = (resource) => {
    let tokenBearer = 'Bearer '.concat(localStorage.getItem('token'))
    return (dispatch) => {
        dispatch(followCountryBegin())
        return fetch(resource, {
            method: 'put',
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
                dispatch(followCountrySuccess(data))
                return data
            })
            .catch(error => {
                dispatch(followCountryFailure(error));
                console.log(error)
            })
    }
}

const handleErrors = (response) => {
    if (!response.ok) {
        throw Error(response.statusText)
    }
    return response
}