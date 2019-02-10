import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import { writePost, showNew } from '../../../actions/newPostActions'
import {fetchPosts} from '../../../actions/postsActions'
import styles from './PostArea.scss'


class PostArea extends Component {

    render() {
        return (
            <div className={`container ${styles.div}`}>
                <textarea className={`${styles.textarea}`}
                    placeholder={`What's happening in ${this.props.countryName}`}
                    id="" cols="20"
                    rows="10"
                    maxLength='500'
                    onChange={(e) => this.props.writePost(e.target.value)}/>
                                    
                <div className={styles.buttonsDiv}>
                    <button className={`btn ${styles.button}`} onClick={(e) => {
                            this.props.newPost(this.props.countryId, this.props.content, this.props.refresh);  
                            this.props.onPost();                  
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
    content: state.newPost.content,
    showNewState: state.newPost.showNew,
    selectedCountry: state.countries.selectedCountry,
    newPostSuccessful: state.newPost.successful,
    newPostLoading: state.newPost.loading,
    newPostError: state.newPost.error
})

const mapDispatchToProps = (dispatch) => ({
    writePost: (content) => dispatch(writePost(content)),   
    fetchPosts: () => dispatch(fetchPosts()),
    navigateTo: (path) => dispatch(push(path)),
    showNew: (showNewState) => dispatch(showNew(showNewState))
})

export default connect(mapStateToProps, mapDispatchToProps)(PostArea)