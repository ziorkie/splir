import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap-grid.min.css'
import {BrowserRouter as Router} from "react-router-dom";


ReactDOM.render(
  <Router>
    <App/>
  </Router>
  ,
  document.getElementById('root')
);

reportWebVitals();
