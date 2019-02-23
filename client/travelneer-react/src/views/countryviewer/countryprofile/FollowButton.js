import React, { Component } from 'react'

import loading from '../../../images/loading.gif'

export default class FollowButton extends Component {

    render() {

        if (this.props.loading)
            return (<img alt='loading' src={loading} />)

            
        if (this.props.followed)
            return (
                <button className={`btn ${this.props.style}`} type="submit" onClick={(e) => {
                    this.props.followCountry(this.props.resource,  'delete');
                    this.props.toggleFollowed(false);
                }}>
                    unfollow
                </button>
            )
        else
            return (
                <button className={`btn ${this.props.style}`} type="submit" onClick={(e) => {
                    this.props.followCountry(this.props.resource, 'put');
                    this.props.toggleFollowed(true);
                }}>
                    follow
                </button>
            )
    }
}