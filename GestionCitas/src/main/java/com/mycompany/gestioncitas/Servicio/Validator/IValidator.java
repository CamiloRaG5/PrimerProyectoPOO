package com.mycompany.gestioncitas.Servicio.Validator;

public interface IValidator<T> {
    /**
     * Valida un objeto. Si la validación falla, lanza una ValidationException.
     * @param objeto El objeto a validar.
     * @throws ValidationException si el objeto no es válido.
     */
    void validar(T objeto) throws ValidationException;
}
