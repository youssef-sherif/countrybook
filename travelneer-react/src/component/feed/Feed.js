import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import { fetchPosts } from '../../actions/postsActions'
import PostList from './postlist/PostList'
import LoadingScreen from '../loadingscreen/LoadingScreen'
import AppNavbar from '../navigation/app/AppNavbar'

import new_post from '../../images/new_post.png'
import styles from './Feed.scss'


class Feed extends Component {

    componentWillMount() {
        this.props.fetchPosts()
    }

    listPosts() {
        if (this.props.loading) {
            return <LoadingScreen />
        }
        if (this.props.successful) {
            return <PostList posts={this.props.posts} />
        }
    }

    render() {
        const listPosts = this.listPosts()
        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <div className={styles.div}>
                    {listPosts}
                </div>
                <img className={`btn ${styles.img}`}
                    alt='new post'
                    src={new_post}
                    onClick={() => this.props.navigateTo('/new')} />
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    posts: state.posts.posts,
    successful: state.posts.successful,
    loading: state.posts.loading,
    error: state.posts.error
})


const mapDispatchToProps = (dispatch) => ({
    fetchPosts: () => dispatch(fetchPosts()),
    navigateTo: (path) => dispatch(push(path))
})

export default connect(mapStateToProps, mapDispatchToProps)(Feed)
