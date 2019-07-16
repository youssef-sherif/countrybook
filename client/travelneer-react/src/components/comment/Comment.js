import React, { Component } from 'react'

import styles from './Comment.scss'
import { connect } from 'react-redux'
import PostHeader from '../postheader/PostHeader'
import CommentArea from '../commentarea/CommentArea';
import { newComment } from '../../actions/newCommentActions'

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
                        this.props.replies.map((comment) => {
                        return (
                            <div>
                                <Comment
                                    key={comment.commentId}
                                    commentId={comment.commentId}
                                    parentPostId={this.props.parentPostId}
                                    content={comment.content}
                                    name={comment.name}
                                    email={comment.email}
                                    timeDiff={comment.timeDiff}
                                    replies={comment.replies.comments}         
                                    newComment={this.props.newComment}                        
                                />
                            </div>
                        )
                    })                
                
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
})

export default connect(mapStateToProps, mapDispatchToProps)(Comment)

