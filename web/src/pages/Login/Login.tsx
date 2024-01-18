import { useState } from 'react'
import { Button, Input, Text } from '@chakra-ui/react'
import MainForm from '../../components/MainForm/MainForm';
import { useForm } from 'react-hook-form';
import { ILoginFormInputs } from '../../types'
import mainApi from '../../utils/MainApi';

interface ILogin {
  isLogin: boolean,
  setIsLogin: React.Dispatch<React.SetStateAction<boolean>>,
}

function Login({ isLogin, setIsLogin }: ILogin) {
  const [submitError, setSubmitError] = useState<string>('');

  const {
    handleSubmit,
    register,
    formState: { errors },
    reset,
  } = useForm<ILoginFormInputs>();

  const onSubmit = handleSubmit((data) => {
    mainApi.sigIn(data)
      .then((res) => {
        console.log(res)
        reset()
      })
      .catch((err) => {
        console.log(err)
        if (err.status === 403) {
          setSubmitError('Неправильный логин или пароль.')
        } else {
          setSubmitError('Сервер не отвечает.')
        }
      });
  });

  return (
    <MainForm
      name='sigIn'
      onSubmit={onSubmit}
      isLogin={isLogin}
      setIsLogin={setIsLogin}
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
      >
        Войти
      </Button>
    </MainForm>
  )
}

export default Login
