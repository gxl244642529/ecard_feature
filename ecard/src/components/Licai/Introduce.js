/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native';
// import PasswordGesture  from 'react-native-gesture-password'
import PasswordGesture from '../../widget/GesturePassword'

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {
  constructor(props){
    super(props);
    this.state={
      message: '请输入手势密码',
      status: 'normal'
    }
  }
  onEnd=(password)=> {
    console.log("onEnd="+password);
    // if (password == '123') {
    //     this.setState({
    //         status: 'right',
    //         message: '密码正确'
    //     });
    //
    //     // your codes to close this view
    // } else {
    //     this.setState({
    //         status: 'wrong',
    //         message: '手机密码错误'
    //     });
    // }
}
onStart=()=> {
  console.log("正在条用onStart")
    this.setState({
        status: 'normal',
        message: '请输入密码'
    });
}
onReset=()=> {
    this.setState({
        status: 'normal',
        message: '请再次输入手势密码'
    });
}
  render() {
    return (
      <PasswordGesture
         ref='pg'
         status={this.state.status}
         message={this.state.message}
         onStart={() => this.onStart()}
         onEnd={(password) => this.onEnd(password)}
         onReset={()=>{this.onReset()}}
         />
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
