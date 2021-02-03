import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Home from './components/home';
import ReportWebVitals from './reportWebVitals'


import { Hot } from 'react-hot-loader/root'

const Hot = Hot(Home);

ReactDOM.render(
  <Hot />,
  document.getElementById('root')
);

ReportWebVitals();