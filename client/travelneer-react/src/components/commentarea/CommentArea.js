import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import { writePost, clearArea } from '../../actions/newPostActions'
import styles from './CommentArea.scss'


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
                <textarea className={`${styles.textarea}`}
                    placeholder={`reply to ${this.props.parentPostAuthorName}`}
                    id="" cols="20"
                    rows="10"
                    maxLength='500'
                    onChange={(e) => this.props.writePost(e.target.value)} />

                <div className={styles.buttonsDiv}>
                    {
                        this.props.content.length === 0 ?
                            <button className={`btn ${styles.disabledButton}`}>
                                write something
                            </button>
                            :
                            <button className={`btn ${this.props.content.length === 0 ?
                                styles.disabledButton : styles.button}`}
                                onClick={(e) => {
                                    this.props.newComment(this.props.parentPostId, this.props.parentCommentId, this.props.content);
                                    if (this.props.isDirectReplyToPost === false) this.props.closeCommentArea();
                                    this.props.clearArea();
                                }}>
                                comment
                            </button>
                    }
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
    clearArea: () => dispatch(clearArea()),
    navigateTo: (path) => dispatch(push(path)),
})

export default connect(mapStateToProps, mapDispatchToProps)(CommentArea)