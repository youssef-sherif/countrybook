import React, { Component } from 'react'
import Comment from '../comment/Comment'
import { connect } from 'react-redux'

import styles from './CommentTree.scss'
import LoadMoreButton from '../postlist/LoadMoreButton'
import LoadingScreen from '../loadingscreen/LoadingScreen'

import { fetchComments } from '../../actions/postInfoActions'

class CommentTree extends Component {

    render() {

        if (this.props.successful)
            return (
                <div className={`container ${styles.div}`}>
                    {this.props.comments.map((comment) => {

                        return (
                            <div className={`${styles.toplevelComments}`}>
                                <Comment
                                    key={comment.commentId}
                                    commentId={comment.commentId}
                                    content={comment.content}
                                    name={comment.name}
                                    email={comment.email}
                                    timeDiff={comment.timeDiff}
                                    replies={comment.replies.comments}
                                />
                                <br />
                            </div>
                        )
                    })}

                    <LoadMoreButton
                        loadMore={this.props.loadMoreComments.bind(this)}
                        nextPostsResource={this.props.nextCommentsResource} />
                </div>
            )
        else
            return (
                <LoadingScreen />
            )
    }
}

const mapStateToProps = (state) => ({
    successful: state.postInfo.successful,
    comments: state.postInfo.comments,
    nextCommentsResource: state.postInfo.nextResource
})

const mapDispatchToProps = (dispatch) => ({
    loadMoreComments: (resource) => dispatch(fetchComments(resource, true)),
})

export default connect(mapStateToProps, mapDispatchToProps)(CommentTree)