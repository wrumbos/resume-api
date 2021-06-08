# resume-api

REST API for create and management resume

## Tópicos
*  **[📝 Requerimientos / tecnologias](#-requerimientos)**
*  **[🚀 Solucion](#-Solucion)**
*  **[🗂 Estructura de Directorios](#-estructura-de-directorios)**

## 📝 Requerimientos / Tecnologias
```
Java 11
Spring boot 2.5.0
Junit 5
H2
Spring security
Swagger
lombok
```
## 🚀 Solucion

Utilizando el framework de Java Spring se realizó un REST API de microservicios para gestionar los principales 
características que poseen un currículum, adicionalmente posee el manejo de login mediante JWT, y gestión de roles en 
el sistema.  Para facilitar el despliegue y utilización de la aplicación se utilizó la base de datos H2 corriendo de 
forma embebida en el proyecto, la aplicación posee un swagger para la documentación y prueba de cada uno de los servicios.

## 🗂 Estructura de Directorios
```
📦 resume-api 
│
│  
└─📁 src                  Código fuente
│ │
│ └─📁 java               Módulos raíces para el software
│ │
│ └─📁 test               Pruebas funcionales del software
│
└─📄 README.md            Documentacion del software

```
## 👓 Pruebas funcionales
* Se realizaron pruebas funcionales en la capa de data cubriendo:
1. AuthDataTest
2. EducationDataResume
3. EmploymentDataTest
4. ResumeDataTest
5. SkillDataTest
```