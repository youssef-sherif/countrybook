import React, { Component } from 'react'
import Post from '../post/Post'
import LoadingScreen from '../../loadingscreen/LoadingScreen'
import { connect } from 'react-redux'

import styles from './PostList.scss'
import LoadMoreButton from './LoadMoreButton';

 class PostList extends Component {

    render() {     
        if (this.props.loading)
            return ( <LoadingScreen /> )

        else if(this.props.successful)
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

                <LoadMoreButton />
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
    countryPostsResource: state.countryProfile.postsResource
})

export default connect(mapStateToProps, null)(PostList)