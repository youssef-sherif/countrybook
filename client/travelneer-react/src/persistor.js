import myStore from './storeConfig'
import { persistStore } from 'redux-persist'

const myPersistor = persistStore(myStore)

export default myPersistor
