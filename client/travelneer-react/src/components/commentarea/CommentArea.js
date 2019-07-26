import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import { writePost, clearArea } from '../../actions/newPostActions'
import styles from './CommentArea.scss'
import CommentButton from './CommentButton'


class CommentArea extends Component {
    
    componentDidMount() {
        this.props.clearArea();
    }

    render() {
        return (
            <div className={`container ${this.props.collapsable === true ? styles.div :
                this.props.isReplying === true ?
                    styles.commentAreaVisible : styles.commentAreaInvisible
                }`}>

                <textarea 
                    className={`${styles.textarea}`}
                    placeholder={`reply to ${this.props.parentPostAuthorName}`}
                    id="" cols="20"
                    rows="10"
                    maxLength='500'
                    onChange={(e) => this.props.writePost(e.target.value)} 
                />

                <CommentButton 
                    styles={styles}
                    newComment={this.props.newComment.bind(this)}
                    clearArea={this.props.clearArea.bind(this)}
                    closeCommentArea={this.props.closeCommentArea}
                    isDirectReplyToPost={this.props.isDirectReplyToPost}
                    parentPostId={this.props.parentPostId}
                    parentCommentId={this.props.parentCommentId}
                    content={this.props.content}
                />

            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    content: state.newPost.content
})

const mapDispatchToProps = (dispatch) => ({
    writePost: (content) => dispatch(writePost(content)),
    clearArea: () => dispatch(clearArea()),
    navigateTo: (path) => dispatch(push(path)),
})

export default connect(mapStateToProps, mapDispatchToProps)(CommentArea)