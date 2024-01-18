import { useState } from 'react'
import { Button, Input, Spinner, Text } from '@chakra-ui/react'
import MainForm from '../../components/MainForm/MainForm';
import { useForm } from 'react-hook-form';
import { ILoginFormInputs, ILoginData } from '../../types'
import mainApi from '../../utils/MainApi';
import useAuth from '../../hooks/useAuth';
import { useNavigate } from 'react-router-dom';

interface ILogin {
  setShowAlert: React.Dispatch<React.SetStateAction<boolean>>,
  setAlertMessage: React.Dispatch<React.SetStateAction<string>>,
}

function Login({ setShowAlert, setAlertMessage }: ILogin) {
  const [submitError, setSubmitError] = useState<string>('');
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const { setAuth } = useAuth();

  const navigate = useNavigate();

  const {
    handleSubmit,
    register,
    formState: { errors },
    reset,
  } = useForm<ILoginFormInputs>();

  const onSubmit = handleSubmit((data) => {
    setIsLoading(true);

    mainApi.signIn(data)
      .then((data) => {
        const { jwtToken } = data as ILoginData;

        if (jwtToken) {
          localStorage.setItem('jwt', jwtToken)

          setAuth(true);
          reset();

          navigate('/', { replace: true });

          setShowAlert(true);
          setAlertMessage('Вы успешно вошли в аккаунт!')
        } else {
          throw new Error('Токен отсутствует');
        }
      })
      .catch((err) => {
        console.log(err)
        if (err.status === 403) {
          setSubmitError('Неправильный логин или пароль.')
        } else {
          setSubmitError('Сервер не отвечает.')
        }
      })
      .finally(() => setIsLoading(false));
  });

  return (
    <MainForm
      name='signIn'
      onSubmit={onSubmit}
      isLogin
    >
      <Input
        width='100%'
        placeholder='Логин'
        size='lg'
        {...register(
          'login',
          { required: 'Пожалуйста, введите логин для входа в систему.' }
        )}
      />
      <div className='main-form__error'>
        <Text fontSize='xs' color='red'>{errors.login?.message || ''}</Text>
      </div>
      <Input
        type='password'
        width='100%'
        placeholder='Пароль'
        size='lg'
        {...register(
          'password',
          { required: 'Пожалуйста, введите пароль для входа в систему.' }
        )}
      />
      <div className='main-form__error'>
        <Text fontSize='xs' color='red'>{errors.password?.message || ''}</Text>
      </div>
      <div className='main-form__error'>
        <Text fontSize='xs' color='red'>{submitError || ''}</Text>
      </div>
      <Button
        type='submit'
        colorScheme='teal'
        size='lg'
        width='100%'
        onClick={() => setSubmitError('')}
        isDisabled={isLoading}
      >
        {isLoading
          ? <Spinner />
          : 'Войти'
        }
      </Button>
    </MainForm>
  )
}

export default Login
