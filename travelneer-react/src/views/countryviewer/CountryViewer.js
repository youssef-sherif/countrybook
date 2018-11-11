import React, { Component } from 'react'
import AppNavbar from '../navigation/app/AppNavbar'
import PostList from '../feed/postlist/PostList'
import CountryProfile from './countryprofile/CountryProfile'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import { fetchCountryInfo} from '../../actions/countryProfileActions'
import PostArea from '../newpost/postarea/PostArea'

import styles from './CountryViewer.scss'

class CountryViewer extends Component {

    componentDidMount() {  
        const countryId = this.props.match.params.countryId         
        this.props.fetchCountryInfo(countryId)
    }

    getPostsDiv = (countryId) => {

        return (
            <div>
                {this.props.compose ?
                <PostArea  countryId={countryId}
                    countryName={this.props.countryName}
                    location={`/countries/${countryId}`}/> 
                :
                <PostList countryPosts={true} />
                }
                
            </div>)
    }

    render() {
        const countryId = this.props.match.params.countryId
        const postsDiv = this.getPostsDiv(countryId);
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
            </div>
        )
    }

}


const mapStateToProps = (state) => ({
    countryName: state.countryProfile.countryName,
    loading: state.posts.loading
})

const mapDispatchToProps = (dispatch) => ({
    navigateTo: (e) => dispatch(push(e)),
    fetchCountryInfo: (countryId) => dispatch(fetchCountryInfo(countryId))
})

export default connect(mapStateToProps, mapDispatchToProps)(CountryViewer)
