# resume-api

REST API for create and management resume

## T贸picos
*  **[馃摑 Requerimientos / tecnologias](#-requerimientos)**
*  **[馃殌 Solucion](#-Solucion)**
*  **[馃梻 Estructura de Directorios](#-estructura-de-directorios)**

## 馃摑 Requerimientos / Tecnologias
```
Java 11
Spring boot 2.5.0
Junit 5
H2
Spring security
Swagger
lombok
```
## 馃殌 Solucion

Utilizando el framework de Java Spring se realiz贸 un REST API de microservicios para gestionar los principales 
caracter铆sticas que poseen un curr铆culum, adicionalmente posee el manejo de login mediante JWT, y gesti贸n de roles en 
el sistema.  Para facilitar el despliegue y utilizaci贸n de la aplicaci贸n se utiliz贸 la base de datos H2 corriendo de 
forma embebida en el proyecto, la aplicaci贸n posee un swagger para la documentaci贸n y prueba de cada uno de los servicios.

## 馃梻 Estructura de Directorios
```
馃摝 resume-api 
鈹?
鈹?  
鈹斺攢馃搧 src                  C贸digo fuente
鈹? 鈹?
鈹? 鈹斺攢馃搧 java               M贸dulos ra铆ces para el software
鈹? 鈹?
鈹? 鈹斺攢馃搧 test               Pruebas funcionales del software
鈹?
鈹斺攢馃搫 README.md            Documentacion del software

```
## 馃憮 Pruebas funcionales
* Se realizaron pruebas funcionales en la capa de data cubriendo:
1. AuthDataTest
2. EducationDataResume
3. EmploymentDataTest
4. ResumeDataTest
5. SkillDataTest
```