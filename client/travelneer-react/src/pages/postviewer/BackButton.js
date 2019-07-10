import React, { Component } from 'react'

import backButton from '../../images/back-button.png'

export default class BackButton extends Component {

    render() {

        return (
            <img
                onClick={(e) => {
                    e.preventDefault();
                    this.props.backButtonPressed(this.props.originalPath);
                }} 
                alt='back button'
                className={this.props.styles.BackButton} src={backButton} 
            />
        )            
        
    }
}