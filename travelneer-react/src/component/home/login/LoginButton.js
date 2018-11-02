
import React, { Component } from 'react'
import { loading } from '../../loading.gif'

export default class LoginButton extends Component {

    redirect() {
        if (this.props.loading) {
            return <img src={loading} alt='loading' />
        }
        if (this.props.successful) {
            this.props.navigateTo('/feed')
        }
    }

    render() {
        return (
                <button className={`btn ${this.props.style}`}
                    type='submit'
                    onClick={(e) => {
                        e.preventDefault();
                        this.props.login(this.props.usernameOrEmail, this.props.usernameOrEmail, this.props.password)
                        this.redirect();
                    }}>
                        Login
                    </button>
                )
    }
}