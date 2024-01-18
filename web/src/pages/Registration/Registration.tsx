import { useState } from 'react'
import { Button, Input, Spinner, Text } from '@chakra-ui/react'
import MainForm from '../../components/MainForm/MainForm';
import mainApi from '../../utils/MainApi';
import { useForm } from 'react-hook-form';
import { IRegistrationFormInputs } from '../../types';
import { useNavigate } from 'react-router-dom';

interface IRegistration {
  setShowAlert: React.Dispatch<React.SetStateAction<boolean>>,
  setAlertMessage: React.Dispatch<React.SetStateAction<string>>,
}

function Registration({ setShowAlert, setAlertMessage }: IRegistration) {
  const [submitError, setSubmitError] = useState<string>('');
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const navigate = useNavigate();

  const {
    handleSubmit,
    register,
    formState: { errors },
    reset,
  } = useForm<IRegistrationFormInputs>();

  const onSubmit = handleSubmit((data) => {
    setIsLoading(true);

    mainApi.signUp(data)
      .then((res) => {
        if (res.ok) {
          reset()
          navigate('/sign-in', { replace: true });

          setShowAlert(true);
          setAlertMessage('Вы успешно зарегистрировались!')
        }
      })
      .catch((err) => {
        if (err.status === 403) {
          setSubmitError('Неправильно заполнены данные.')
        } else {
          setSubmitError('Сервер не отвечает.')
        }
      })
      .finally(() => setIsLoading(false));
  });

  return (
    <MainForm
      name='signUp'
      onSubmit={onSubmit}
    >
      <Input
        type='text'
        width='100%'
        placeholder='Логин'
        size='lg'
        {
        ...register(
          'login',
          {
            required: 'Обязательное поле',
            minLength: {
              value: 3,
              message: 'Длина поля должна быть не менее 3 символов.'
            }
          }
        )
        }
      />
      <div className='main-form__error'>
        <Text fontSize='xs' color='red'>{errors.login?.message || ''}</Text>
      </div>
      <Input
        type='password'
        width='100%'
        placeholder='Пароль'
        size='lg'
        {
        ...register(
          'password',
          {
            required: 'Обязательное поле',
            minLength: {
              value: 7,
              message: 'Длина поля должна быть не менее 7 символов.'
            }
          }
        )
        }
      />
      <div className='main-form__error'>
        <Text fontSize='xs' color='red'>{errors.password?.message || ''}</Text>
      </div>
      <Input
        type='text'
        width='100%'
        placeholder='Имя'
        size='lg'
        {
        ...register(
          'firstName',
          {
            required: 'Обязательное поле',
            minLength: {
              value: 2,
              message: 'Длина поля должна быть не менее 2 символов.'
            }
          }
        )
        }
      />
      <div className='main-form__error'>
        <Text fontSize='xs' color='red'>{errors.firstName?.message || ''}</Text>
      </div>
      <Input
        type='text'
        width='100%'
        placeholder='Фамилия'
        size='lg'
        {
        ...register(
          'lastName',
          {
            required: 'Обязательное поле',
            minLength: {
              value: 2,
              message: 'Длина поля должна быть не менее 2 символов.'
            }
          }
        )
        }
      />
      <div className='main-form__error'>
        <Text fontSize='xs' color='red'>{errors.lastName?.message || ''}</Text>
      </div>
      <Input
        type='email'
        width='100%'
        placeholder='Почта'
        size='lg'
        {
        ...register(
          'email',
          {
            required: 'Обязательное поле',
            pattern: {
              value: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
              message: 'Введите корректный адрес электронной почты.'
            }
          }
        )
        }
      />
      <div className='main-form__error'>
        <Text fontSize='xs' color='red'>{errors.email?.message || ''}</Text>
      </div>
      <Input
        type='number'
        width='100%'
        placeholder='Телефон'
        size='lg'
        {...register('phoneNumber', { required: 'Обязательное поле.' })}
      />
      <div className='main-form__error'>
        <Text fontSize='xs' color='red'>{errors.phoneNumber?.message || ''}</Text>
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
          : 'Зарегистрироваться'
        }
      </Button>
    </MainForm>
  )
}

export default Registration;
