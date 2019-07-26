import React, { Component } from 'react'
import { connect } from 'react-redux'
import styles from './ForgotPassword.scss'
import InputText from '../../components/inputtext/InputText'
import AccountNotRequiredRoute from '../../routes/AccountNotRequiredRoute';

class ForgotPassword extends Component {

    render() {

        return (
            <AccountNotRequiredRoute>                
                <div className={`${styles.div} container`}>
                    <br />
                    <form className={styles.form}>
                        <InputText
                            type='email' />

                        <br />

                        <button className={`btn`}>Reset Password</button>
                    </form>
                    <br />
                </div>
            </AccountNotRequiredRoute>
        )

    }
}

const mapStateToProps = (state) => {
    return {
    }
}

const mapDispatchToProps = (dispatch) => ({
})

export default connect(mapStateToProps, mapDispatchToProps)(ForgotPassword)