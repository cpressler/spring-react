import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

/*
          <Button color="link"><Link to={`${process.env.PUBLIC_URL}` +"/"}>Home Page</Link></Button>
           <Button color="link"><Link to="/">Home Page</Link></Button>

 */

class TestLoad extends Component {
  render() {
    return (
      <div>
        <AppNavbar/>
        <small>You are running this application in <b>{process.env.NODE_ENV}</b> mode.</small>
        <div><h1>Test Static Load</h1></div>
        <Container fluid>

            <Button color="link"><Link to="/">Home Page</Link></Button>

        </Container>
        <div><h5>{process.env.REACT_APP_GROUP_API_URL}</h5></div>
      </div>

    );
  }
}

export default TestLoad;