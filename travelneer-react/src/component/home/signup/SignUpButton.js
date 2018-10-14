
import React, { Component } from 'react'
import { loading } from './loading.gif'

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
        const redirect = this.redirect();
        return (
            <div>
                <button className={'btn btn-block'}
                    type='submit'
                    onClick={(e) => {
                        e.preventDefault();
                        this.props.createUser(this.props.userName, this.props.email, this.props.password)
                    }}>
                    Sign Up
                </button>
                {redirect}
            </div>
        )
    }
}