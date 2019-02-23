import React, { Component } from 'react'
import LoginNavbar from './login/LoginNavbar'

import logo from '../../../images/logo.png'
import styles from './HomeNavbar.scss'

import { connect } from 'react-redux'
import { authorizeUserHome } from '../../../actions/authActions'


class HomeNavbar extends Component {

    componentDidMount() {
        this.props.authorizeUserHome()
    }

    render() {

        return (
            <nav>
                <div className={`container-fluid`}>
                    <div>
                        <a href="http://localhost:3000/">
                            <img alt="logo" src={logo} />
                        </a>
                    </div>                    
                    <div className={styles.input} >
                        <LoginNavbar />
                    </div>
                </div>
            </nav>
        )
    }
}

const mapDispatchToProps = (dispatch) => ({
    authorizeUserHome: () => dispatch(authorizeUserHome())
  })
  

  const mapStateToProps = (state) => {
    return {
        authSuccessful: state.auth.successful,
        authLoading: state.auth.loading
    }
  }

export default connect(mapStateToProps, mapDispatchToProps)(HomeNavbar)