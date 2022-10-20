import React, { useState, useContext } from 'react';
import { useForm, Controller } from 'react-hook-form';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Password } from 'primereact/password';
import { classNames } from 'primereact/utils';

import { AuthContext } from '../../context/AuthContext';

import './login.css'

const LoginPage = () => {

    const { authenticated, login } = useContext(AuthContext);

    const [formData, setFormData] = useState({});

    const defaultValues = {
        name: '',
        password: '',
    }

    const { control, formState: { errors }, handleSubmit, reset } = useForm({ defaultValues });

    const onSubmit = (data) => {

        setFormData(data);
        console.log(data)
        login(data.name, data.password)
        reset();
    };


    const getFormErrorMessage = (name) => {
        return errors[name] && <small className="p-error">{errors[name].message}</small>
    };

    return (
        <div >
            <div className="form-demo">
                <div className="flex justify-content-center">
                    <div className="card">
                        <h1 className="text-center">Login</h1>
                        <form onSubmit={handleSubmit(onSubmit)} className="p-fluid">
                            <div className="field">
                                <span className="p-float-label">
                                    <Controller name="name" control={control} rules={{ required: 'Name is required.' }} render={({ field, fieldState }) => (
                                        <InputText id={field.name} {...field} autoFocus className={classNames({ 'p-invalid': fieldState.invalid })} />
                                    )} />
                                    <label htmlFor="name" className={classNames({ 'p-error': errors.name })}>Name</label>
                                </span>
                                {getFormErrorMessage('name')}
                            </div>

                            <div className="field mt-3">
                                <span className="p-float-label">
                                    <Controller name="password" control={control} rules={{ required: 'Password is required.' }} render={({ field, fieldState }) => (
                                        <Password id={field.name} {...field} feedback={false} toggleMask className={classNames({ 'p-invalid': fieldState.invalid })} />
                                    )} />
                                    <label htmlFor="password" className={classNames({ 'p-error': errors.password })}>Password</label>
                                </span>
                                {getFormErrorMessage('password')}
                            </div>


                            {/* <div className="field-checkbox">
                            <Controller name="accept" control={control} rules={{ required: true }} render={({ field, fieldState }) => (
                                <Checkbox inputId={field.name} onChange={(e) => field.onChange(e.checked)} checked={field.value} className={classNames({ 'p-invalid': fieldState.invalid })} />
                            )} />
                            <label htmlFor="accept" className={classNames({ 'p-error': errors.accept })}>I agree to the terms and conditions*</label>
                        </div> */}

                            <Button type="submit" label="Login" className="mt-2" />
                        </form>
                    </div>
                </div>
            </div>


        </div>
    )
}

export default LoginPage