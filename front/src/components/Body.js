import React, {Component} from "react";
import About from "./About";
import Admin from "./Admin";
import Home from "./Home";
import Signin from "./Signin";
import Signup from "./Signup";
import {Route} from "react-router-dom";

class Body extends Component {
  render() {
    return (
      <div>
        <Route path="/about" component={About}/>
        <Route path="/admin" component={Admin}/>
        <Route path="/" component={Home} exact/>
        <Route path="/signin" component={Signin}/>
        <Route path="/signup" component={Signup}/>
      </div>
    )
  }
}

export default Body
