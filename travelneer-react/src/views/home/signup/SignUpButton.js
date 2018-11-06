
import React, { Component } from 'react'
import { loading } from '../../../images/loading.gif'

export default class SignupButton extends Component {

    redirect() {
        if (this.props.loading) {
            return <img src={loading} alt='loading' />
        }
        if (this.props.successful) {
            setTimeout(() => this.props.navigateTo('/feed'), 500);
        }
    }

    render() {
        this.redirect();
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