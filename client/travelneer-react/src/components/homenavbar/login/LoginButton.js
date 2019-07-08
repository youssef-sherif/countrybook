
import React, { Component } from 'react'
import loading from '../../../images/loading.gif'


export default class LoginButton extends Component {

    render() {
        if(this.props.loading)
            return  <img alt='loading' src={loading}/>              
            
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