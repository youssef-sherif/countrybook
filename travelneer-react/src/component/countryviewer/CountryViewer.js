import React, { Component } from 'react'
import AppNavbar from '../navigation/app/AppNavbar'
import PostList from '../feed/postlist/PostList'
import CountryProfile from './countryprofile/CountryProfile'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import { fetchCountryPosts } from '../../actions/postsActions'
import { fetchCountryInfo } from '../../actions/countryProfileActions'
import { newPost, writePost } from '../../actions/postsActions'
import PostArea from '../newpost/postarea/PostArea'

import styles from './CountryViewer.scss'

class CountryViewer extends Component {

    constructor(props) {
        super(props)
        if (props.location.fetchInfo !== false) {
            const countryId = this.props.match.params.countryId
            this.props.fetchCountryInfo(countryId)
        }
    }

    componentDidMount() {
        if (!this.props.compose)
            this.props.fetchCountryPosts(this.props.postsResource);
    }


    newPost() {
        return (
            <PostArea
                writePost={this.props.writePost.bind(this)}
                newPost={this.props.newPost.bind(this)}
                content={this.props.newPostContent}
                successful={this.props.newPostSuccessful}
                loading={this.props.newPostLoading} />
        )
    }

    listPosts() {
        return <PostList posts={this.props.posts}
            loading={this.props.loading}
            successful={this.props.successful} />

    }

    render() {
        const compose = this.newPost()
        const posts = this.listPosts()
        const countryId = this.props.match.params.countryId
        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <CountryProfile countryId={countryId} />
                <br />
                <br />
                <div className={styles.div}>
                    <button className={styles.button}
                        onClick={(e) => {
                            e.preventDefault();
                            this.props.navigateTo({ pathname: `/countries/${countryId}`, fetchInfo: false });
                        }}>
                        Posts
                    </button>
                    <button className={styles.button}
                        onClick={(e) => {      
                            e.preventDefault();                      
                            this.props.navigateTo({ pathname: `/countries/${countryId}/new`, fetchInfo: false });
                        }}>
                        New Post
                    </button>
                </div>
                <br />
                <br />
                <div className={`container ${styles.postsDiv}`}  >
                    {this.props.compose
                        ? compose : posts}
                </div>
            </div>
        )
    }

}


const mapStateToProps = (state) => ({
    posts: state.posts.posts,
    successful: state.posts.successful,
    loading: state.posts.loading,

    newPostSuccessful: state.posts.newPost.successful,
    newPostLoading: state.posts.newPost.loading,
    newPostContent: state.posts.newPost.content,
    error: state.posts.error,

    postsResource: state.countryProfile.postsResource
})

const mapDispatchToProps = (dispatch) => ({
    navigateTo: (e) => dispatch(push(e)),
    newPost: (countryId, content) => dispatch(newPost(countryId, content)),
    writePost: (content) => dispatch(writePost(content)),
    fetchCountryPosts: () => dispatch(fetchCountryPosts()),
    fetchCountryInfo: (countryId) => dispatch(fetchCountryInfo(countryId))
})

export default connect(mapStateToProps, mapDispatchToProps)(CountryViewer)
