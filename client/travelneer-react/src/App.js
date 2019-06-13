import React, { Component } from 'react'
import Routes from './Routes'
import { ConnectedRouter } from 'connected-react-router'
import { Provider } from 'react-redux'
import myStore from './storeConfig'
import myHistory from './history'


export default class App extends Component {

    render() {

        return (
        <Provider store={myStore}>
            <ConnectedRouter history={myHistory}>
			    <Routes />
		    </ConnectedRouter>
        </Provider>
        )
    }
}



