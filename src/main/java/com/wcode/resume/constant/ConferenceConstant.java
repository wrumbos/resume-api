package com.wcode.resume.constant;

public interface ConferenceConstant {

    //Resume
    String ERROR_FIND_RESUME = "Ocurrio un error encontrando el resume";
    String ERROR_CREATE_RESUME = "Ocurrio un error creando el resume";
    String ERROR_UPDATE_RESUME = "Ocurrio un error actualizando el resume";
    String ERROR_CONSOLIDATE_RESUME = "Ocurrio un error actualizando el resume";

    //Log Resume
    String LOG_FIND_RESUME = "resume: Iniciando servicio getResumeById";
    String LOG_CREATE_RESUME = "resume: Iniciando servicio registerResume";
    String LOG_UPDATE_RESUME = "resume: Iniciando servicio updateResume";
    String LOG_CONSOLIDATE_RESUME = "resume: Iniciando servicio consolidate ";


    //AUTH CONTROLLER
    String ERROR_CREATE_USER = "Ocurrio un error creando el usuario";
    String ERROR_LOGIN_USER = "Ocurrio un error autenticando el usuario";

    //AUTH LOG

    String LOG_CREATE_USER = "AUTH: Iniciando servicio registerUser";
    String LOG_LOGIN_USER = "AUTH: Iniciando servicio authenticateUser ";



    String LOG_ERROR_EXCEPTION_HANDLER = "Ha ocurrido un error: ";


}
