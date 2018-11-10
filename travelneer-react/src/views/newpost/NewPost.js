import React, { Component } from 'react'
import CountrySelect from './countryselect/CountrySelect'
import { connect } from 'react-redux'
import PostArea from './postarea/PostArea'

class NewPost extends Component {

    render() {
        return (
            <div className={`container`}  >
                    <CountrySelect />
                    <PostArea countryId={this.props.countryId} text={"What's hapenning?"} />                        
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    countryId: state.countries.selectedCountryId
})


export default connect(mapStateToProps, null)(NewPost)