import React from 'react'
import ReactDOM from 'react-dom'
import { Provider } from 'react-redux'
import { ConnectedRouter } from 'connected-react-router'
import myStore from './storeConfig'
import myHistory from './history'
import App from './App'
import registerServiceWorker from './registerServiceWorker';


ReactDOM.render(
	<Provider store={myStore}>
		<ConnectedRouter history={myHistory}>
			<App />
		</ConnectedRouter>
	</Provider>,
	document.getElementById('root'))

registerServiceWorker()
