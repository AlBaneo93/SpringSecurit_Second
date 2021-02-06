import React from 'react'
import http from '../Utilities/http'

class Signin extends React.Component {

  handleSubmit = (e) => {
    e.preventDefault();

    let userData = new FormData()
    userData.append('email', e.target.email.value)
    userData.append('password', e.target.password.value)

    http
      .post('/signin', userData)
      .then(res => {
        console.log(res.data)
      })
      .catch(err => {
        console.log(err)
      })
  }

  render() {
    return (
      <div>
        <h1>Signin Page</h1>
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
                <input type="submit" value="Submit"/>

              </tr>
            </table>
          </form>
        </div>
      </div>
    )
  }
}

export default Signin;
