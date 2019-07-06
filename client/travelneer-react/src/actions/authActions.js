import { push } from 'connected-react-router'
import { fetchMyPosts } from './postsActions' 

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

const authorizeUserSuccess = (data) => ({
    type: AUTHORIZE_USER_SUCCESS,
    payload: { data }
})

const authorizeUserFailure = () => ({
    type: AUTHORIZE_USER_FAILURE
})


export const authorizeUser = () => {
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`
    return (dispatch) => {
        dispatch(authorizeUserBegin())
        fetch('http://localhost:8080/api/me', {
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
                dispatch(authorizeUserSuccess(data))
                localStorage.setItem('logged_in', 'true')   
                
                return data
            })
            .catch((error) => { 
                dispatch(authorizeUserFailure())                
                localStorage.setItem('logged_in', 'false')   
                dispatch(push('/'))
            })
    }
}

export const fetchUserInfo = () => {
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`
    return (dispatch) => {
        dispatch(authorizeUserBegin())
        fetch('http://localhost:8080/api/me', {
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
                dispatch(authorizeUserSuccess(data))
                dispatch(fetchMyPosts(data._links.myPosts.href))
                localStorage.setItem('logged_in', 'true')   
                
                return data
            })
            .catch((error) => { 
                dispatch(authorizeUserFailure())                
                localStorage.setItem('logged_in', 'false')   
                dispatch(push('/'))
            })
    }
}

export const authorizeUserHome = () => {
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`
    return (dispatch) => {
        dispatch(authorizeUserBegin())
        fetch('http://localhost:8080/api/me', {
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
                dispatch(authorizeUserSuccess(data))
                localStorage.setItem('logged_in', 'true')   
                dispatch(push('/feed'))
                
                return data
            })
            .catch((error) => { 
                dispatch(authorizeUserFailure())                
                localStorage.setItem('logged_in', 'false')   
            })
    }
}