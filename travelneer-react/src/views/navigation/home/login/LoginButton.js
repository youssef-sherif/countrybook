
import React, { Component } from 'react'

export default class LoginButton extends Component {

    render() {
        return (
                <button className={`btn ${this.props.style}`}
                    type='submit'
                    onClick={(e) => {         
                        e.preventDefault();               
                        this.props.login(this.props.usernameOrEmail, this.props.usernameOrEmail, this.props.password);                       
                    }}>
                        Login
                </button>
                )
    }
}