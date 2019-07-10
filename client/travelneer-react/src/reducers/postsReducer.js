import {
  FETCH_POSTS_BEGIN,
  FETCH_POSTS_SUCCESS,
  FETCH_POSTS_FAILURE,
  FAVOURITE_POST_BEGIN,
  FAVOURITE_POST,
  UNFAVOURITE_POST,
  FAVOURITE_POST_FAILURE,
  SAVE_SCROLL_POSITION,
  BACK_BUTTON_PRESSED
} from '../actions/postsActions'

import { LOCATION_CHANGE } from 'connected-react-router';

const initialState = {
  successful: false,
  loading: false,
  favouriteLoading: false,
  favouriteSuccessful: false,
  nextResource: "",
  error: "",
  posts: [],
  backButtonPressed: false,
  returnToScroll: false,
  originalPath: "",
  scrollPositionY: 0
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
          posts: action.payload.loadMore === true? state.posts.concat(action.payload.data.posts) 
            : action.payload.data.posts,
          nextResource: action.payload.data._links.next.href
      }
          
    case FETCH_POSTS_FAILURE:
      return {
          ...state,
          loading: false,
          successful: false,
          error: action.payload.error
      }

    case FAVOURITE_POST_BEGIN:

      return {
          ...state,
          favouriteLoading: true                
      }

    case FAVOURITE_POST: 
      let updatedPosts = state.posts.map(post => {
          if(post.postId === action.payload.data.postId){  
            return { 
              ...post,
              favourite: true 
            };
          }
          return post;
      })
      return {
        ...state,    
        posts: updatedPosts,    
        favouriteLoading: false
      }

    case UNFAVOURITE_POST:
      updatedPosts = state.posts.map(post => {
          if(post.postId === action.payload.data.postId){   
            return { 
              ...post,
              favourite: false 
            }
          }
          return post
      })
      return {
        ...state,    
        posts: updatedPosts,    
        favouriteLoading: false
      }     

    case FAVOURITE_POST_FAILURE:

      return {
          ...state,
          favouriteLoading: false,
          error: action.payload.error                
      }

    case SAVE_SCROLL_POSITION:
      
      return {
        ...state,
        scrollPositionY: action.payload.scrollY,
        originalPath: action.payload.originalPath       
      }

    case BACK_BUTTON_PRESSED:

      return {
        ...state,
        backButtonPressed: true
      }

    case LOCATION_CHANGE:

      return {
        ...state,
        backButtonPressed: false
      }

    default:
      return {
        ...state
      }
  }
}
