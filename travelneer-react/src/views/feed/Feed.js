import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import PostList from './postlist/PostList'
import AppNavbar from '../navigation/app/AppNavbar'
import CountrySelect from '../newpost/countryselect/CountrySelect'
import PostArea from '../newpost/postarea/PostArea'

import new_post from '../../images/new_post.png'
import styles from './Feed.scss'
import { showNew, newPost } from '../../actions/newPostActions'
import NewPostIndicator from './NewPostIndicator'

import { fetchPosts } from '../../actions/postsActions'

class Feed extends Component {

    componentDidMount() {
        this.props.fetchPosts()
    }
    
    onPost = () => {
        this.props.showNew(false);    
    }    

    getCollapsablePostArea = () => {
        return (
            <div className={`container ${this.props.showNewState? styles.NewPostShow : styles.NewPostHide}`}>                    
                <br/><br/><br/>
                <CountrySelect />
                <PostArea refresh={false}
                    countryId={this.props.countryId} 
                    countryName={this.props.countryName === "" ? 
                        "(choose a country)" : this.props.countryName}
                    onPost={this.onPost.bind(this)}
                    newPost={this.props.newPost.bind(this)} />                                            
            </div>
        )
    }

    getPostArea = () => {
        return(
            <div>
                {this.props.showNewState?
                    <div />
                    :
                    <div className={`container`}  >
                        <CountrySelect />
                        <PostArea refresh={true}
                            countryId={this.props.countryId} 
                            countryName={this.props.countryName === "" ? 
                            "(choose a country)" : this.props.countryName}
                            onPost={this.onPost.bind(this)}
                            newPost={this.props.newPost.bind(this)} />                        
                    </div>
                }              
                <br/>      
                <PostList />
            </div>)
    }

    getNewPostButton = () => {
        return (
            <img className={`btn ${styles.newPostImg}`}
                alt='new post'
                src={new_post}
                onClick={() => {
                    this.props.showNew(!this.props.showNewState)
                }} />
        )
    }

    render() {

        const collapsablePostArea = this.getCollapsablePostArea()
        const postArea = this.getPostArea()
        const newPostButton = this.getNewPostButton()

        return (
            <div>
                <AppNavbar />                
            
                {collapsablePostArea}

                <br/><br/><br/>

                {postArea}

                {newPostButton}

                <NewPostIndicator
                    styles={styles}
                    successful={this.props.newPostSuccessful}
                    loading={this.props.newPostLoading} 
                    error={this.props.newPostError}
                    errorMessage={this.props.newPostErrorMessage}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    showNewState: state.newPost.showNew,
    newPostSuccessful: state.newPost.successful,
    newPostLoading: state.newPost.loading,
    newPostError: state.newPost.error,
    newPostErrorMessage: state.newPost.errorMessage,
    countryId: state.countries.selectedCountryId,
    countryName: state.countries.selectedCountryName,
    countries: state.countries.countries
})


const mapDispatchToProps = (dispatch) => ({
    navigateTo: (path) => dispatch(push(path)),
    showNew: (showNewState) => dispatch(showNew(showNewState)),
    newPost: (countryId, content, refresh) =>  dispatch(newPost(countryId, content, refresh)),
    fetchPosts: () => dispatch(fetchPosts())
})

export default connect(mapStateToProps, mapDispatchToProps)(Feed)
