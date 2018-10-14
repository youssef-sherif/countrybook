import {
  FETCH_POSTS_BEGIN,
  FETCH_POSTS_SUCCESS,
  FETCH_POSTS_FAILURE,
  NEW_POST_BEGIN,
  NEW_POST_SUCCESS,
  NEW_POST_FAILURE,
  WRITE_POST,  
} from '../actions/postsActions'

const initialState = {
  successful: false,
  loading: false,
  error: "",
  posts: [],
  newPost: {
    successful: false,
    loading: false,
    error: "",
    content: ""
  }
}

export function postsReducer(state = initialState, action) {
  switch (action.type) {

    case FETCH_POSTS_BEGIN:
      return {
        ...state,
        loading: true
      }
    case FETCH_POSTS_SUCCESS:
      return {
        ...state,
        loading: false,
        successful: true,
        posts: action.payload.posts

      }
    case FETCH_POSTS_FAILURE:
      return {
        ...state,
        loading: false,
        successful: false,
        error: action.payload.error
      }

    case NEW_POST_BEGIN:
      return {
        ...state,        
        newPost: {
          loading: true
        }
      }
    case NEW_POST_SUCCESS:
      return {
        ...state,
        newPost: {
          loading: false,
          successful: true
        }
      }
    case NEW_POST_FAILURE:
      return {
        ...state,
        newPost: {
          loading: false,
          successful: false,
          error: action.payload.error
        }
      }

    case WRITE_POST:
      return {
        ...state,
        newPost: {
          content: action.payload.content,          
        }
      }  

    default:
      return {
        ...state
      }
  }
}
