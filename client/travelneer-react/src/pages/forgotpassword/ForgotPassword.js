import React, { Component } from 'react'
import { connect } from 'react-redux'
import styles from './ForgotPassword.scss'
import InputText from '../../components/inputtext/InputText'
import AccountNotRequiredRoute from '../../routes/AccountNotRequiredRoute';
import { validateEmail } from '../../actions/validationActions'
import { requestPasswordResetToken } from '../../actions/passwordsActions'

class ForgotPassword extends Component {

    render() {

        return (
            <AccountNotRequiredRoute>
                <div className={`${styles.div} container`}>
                    <br />
                    <form className={styles.form}>
                        <InputText
                            type='email'
                            data={this.props.email}
                            className={styles.email}
                            isLoading={this.props.isLoadingEmail}
                            isValid={this.props.isValidEmail}
                            validate={this.props.validateEmail.bind(this)} 
                        />

                        <br />

                        <button className={`btn`}
                            onClick={() => {
                                this.props.requestPasswordResetToken()
                            }}>
                            Reset Password
                        </button>
                    </form>
                    <br />
                </div>
            </AccountNotRequiredRoute>
        )

    }
}

const mapStateToProps = (state) => ({    
    isValidEmail: state.user.email.isValid,
    isLoadingEmail: state.user.email.loading,
    email: state.user.email.value,    
})

const mapDispatchToProps = (dispatch) => ({
    validateEmail: (e) => dispatch(validateEmail(e)),
    requestPasswordResetToken: () => dispatch(requestPasswordResetToken())
})

export default connect(mapStateToProps, mapDispatchToProps)(ForgotPassword)