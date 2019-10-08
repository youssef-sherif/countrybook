import React, { Component } from 'react'

import { connect } from 'react-redux'
import { authorizeAccessToPrivateRoute } from '../actions/authActions'

import AppNavbar from '../components/appnavbar/AppNavbar'

class PrivateRoute extends Component {

    componentDidMount() {
        this.props.authorizeAccessToPrivateRoute()
    }

    render() {
        return (
            <div>
                <AppNavbar />
                <br /><br />
                {this.props.children}
            </div>
        )
    }
}

const mapDispatchToProps = (dispatch) => ({
    authorizeAccessToPrivateRoute: () => dispatch(authorizeAccessToPrivateRoute()),    
})
  

  const mapStateToProps = (state) => {
    return {
        red: state.auth.successful,
        authLoading: state.auth.loading
    }
  }

export default connect(mapStateToProps, mapDispatchToProps)(PrivateRoute)