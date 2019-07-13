import {
    SHOW_COLLAPSABLE_COMMENT_AREA
} from '../actions/newCommentActions'

const initialState = {
    commentAreaVisible: false
  }


export function newCommentReducer(state = initialState, action) {

    switch (action.type) {

        case SHOW_COLLAPSABLE_COMMENT_AREA:        
            return {
                ...state,
                commentAreaVisible: action.payload.showCommentArea
            }

        default:
            return {
                ...state
            }

    }
}