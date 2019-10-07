import React, { Component } from 'react'
import LogoutButton from './LogoutButton'
import { connect } from 'react-redux'
import { validatePassword, retypePassword } from '../../../actions/validationActions'
import { changePassword } from '../../../actions/passwordsActions'
import InputPassword from '../../../components/inputpassword/InputPassword'

import styles from './UserProfile.scss'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class UserProfile extends Component {

    constructor(props) {
        super(props)
        this.state = { oldPassword: "" }
    }

    render() {

        return (
            <div className={`container ${styles.div}`}>
                <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                        <p class="text-center">Use the form below to change your password.</p>
                        <form>
                            <input type="password" className={styles.input} placeholder="old password"
                                onChange={(e) => {
                                    const val = e.target.value
                                    this.setState((state, props) => {
                                        return {
                                            oldPassword: val
                                        }
                                    })
                                }
                                } />
                            <br />
                            <br />
                            <InputPassword
                                data={this.props.password}
                                isLoading={this.props.isLoadingPassword}
                                passwordStrength={this.props.passwordStrength}
                                validate={this.props.validatePassword.bind(this)}
                            />
                            <br />
                            <p>retype password</p>
                            <InputPassword
                                data={this.props.retype}
                                password={this.props.password}
                                retype={true}
                                isLoading={this.props.isLoadingPassword}
                                validate={this.props.retypePassword.bind(this)}
                            />
                            <br />
                            <input type="submit" class={`btn ${styles.changePasswordButton}`} value="Change Password"
                                onClick={(e) => {
                                    e.preventDefault();  
                                    this.props.changePassword(this.state.oldPassword, this.props.password)                                                                    
                                }} />
                            <br />
                            <br />
                            <br />
                            <LogoutButton style={styles.LogoutButton} />                            
                        </form>
                        <br />
                    </div>
                </div>
                <br />                
                <ToastContainer enableMultiContainer containerId={'passwordChangeToast'} position={toast.POSITION.TOP_RIGHT} />
            </div>
        )
    }

}


const mapStateToProps = (state) => {
    return {
        passwordStrength: state.user.password.passwordStrength,
        isLoadingPassword: state.user.password.loading,
        password: state.user.password.value,
        retype: state.user.password.retype,
        changePasswordSuccessful: state.passwords.changePasswordSuccessful,
        changePasswordLoading: state.passwords.changePasswordLoading,
        changePasswordError: state.passwords.changePasswordError
    }
}

const mapDispatchToProps = (dispatch) => ({
    validatePassword: (e) => dispatch(validatePassword(e)),
    retypePassword: (e) => dispatch(retypePassword(e)),
    changePassword: (oldPassword, newPassword) => dispatch(changePassword(oldPassword, newPassword))
})

export default connect(mapStateToProps, mapDispatchToProps)(UserProfile)
