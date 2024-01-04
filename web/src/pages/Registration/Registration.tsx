import { useState } from 'react'
import { Button, Input } from '@chakra-ui/react'
import MainForm from '../../components/MainForm/MainForm';
import mainApi from '../../utils/mainApi';

interface IRegistration {
  isLogin: boolean,
  setIsLogin: React.Dispatch<React.SetStateAction<boolean>>,
}

// "login": "pdursley",
// "password": "Q1234qwe",
// "firstName": "Nikita",
// "lastName": "Gamrekeli",
// "email": "nekkimark2@gmail.com",
// "phoneNumber": "+79105988089"

function Registration({ isLogin, setIsLogin }: IRegistration) {
  const [login, setLogin] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [firstName, setFirstName] = useState<string>('');
  const [lastName, setLastName] = useState<string>('');
  const [email, setEmail] = useState<string>('');
  const [phoneNumber, setPhoneNumber] = useState<string>('');

  function onSubmit(evt: React.FormEvent<HTMLFormElement>) {
    evt.preventDefault();

    mainApi.sigUp(
      login,
      password,
      firstName,
      lastName,
      email,
      phoneNumber
    );

    setLogin('');
    setPassword('');
    setFirstName('');
    setLastName('');
    setEmail('');
    setPhoneNumber('');
  }

  return (
    <MainForm
      name='login'
      onSubmit={onSubmit}
      isLogin={isLogin}
      setIsLogin={setIsLogin}
    >
      <Input
        type='text'
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
      <Input
        type='text'
        width='49%'
        placeholder='Имя'
        size='lg'
        isRequired
        onChange={(evt) => setFirstName(evt.target.value)}
        value={firstName}
      />
      <Input
        type='text'
        width='49%'
        placeholder='Фамилия'
        size='lg'
        isRequired
        onChange={(evt) => setLastName(evt.target.value)}
        value={lastName}
      />
      <Input
        type='email'
        width='100%'
        placeholder='Почта'
        size='lg'
        isRequired
        onChange={(evt) => setEmail(evt.target.value)}
        value={email}
      />
      <Input
        type='tel'
        width='100%'
        placeholder='Телефон'
        size='lg'
        isRequired
        onChange={(evt) => setPhoneNumber(evt.target.value)}
        value={phoneNumber}
      />
      <Button
        type='submit'
        colorScheme='teal'
        size='lg'
        width='100%'
      >
        Зарегистрироваться
      </Button>
    </MainForm>
  )
}

export default Registration;