import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import PostList from '../../components/postlist/PostList'
import AppNavbar from '../../components/appnavbar/AppNavbar'
import CountrySelect from '../../components/countryselect/CountrySelect'
import PostArea from '../../components/postarea/PostArea'

import newPostImg from '../../images/new-post.png'
import styles from './Feed.scss'
import { showCollapsablePostArea, newPost } from '../../actions/newPostActions'
import Indicator from '../../components/indicator/Indicator'

import { fetchPosts } from '../../actions/postsActions'

class Feed extends Component {

    componentDidMount() {
        if(this.props.backButtonPressed === true) {
            window.scrollTo(0, this.props.scrollPositionY)
        } else {
            this.props.fetchPosts()
        }        
    }

    getCollapsablePostArea = () => {
        return (
            <div className={`container ${this.props.collapsablePostAreaState? styles.NewPostShow : styles.NewPostHide}`}>                    
                <br/><br/><br/>
                <CountrySelect />
                <PostArea 
                    refresh={false}
                    countryId={this.props.countryId} 
                    countryName={this.props.countryName === "" ? 
                        "(choose a country)" : this.props.countryName}
                    newPost={this.props.newPost.bind(this)} 
                />                                            
            </div>
        )
    }

    getPostArea = () => {
        return(
            <div>
                {this.props.collapsablePostAreaState?
                    <div />
                    :
                    <div className={`container`}  >
                        <CountrySelect />
                        <PostArea 
                            refresh={true}
                            countryId={this.props.countryId} 
                            countryName={this.props.countryName === "" ? 
                            "(choose a country)" : this.props.countryName}
                            newPost={this.props.newPost.bind(this)} 
                        />                        
                    </div>
                }              
                <br/>      
                <PostList 
                    fromFeed={true}
                    originalPath={"/feed"}
                />
            </div>)
    }

    getNewPostButton = () => {
        return (
            <img className={`btn ${styles.newPostImg}`}
                alt='new post'
                src={newPostImg}
                onClick={() => {
                    this.props.showCollapsablePostArea(!this.props.collapsablePostAreaState)
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

                <Indicator
                    successful={this.props.newPostSuccessful}
                    loading={this.props.newPostLoading} 
                    error={this.props.newPostError}
                    errorMessage={this.props.newPostErrorMessage}/>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    collapsablePostAreaState: state.newPost.collapsablePostAreaState,
    newPostSuccessful: state.newPost.successful,
    newPostLoading: state.newPost.loading,
    newPostError: state.newPost.error,
    newPostErrorMessage: state.newPost.errorMessage,
    countryId: state.countries.selectedCountryId,
    countryName: state.countries.selectedCountryName,
    countries: state.countries.countries,
    scrollPositionY: state.posts.scrollPositionY,
    backButtonPressed: state.posts.backButtonPressed
})


const mapDispatchToProps = (dispatch) => ({
    navigateTo: (path) => dispatch(push(path)),
    showCollapsablePostArea: (showNewState) => dispatch(showCollapsablePostArea(showNewState)),
    newPost: (countryId, content, refresh) =>  dispatch(newPost(countryId, content, refresh)),
    fetchPosts: () => dispatch(fetchPosts())
})

export default connect(mapStateToProps, mapDispatchToProps)(Feed)
