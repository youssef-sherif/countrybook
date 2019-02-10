import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import { newPost, writePost, showNew } from '../../../actions/newPostActions'
import {fetchPosts} from '../../../actions/postsActions'
import styles from './PostArea.scss'


class PostArea extends Component {

    render() {
        return (
            <div className={`container ${styles.div}`}>
                <textarea className={`${styles.textarea}`}
                    placeholder={this.props.text != null? this.props.text : `What's happening in ${this.props.countryName}`}
                    id="" cols="20"
                    rows="10"
                    maxLength='500'
                    onChange={(e) => this.props.writePost(e.target.value)}
                    >
                </textarea>
                <div className={styles.buttonsDiv}>
                    <button className={`btn ${styles.button}`} onClick={(e) => {
                            this.props.newPost(this.props.countryId, this.props.content);
                            this.props.location != null? 
                                this.props.navigateTo(this.props.location)
                                :
                                setTimeout(() => {
                                    if(this.props.refresh) {
                                        this.props.fetchPosts();
                                    } else {
                                        this.props.showNew(false);
                                    }
                                }, 500)
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
    selectedCountry: state.countries.selectedCountry
})

const mapDispatchToProps = (dispatch) => ({
    newPost: (countryId, content) => dispatch(newPost(countryId, content)),
    writePost: (content) => dispatch(writePost(content)),   
    fetchPosts: () => dispatch(fetchPosts()),
    navigateTo: (path) => dispatch(push(path)),
    showNew: (showNewState) => dispatch(showNew(showNewState))
})

export default connect(mapStateToProps, mapDispatchToProps)(PostArea)