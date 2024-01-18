import { useEffect, useState } from 'react';
import { Alert, AlertIcon, SlideFade } from '@chakra-ui/react';
import { Route, Routes } from "react-router-dom";
import Login from "./pages/Login/Login";
import Registration from "./pages/Registration/Registration";
import Main from "./pages/Main/Main";
import PrivateRoute from "./components/PrivateRoute/PrivateRoute";

function App() {
  const [showAlert, setShowAlert] = useState<boolean>(false)
  const [alertMessage, setAlertMessage] = useState<string>('')

  useEffect(() => {
    setTimeout(() => setShowAlert(false), 5000)
  }, [showAlert])

  return (
    <>
      <SlideFade
        in={showAlert}
        style={{ zIndex: 10 }}
        offsetY='-50px'
        unmountOnExit
      >
        <Alert
          status='success'
          position='absolute'
          display='flex'
          justifyContent='center'
        >
          <AlertIcon />
          {alertMessage}
        </Alert>
      </SlideFade>

      <Routes>
        <Route
          path="/sign-in"
          element={
            <Login
              setShowAlert={setShowAlert}
              setAlertMessage={setAlertMessage}
            />
          }
        />
        <Route
          path="/sign-up"
          element={
            <Registration
              setShowAlert={setShowAlert}
              setAlertMessage={setAlertMessage}
            />
          }
        />

        <Route element={<PrivateRoute />}>
          <Route index element={<Main />} />
        </Route>
      </Routes>
    </>
  )
}

export default App
