import Reatct ,{ Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css'
import { Button, ButtonToolbar } from 'react-bootstrap';
import ApexCharts from 'apexcharts';

class Top extends Component{
  render(){
    return(
      <ButtonToolbar>
        <Button variant="dark">데스크탑</Button>
        <Button variant="dark">노트북</Button>
        <Button variant="dark">핸드폰</Button>
        <Button variant="dark">태블릿</Button>
      </ButtonToolbar>
    );
  }
}

class Middle extends Component{
  render(){
    return(
      <div className='container'>

      </div>
    )
  }
}

class App extends Component{
  render(){
    return (
      <div className='App'>
        <Top></Top>
        <Middle></Middle>
      </div>
    );
  }
}

export default App;
