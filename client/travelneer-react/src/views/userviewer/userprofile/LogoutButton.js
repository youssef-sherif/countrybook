
import React, { Component } from 'react'


export default class LoginButton extends Component {

    logout = () => {
        localStorage.removeItem('token')
        localStorage.setItem('logged_in', 'false')
        window.location.reload()    
    }

    render() {
            
        return (
                <button className={`btn ${this.props.style}`}
                    type='submit'
                    onClick={(e) => {         
                        e.preventDefault();               
                        this.logout(); 
                    }}>
                        Logout
                </button>
                )
    }
}