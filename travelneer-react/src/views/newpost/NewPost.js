import React, { Component } from 'react'
import CountrySelect from './countryselect/CountrySelect'
import { connect } from 'react-redux'
import PostArea from './postarea/PostArea'
import styles from './NewPost.scss'


class NewPost extends Component {

    render() {
        return (
            <div className={`container ${styles.div}`}  >
                    <CountrySelect />
                    <PostArea refresh={this.props.refresh === false? false : true} 
                        countryId={this.props.countryId} 
                        text={`What's hapenning in ${this.props.countryName === "" ? 
                            "(choose a country)" : this.props.countryName}?`} />                        
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    countryId: state.countries.selectedCountryId,
    countryName: state.countries.selectedCountryName,
    countries: state.countries.countries
})


export default connect(mapStateToProps, null)(NewPost)