export const FETCH_POSTS_BEGIN = 'FETCH_POSTS_BEGIN'
export const FETCH_POSTS_SUCCESS = 'FETCH_POSTS_SUCCESS'
export const FETCH_POSTS_FAILURE = 'FETCH_POSTS_FAILURE'
export const FAVOURITE_POST_BEGIN = 'FAVOURITE_POST_BEGIN'
export const FAVOURITE_POST = 'FAVOURITE_POST'
export const UNFAVOURITE_POST = 'UNFAVOURITE_POST'
export const FAVOURITE_POST_FAILURE = 'FAVOURITE_POST_FAILURE'

const favouritePostBegin = () => ({
    type: FAVOURITE_POST_BEGIN
})

const favouritePostSuccess = (data) => ({
    type: FAVOURITE_POST,
    payload:  {
        data
    }
})

const unFavouritePostSuccess = (data) => ({
    type: UNFAVOURITE_POST,
    payload:  {
        data
    }
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
                'Content-Type': 'application/json',
                'Access-Control-Allow-origin': 'http://localhost:8080'           
            }
        })
            .then(handleErrors)
            .then((response) => {
                return response.json()
            })
            .then((data) => {
                if(method === 'put')                
                    dispatch(favouritePostSuccess(data.post))
                else
                    dispatch(unFavouritePostSuccess(data.post))
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


export const fetchPostsBegin = () => ({
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


export const fetchPosts = () => {
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`
    return (dispatch) => {
        dispatch(fetchPostsBegin())
        fetch('http://localhost:8080/api/feed', {
            method: 'get',
            headers: {
                'Authorization': tokenBearer,
                'Content-Type': 'application/json',
                'Access-Control-Allow-origin': 'http://localhost:8080'
            }
        })
            .then(handleErrors)
            .then((response) => response.json())
            .then((data) => {
                dispatch(fetchPostsSuccess(data.posts))                
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
                'Content-Type': 'application/json',
                'Access-Control-Allow-origin': 'http://localhost:8080'
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