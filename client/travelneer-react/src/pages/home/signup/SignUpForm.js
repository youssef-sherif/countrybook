import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'

import InputText from './inputtext/InputText'
import InputPassword from './inputpassword/InputPassword'
import SignUpButton from './SignUpButton'

import {
  validateUsername,
  validateEmail,
  validatePassword
} from '../../../actions/validationActions'
import { createUser } from '../../../actions/signUpActions'
import { login } from '../../../actions/loginActions' 
import styles from './SignUpForm.scss'

class SignUpForm extends Component {

  render() {

    return (
      <div className={styles.div}>
        <form className={styles.form}>
          <br />
          <InputText
            type='username'
            data={this.props.username}
            className={styles.username}
            isLoading={this.props.isLoadingUsername}
            isValid={this.props.isValidUsername}
            validate={this.props.validateUsername.bind(this)} />
          <br />
          <InputText
            type='email'
            data={this.props.email}
            className={styles.email}
            isLoading={this.props.isLoadingEmail}
            isValid={this.props.isValidEmail}
            validate={this.props.validateEmail.bind(this)} />
          <br />
          <InputPassword
            className={styles.password}
            data={this.props.password}
            isLoading={this.props.isLoadingPassword}
            passwordStrength={this.props.passwordStrength}
            validate={this.props.validatePassword.bind(this)} />
          <br />
          <SignUpButton className={styles.SignUpButton}
            createUser={this.props.createUser.bind(this)}
            login={this.props.login.bind(this)}
            username={this.props.username}
            email={this.props.email}
            password={this.props.password}
            loading={this.props.signUpLoading}
            successful={this.props.signUpSuccessful}
            navigateTo={this.props.navigateTo} />
        </form>
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  return {
    isValidUsername: state.user.username.isValid,
    isLoadingUsername: state.user.username.loading,

    isValidEmail: state.user.email.isValid,
    isLoadingEmail: state.user.email.loading,

    passwordStrength: state.user.password.passwordStrength,
    isLoadingPassword: state.user.password.loading,

    username: state.user.username.value,
    email: state.user.email.value,
    password: state.user.password.value,

    signUpLoading: state.signUp.signUp.loading | state.login.login.loading,    
    signUpSuccessful: state.signUp.signUp.successful
  }
}

const mapDispatchToProps = (dispatch) => ({
  validateUsername: (e) => dispatch(validateUsername(e)),
  validateEmail: (e) => dispatch(validateEmail(e)),
  validatePassword: (e) => dispatch(validatePassword(e)),
  createUser: (username, email, password) => dispatch(createUser(username, email, password)),
  login: (username, email, password) => dispatch(login(username, email, password)),
  navigateTo: (e) => dispatch(push(e))
})

export default connect(mapStateToProps, mapDispatchToProps)(SignUpForm)
