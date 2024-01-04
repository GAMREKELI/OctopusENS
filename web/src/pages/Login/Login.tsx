import { useState } from 'react'
import { Button, Input } from '@chakra-ui/react'
import MainForm from '../../components/MainForm/MainForm';
import mainApi from '../../utils/mainApi';

interface ILogin {
  isLogin: boolean,
  setIsLogin:  React.Dispatch<React.SetStateAction<boolean>>, 
}

function Login({ isLogin, setIsLogin }: ILogin) {
  const [login, setLogin] = useState<string>('');
  const [password, setPassword] = useState<string>('');

  function onSubmit(evt: React.FormEvent<HTMLFormElement>) {
    evt.preventDefault();

    mainApi.sigIn(login, password);

    setLogin('');
    setPassword('');
  }

  return (
    <MainForm
      name='login'
      onSubmit={onSubmit}
      isLogin={isLogin}
      setIsLogin={setIsLogin}
    >
      <Input
        width='100%'
        placeholder='Логин'
        size='lg'
        isRequired
        onChange={(evt) => setLogin(evt.target.value)}
        value={login}
      />
      <Input
        type='password'
        width='100%'
        placeholder='Пароль'
        size='lg'
        isRequired
        onChange={(evt) => setPassword(evt.target.value)}
        value={password}
      />
      <Button
        type='submit'
        colorScheme='teal'
        size='lg'
        width='100%'
      >
        Войти
      </Button>
    </MainForm>
  )
}

export default Login