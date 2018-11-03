import React, { Component } from 'react'

import loading from '../../../images/loading.gif'

export default class FollowButton extends Component {

    render() {

        if (this.props.isLoading)
            return (<img alt='loading' src={loading} />)

            
        if (this.props.isFollowed)
            return (
                <button className={`btn ${this.props.style}`} type="submit" onClick={(e) => {
                    e.preventDefault();
                    //this.props.unFollowCountry(this.props.resource,  this.props.countryId);
                }}>
                    unfollow
                </button>
            )
        else
            return (
                <button className={`btn ${this.props.style}`} type="submit" onClick={(e) => {
                    e.preventDefault();
                    this.props.followCountry(this.props.resource, this.props.countryId);
                }}>
                    follow
                </button>
            )
    }
}