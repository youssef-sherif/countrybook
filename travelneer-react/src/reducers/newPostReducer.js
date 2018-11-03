import {
    NEW_POST_BEGIN,
    NEW_POST_SUCCESS,
    NEW_POST_FAILURE,
    WRITE_POST,  
  } from '../actions/newPostActions'
  
const initialState = {
      successful: false,
      loading: false,
      error: "",
      content: ""
  }
  
export function newPostReducer(state = initialState, action) {
    switch (action.type) {
  
      case NEW_POST_BEGIN:
        return {
          ...state,        
            loading: true
        }
      case NEW_POST_SUCCESS:
        return {
          ...state,
            loading: false,
            successful: true
    
        }
      case NEW_POST_FAILURE:
        return {
          ...state,
           loading: false,
           successful: false,
        }
  
      case WRITE_POST:
        return {
          ...state,
            content: action.payload.content,          
        }  
  
      default:
        return {
          ...state
        }
    }
  }
  