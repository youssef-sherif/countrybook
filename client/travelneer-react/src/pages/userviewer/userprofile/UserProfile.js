import React, { Component } from 'react'
import LogoutButton from './LogoutButton'
import { connect } from 'react-redux'

import styles from './UserProfile.scss'

class UserProfile extends Component {

    componentDidMount() {        

    }

    render() {

        return (
            <div className={`container ${styles.div}`}>            
                <LogoutButton style={styles.LogoutButton} />                
            </div>
        )
    }

}


const mapStateToProps = (state) => ({

})

const mapDispatchToProps = (dispatch) => ({
})

export default connect(mapStateToProps, mapDispatchToProps)(UserProfile)
