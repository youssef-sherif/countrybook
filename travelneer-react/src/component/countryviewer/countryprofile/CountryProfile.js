import React, { Component } from 'react'
import FollowButton from './FollowButton'
import CountryDetails from './CountryDetails'

import { fetchCountryInfo,
    fetchFollowersCount, 
    fetchPostsCount } from '../../../actions/countryProfileActions'
import { followCountry } from '../../../actions/followCountryActions'
import { connect } from 'react-redux'
import styles from './CountryProfile.scss'


class CountryProfile extends Component {


    componentDidMount() {
        this.props.fetchPostsCount(this.props.postsCountResource)
    }

    render() {
        return (
            <div className={`container ${styles.div}`}>
                <img className={styles.img}
                    alt='country flag'
                    src={this.props.profileURL} />

                <CountryDetails countryName={this.props.countryName}
                    countryId={this.props.countryId}
                    postsCount={this.props.postsCount}
                    loading={this.props.loading}
                    successful={this.props.successful}
                     />
                <br/>     
                <FollowButton style={styles.FollowButton}
                    followCountry={this.props.followCountry.bind(this)}
                    resource={this.props.followResource}
                    countryId={this.props.countryId}
                    isLoading={this.props.followCountryLoading}
                    isSuccessful={this.props.followCountrySuccessful}
                    isFollowed={this.props.isCountryFollowed} />
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    profileURL: state.countryProfile.profileURL,

    postsCountResource: state.countryProfile.postsCountResource,
    followersCountResource: state.countryProfile.followersCountResource,
    countryName: state.countryProfile.countryName,
    countryId: state.countryProfile.countryId,
    postsCount: state.countryProfile.postsCount,
    successful: state.countryProfile.successful,
    loading: state.countryProfile.loading,
    isCountryFollowed: state.countryProfile.followed,
    followResource: state.countryProfile.followResource,

    followCountryLoading: state.followCountry.loading,
    followCountrySuccessful: state.followCountry.successful,
})

const mapDispatchToProps = (dispatch) => ({
    fetchCountryInfo: (countryId) => dispatch(fetchCountryInfo(countryId)),
    followCountry: (resource, countryId) => dispatch(followCountry(resource, countryId)),
    fetchFollowersCount: (resource) => dispatch(fetchFollowersCount(resource)),
    fetchPostsCount: (resource) => dispatch(fetchPostsCount(resource))
    
})

export default connect(mapStateToProps, mapDispatchToProps)(CountryProfile)