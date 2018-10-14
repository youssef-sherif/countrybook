import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'react-router-redux'

import InputText from './inputtext/InputText'
import InputPassword from './inputpassword/InputPassword'
import SignUpButton from './SignUpButton'

import {
  validateUserName,
  validateEmail,
  validatePassword
} from '../../../actions/validationActions'
import { authorizeUser } from '../../../actions/authActions'
import { createUser } from '../../../actions/signUpActions'
import styles from './SignUpForm.scss'

class SignUpForm extends Component {

  render() {

    return (
      <div className={styles.div}>
        <form className={styles.form}>
          <br />
          <InputText
            type='username'
            data={this.props.userName}
            className={styles.userName}
            isLoading={this.props.isLoadingUserName}
            isValid={this.props.isValidUserName}
            validate={this.props.validateUserName.bind(this)} />
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
            userName={this.props.userName}
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
    isValidUserName: state.user.userName.isValid,
    isLoadingUserName: state.user.userName.loading,

    isValidEmail: state.user.email.isValid,
    isLoadingEmail: state.user.email.loading,

    passwordStrength: state.user.password.passwordStrength,
    isLoadingPassword: state.user.password.loading,

    userName: state.user.userName.value,
    email: state.user.email.value,
    password: state.user.password.value,

    signUpLoading: state.signUp.signUp.loading,    
    signUpSuccessful: state.signUp.signUp.successful
  }
}

const mapDispatchToProps = (dispatch) => ({
  validateUserName: (e) => dispatch(validateUserName(e)),
  validateEmail: (e) => dispatch(validateEmail(e)),
  validatePassword: (e) => dispatch(validatePassword(e)),
  authorizeUser: () => dispatch(authorizeUser()),
  createUser: (userName, email, password) => dispatch(createUser(userName, email, password)),
  navigateTo: (e) => dispatch(push(e))
})

export default connect(mapStateToProps, mapDispatchToProps)(SignUpForm)
