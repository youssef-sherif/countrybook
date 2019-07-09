import {
    FETCH_COMMENTS_BEGIN,
    FETCH_COMMENTS_FAILURE,
    FETCH_COMMENTS_SUCCESS,

    FETCH_POST_INFO_BEGIN,
    FETCH_POST_INFO_FAILURE,
    FETCH_POST_INFO_SUCCESS,

    FETCH_FAVOURITES_COUNT_BEGIN,
    FETCH_FAVOURITES_COUNT_FAILURE,
    FETCH_FAVOURITES_COUNT_SUCCESS,

    FETCH_COMMENTS_COUNT_BEGIN,
    FETCH_COMMENTS_COUNT_FAILURE,
    FETCH_COMMENTS_COUNT_SUCCESS,
    TOGGLE_FAVOURITE
  } from '../actions/postInfoActions'
  
  const initialState = {
    successful: false,
    loading: false,
    nextResource: "",
    error: "",
    comments: [],
    content: "",
    name: "",
    email: "",
    countryId: null,
    timeDiff: "",    
    favourite: null,
    favouritesCount: null,
    commentsCount: null,
    favouritesResource: ""       
  }
  
  export function postInfoReducer(state = initialState, action) {
    switch (action.type) {
  
      case FETCH_COMMENTS_BEGIN:
        return {
          ...state,
          loading: true
        }
      case FETCH_COMMENTS_SUCCESS:
        return {
          ...state,
          loading: false,
          successful: true,
          comments: action.payload.loadMore === true? state.comments.concat(action.payload.data.comments) 
            : action.payload.data.comments,
          nextResource: action.payload.data._links.next.href
        }
      case FETCH_COMMENTS_FAILURE:
        return {
          ...state,
          loading: false,
          successful: false,
          error: action.payload.error
        }

       case FETCH_POST_INFO_BEGIN:
           return {
               ...state,
           } 
        
       case FETCH_POST_INFO_SUCCESS:
           return {
               ...state,
               content: action.payload.data.content,
               name: action.payload.data.name,
               email: action.payload.data.email,
               countryId: action.payload.data.countryId,
               timeDiff: action.payload.data.timeDiff,    
               favourite: action.payload.data.favourite,
               favouritesResource: action.payload.data._links.favourite.href  
           } 

        case FETCH_POST_INFO_FAILURE:    
            return {
                ...state,
                    
            }

        case FETCH_FAVOURITES_COUNT_BEGIN:
            return {
                ...state
            }

        case FETCH_FAVOURITES_COUNT_FAILURE:
            return {
                ...state
            }

        case FETCH_FAVOURITES_COUNT_SUCCESS:
            return {
                ...state,
                favouritesCount: action.payload.count
            }

        case FETCH_COMMENTS_COUNT_BEGIN:
            return {
                ...state
            }

        case FETCH_COMMENTS_COUNT_FAILURE:
            return {
                ...state
            }    
        
        case FETCH_COMMENTS_COUNT_SUCCESS:
            return {
                ...state,
                commentsCount: action.payload.count
            }
        
        case TOGGLE_FAVOURITE:
            return {
                ...state,
                favourite: !state.favourite,
                favouritesCount: state.favourite === false? state.favouritesCount + 1 
                    : state.favouritesCount - 1 
            }

      default:
        return {
          ...state
        }
    }
  }
  