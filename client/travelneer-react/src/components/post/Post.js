import React, { Component } from 'react'

import styles from './Post.scss'
import { favouritePost, saveScrollPosition } from '../../actions/postsActions'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'

import FavouritesButton from './FavouritesButton'
import PostHeader from '../postheader/PostHeader';

class Post extends Component {

    render() {        
        return (
            <div className={styles.story}>
                <PostHeader 
                    user={this.props.user}
                    email={this.props.email}
                    timeDiff={this.props.timeDiff} 
                />

                <blockquote className={styles.content} onClick={() => {
                    this.props.saveScrollPosition(this.props.originalPath, window.scrollY);
                    this.props.navigateTo(`/post/${this.props.postId}`)
                }}>
                    {this.props.content}
                </blockquote>

                <div className={`container ${styles.actions}`}>
                    <i className={`col-sm-6 col-xs-6 col-lg-6 col-md-6 glyphicon glyphicon-comment ${styles.commentIcon}`}></i>
                    <div className={`col-sm-6 col-xs-6 col-lg-6 col-md-6`}>
                        <FavouritesButton
                            styles={styles}
                            isFavourite={this.props.isFavourite}
                            favourite={this.props.favouritePost.bind(this)}    
                            resource={this.props.favouritesResource}
                            loading={this.props.favouritePostLoading}
                        />
                    </div>
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    favouritePostLoading: state.posts.favouriteLoading,
    favouritePostSuccessful: state.posts.favouriteSuccessful
})

const mapDispatchToProps = (dispatch) => ({
    favouritePost: (resource, method) => dispatch(favouritePost(resource, method)),
    navigateTo: (location) => dispatch(push(location)),
    saveScrollPosition: (originalPath, scrollY) => dispatch(saveScrollPosition(originalPath, scrollY))

})
export default connect(mapStateToProps, mapDispatchToProps)(Post)

