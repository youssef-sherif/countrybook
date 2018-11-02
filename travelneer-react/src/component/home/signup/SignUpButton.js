
import React, { Component } from 'react'
import { loading } from '../../loading.gif'

export default class SignupButton extends Component {

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
            <div>
                <button className={'btn btn-block'}
                    type='submit'
                    onClick={(e) => {
                        e.preventDefault();
                        this.props.createUser(this.props.username, this.props.email, this.props.password)
                        this.redirect();
                    }}>
                    Sign Up
                </button>
            </div>
        )
    }
}