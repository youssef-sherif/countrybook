import React, { Component } from 'react'

import { connect } from 'react-redux'
import { //fetchFollowedCountries,
    fetchCountries } from '../../actions/countryActions'

class CountrySelect extends Component {


    componentDidMount() {
        this.props.fetchCountries()
    }

    render() {        
        return (
            <div>
                <select>
                    {this.props.countries.map((country) => 
                        <option value={country.country.id}>
                            {country.country.name}
                        </option>
                    )}
                </select>
            </div>
        )
    }
}


const mapStateToProps = (state) => ({      
    resource: state.countries.followedCountriesResource,
    followedCountries: state.countries.countries,
    countries: state.countries.countries
})


const mapDispatchToProps = (dispatch) => ({
    fetchCountries: () => dispatch(fetchCountries())
})

export default connect(mapStateToProps, mapDispatchToProps)(CountrySelect)