import React, { Component } from 'react'
import AppNavbar from '../navigation/app/AppNavbar'
import PostList from '../feed/postlist/PostList'
import CountryProfile from './countryprofile/CountryProfile'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import { fetchCountryInfo} from '../../actions/countryProfileActions'
import { newPost2 } from '../../actions/newPostActions'
import PostArea from '../newpost/postarea/PostArea'

import styles from './CountryViewer.scss'
import NewPostIndicator from '../feed/NewPostIndicator';

class CountryViewer extends Component {

    componentDidMount() {        
        this.props.fetchCountryInfo(this.props.match.params.countryId)
    }

    onPost = () => {
        console.log("success")
    }

    getPostsDiv = () => {

        return (
            <div>
                {this.props.compose ?
                <PostArea  countryId={this.props.match.params.countryId}
                    countryName={this.props.countryName}
                    onPost={this.onPost.bind(this)}
                    newPost={this.props.newPost2.bind(this)}/> 
                :
                <PostList />
                }
                
            </div>)
    }

    render() {
        const countryId = this.props.match.params.countryId
        const postsDiv = this.getPostsDiv();
        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <CountryProfile countryId={countryId} />
                <br />
                <br />
                <div className={styles.buttonsDiv}>
                    <button className={`btn ${styles.btn} ${styles.postsButton}`}
                        onClick={(e) => {                            
                            this.props.navigateTo({ pathname: `/countries/${countryId}` });
                        }}>
                        Posts
                    </button>            
                    <button className={`btn ${styles.btn} ${styles.newPostButton}`}
                        onClick={(e) => {      
                            this.props.navigateTo({ pathname: `/countries/${countryId}/new`});
                        }}>
                        New
                    </button>
                </div>
                <br />
                <br />
                
                {postsDiv}

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
    countryName: state.countryProfile.countryName,
    loading: state.posts.loading,
    newPostSuccessful: state.newPost.successful,
    newPostLoading: state.newPost.loading,
    newPostError: state.newPost.error,
    newPostErrorMessage: state.newPost.errorMessage
})

const mapDispatchToProps = (dispatch) => ({
    navigateTo: (e) => dispatch(push(e)),
    fetchCountryInfo: (countryId) => dispatch(fetchCountryInfo(countryId)).countryProfile,
    newPost2: (countryId, content) =>  dispatch(newPost2(countryId, content)),

})

export default connect(mapStateToProps, mapDispatchToProps)(CountryViewer)
