import {
    FETCH_COUNTRY_INFO_BEGIN,
    FETCH_COUNTRY_INFO_SUCCESS,
    FETCH_COUNTRY_INFO_FAILURE,
    FETCH_POSTS_COUNT_BEGIN,
    FETCH_POSTS_COUNT_SUCCESS,
    FETCH_POSTS_COUNT_FAILURE
} from '../actions/countryProfileActions'


const initialState = {
    loading: false,
    successful: false,
    followed: false,
    followersCountResource: "",
    postsCountResource: "",
    profileURL: "",
    countryName: "",    
    countryId: null,
    error: "",
    postsResource: "",
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
                profileURL: action.payload.country.profileImageURL,
                followed: action.payload.followed,
                countryName: action.payload.country.name,
                countryId: action.payload.country.id,
                postsCountResource: action.payload._links.postsCount.href,
                followersCountResource: action.payload._links.followersCount.href,
                postsResource: action.payload._links.countryPosts.href,
                followResource: action.payload._links.follow.href,                

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

        default:
            return { ...state }
    }

}
