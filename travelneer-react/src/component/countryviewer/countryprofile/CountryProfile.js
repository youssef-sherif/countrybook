import React, { Component } from 'react'
import FollowButton from './FollowButton'
import CountryDetails from './CountryDetails'

import { fetchCountryInfo } from '../../../actions/countryProfileActions'
import { followCountry } from '../../../actions/followCountryActions'
import { connect } from 'react-redux'
import styles from './CountryProfile.scss'


class CountryProfile extends Component {

    render() {
        return (
            <div className={`container ${styles.div}`}>
                <img className={styles.img}
                    alt='country flag'
                    src={this.props.profileURL} />

                <CountryDetails countryName={this.props.countryName}
                    followersCount={this.props.followersCount}
                    postsCount={this.props.postsCount} />

                <FollowButton className={styles.FollowButton}
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

    postsCount: state.countryProfile.postsCount,
    followersCount: state.countryProfile.followersCount,
    countryName: state.countryProfile.countryName,
    countryId: state.countryProfile.countryId,

    successful: state.countryProfile.successful,
    loading: state.countryProfile.loading,
    isCountryFollowed: state.countryProfile.followed,
    followResource: state.countryProfile.followResource,
    followCountryLoading: state.followCountry.loading,
    followCountrySuccessful: state.followCountry.successful,
})

const mapDispatchToProps = (dispatch) => ({
    fetchCountryInfo: (countryId) => dispatch(fetchCountryInfo(countryId)),
    followCountry: (resource, countryId) => dispatch(followCountry(resource, countryId))
})

export default connect(mapStateToProps, mapDispatchToProps)(CountryProfile)