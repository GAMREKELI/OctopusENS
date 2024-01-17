import { Button, Text } from '@chakra-ui/react';
import './MainForm.css';

interface IMainForm {
  name: string,
  onSubmit: (evt: React.FormEvent<HTMLFormElement>) => void,
  children: React.ReactNode;
  isLogin: boolean,
  setIsLogin:  React.Dispatch<React.SetStateAction<boolean>>
}

function MainForm({ name, onSubmit, children, isLogin, setIsLogin }: IMainForm) {
  return (
    <main className='main-form'>
      <form
        action="#"
        name={name}
        className='main-form__form'
        onSubmit={(evt) => onSubmit(evt)}
      >
        {children}
      </form>
      {isLogin
        ? (
          <Text>
            Ещё не зарегистрированы?&nbsp;
            <Button
              colorScheme='blue'
              variant='link'
              onClick={() => setIsLogin(false)}
            >
              Регистрация
            </Button>
          </Text>
        )
        : (
          <Text>
            Уже зарегистрированы?&nbsp;
            <Button
              colorScheme='blue'
              variant='link'
              onClick={() => setIsLogin(true)}
            >
              Войти
            </Button>
          </Text>
        )}
    </main>
  )
}

export default MainForm;