import React, { Component } from 'react'

import loading from '../../../images/loading.gif'

export default class FavouritesButton extends Component {

    render() {
        if (this.props.loading) {
            return (<img alt='loading' src={loading} />)
        }
        else if (this.props.successful) {
            if(this.props.isFavourite) {
                return (
                    <i onClick={(e) => {                                
                            this.props.favourite(this.props.resource, 'delete');                                
                        }} 
                        className={`col-sm-6 col-xs-6 col-lg-6 col-md-6 glyphicon glyphicon-star ${this.props.styles.goldIcon}`}>
                    </i> 
                )
            }
            else {
               return (
                    <i onClick={(e) => {
                            this.props.favourite(this.props.resource, 'put');
                        }} 
                    className={`col-sm-6 col-xs-6 col-lg-6 col-md-6 glyphicon glyphicon-star ${this.props.styles.icon}`}>
                    </i> 
                )
            }
        } else {
            return (<img alt='loading' src={loading} />)
        }
    }
}