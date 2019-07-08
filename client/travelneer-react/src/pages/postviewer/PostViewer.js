import React, { Component } from 'react'
import AppNavbar from '../../components/appnavbar/AppNavbar'
import { connect } from 'react-redux'
import Post from '../../components/post/Post'

import styles from './PostViewer.scss'


class PostViewer extends Component {

    componentDidMount() {        
        
    }

    render() {

        return (
           <div>
               <AppNavbar />
               <br /><br /><br />
                <div className={`container ${styles.div}`}>                                
                    <Post />
                </div>                
            </div> 

        )
    }

}


const mapStateToProps = (state) => ({
})

const mapDispatchToProps = (dispatch) => ({
})

export default connect(mapStateToProps, mapDispatchToProps)(PostViewer)
