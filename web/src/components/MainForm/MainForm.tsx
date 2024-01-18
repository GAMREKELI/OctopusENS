import { Button, Heading, Text } from '@chakra-ui/react';
import './MainForm.css';
import redButton from '../../../public/red-button.svg'

interface IMainForm {
  name: string,
  onSubmit: (e?: React.BaseSyntheticEvent<object, unknown, unknown> | undefined) => Promise<void>,
  children: React.ReactNode;
  isLogin: boolean,
  setIsLogin: React.Dispatch<React.SetStateAction<boolean>>
}

function MainForm({ name, onSubmit, children, isLogin, setIsLogin }: IMainForm) {
  return (
    <main className='main-form'>
      <img
        src={redButton}
        className='main-form__logo'
      />
      <Heading
        as='h1'
        paddingBottom={5}
      >
        OctopusENS
      </Heading>
      <form
        action="#"
        name={name}
        className='main-form__form'
        onSubmit={onSubmit}
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
