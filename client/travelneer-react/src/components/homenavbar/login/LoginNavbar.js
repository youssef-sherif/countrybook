import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'

import {
  changePassword,
  changeUsernameOrEmail
} from '../../../actions/validationActions'
import {
  login
} from '../../../actions/loginActions'
import styles from './LoginNavbar.scss'
import LoginButton from './LoginButton';
import { Link } from 'react-router-dom'

class LoginNavbar extends Component {

  render() {
    return (
      <div className="nav navbar-nav navbar-right">
        <form className={styles.form}>
          <input className={styles.input} placeholder="username or email"
            onChange={e => this.props.changeUsernameOrEmail(e.target.value)} />
          <input className={styles.input} placeholder="password"
            onChange={e => this.props.changePassword(e.target.value)} type="password" />
          <LoginButton
            style={styles.LoginButton}
            usernameOrEmail={this.props.usernameOrEmail}
            password={this.props.password}
            login={this.props.login.bind(this)}
            loading={this.props.loginLoading}
            successful={this.props.loginSuccessful}
            navigateTo={this.props.navigateTo}
          />
          <Link to="/request-password-reset">
            trouble logging in?
          </Link>
        </form>
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {
    loginLoading: state.login.loading,
    loginSuccessful: state.login.successful,
    usernameOrEmail: state.user.loginUsername,
    password: state.user.loginPassword
  }
}

const mapDispatchToProps = (dispatch) => ({
  login: (username, email, password) => dispatch(login(username, email, password)),
  changeUsernameOrEmail: (usernameOrEmail) => dispatch(changeUsernameOrEmail(usernameOrEmail)),
  changePassword: (password) => dispatch(changePassword(password)),
  navigateTo: (e) => dispatch(push(e))
})

export default connect(mapStateToProps, mapDispatchToProps)(LoginNavbar)
