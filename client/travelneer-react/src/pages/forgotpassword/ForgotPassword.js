import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import styles from './ForgotPassword.scss'
import InputText from '../../components/inputtext/InputText'
import { validateEmail } from '../../actions/validationActions'
import HomeNavbar from '../../components/homenavbar/HomeNavbar';

class ForgotPassword extends Component {

    render() {

        return (
            <div>
                <HomeNavbar />
                <div className={`${styles.div} container`}>
                    <br />
                    <form className={styles.form}>
                        <InputText
                            type='email'
                            data={this.props.email}
                            className={styles.email}
                            isLoading={this.props.isLoadingEmail}
                            isValid={this.props.isValidEmail}
                            validate={this.props.validateEmail.bind(this)} />

                        <br />

                        <button className={`btn`}>Reset Password</button>
                    </form>
                    <br />
                </div>
            </div>
        )

    }
}

const mapStateToProps = (state) => {
    return {
        isValidEmail: state.user.email.isValid,
        isLoadingEmail: state.user.email.loading,

        email: state.user.email.value,
    }
}

const mapDispatchToProps = (dispatch) => ({
    validateEmail: (e) => dispatch(validateEmail(e)),
    navigateTo: (e) => dispatch(push(e))
})

export default connect(mapStateToProps, mapDispatchToProps)(ForgotPassword)