import {
    FAVOURITE_POST_BEGIN,
    FAVOURITE_POST_SUCCESS,    
    FAVOURITE_POST_FAILURE,
} from '../actions/favouritesActions'


const initialState = {
    loading: false,
    successful: true,     
    error: ""
}

export function favouritesReducer(state = initialState, action) {
    switch (action.type) {

        case FAVOURITE_POST_BEGIN:

            return {
                ...state,
                loading: true                
            }

        case FAVOURITE_POST_SUCCESS:

            return {
                ...state,
                successful: true,
                loading: false                
            }

        case FAVOURITE_POST_FAILURE:

            return {
                ...state,
                loading: false,
                error: action.payload.error                
            }

        default:
            return { ...state }
    }

}
