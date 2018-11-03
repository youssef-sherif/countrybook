import React, { Component } from 'react'
import AppNavbar from '../navigation/app/AppNavbar'
import PostList from '../feed/postlist/PostList'
import CountryProfile from './countryprofile/CountryProfile'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import { fetchCountryPosts } from '../../actions/postsActions'
import { fetchCountryInfo} from '../../actions/countryProfileActions'
import { newPost, writePost } from '../../actions/postsActions'
import PostArea from '../newpost/postarea/PostArea'

import styles from './CountryViewer.scss'

class CountryViewer extends Component {


    componentWillReceiveProps() {
        const countryId = this.props.match.params.countryId         
        this.props.fetchCountryInfo(countryId);
    }

    componentDidMount() {  
        this.props.fetchCountryPosts(this.props.postsResource)   
    }

    render() {
        const countryId = this.props.match.params.countryId
        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <CountryProfile countryId={countryId} />
                <br />
                <br />
                <div className={styles.buttonsDiv}>
                    <button className={`btn ${styles.postsButton}`}
                        onClick={(e) => {                            
                            this.props.navigateTo({ pathname: `/countries/${countryId}` });
                        }}>
                        Posts
                    </button>            
                    <button className={`btn ${styles.newPostButton}`}
                        onClick={(e) => {      
                            this.props.navigateTo({ pathname: `/countries/${countryId}/new`});
                        }}>
                        New
                    </button>
                </div>
                <br />
                <br />
                <div className={`container ${styles.postsDiv}`}  >
                    {this.props.compose ?
                            <PostArea    
                                writePost={this.props.writePost.bind(this)}
                                newPost={this.props.newPost.bind(this)}
                                content={this.props.newPostContent}
                                successful={this.props.newPostSuccessful}
                                loading={this.props.newPostLoading} /> 
                        :
                            <PostList
                                posts={this.props.posts}
                                loading={this.props.loading}
                                successful={this.props.successful} />
                        }
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

    postsResource: state.countryProfile.postsResource
})

const mapDispatchToProps = (dispatch) => ({
    navigateTo: (e) => dispatch(push(e)),
    newPost: (countryId, content) => dispatch(newPost(countryId, content)),
    writePost: (content) => dispatch(writePost(content)),
    fetchCountryPosts: (postsResource) => dispatch(fetchCountryPosts(postsResource)),
    fetchCountryInfo: (countryId) => dispatch(fetchCountryInfo(countryId)),
})

export default connect(mapStateToProps, mapDispatchToProps)(CountryViewer)
