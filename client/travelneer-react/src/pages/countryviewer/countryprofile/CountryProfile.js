import React, { Component } from 'react'
import FollowButton from './FollowButton'
import CountryDetails from './CountryDetails'

import { fetchCountryInfo, 
    fetchPostsCount,
    followCountry } from '../../../actions/countryInfoActions'
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
                    followed={this.props.countryFollowed} />
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    profileImageUrl: state.countryInfo.profileImageUrl,

    postsCountResource: state.countryInfo.postsCountResource,
    followersCountResource: state.countryInfo.followersCountResource,
    countryName: state.countryInfo.countryName,
    countryId: state.countryInfo.countryId,
    postsCount: state.countryInfo.postsCount,
    successful: state.countryInfo.successful,
    loading: state.countryInfo.loading,
    countryFollowed: state.countryInfo.followed,
    followResource: state.countryInfo.followResource,
    followCountryLoading: state.countryInfo.followLoading,
    followCountrySuccessful: state.countryInfo.followSuccessful,
})

const mapDispatchToProps = (dispatch) => ({
    fetchCountryInfo: (countryId) => dispatch(fetchCountryInfo(countryId)),
    followCountry: (resource, method) => dispatch(followCountry(resource, method)),
    fetchPostsCount: (resource) => dispatch(fetchPostsCount(resource))  
})

export default connect(mapStateToProps, mapDispatchToProps)(CountryProfile)