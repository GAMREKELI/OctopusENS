import { useState } from 'react';
import Login from './pages/Login/Login';
import Registration from './pages/Registration/Registration';

function App() {
  const [isLogin, setIsLogin] = useState<boolean>(true);

  return (
    isLogin 
      ? <Login isLogin={isLogin} setIsLogin={setIsLogin} />
      : <Registration isLogin={isLogin} setIsLogin={setIsLogin} />
  )
}

export default App
