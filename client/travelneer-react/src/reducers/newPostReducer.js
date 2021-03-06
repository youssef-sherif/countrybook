import {
    NEW_POST_BEGIN,
    NEW_POST_SUCCESS,
    NEW_POST_FAILURE,
    WRITE_POST,  
    SHOW_COLLAPSABLE_POST_AREA,
    CLEAR_AREA
  } from '../actions/newPostActions'
  
const initialState = {
      successful: false,
      loading: false,
      error: false,
      errorMessage: "aa",
      content: "",
      postAreaVisible: false
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
            successful: true,
            content: ""
    
        }
      case NEW_POST_FAILURE:
        return {
          ...state,
           loading: false,
           successful: false,
           error: true,
           errorMessage: action.payload.error
        }
  
      case WRITE_POST:
        return {
          ...state,
            content: action.payload.content,          
        }
      
      case CLEAR_AREA:
        return {
          ...state,
          content: ""
        }
      
      case SHOW_COLLAPSABLE_POST_AREA:
        return {
          ...state,
          postAreaVisible: action.payload.showNew
        }

      default:
        return {
          ...state
        }
    }
  }
  