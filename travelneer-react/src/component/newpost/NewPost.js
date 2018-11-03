import React, { Component } from 'react'
import styles from './NewPost.scss'
import AppNavbar from '../navigation/app/AppNavbar'
import CountrySelect from './CountrySelect'
import PostArea from './postarea/PostArea'

class NewPost extends Component {

    render() {
        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <div className={`container ${styles.div}`}  >
                    <CountrySelect />
                    <PostArea />                        
                </div>
            </div>
        )
    }
}


export default NewPost
