import React,{Component} from 'react'
import {Provider} from 'react-redux'
import configureStore from './src/Store';


import Navigation from './src/router/Navigation'

const store = configureStore();

export default class App extends Component {
  render(){
    return(
      <Provider store={store}>
        <Navigation/>
      </Provider>
    );
  }
}
