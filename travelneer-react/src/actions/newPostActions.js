export const NEW_POST_BEGIN = 'NEW_POST_BEGIN'
export const NEW_POST_SUCCESS = 'NEW_POST_SUCCESS'
export const NEW_POST_FAILURE = 'NEW_POST_FAILURE'

export const WRITE_POST = 'WRITE_POST'

const handleErrors = (response) => {
    if (!response.ok) {
        throw Error(response.statusText)
    }
    return response
}

export const writePost = (content) => ({
    type: WRITE_POST,
    payload: {content}
})

const newPostBegin = () => ({
    type: NEW_POST_BEGIN   
})

const newPostSuccess = () => ({
    type: NEW_POST_SUCCESS 
})

const newPostFailure = (error) => ({
    type: NEW_POST_FAILURE,
    payload: {error}
})


export const newPost = (countryId, content) => {
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`
    return (dispatch) => {
        dispatch(newPostBegin())
        fetch('localhost:8080/api/feed', {
            method: 'post',
            headers: {
                'Authorization': tokenBearer,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                countryId: countryId,
                content: content,                
            })
        })
            .then(handleErrors)
            .then((response) => {return response.json();})
            .then((data) => {
                dispatch(newPostSuccess());                
                return data
            })
            .catch((error) => { 
                dispatch(newPostFailure(error))                                
            })
    }
}