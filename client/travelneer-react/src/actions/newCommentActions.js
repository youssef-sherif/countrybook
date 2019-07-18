import { fetchComments } from './postInfoActions'
import { continueThread } from './threadsActions';

export const NEW_COMMENT_BEGIN = 'NEW_COMMENT_BEGIN'
export const NEW_COMMENT_SUCCESS = 'NEW_COMMENT_SUCCESS'
export const NEW_COMMENT_FAILURE = 'NEW_COMMENT_FAILURE'

export const SHOW_COLLAPSABLE_COMMENT_AREA = 'SHOW_COLLAPSABLE_COMMENT_AREA'


export const showCollapsableCommentArea = (showCommentArea) => ({
    type: SHOW_COLLAPSABLE_COMMENT_AREA,
    payload: {showCommentArea}
})


const handleErrors = (response) => {
    if (!response.ok) {
        throw Error(response.statusText)
    }
    return response
}

const newCommentBegin = () => ({
    type: NEW_COMMENT_BEGIN   
})

const newCommentSuccess = () => ({
    type: NEW_COMMENT_SUCCESS 
})

const newCommentFailure = (error) => ({
    type: NEW_COMMENT_FAILURE,
    payload: {error}
})


export const newComment = (postId, commentId=null, content) => {
    let resource = "";
    if(commentId === null) {
        resource = `http://localhost:8080/api/posts/${postId}/comments`
    } else {
        resource = `http://localhost:8080/api/posts/${postId}/comments/${commentId}/replies`
    }
    let tokenBearer = `Bearer ${localStorage.getItem('token')}`;
    return (dispatch) => {
        dispatch(newCommentBegin())
        fetch(resource, {
            method: 'post',
            headers: {
                'Authorization': tokenBearer, 
                'Content-Type': 'application/json; charset=utf-8',
                'Access-Control-Allow-Origin': 'http://localhost:8080',  
              },             
            body: JSON.stringify({
                content: content                                        
            })
        })
            .then(handleErrors)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                dispatch(newCommentSuccess());      
                dispatch(fetchComments(`http://localhost:8080/api/posts/${postId}/comments`)); 
                dispatch(continueThread(postId, commentId));                                       
                return data
            })
            .catch((error) => { 
                console.log("error", error);
                dispatch(newCommentFailure(error))                                
            })
    }
}