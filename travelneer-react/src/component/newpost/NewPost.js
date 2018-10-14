import React, { Component } from 'react'
import styles from './NewPost.scss'
import AppNavbar from '../navigation/app/AppNavbar'
import CountrySelect from './CountrySelect'
import PostArea from './postarea/PostArea'

import { connect } from 'react-redux'
import { newPost, writePost } from '../../actions/postsActions'
import { fetchCountries } from '../../actions/countryActions'

class NewPost extends Component {

    componentDidMount() {
        this.props.fetchCountries()
    }

    render() {
        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <div className={`container ${styles.div}`}  >
                    <CountrySelect countries={this.props.followedCountries} />
                    <PostArea
                        writePost={this.props.writePost.bind(this)}
                        // newPost={this.props.newPost.bind(this)}                        
                        content={this.props.content}
                        successful={this.props.newPostSuccessful}
                        loading={this.props.newPostLoading} />                        
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({      
    newPostSuccessful: state.posts.newPost.successful,
    newPostLoading: state.posts.newPost.loading,
    newPostError: state.posts.newPost.error,
    content: state.posts.newPost.content,
    followedCountries: state.countries.countries
})


const mapDispatchToProps = (dispatch) => ({
    newPost: (countryId, content) => dispatch(newPost(countryId, content)),
    writePost: (content) => dispatch(writePost(content)),
    fetchCountries: () => dispatch(fetchCountries())
})

export default connect(mapStateToProps, mapDispatchToProps)(NewPost)
