import React, { Component } from 'react'
import AppNavbar from '../../components/appnavbar/AppNavbar'
import { connect } from 'react-redux'
import FavouritesButton from '../../components/post/FavouritesButton'

import styles from './PostViewer.scss'

import { fetchPostInfo } from '../../actions/postInfoActions'
import { favouritePost, backButtonPressed } from '../../actions/postsActions'
import { newComment } from '../../actions/newCommentActions'
import { continueThread } from '../../actions/threadsActions'
import { showCollapsableCommentArea } from '../../actions/newCommentActions'
import PostHeader from '../../components/postheader/PostHeader'
import BackButton from './BackButton'
import CommentArea from '../../components/commentarea/CommentArea'
import Comment from '../../components/comment/Comment'
import CollapsableView from '../../components/collapsableview/CollapsableView'
import CommentTree from '../../components/commenttree/CommentTree'
import { Link } from 'react-router-dom'
import newPostImg from '../../images/new-post.png'

class PostViewer extends Component {

    componentDidMount() {
        this.props.fetchPostInfo(this.props.match.params.postId)    
        if (this.props.commentView === true) {            
            this.props.continueThread(this.props.match.params.postId, this.props.match.params.commentId)                            
        }
    }

    getNewPostButton = () => {
        return (
            <img className={`btn ${styles.newPostImg}`}
                alt='new post'
                src={newPostImg}
                onClick={() => {
                    this.props.showCollapsableCommentArea(true)
                }} />
        )
    }

    getCommentTree = () => {

        if (this.props.commentView === true) {
            
            // this.props.continueThread(this.props.match.params.postId, this.props.match.params.commentId)            

            return (

                <CommentTree>

                    <Link to={`/c/${this.props.match.params.countryCode}/posts/${this.props.match.params.postId}`} >
                        go back to full comment list
                    </Link>
                    {this.props.threadReplies.map((comment) => {

                        return (
                            <div>
                                <Comment
                                    key={comment.commentId}
                                    commentId={comment.commentId}
                                    parentCommentId={comment.parentCommentId}
                                    parentPostId={this.props.match.params.postId}
                                    content={comment.content}
                                    name={comment.name}                                    
                                    email={comment.email}
                                    timeDiff={comment.timeDiff}
                                    replies={comment.replies.comments}
                                    maxDepth={5}
                                    countryCode={this.props.match.params.countryCode}
                                />
                                <br />
                            </div>
                        )
                    })}
                </CommentTree>
            )
        } else {
            return (
                <CommentTree>
                    {this.props.comments.map((comment) => {

                        return (
                            <div>
                                <Comment
                                    key={comment.commentId}
                                    commentId={comment.commentId}
                                    parentCommentId={comment.parentCommentId}
                                    parentPostId={this.props.match.params.postId}
                                    content={comment.content}
                                    name={comment.name}
                                    email={comment.email}
                                    timeDiff={comment.timeDiff}
                                    replies={comment.replies.comments}
                                    maxDepth={5}
                                    countryCode={this.props.match.params.countryCode}
                                />
                                <br />
                            </div>
                        )
                    })}
                </CommentTree>
            )
        }
    }

    render() {

        const newPostButton = this.getNewPostButton()
        const commentTree = this.getCommentTree()

        return (
            <div>
                <AppNavbar />

                <CollapsableView
                    collapsableStyle={styles.collapsable}
                    visible={this.props.commentAreaVisible}
                    showCollapsableArea={this.props.showCollapsableCommentArea.bind(this)}
                >
                    <CommentArea
                        collapsable={true}
                        className={styles.commentArea}
                        parentPostAuthorName={this.props.name}
                        parentPostId={this.props.match.params.postId}
                        newComment={this.props.newComment.bind(this)}
                    />
                </CollapsableView>

                <br /> <br /> <br />

                <BackButton
                    styles={styles}
                    backButtonPressed={this.props.backButtonPressed.bind(this)}
                    originalPath={this.props.originalPath}
                />

                <div className={`container ${styles.div} ${styles.story}`}>

                    <br /><br /><br />

                    <PostHeader
                        name={this.props.name}
                        email={this.props.email}
                        timeDiff={this.props.timeDiff}
                    />

                    <blockquote className={styles.content}>
                        {this.props.content}
                    </blockquote>

                    <div className={`container ${styles.actions}`}>
                        <i className={`col-sm-3 col-xs-3 col-lg-3 col-md-3 glyphicon glyphicon-comment ${styles.commentIcon}`} />
                        <div className={`col-sm-3 col-xs-3 col-lg-3 col-md-3`}>{this.props.commentsCount}</div>


                        <div className={`col-sm-3 col-xs-3 col-lg-3 col-md-3`}>
                            <FavouritesButton
                                styles={styles}
                                isFavourite={this.props.favourite}
                                favourite={this.props.favouritePost.bind(this)}
                                resource={this.props.favouritesResource}
                                loading={this.props.favouritePostLoading}
                            />
                        </div>
                        <div className={`col-sm-3 col-xs-3 col-lg-3 col-md-3`}>{this.props.favouritesCount}</div>
                    </div>

                    <br />

                    <CommentArea
                        isReplying={true}
                        className={styles.commentArea}
                        parentPostAuthorName={this.props.name}
                        parentPostId={this.props.match.params.postId}
                        newComment={this.props.newComment.bind(this)}
                    />
                </div>

                <br /><br />

                {commentTree}

                {newPostButton}

            </div >

        )
    }

}


const mapStateToProps = (state) => ({
    comments: state.postInfo.comments,
    content: state.postInfo.content,
    name: state.postInfo.name,
    email: state.postInfo.email,
    timeDiff: state.postInfo.timeDiff,
    favourite: state.postInfo.favourite,
    favouritesCount: state.postInfo.favouritesCount,
    commentsCount: state.postInfo.commentsCount,
    favouritesResource: state.postInfo.favouritesResource,
    favouritePostLoading: state.posts.favouriteLoading,
    originalPath: state.posts.originalPath,
    commentAreaVisible: state.newComment.commentAreaVisible,
    threadReplies: state.threads.replies
})

const mapDispatchToProps = (dispatch) => ({
    favouritePost: (resource, method) => dispatch(favouritePost(resource, method)),
    fetchPostInfo: (postId) => dispatch(fetchPostInfo(postId)),
    backButtonPressed: (path) => dispatch(backButtonPressed(path)),
    showCollapsableCommentArea: (bool) => dispatch(showCollapsableCommentArea(bool)),
    newComment: (postId, commentId, content) => dispatch(newComment(postId, commentId, content)),
    continueThread: (postId, commentId) => dispatch(continueThread(postId, commentId))
})

export default connect(mapStateToProps, mapDispatchToProps)(PostViewer)
