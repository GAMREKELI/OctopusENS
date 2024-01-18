import { createContext, useState } from "react";

interface IAuthContext {
  isAuthenticated: boolean,
  setAuth: (auth: boolean) => void,
}

const AuthContext = createContext<IAuthContext>({
  isAuthenticated: false,
  setAuth: () => {},
})

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [isAuthenticated, setAuth] = useState<boolean>(false);

  return (
    <AuthContext.Provider value={{ isAuthenticated, setAuth }}>
      {children}
    </AuthContext.Provider>
  )
}

export default AuthContext;
