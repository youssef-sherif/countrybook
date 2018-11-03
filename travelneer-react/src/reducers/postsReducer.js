import {
  FETCH_POSTS_BEGIN,
  FETCH_POSTS_SUCCESS,
  FETCH_POSTS_FAILURE,
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

    default:
      return {
        ...state
      }
  }
}
