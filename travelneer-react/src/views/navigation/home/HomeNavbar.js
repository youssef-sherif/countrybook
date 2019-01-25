import React, { Component } from 'react'
import LoginNavbar from './login/LoginNavbar'

import logo from '../../../images/logo.png'
import styles from './HomeNavbar.scss'

import { connect } from 'react-redux'
import { push } from 'react-router-redux'
import { authorizeUser } from '../../../actions/authActions'


class HomeNavbar extends Component {

    componentDidMount() {
        this.props.authorizeUser()
        setTimeout(() => {
            if(localStorage.getItem('logged_in')  === 'true') {
                this.props.navigateTo('/feed');
            } 
        }, 500);

    }

    render() {

        return (
            <nav>
                <div className={`container-fluid`}>
                    <div>
                        <a>
                            <img alt="logo" src={logo} />
                        </a>
                    </div>
                    <ul className={`nav navbar-nav navbar-right`}>
                        <li><a id={`${styles.loginBtn}`} href="/login">Login</a></li>
                    </ul>
                    <div className={styles.input} >
                        <LoginNavbar />
                    </div>
                </div>
            </nav>
        )
    }
}

const mapDispatchToProps = (dispatch) => ({
    navigateTo: (e) => dispatch(push(e)),
    authorizeUser: () => dispatch(authorizeUser())
  })
  

  const mapStateToProps = (state) => {
    return {
        authSuccessful: state.auth.successful,
        authLoading: state.auth.loading
    }
  }

export default connect(mapStateToProps, mapDispatchToProps)(HomeNavbar)