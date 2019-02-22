import React, { Component } from 'react'
import AppNavbar from '../navigation/app/AppNavbar'
import PostList from '../feed/postlist/PostList'
import CountryProfile from './countryprofile/CountryProfile'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import { fetchCountryInfo} from '../../actions/countryProfileActions'
import { newPost2 } from '../../actions/newPostActions'
import PostArea from '../newpost/postarea/PostArea'

import styles from './CountryViewer.scss'
import NewPostIndicator from '../feed/newpostindicator/NewPostIndicator';

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

    getButtons = () => {
        return(
            <div className={styles.buttonsDiv}>
                <button className={`btn ${styles.btn} ${styles.postsButton}`}
                    onClick={(e) => {                            
                        this.props.navigateTo({ pathname: `/countries/${this.props.match.params.countryId}` });
                    }}>
                    Posts
                </button>            
                <button className={`btn ${styles.btn} ${styles.newPostButton}`}
                    onClick={(e) => {      
                        this.props.navigateTo({ pathname: `/countries/${this.props.match.params.countryId}/new`});
                    }}>
                    New
                </button>
            </div>)
    }

    render() {
        const postsDiv = this.getPostsDiv()
        const buttons = this.getButtons()

        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                
                <CountryProfile countryId={this.props.match.params.countryId} />

                <br /><br />

                {buttons}

                <br /><br />
                
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
