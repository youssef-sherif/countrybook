import React, { Component } from 'react'
import styles from './NewPost.scss'
import AppNavbar from '../navigation/app/AppNavbar'
import CountrySelect from './CountrySelect'
import { connect } from 'react-redux'
import PostArea from './postarea/PostArea'

class NewPost extends Component {

    render() {
        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <div className={`container ${styles.div}`}  >
                    <CountrySelect />
                    <PostArea countryId={this.props.country.id} 
                        countryName={this.props.country.name} />                        
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    country: state.countries.selectedCountry
})


export default connect(mapStateToProps, null)(NewPost)