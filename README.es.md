# Meli
Aplicación Android para la tienda "Mercado Libre" utilizando las últimas componentes de arquitectura e inyección de dependencias.

## Vista previa del primer enfoque


## Tecnologías

### Bibliotecas
- **Android ViewModels**
- **Android Flow:** Obtener lista de productos
- **Dagger-Hilt:** Inyección de dependencias
- **Retrofit:** Consumo de API
- **Gson**: Mapear respuestas de solicitudes
- **Okhttp3-logging-interceptor:** Un interceptor de OkHttp que registra datos de solicitud y respuesta HTTP.
- **Mockito:** Mocks en pruebas
- **Coil:** Cargar imágenes
- **Compose:** Crear IU
- **Compose-navigation:** Ayuda a navegar entre destinos componibles.

### Estrategia de fondo:
- **Kotlin Coroutines**

### Calidad del código
**Pruebas unitarias**: Se crearon pruebas unitarias para los módulos utilizando Mockito y Junit.

## Arquitectura
- Se utiliza **Clean Architecture** para toda la aplicación con 3 capas: Datos, Dominio, presentación, y se utiliza **MVVM** para la capa de presentación.

### Capa de IU

1. Patrón de presentación: MVVM
2. Flujo de datos unidireccional
3. Observables MVVM con estado (Compose)
4. Estados de IU: data class
5. Eventos de IU: clase sellada
6. Navegación

### Capa de datos

1. Inversión de dependencia
2. Repositorios y fuentes de datos
3. Manejo de errores

### POR HACER
**Agregar al carrito** : Agregar producto seleccionado a un carrito
**Modelar error con más detalles** : Agregar nuevos modelos de errores para manejarlos de manera específica
- **Pruebas de IU**: Se crearon pruebas unitarias para la interfaz de usuario.
- **Estrategia BuildSrc**: Mejorar las fuentes de compilación de dependencias
- **Animaciones**: Agregar animaciones a la IU
