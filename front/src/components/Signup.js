import React from 'react'
import {Link} from "react-router-dom";
import http from "../Utilities/http";

class Signup extends React.Component {
  handleSubmit = (e) => {
    // 기본으로 지정되어 있는 액션을 막음
    e.preventDefault();
    console.log(e.target)
    let userData = {
      'email': e.target.email.value,
      'password': e.target.password.value,
      'passwordConfirm': e.target.passwordConfirm.value,
      'name': e.target.name.value
    }

    http
      .post('/signup', userData)
      .then(res => {
        console.log(res.data)
        console.log("회원가입 성공")
      })
      .catch(err => {
        console.log(err)
        console.log("회원가입 실패")
      })
  }

  render() {
    return (
      <div>
        <h1>Signup.js</h1>
        <div>
          <form onSubmit={this.handleSubmit}>
            <table border="1">

              <tr>
                <td>E-Mail</td>
                <td><input name="email" type="text"/></td>
              </tr>
              <tr>
                <td><label>Password</label></td>
                <td><input name="password" type="password"/></td>
              </tr>
              <tr>
                <td><label>Password Confirm</label></td>
                <td><input name="passwordConfirm" type="password"/></td>
              </tr>
              <tr>
                <td><label>Name</label></td>
                <td><input name="name" type="text"/></td>
              </tr>
              <tr>
                <input type="submit" value="Submit"/>
                <Link to="/">취소</Link>
              </tr>
            </table>
          </form>
        </div>
      </div>
    )
  }
}

export default Signup;
