import React, { Component } from 'react'
import { connect } from 'react-redux'
import { newPost, writePost } from '../../../actions/newPostActions'
import styles from './PostArea.scss'


class PostArea extends Component {

    render() {
        return (
            <div>
                <textarea className={styles.textarea}
                    placeholder={`What's happening in ${this.props.countryName}`}
                    id="" cols="20"
                    rows="10"
                    maxLength='500'
                    onChange={(e) => this.props.writePost(e.target.value)}
                    >
                </textarea>
                <div className={styles.buttonsDiv}>
                    <button className={`btn ${styles.button}`} onClick={(e) => {
                            this.props.newPost(this.props.countryId, this.props.content)
                        }}>
                        post
                    </button>
                    <button className={`btn ${styles.button}`}>
                        upload
                    </button>
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    content: state.posts.newPost.content,
    newPostSuccessful: state.posts.newPost.successful,
    newPostLoading: state.posts.newPost.loading,
    newPostContent: state.posts.newPost.content,

})

const mapDispatchToProps = (dispatch) => ({
    newPost: (countryId, content) => dispatch(newPost(countryId, content)),
    writePost: (content) => dispatch(writePost(content)),
})

export default connect(mapStateToProps, mapDispatchToProps)(PostArea)