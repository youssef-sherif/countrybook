import React, { Component } from 'react'

import styles from  './Login.scss'

class Login extends Component {

    render() {

        return (
            <div className="nav navbar-nav navbar-right">
                <input placeholder="username or email" className={styles.input} />
                <input placeholder="password" className={styles.input} type="password" />
                <button className={`btn ${styles.button}`}>login</button>
            </div>
        )
    }
} export default Login
