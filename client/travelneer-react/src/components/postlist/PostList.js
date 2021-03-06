import React, { Component } from 'react'
import Post from '../post/Post'
import { connect } from 'react-redux'

import styles from './PostList.scss'
import LoadMoreButton from './LoadMoreButton'
import LoadingScreen from '../loadingscreen/LoadingScreen'

import { fetchPosts, fetchCountryPosts, fetchMyPosts } from '../../actions/postsActions'

class PostList extends Component {

    getLoadMorebutton = () => {
        console.log(this.props.nextPostsResource)
        if (this.props.fromCountryViewer) {
            return (
                <LoadMoreButton
                    loadMore={this.props.loadMoreCountryPosts.bind(this)}
                    nextPostsResource={this.props.nextPostsResource} />
            )
        }
        else if (this.props.fromProfile) {
            return (
                <LoadMoreButton
                    loadMore={this.props.loadMoreMyPosts.bind(this)}
                    nextPostsResource={this.props.nextPostsResource} />
            )
        }
        else if (this.props.fromFeed) {
            return (
                <LoadMoreButton
                    loadMore={this.props.loadMorePosts.bind(this)}
                    nextPostsResource={this.props.nextPostsResource} />
            )
        }
    }

    render() {

        const loadMoreButton = this.getLoadMorebutton()

        if (this.props.successful) {
            return (
                <div className={`container ${styles.div}`}>
                    {this.props.posts.map((post) => {
                        return <Post
                            key={post.postId}
                            postId={post.postId}
                            content={post.content}
                            name={post.name}
                            email={post.email}
                            timeDiff={post.timeDiff}
                            isFavourite={post.favourite}
                            favouritesResource={post._links.favourite.href}
                            originalPath={this.props.originalPath}
                            countryCode={post.countryCode}
                        />
                    })}

                    {this.props.posts.length >= 10?                
                        loadMoreButton
                        :
                        <div />
                    }
                </div>
            )
        }
        else
            return (
                <LoadingScreen />
            )
    }
}

const mapStateToProps = (state) => ({
    posts: state.posts.posts,
    successful: state.posts.successful,
    loading: state.posts.loading,
    favouritesResource: state.posts.favouritesResource,
    countryPostsResource: state.countryInfo.postsResource,
    nextPostsResource: state.posts.nextResource,
})

const mapDispatchToProps = (dispatch) => ({
    loadMorePosts: (resource) => dispatch(fetchPosts(resource, true)),
    loadMoreCountryPosts: (resource) => dispatch(fetchCountryPosts(resource, true)),
    loadMoreMyPosts: (resource) => dispatch(fetchMyPosts(resource, true))
})

export default connect(mapStateToProps, mapDispatchToProps)(PostList)