import React, { Component } from 'react'
import Post from '../post/Post'
import LoadingScreen from '../../loadingscreen/LoadingScreen'
import { fetchCountryPosts, fetchPosts } from '../../../actions/postsActions'
import { connect } from 'react-redux'

import styles from './PostList.scss'

 class PostList extends Component {

    componentDidMount() {
        if(this.props.countryPosts) {
            setTimeout(() => this.props.fetchCountryPosts(this.props.countryPostsResource), 500)
        } else if(this.props.feed){
            this.props.fetchPosts()
        }
    }

    render() {     
        if (this.props.loading) {
            return <LoadingScreen />
        }   
        return (
            <div className={`container ${styles.div}`}>
                {this.props.posts.map((post) => {                    
                    return <Post key={post.postId}
                        content={post.content}
                        name={post.name}                    
                        email={post.email} 
                        timeDiff={post.timeDiff}
                        favourite={post.favourite}/>
                })}
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    posts: state.posts.posts,
    successful: state.posts.successful,
    loading: state.posts.loading,

    countryPostsResource: state.countryProfile.postsResource
})

const mapDispatchToProps = (dispatch) => ({
    fetchPosts: () => dispatch(fetchPosts()),
    fetchCountryPosts: (postsResource) => dispatch(fetchCountryPosts(postsResource)),
})
export default connect(mapStateToProps, mapDispatchToProps)(PostList)