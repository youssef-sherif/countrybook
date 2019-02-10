
import React, { Component } from 'react'

export default class SignupButton extends Component {

    render() {        
        return (
                <button className={'btn btn-block'}
                    type='submit'
                    onClick={(e) => {     
                        e.preventDefault();               
                        this.props.createUser(this.props.username, this.props.email, this.props.password);
                    }}>
                    Sign Up
                </button>
        )
    }
}