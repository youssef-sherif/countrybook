import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import { writePost } from '../../actions/newPostActions'
import styles from './CommentArea.scss'


class CommentArea extends Component {

    render() {
        return (
            <div className={`container ${this.props.collapsable === true ? styles.div :
                                    this.props.isReplying === true ? 
                                        styles.commentAreaVisible : styles.commentAreaInvisible
                                    }`}>
                <textarea className={`${styles.textarea}`}
                    placeholder={`reply to ${this.props.parentPostAuthorName}`}
                    id="" cols="20"
                    rows="10"
                    maxLength='500'
                    onChange={(e) => this.props.writePost(e.target.value)} />

                <div className={styles.buttonsDiv}>
                    <button className={`btn ${styles.button}`} onClick={(e) => {
                        this.props.newComment(this.props.parentPostId, this.props.parentCommentId, this.props.content);
                    }}>
                        comment
                    </button>
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    content: state.newPost.content
})

const mapDispatchToProps = (dispatch) => ({
    writePost: (content) => dispatch(writePost(content)),
    navigateTo: (path) => dispatch(push(path)),
})

export default connect(mapStateToProps, mapDispatchToProps)(CommentArea)