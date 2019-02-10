import { fetchPosts } from './postsActions'
import { push } from 'react-router-redux'

export const NEW_POST_BEGIN = 'NEW_POST_BEGIN'
export const NEW_POST_SUCCESS = 'NEW_POST_SUCCESS'
export const NEW_POST_FAILURE = 'NEW_POST_FAILURE'

export const WRITE_POST = 'WRITE_POST'

export const SHOW_NEW = 'SHOW_NEW'

const handleErrors = (response) => {
    if (!response.ok) {
        throw Error(response.statusText)
    }
    return response
}

export const showNew = (showNew) => ({
    type: SHOW_NEW,
    payload: {showNew}
})

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


export const newPost = (countryId, content, refresh) => {
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`;
    return (dispatch) => {
        dispatch(newPostBegin())
        fetch('http://localhost:8080/api/feed', {
            method: 'post',
            mode: 'cors',
            headers: {
                'Authorization': tokenBearer, 
                'Content-Type': 'application/json; charset=utf-8'
              },             
            body: JSON.stringify({
                content: content,                
                countryId: countryId
            })
        })
            .then(handleErrors)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                dispatch(newPostSuccess());   
                if(refresh)
                    dispatch(fetchPosts());
                return data
            })
            .catch((error) => { 
                console.log("error", error);
                dispatch(newPostFailure(error))                                
            })
    }
}

export const newPost2 = (countryId, content) => {
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`;
    return (dispatch) => {
        dispatch(newPostBegin())
        fetch('http://localhost:8080/api/feed', {
            method: 'post',
            mode: 'cors',
            headers: {
                'Authorization': tokenBearer, 
                'Content-Type': 'application/json; charset=utf-8'
              },             
            body: JSON.stringify({
                content: content,                
                countryId: countryId
            })
        })
            .then(handleErrors)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                dispatch(newPostSuccess());   
                dispatch(push(`/countries/${countryId}`))
                return data
            })
            .catch((error) => { 
                dispatch(newPostFailure(error))                                
            })
    }
}