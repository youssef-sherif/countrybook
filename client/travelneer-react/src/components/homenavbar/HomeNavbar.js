import React, { Component } from 'react'
import LoginNavbar from './login/LoginNavbar'

import logo from '../../images/logo.png'
import styles from './HomeNavbar.scss'

import { Link } from 'react-router-dom'


export default class HomeNavbar extends Component {

    render() {

        return (
            <div className={`navbar navbar-fixed-top ${styles.navbar}`}>
                <div className={`container-fluid`}>
                    <div>                     
                        <Link to="/">
                            <img alt="logo" src={logo} />       
                        </Link>                 
                    </div>                    
                    <div className={styles.input} >
                        <LoginNavbar />
                    </div>
                </div>
            </div>
        )
    }
}