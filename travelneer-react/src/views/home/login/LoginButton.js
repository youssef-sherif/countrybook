
import React, { Component } from 'react'
import { loading } from '../../../images/loading.gif'

export default class LoginButton extends Component {

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