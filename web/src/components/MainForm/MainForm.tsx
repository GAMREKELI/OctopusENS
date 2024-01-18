import { Button, Heading, Text } from '@chakra-ui/react';
import './MainForm.css';
import redButton from '../../../public/red-button.svg'
import { useNavigate } from 'react-router-dom';

interface IMainForm {
  name: string,
  onSubmit: (e?: React.BaseSyntheticEvent<object, unknown, unknown> | undefined) => Promise<void>,
  children: React.ReactNode,
  isLogin?: boolean,
}

function MainForm({ name, onSubmit, children, isLogin }: IMainForm) {
  const navigate = useNavigate();

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
      {isLogin === true
        ? (
          <Text>
            Ещё не зарегистрированы?&nbsp;
            <Button
              colorScheme='blue'
              variant='link'
              onClick={() => navigate('/sign-up')}
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
              onClick={() => navigate('/sign-in')}
            >
              Войти
            </Button>
          </Text>
        )}
    </main>
  )
}

export default MainForm;
