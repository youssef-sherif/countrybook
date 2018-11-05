import React, { Component } from 'react'

import { connect } from 'react-redux'
import { fetchCountries, selectCountry } from '../../actions/countryActions'

class CountrySelect extends Component {


    componentDidMount() {
        this.props.fetchCountries()
    }


    render() {        
        return (
            <div>
                <select onChange={(e) => {         
                    this.props.selectCountry(e.target.value);
                        }}>
                    {this.props.countries.map((country) => 
                        <option  key={country.country.id} value={country.country.id}>
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
    countries: state.countries.countries,
    selectedCountry: state.countries.selectedCountry
})


const mapDispatchToProps = (dispatch) => ({
    fetchCountries: () => dispatch(fetchCountries()),
    selectCountry: (countryId) => dispatch(selectCountry(countryId))
})

export default connect(mapStateToProps, mapDispatchToProps)(CountrySelect)