export const FETCH_POSTS_BEGIN = 'FETCH_POSTS_BEGIN'
export const FETCH_POSTS_SUCCESS = 'FETCH_POSTS_SUCCESS'
export const FETCH_POSTS_FAILURE = 'FETCH_POSTS_FAILURE'

const handleErrors = (response) => {
    if (!response.ok) {
        throw Error(response.statusText)
    }
    return response
}


const fetchPostsBegin = () => ({
    type: FETCH_POSTS_BEGIN
})

const fetchPostsSuccess = (posts, _links) => ({
    type: FETCH_POSTS_SUCCESS,    
    payload: {posts, _links}
})

const fetchPostsFailure = (error) => ({
    type: FETCH_POSTS_FAILURE,
    payload: {error}
})


export const fetchPosts = () => {
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`
    return (dispatch) => {
        dispatch(fetchPostsBegin())
        fetch('http://localhost:8080/api/feed', {
            method: 'get',
            headers: {
                'Authorization': tokenBearer,
                'Content-Type': 'application/json'
            }
        })
            .then(handleErrors)
            .then((response) => response.json())
            .then((data) => {
                dispatch(fetchPostsSuccess(data.posts, data._links))                
                return data
            })
            .catch((error) => { 
                dispatch(fetchPostsFailure(error))                                                                             
            })
    }
}

export const fetchCountryPosts = (resource) => {
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`
    return (dispatch) => {
        dispatch(fetchPostsBegin())
        fetch(resource, {
            method: 'get',
            headers: {
                'Authorization': tokenBearer,
                'Content-Type': 'application/json'
            }
        })
            .then(handleErrors)
            .then((response) => response.json())
            .then((data) => {
                dispatch(fetchPostsSuccess(data.posts, data._links))                
                return data
            })
            .catch((error) => { 
                dispatch(fetchPostsFailure(error))                                                                             
            })
    }
}