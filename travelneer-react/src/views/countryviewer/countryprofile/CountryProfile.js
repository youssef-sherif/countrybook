import React, { Component } from 'react'
import FollowButton from './FollowButton'
import CountryDetails from './CountryDetails'

import { fetchCountryInfo, 
    fetchPostsCount,
    toggleFollowed } from '../../../actions/countryProfileActions'
import { followCountry } from '../../../actions/followCountryActions'
import { connect } from 'react-redux'
import styles from './CountryProfile.scss'


class CountryProfile extends Component {

    render() {
        return (
            <div className={`container ${styles.div}`}>
                <img className={styles.img}
                    alt='country flag'
                    src={this.props.profileImageUrl} />

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
                    loading={this.props.followCountryLoading}
                    successful={this.props.followCountrySuccessful}
                    toggleFollowed={this.props.toggleFollowed.bind(this)}
                    followed={this.props.countryFollowed} />
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    profileImageUrl: state.countryProfile.profileImageUrl,

    postsCountResource: state.countryProfile.postsCountResource,
    followersCountResource: state.countryProfile.followersCountResource,
    countryName: state.countryProfile.countryName,
    countryId: state.countryProfile.countryId,
    postsCount: state.countryProfile.postsCount,
    successful: state.countryProfile.successful,
    loading: state.countryProfile.loading,
    countryFollowed: state.countryProfile.followed,
    followResource: state.countryProfile.followResource,

    followCountryLoading: state.followCountry.loading,
    followCountrySuccessful: state.followCountry.followSuccessful,
})

const mapDispatchToProps = (dispatch) => ({
    fetchCountryInfo: (countryId) => dispatch(fetchCountryInfo(countryId)),
    followCountry: (resource, method) => dispatch(followCountry(resource, method)),
    fetchPostsCount: (resource) => dispatch(fetchPostsCount(resource)),
    toggleFollowed: (bool) => dispatch(toggleFollowed(bool))
    
})

export default connect(mapStateToProps, mapDispatchToProps)(CountryProfile)