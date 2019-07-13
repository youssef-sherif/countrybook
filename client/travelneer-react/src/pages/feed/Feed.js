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
import CollapsableView from '../../components/collapsableview/CollapsableView';

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
            <CollapsableView 
                visible={this.props.postAreaVisible}
                showCollapsableArea={this.props.showCollapsablePostArea.bind(this)}             
            >                
                <CountrySelect />
                <PostArea 
                    refresh={false}
                    countryCode={this.props.countryCode} 
                    countryName={this.props.countryName === "" ? 
                        "(choose a country)" : this.props.countryName}
                    newPost={this.props.newPost.bind(this)} 
                />                                            
            </CollapsableView>
        )
    }

    getNewPostButton = () => {
        return (
            <img className={`btn ${styles.newPostImg}`}
                alt='new post'
                src={newPostImg}
                onClick={() => {
                    this.props.showCollapsablePostArea(true)
                }} />
        )
    }

    render() {

        const collapsablePostArea = this.getCollapsablePostArea()        
        const newPostButton = this.getNewPostButton()

        return (
            <div>
                <AppNavbar />                
            
                {collapsablePostArea}

                <br/><br/><br/>

                <br/>      
                <PostList 
                    fromFeed={true}
                    originalPath={"/feed"}
                />

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
    postAreaVisible: state.newPost.postAreaVisible,
    newPostSuccessful: state.newPost.successful,
    newPostLoading: state.newPost.loading,
    newPostError: state.newPost.error,
    newPostErrorMessage: state.newPost.errorMessage,
    countryCode: state.countries.selectedCountryCode,
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
