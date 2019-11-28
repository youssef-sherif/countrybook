import React, { Component } from 'react'

import { connect } from 'react-redux'
import { authorizeUser } from '../actions/authActions'

import HomeNavbar from '../components/homenavbar/HomeNavbar'
import AppNavbar from '../components/appnavbar/AppNavbar';

class PublicRoute extends Component {

    componentDidMount() {
        this.props.authorizeUser()
    }

    render() {
        return (
            <div>
                {localStorage.getItem('logged_in') === 'true' ?
                    <div>
                        <AppNavbar />
                        <br /><br />
                    </div>
                    :
                    <div>
                        <HomeNavbar />
                        <br /><br /><br />
                    </div>
                }
                {this.props.children}
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => ({
    authorizeUser: () => dispatch(authorizeUser())
})


const mapStateToProps = (state) => {
    return {
        authSuccessful: state.auth.successful,
        authLoading: state.auth.loading
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(PublicRoute)