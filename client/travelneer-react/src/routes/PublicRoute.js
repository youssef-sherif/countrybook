import React, { Component } from 'react'

import { connect } from 'react-redux'
import { authorizeAccessToPublicRoute } from '../actions/authActions'

import HomeNavbar from '../components/homenavbar/HomeNavbar'

class PublicRoute extends Component {

    componentDidMount() {
        this.props.authorizeAccessToPublicRoute()
    }

    render() {
        return (
            <div>
                <HomeNavbar />                
                {this.props.children}
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => ({
    authorizeAccessToPublicRoute: () => dispatch(authorizeAccessToPublicRoute())
})
  

  const mapStateToProps = (state) => {
    return {
        authSuccessful: state.auth.successful,
        authLoading: state.auth.loading
    }
  }

export default connect(mapStateToProps, mapDispatchToProps)(PublicRoute)