export const FETCH_POSTS_BEGIN = 'FETCH_POSTS_BEGIN'
export const FETCH_POSTS_SUCCESS = 'FETCH_POSTS_SUCCESS'
export const FETCH_POSTS_FAILURE = 'FETCH_POSTS_FAILURE'

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


const fetchPostsBegin = () => ({
    type: FETCH_POSTS_BEGIN
})

const fetchPostsSuccess = (posts) => ({
    type: FETCH_POSTS_SUCCESS,    
    payload: {posts}
})

const fetchPostsFailure = (error) => ({
    type: FETCH_POSTS_FAILURE,
    payload: {error}
})


export const writePost = (content) => ({
    type: WRITE_POST,
    payload: {content}
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
            body: {
                countryId: countryId,
                content: content,                
            }
        })
            .then(handleErrors)
            .then((response) => response.json())
            .then((data) => {
                dispatch(newPostSuccess())                
                return data
            })
            .catch((error) => { 
                dispatch(newPostFailure(error))                                
            })
    }
}