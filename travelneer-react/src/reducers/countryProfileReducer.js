import {
    FETCH_COUNTRY_INFO_BEGIN,
    FETCH_COUNTRY_INFO_SUCCESS,
    FETCH_COUNTRY_INFO_FAILURE
} from '../actions/countryProfileActions'

import {
    FOLLOW_COUNTRY_SUCCESS
} from '../actions/followCountryActions'


const initialState = {
    loading: false,
    successful: false,
    followed: false,
    followersCount: "",
    postsCount: "",
    profileURL: "",
    countryName: "",    
    countryId: null,
    error: "",
    postsResource: "",
    followResource: ""    
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


        case FOLLOW_COUNTRY_SUCCESS:
            return {
                ...state,
                followed: true
            }

        case FETCH_COUNTRY_INFO_SUCCESS:

            return {
                ...state,
                successful: true,
                loading: false,
                followersCount: action.payload.countryDetails.followersCount,
                postsCount: action.payload.countryDetails.postsCount,
                profileURL: action.payload.countryDetails.profileURL,
                followed: action.payload.countryDetails.followed,
                countryName: action.payload.country.countryName,
                countryId: action.payload.country.countryId,
                postsResource: action.payload._links.countryPosts.href,
                followResource: action.payload._links.followOrUnfollow.href,                

            }

        case FETCH_COUNTRY_INFO_FAILURE:

            return {
                ...state,
                loading: false,
                error: action.payload.error
            }

        default:
            return { ...state }
    }

}
