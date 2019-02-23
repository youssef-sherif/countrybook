
import React, { Component } from 'react'
import loading from '../../../images/loading.gif'


export default class SignupButton extends Component {

    render() {      
        if(this.props.loading) 
            return  <img alt='loading' src={loading}/>                       

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