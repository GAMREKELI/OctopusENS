import { Navigate, Outlet, useLocation } from "react-router-dom";
import useAuth from "../../hooks/useAuth";

function PrivateRoute() {
  const { isAuthenticated } = useAuth();

  const location = useLocation();

  return (
    isAuthenticated === true
      ? <Outlet />
      : <Navigate to="/sign-in" state={{ from: location }} replace />
  )
}

export default PrivateRoute;
