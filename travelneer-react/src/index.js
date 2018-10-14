import React from 'react'
import ReactDOM from 'react-dom'
import { Provider } from 'react-redux'
import { ConnectedRouter } from 'react-router-redux'
// import { PersistGate } from 'redux-persist/integration/react'
import myStore from './storeConfig'
// import myPersistor from './persistor'
import myHistory from './history'
import App from './App'
import registerServiceWorker from './registerServiceWorker';
// import LoadingScreen from './component/loadingscreen/LoadingScreen'


ReactDOM.render(
	<Provider store={myStore}>
		{/*<PersistGate loading={<LoadingScreen />} persistor={myPersistor}>*/}
			<ConnectedRouter history={myHistory}>
				<App />
			</ConnectedRouter>
		{/*</PersistGate>*/}
	</Provider>,
	document.getElementById('root'))

registerServiceWorker()
