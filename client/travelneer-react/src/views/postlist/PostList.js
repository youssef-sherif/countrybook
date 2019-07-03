import React, { Component } from 'react'
import Post from '../post/Post'
import { connect } from 'react-redux'

import styles from './PostList.scss'
import LoadMoreButton from './LoadMoreButton'

import { fetchPosts, fetchCountryPosts } from '../../actions/postsActions'

 class PostList extends Component {

    getLoadMorebutton = () => {

        if (this.props.fromCountryViewer === false)
            return (                
                <LoadMoreButton
                    loadMore={this.props.loadMorePosts.bind(this)}
                    nextPostsResource={this.props.nextPostsResource} />                
            )
        else {
            return (
                <LoadMoreButton
                    loadMore={this.props.loadMoreCountryPosts.bind(this)}
                    nextPostsResource={this.props.nextPostsResource} />
            )
        }
    }

    render() {     

        const loadMoreButton = this.getLoadMorebutton()

        if(this.props.successful)
            return (
            <div className={`container ${styles.div}`}>
                {this.props.posts.map((post) => {                    
                    return <Post key={post.postId}
                                content={post.content}
                                name={post.name}                    
                                email={post.email} 
                                timeDiff={post.timeDiff}
                                isFavourite={post.favourite}
                                favouritesResource={post._links.favourite.href}
                            />
                })}
                
                {loadMoreButton}
            </div>
        )
        else   
            return (<div/>)
    }
}

const mapStateToProps = (state) => ({
    posts: state.posts.posts,
    successful: state.posts.successful,
    loading: state.posts.loading,
    favouritesResource: state.posts.favouritesResource,
    countryPostsResource: state.countryProfile.postsResource,
    nextPostsResource: state.posts.nextResource
})

const mapDispatchToProps = (dispatch) => ({
    loadMorePosts: (resource) => dispatch(fetchPosts(resource, true)),
    loadMoreCountryPosts: (resource) => dispatch(fetchCountryPosts(resource, true))
})

export default connect(mapStateToProps, mapDispatchToProps)(PostList)