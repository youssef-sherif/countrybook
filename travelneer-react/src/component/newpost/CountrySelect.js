import React, { Component } from 'react'

export default class PostArea extends Component {

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
