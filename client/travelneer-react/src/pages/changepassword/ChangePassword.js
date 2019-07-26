import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import styles from './ChangePassword.scss'
import InputPassword from '../../components/inputpassword/InputPassword'
import PublicRoute from '../../routes/PublicRoute'

class ChangePassword extends Component {

    render() {

        return (
            <PublicRoute>                
                <div className={`${styles.div} container`}>
                    <br />
                    <form className={styles.form}>
                        <InputPassword />
                        <br />
                        <InputPassword />
                        
                        <br />

                        <button className={`btn`}>Reset Password</button>
                    </form>
                    <br />
                </div>
            </PublicRoute>
        )

    }
}

const mapStateToProps = (state) => {
    return {
    }
}

const mapDispatchToProps = (dispatch) => ({
    navigateTo: (e) => dispatch(push(e))
})

export default connect(mapStateToProps, mapDispatchToProps)(ChangePassword)