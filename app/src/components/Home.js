import React, { Component } from 'react';
import '../App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

/*
          <Button color="link"><Link to={`${process.env.PUBLIC_URL}/groups`}>Manage JUG Groups</Link></Button>
          <Button color="link"><Link to={`${process.env.PUBLIC_URL}/testload`}>TestLoad</Link></Button>

          <Button color="link"><Link to="/groups">Manage JUG Groups</Link></Button>
          <Button color="link"><Link to="/testload">TestLoad</Link></Button>


        <Container fluid>
            <Button color="link"><Link to="/groups">Manage JUG Groups</Link></Button>
            <Button color="link"><Link to="/testload">TestLoad</Link></Button>
        </Container>
 */
class Home extends Component {
  render() {
    return (
      <div>
        <AppNavbar/>
        <small>You are running this application in <b>{process.env.NODE_ENV}</b> mode.</small>
          <br/>
          <small>root context is at  <b>{`${process.env.PUBLIC_URL}`}</b></small>
          <br/>
          <small>test var is   <b>{process.env.REACT_APP_TEST_VAR}</b></small>


          <Container fluid>
              <Button color="link"><Link to="/groups">Manage JUG Groups</Link></Button>
              <Button color="link"><Link to="/testload">TestLoad</Link></Button>
          </Container>

        <div><h1>{process.env.REACT_APP_GROUP_API_URL}</h1></div>
          <div><h2>Home Page</h2></div>
      </div>

    );
  }
}

export default Home;