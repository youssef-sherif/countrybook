import React, { Component } from 'react'
import { connect } from 'react-redux'
import { push } from 'connected-react-router'
import { fetchCountries, fetchFollowedCountries, searchCountries } from '../../actions/countriesActions'

import AppNavbar from '../../components/appnavbar/AppNavbar'
import CountryList from './countrylist/CountryList'

import styles from './Countries.scss'
    
class Countries extends Component {

    componentDidMount() {
        this.props.fetchCountries()
    }

    render() {

        return (
            <div>
                <AppNavbar />
                <br /><br /><br />
                <div className={`container`}>
                    <input
                        className={styles.search} 
                        onChange={(e) => {
                            this.props.searchCountries(this.props.searchResource, e.target.value);                    
                        }}
                        rows='1' placeholder='Search for a country' 
                    />
                    <br/><br/>            
                    <CountryList 
                        countries={this.props.countries}
                        navigateTo={this.props.navigateTo}
                        loading={this.props.loading}
                        successful={this.props.successful} 
                    />                    
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state) => ({
    countries: state.countries.countries,
    loading: state.countries.loading,
    successful: state.countries.successful,    
    searchResource: state.countries.searchCountriesResource,
    followedResource: state.countries.followedCountriesResource
})

const mapDispatchToProps = (dispatch) => ({
    navigateTo: (e) => dispatch(push(e)),
    fetchCountries: () => dispatch(fetchCountries()),
    fetchFollowedCountries: (resource) => dispatch(fetchFollowedCountries(resource)),
    searchCountries: (resource, param) => dispatch(searchCountries(resource, param))
})

export default connect(mapStateToProps, mapDispatchToProps)(Countries)
