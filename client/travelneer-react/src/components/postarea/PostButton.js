
import React, { Component } from 'react'


export default class PostButton extends Component {

    getButton = () => {
        if (localStorage.getItem('logged_in') === 'false') {
            return (
                <button className={`btn ${this.props.styles.disabledButton}`}>
                    Login or Sign up to start posting
                </button>
            )
        } else if (this.props.content.length === 0 || this.props.countryName === "(choose a country)") {
            return (
                <button className={`btn ${this.props.styles.disabledButton}`}>
                    write something and select a country'
                </button>
            )
        } else {
            return (
                <button className={`btn ${this.props.styles.button}`}
                    onClick={(e) => {
                        this.props.newPost(this.props.countryCode, this.props.content, this.props.refresh);
                        this.props.clearArea();
                    }}>
                    post
                </button>
            )
        }
    }

    render() {

        const button = this.getButton()

        return (
            <div className={this.props.styles.buttonsDiv}>
                {button}
            </div>
        )
    }
}