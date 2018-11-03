import React, { Component } from 'react'

import Home from './component/home/Home'
import Feed from './component/feed/Feed'
import Countries from './component/countries/Countries'
import CountryViewer from './component/countryviewer/CountryViewer'
import NewPost from './component/newpost/NewPost'
import { Route } from 'react-router-dom'


export default class Routes extends Component {

    render() {
        return (
            <div>
                <Route path="/" exact component={Home} />
                <Route path="/feed" exact component={Feed} />
                <Route path="/new" exact component={NewPost} />                
                <Route path="/my_countries" render={(props) => <Countries {...props} search={false} />} />
                <Route path="/search_countries" render={(props) => <Countries {...props} search={true} />} />                                
                <Route path="/countries/:countryId/posts" render={(props) => <CountryViewer {...props} compose={false} />} />
                <Route path="/countries/:countryId/new" render={(props) => <CountryViewer {...props} compose={true} />} />                
            </div>
        )
    }
}