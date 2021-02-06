import React, {Component} from "react";
import {Link} from "react-router-dom";

class Header extends Component {
  render() {
    const transparentWord={
      color:'#00000000'
    }
    return (
      <div>
        <Link to="/about">about</Link>
        <span style={transparentWord}>&amp</span>
        <Link to="/admin">관리</Link>
        <span style={transparentWord}>&amp</span>
        <Link to="/">홈</Link>
        <span style={transparentWord}>&amp</span>
        <Link to="/signin">Signin</Link>
        <span style={transparentWord}>&amp</span>
        <Link to="/signup">Signup</Link>
      </div>
    )
  }
}

export default Header
