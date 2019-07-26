import { toggleFavourite } from "./postInfoActions"
import { push } from 'connected-react-router'

export const FETCH_POSTS_BEGIN = 'FETCH_POSTS_BEGIN'
export const FETCH_POSTS_SUCCESS = 'FETCH_POSTS_SUCCESS'
export const FETCH_POSTS_FAILURE = 'FETCH_POSTS_FAILURE'
export const FAVOURITE_POST_BEGIN = 'FAVOURITE_POST_BEGIN'
export const FAVOURITE_POST = 'FAVOURITE_POST'
export const UNFAVOURITE_POST = 'UNFAVOURITE_POST'
export const FAVOURITE_POST_FAILURE = 'FAVOURITE_POST_FAILURE'
export const SAVE_SCROLL_POSITION = 'SAVE_SCROLL_POSITION'
export const BACK_BUTTON_PRESSED = 'BACK_BUTTON_PRESSED'

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
                dispatch(toggleFavourite())                
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

const fetchPostsSuccess = (data, loadMore) => ({
    type: FETCH_POSTS_SUCCESS,    
    payload: {data, loadMore}
})

const fetchPostsFailure = (error) => ({
    type: FETCH_POSTS_FAILURE,
    payload: {error}
})


export const fetchPosts = (resource='http://localhost:8080/auth/feed', loadMore=false) => {
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
                dispatch(fetchPostsSuccess(data, loadMore))                
                return data
            })
            .catch((error) => { 
                dispatch(fetchPostsFailure(error))                                                                             
            })
    }
}

export const fetchCountryPosts = (resource, loadMore=false) => {
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
                dispatch(fetchPostsSuccess(data, loadMore))                
                return data
            })
            .catch((error) => { 
                dispatch(fetchPostsFailure(error))                                                                             
            })
    }
}

export const fetchMyPosts = (resource, loadMore=false) => {
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
                dispatch(fetchPostsSuccess(data, loadMore))                
                return data
            })
            .catch((error) => { 
                dispatch(fetchPostsFailure(error))                                                                             
            })
    }
}

export const backButtonPressed = (originalPath) => {
    return (dispatch) => {
        dispatch(push(originalPath))
        dispatch({
            type: BACK_BUTTON_PRESSED
        })
    }    
}

export const saveScrollPosition = (originalPath, scrollY) => ({
    type: SAVE_SCROLL_POSITION,
    payload: {originalPath, scrollY}
})