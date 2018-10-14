import React, { Component } from 'react'

import loading from '../../loading.gif'

export default class FollowButton extends Component {

    render() {

        if (this.props.isLoading)
            return (<img alt='loading' src={loading} />)

            
        if (this.props.isFollowed)
            return (
                <button type="submit" onClick={(e) => {
                    e.preventDefault();
                    this.props.followCountry(this.props.resource, 'unfollow', this.props.countryId);
                }}>
                    unfollow
                </button>
            )
        else
            return (
                <button type="submit" onClick={(e) => {
                    e.preventDefault();
                    this.props.followCountry(this.props.resource, 'follow', this.props.countryId);
                }}>
                    follow
                </button>
            )
    }
}