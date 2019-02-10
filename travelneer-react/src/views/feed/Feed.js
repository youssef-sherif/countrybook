import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import PostList from './postlist/PostList'
import AppNavbar from '../navigation/app/AppNavbar'

import new_post from '../../images/new_post.png'
import styles from './Feed.scss'
import NewPost from '../newpost/NewPost';
import { showNew } from '../../actions/newPostActions';
import NewPostIndicator from './NewPostIndicator';


class Feed extends Component {

    render() {
        return (
            <div>
                <AppNavbar />
                <br/><br/><br/>
                <div className={this.props.showNewState? styles.NewPostShow : styles.NewPostHide}>
                    <NewPost refresh={false}/>
                </div>

                <div>
                    {this.props.showNewState?
                        <div />
                            :
                        <NewPost />
                    }              
                    <br/>      
                    <PostList feed={true}/>
                </div>

                <img className={`btn ${styles.newPostImg}`}
                    alt='new post'
                    src={new_post}
                    onClick={() => {
                        this.props.showNew(!this.props.showNewState)
                    }} />

                    <NewPostIndicator
                        successful={this.props.newPostSuccessful}
                        loading={this.props.newPostLoading} 
                        error={this.props.newPostError}
                        errorMessage={this.props.newPostErrorMessage}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    showNewState: state.newPost.showNew,
    newPostSuccessful: state.newPost.successful,
    newPostLoading: state.newPost.loading,
    newPostError: state.newPost.error,
    newPostErrorMessage: state.newPost.errorMessage
})


const mapDispatchToProps = (dispatch) => ({
    navigateTo: (path) => dispatch(push(path)),
    showNew: (showNewState) => dispatch(showNew(showNewState))
})

export default connect(mapStateToProps, mapDispatchToProps)(Feed)
