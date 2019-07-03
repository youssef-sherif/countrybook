import {
    FETCH_COUNTRY_INFO_BEGIN,
    FETCH_COUNTRY_INFO_SUCCESS,
    FETCH_COUNTRY_INFO_FAILURE,
    FETCH_POSTS_COUNT_BEGIN,
    FETCH_POSTS_COUNT_SUCCESS,
    FETCH_POSTS_COUNT_FAILURE,
    FOLLOW_COUNTRY_BEGIN,
    FOLLOW_COUNTRY_SUCCESS,    
    FOLLOW_COUNTRY_FAILURE,
    TOGGLE_FOLLOWED
} from '../actions/countryProfileActions'


const initialState = {
    loading: false,
    successful: false,
    followed: false,
    followersCountResource: "",
    postsCountResource: "",
    profileImageUrl: "",
    countryName: "",    
    countryId: null,
    error: "",
    postsResource: "",
    nextResource: "",
    followResource: "", 
    postsCount: 0
}

export function countryProfileReducer(state = initialState,
    action) {
    switch (action.type) {

        case FETCH_COUNTRY_INFO_BEGIN:

            return {
                ...state,
                loading: true,
                error: null
            }

        case FETCH_COUNTRY_INFO_SUCCESS:

            return {
                ...state,
                successful: true,
                loading: false,
                profileImageUrl: action.payload.data.profileImageUrl,
                followLoading: false,
                followSuccessful: false,     
                followError: "",
                followed: action.payload.data.followed,
                countryName: action.payload.data.name,
                countryId: action.payload.data.countryId,
                postsCountResource: action.payload.data._links.postsCount.href,
                followersCountResource: action.payload.data._links.followersCount.href,
                postsResource: action.payload.data._links.countryPosts.href,
                followResource: action.payload.data._links.follow.href                
            }

        case FETCH_COUNTRY_INFO_FAILURE:

            return {
                ...state,
                loading: false,
                error: action.payload.error
            }

        case FETCH_POSTS_COUNT_BEGIN:

            return {
                ...state,
                loading: true,
                error: null
            }

        case FETCH_POSTS_COUNT_SUCCESS:

            return {
                ...state,             
                successful: true,
                postsCount: action.payload.count
            }

        case FETCH_POSTS_COUNT_FAILURE:

            return {
                ...state,
                loading: false,
                error: action.payload.error
            }          
            
        case FOLLOW_COUNTRY_BEGIN:

            return {
                ...state,
                followLoading: true                
            }

        case FOLLOW_COUNTRY_SUCCESS:

            return {
                ...state,
                followSuccessful: true,
                followLoading: false                
            }

        case FOLLOW_COUNTRY_FAILURE:

            return {
                ...state,
                followLoading: false,
                followError: action.payload.error                
            }            

        case TOGGLE_FOLLOWED:

            return {
                ...state,
                followed: action.payload.followed
            }              

        default:
            return { ...state }
    }

}
