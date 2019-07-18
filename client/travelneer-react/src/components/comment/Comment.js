import React, { Component } from 'react'

import styles from './Comment.scss'
import { connect } from 'react-redux'
import PostHeader from '../postheader/PostHeader'
import CommentArea from '../commentarea/CommentArea'
import { newComment } from '../../actions/newCommentActions'
import { Link } from 'react-router-dom'
import { continueThread } from '../../actions/threadsActions'

class Comment extends Component {

    constructor(props) {
        super(props);
        this.state = { isReplying: false };
    }

    render() {
        return (
            <div className={styles.story}>
                <PostHeader
                    user={this.props.user}
                    email={this.props.email}
                    timeDiff={this.props.timeDiff}
                />

                <blockquote className={styles.content}>
                    {this.props.content}
                </blockquote>

                <div className={`${styles.actions}`}>
                    <i className={`col-sm-6 col-xs-6 col-lg-6 col-md-6 glyphicon glyphicon-comment ${styles.commentIcon}`}
                        onClick={() => {
                            this.setState((state, props) => {
                                return {
                                    isReplying: !state.isReplying
                                };
                            });
                        }}
                    />
                </div>

                {this.state.isReplying ?
                    <div className={styles.replyArea}>
                        <CommentArea
                            isReplying={this.state.isReplying}
                            parentPostAuthorName={this.props.name}
                            parentPostId={this.props.parentPostId}
                            parentCommentId={this.props.commentId}
                            newComment={this.props.newComment.bind(this)}                            
                        />
                    </div>
                    :
                    <br />}

                {this.props.replies === null ?
                    <div />
                    :
                    this.props.maxDepth > 0 ?
                        this.props.replies.map((comment) => {
                            return (
                                <div>
                                    <Comment
                                        key={comment.commentId}
                                        commentId={comment.commentId}
                                        parentPostId={this.props.parentPostId}
                                        parentCommentId={comment.parentCommentId}
                                        content={comment.content}
                                        name={comment.name}
                                        email={comment.email}
                                        timeDiff={comment.timeDiff}
                                        replies={comment.replies.comments}
                                        newComment={this.props.newComment}
                                        maxDepth={this.props.maxDepth - 1}
                                        countryCode={this.props.countryCode}                                        
                                    />
                                </div>
                            )
                        })
                        :                         
                        <Link 
                            to={`/c/${this.props.countryCode}/posts/${this.props.parentPostId}/threads/${this.props.commentId}`}
                            onClick={() => {
                                this.props.continueThread(this.props.parentPostId, this.props.commentId)
                            }}
                        >
                            continue thread ->
                        </Link>                            
            }

                <br />
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    parentPostId: state.postInfo.postId
})

const mapDispatchToProps = (dispatch) => ({
    newComment: (postId, commentId, content) => dispatch(newComment(postId, commentId, content)),
    continueThread: (postId, commentId) => dispatch(continueThread(postId, commentId))
})

export default connect(mapStateToProps, mapDispatchToProps)(Comment)

