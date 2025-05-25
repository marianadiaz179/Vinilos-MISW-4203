# Proyecto: Vinilos

Este proyecto es una aplicación móvil desarrollada en **Kotlin**, siguiendo el patrón de arquitectura **MVVM** y utilizando un **Repository Pattern** y **Service Adapter** para la gestión de datos y conexión con la API. La aplicación hasta este punto visualizar un catálogo de álbumes de manera fluida y moderna.


---
# !! Aviso Importante

Al momento de esta entrega, la URL del backend, que originalmente estaba expuesta públicamente (https://backvynils-q6yc.onrender.com/), no está funcionando correctamente. El APK, que se encuentra en la carpeta `app/releases/vinilos-sprint3.apk`, se generó utilizando la IP de una instancia EC2 donde se levantó el backend para para poder probarlo correctamente en dispositivos físicos (http://44.195.111.97:3000/)

El APK de esta entrega se encuentra en `app/releases/vinilos-sprint-3.apk`, se ha mantenido el apk de las entregas anteriores en caso de ser necesario y se encuentra en la misma carpeta `app/releases/vinilos-sprint-1.apk` y `app/releases/vinilos-sprint-2.apk`

En el código principal del repositorio, la URL que conecta al backend se ha actualizado a `http://10.0.2.2:3000/` para facilitar las pruebas locales.(ya que esta es la IP que se expone en un simulador de Android). Para que la aplicación funcione correctamente de manera local, debes asegurarte de seguir los pasos de la sección del **README** para levantar el backend localmente, ya que esta es la URL que el código utilizará durante el desarrollo.


## Requisitos Previos

Antes de correr el proyecto de manera local, asegúrate de tener instalado:

- **Android Studio** (preferentemente versión Electric Eel o superior)
- **JDK** 11 o superior
- **Gradle** (Android Studio ya maneja Gradle de manera interna)
- **Dispositivo físico** o **emulador** configurado para pruebas
- **Conexión a internet**

## Tecnologías Principales

- Kotlin
- Espresso

## Cómo correr el Backend

El backend de la aplicación está disponible en el siguiente repositorio:

[https://github.com/TheSoftwareDesignLab/BackVynils](https://github.com/TheSoftwareDesignLab/BackVynils)

### 1. Clona el repositorio del backend

```bash
git clone https://github.com/TheSoftwareDesignLab/BackVynils.git
```

### 2. Asegúrate de tener Node.js v18 instalado

Puedes verificar la versión de Node.js con el siguiente comando: ``` node -v ```

### 3. Instala las dependencias del backend

Navega al directorio del proyecto y ejecuta el siguiente comando para instalar todas las dependencias necesarias:

``` cd BackVynils ```


``` npm install ```

### 4. Configura la Base de Datos

- En PgAdmin crea una base de datos en alguno de tus servidores con el nombre ```vynils```

- En el repositorio del backend en el archivo ```ormconfig.json``` ubicado en la raiz del repositorio actualiza las credenciales de postgres con tus datos para que se conecten a tu base de datos local


### 5. Ejecuta el BackEnd

Una vez instaladas las dependencias, ejecuta el siguiente comando para iniciar el servidor:

``` npm run start ```

El backend se desplegará en http://localhost:3000.

### 6. (Opcional) Cambiar la URL de conexión en el frontend

Si necesitas cambiar la URL de conexión entre el frontend y el backend, sigue estos pasos:

- Abre el archivo NetworkServiceAdapter.kt en el siguiente directorio:
```Vinilos-MISW-4203/app/src/main/java/com/app/vinilos_misw4203/network/NetworkServiceAdapter.kt```

- En la línea 18, cambia la variable BASE_URL para que apunte a la URL correcta del backend, recuerda que la IP que se debe colocar es la que se genera entre el emulador y tu PC:

    ```const val BASE_URL = "http://10.0.2.2:3000"  // Cambia aquí si es necesario```


## Cómo correr el proyecto localmente

### 1. Clona el repositorio

```bash
git clone <https://github.com/marianadiaz179/Vinilos-MISW-4203.git>

```

### 2. Abre el proyecto en Android Studio

- Selecciona File > Open y navega a la carpeta del proyecto que clonaste.

- Android Studio detectará automáticamente el proyecto como un proyecto de Android.

---
### !!! Aviso importante

Asegurese que en el archivo oculto `local.properties` esté definido correctamente el path al sdk de Android

Ejemplo: `sdk.dir=/Users/<your_user>/Library/Android/sdk`

---

### 3. Sincroniza el proyecto

- Asegúrate de que Gradle sincronice correctamente.

- Si ves un aviso para sincronizar el proyecto, haz clic en “Sync Now”

### 4. Configura tu dispositivio

- Puedes utilizar un emulador o conectar un dispositivo Android físico en modo desarrollador.

- Asegúrate de que el dispositivo esté disponible en la lista de dispositivos de ejecución de Android Studio.

### 5. Compila y Corre la Aplicación

- Haz clic en el botón Run (ícono de play verde) o usa el atajo Shift + F10.

- Selecciona el dispositivo donde deseas ejecutar la aplicación.

- Si encuentras errores de dependencias, puedes limpiar y reconstruir el proyecto:


```bash
./gradlew clean build
```

## Resultados de Preubas

Durante este sprint se realizaron pruebas E2E con Espresso para validar HU001 y HU002, las cuales están documentadas y automatizadas en el proyecto. 

Para correr las pruebas desde Android Studio desde el directorio haga click derecho sobre el archivo y busque la opción que permita ejecutar estas pruebas, al finalizar la ejecución se podrá ver en la parte inferior de la terminal los resultados.

https://github.com/marianadiaz179/Vinilos-MISW-4203/wiki/Sprint-3#espresso-testing


Adicionalmente, se aplicaron pruebas de calidad y desempeño como Monkey Testing, Firebase Test Lab, y un profiler en Android Studio, cuyos hallazgos están registrados también en la wiki del Sprint 3.

https://github.com/marianadiaz179/Vinilos-MISW-4203/wiki/Sprint-3#monkey-testing
https://github.com/marianadiaz179/Vinilos-MISW-4203/wiki/Sprint-3#firebase-test-lab
https://github.com/marianadiaz179/Vinilos-MISW-4203/wiki/Sprint-3#profiler


Se llevó a cabo un Code Inspection orientado a microoptimización, buenas prácticas en consumo de memoria y manejo de ANRs. Los detalles están disponibles en las siguientes secciones:

https://github.com/marianadiaz179/Vinilos-MISW-4203/wiki/Sprint-2#code-inspection
https://github.com/marianadiaz179/Vinilos-MISW-4203/wiki/Sprint-2#micro-optimizaciones
https://github.com/marianadiaz179/Vinilos-MISW-4203/wiki/Sprint-2#buenas-pr%C3%A1cticas-para-el-consumo-de-memoria
https://github.com/marianadiaz179/Vinilos-MISW-4203/wiki/Sprint-2#buenas-pr%C3%A1cticas-para-anrs

Finalmente, se trabajó en la accesibilidad de la aplicación, realizando mejoras sugeridas por herramientas como Accessibility Scanner y Talkback. Los resultados están en:
https://github.com/marianadiaz179/Vinilos-MISW-4203/wiki/Sprint-3#accesibilidad


## Autores

- Mariana Diaz - m.diaza2@uniandes.edu.co
