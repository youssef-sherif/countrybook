import {
    FOLLOW_COUNTRY_BEGIN,
    FOLLOW_COUNTRY_SUCCESS,    
    FOLLOW_COUNTRY_FAILURE,
} from '../actions/followCountryActions'


const initialState = {
    loading: false,
    successful: false,     
    error: ""
}

export function followCountryReducer(state = initialState, action) {
    switch (action.type) {

        case FOLLOW_COUNTRY_BEGIN:

            return {
                ...state,
                loading: true                
            }

        case FOLLOW_COUNTRY_SUCCESS:

            return {
                ...state,
                successful: true,
                loading: false                
            }

        case FOLLOW_COUNTRY_FAILURE:

            return {
                ...state,
                loading: false,
                error: action.payload.error                
            }

        default:
            return { ...state }
    }

}
