import {
    CONTINUE_THREAD_BEGIN,
    CONTINUE_THREAD_SUCCESS,
    CONTINUE_THREAD_FAILURE
} from '../actions/commentTreeActions'

const initialState = {    
    replies: [],
    successful: false,
    loading: false,
}

export function commentTreeReducer(state = initialState, action) {
    switch (action.type) {

        case CONTINUE_THREAD_BEGIN:
            return {
                ...state,
                loading: true,                
            }

        case CONTINUE_THREAD_SUCCESS:
            return {
                ...state,
                loading: false,
                successful: true,
                replies: action.payload.data,
            }


        case CONTINUE_THREAD_FAILURE:
            return {
                ...state,
                successful: false
            }

        default:
            return {
                ...state
            }
    }
}
