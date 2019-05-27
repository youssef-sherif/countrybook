import React, { Component } from 'react'

import { connect } from 'react-redux'
import { fetchCountries, selectCountry } from '../../../actions/countriesActions'

import styles from './CountrySelect.scss'

class CountrySelect extends Component {


    componentDidMount() {
        this.props.fetchCountries()
    }


    render() {        
        return (
            <div className={`container ${styles.div}`}>
                <select className={`${styles.select}`} onChange={(e) => {         
                    this.props.selectCountry(e.target.value);
                        }}>
                        <option  key={0} value={0}>
                            Select a Country
                        </option>  
                    {this.props.countries.map((country) => 
                        <option  key={country.countryId} value={country.countryId}>
                            {country.name}
                        </option>                    
                    )}
                </select>
            </div>
        )
    }
}


const mapStateToProps = (state) => ({      
    countries: state.countries.countries,
    selectedCountry: state.countries.selectedCountry
})


const mapDispatchToProps = (dispatch) => ({
    fetchCountries: () => dispatch(fetchCountries()),
    selectCountry: (countryId) => dispatch(selectCountry(countryId))
})

export default connect(mapStateToProps, mapDispatchToProps)(CountrySelect)