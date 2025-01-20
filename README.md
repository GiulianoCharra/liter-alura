# Liter-Alura 📚

**Liter-Alura** es un catálogo de libros interactivo que permite realizar consultas sobre libros y autores utilizando la
API de Gutendex. Los datos obtenidos se almacenan en una base de datos PostgreSQL para una gestión eficiente y
persistente. Este proyecto está construido utilizando **Spring Boot** y sigue principios de diseño como SOLID para
lograr un código limpio y mantenible.

---

## Características ✨

- Búsqueda de libros por título o autor en la API de Gutendex.
- Guardado de libros y autores en una base de datos PostgreSQL.
- Listado de libros y autores.
- Consulta de autores vivos en un año determinado.
- Filtrado de libros por idioma.

---

## Tecnologías 🛠️

- **Lenguaje:** Java 17+
- **Framework Backend:** Spring Boot 3.x
    - Spring Data JPA
    - Spring Web
- **Base de Datos:** PostgreSQL
- **Cliente HTTP:** HttpClient
- **Gestión de Dependencias:** Maven
- **ORM:** Hibernate
- **Otros:**
    - Lombok (para reducir boilerplate)
    - Jackson (para trabajar con JSON)
    - Git (control de versiones)

---

## Estructura del Proyecto 📂

El proyecto sigue una estructura modular, basada en una arquitectura limpia:

```
## liter-alura
- `.idea/`  
- `.mvn/`  
- `src/`  
  - `main/`  
    - `java/`  
      - `oracle.alura.challenge.literalura/`  
        - `application/`  
          - `service/`  
            - `BookService.java`  
            - `BookServiceImpl.java`  
        - `domain/`  
          - `entities/`  
            - `Author.java`  
            - `Book.java`  
          - `repositories/`  
            - `AuthorRepository.java`  
            - `BookRepository.java`  
        - `infrastructure/`  
          - `client/`  
            - `dto/`  
              - `AuthorResponse.java`  
              - `BookResponse.java`  
            - `GutendexClient.java`  
            - `GutendexClientImpl.java`  
          - `config/`  
            - `HttpClientConfig.java`  
          - `persistence/`  
            - `JpaBookRepository.java`  
        - `presentation/`  
          - `cli/`  
            - `BookCLI.java`  
        - `LiterAluraApplication.java`  
  - `resources/`  
    - `application.properties`  
- `test/`  
- `target/`  
- `.gitattributes`  
- `.gitignore`  
- `HELP.md`  
- `liter-alura.iml`  
- `mvnw`  
- `mvnw.cmd`  
- `pom.xml`
```

---

## Requisitos Previos 🚀

1. **Java 17 o superior**: Asegúrate de tener instalado Java 17+ en tu máquina.
2. **Maven**: Para la gestión de dependencias.
3. **PostgreSQL**:
    - Crea una base de datos llamada `liter-alura`.
    - Configura las credenciales de usuario y contraseña en el archivo `application.properties`.

---

## Configuración Inicial ⚙️

### **1. Clonar el Repositorio**

```bash
  git clone https://github.com/tu-usuario/liter-alura.git
  cd liter-alura
```

### **2. Configurar el Archivo `application.properties`**

Edita el archivo `src/main/resources/application.properties` con las credenciales de tu base de datos PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/liter-alura
spring.datasource.username=postgres
spring.datasource.password=123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
```

### **3. Instalar Dependencias**

```bash
mvn clean install
```

---

## Ejecución del Proyecto ▶️

### **Desde la Consola**

1. Ejecuta el siguiente comando:
   ```bash
   mvn spring-boot:run
   ```

2. Una vez iniciado, el programa interactivo estará disponible en la consola. Sigue las instrucciones del menú.

---

## Uso del Proyecto 📖

Al ejecutar el programa, verás un menú interactivo en la consola con opciones como:

1. **Buscar libro por título o autor:**  
   Permite buscar un libro en la API de Gutendex y almacenarlo en la base de datos.

2. **Listar todos los libros:**  
   Muestra una lista de todos los libros almacenados.

3. **Listar todos los autores:**  
   Muestra una lista de todos los autores almacenados.

4. **Listar autores vivos en un año determinado:**  
   Filtra los autores que estaban vivos en un año específico.

5. **Listar libros por idioma:**  
   Muestra los libros almacenados según un idioma específico.

6. **Salir:**  
   Finaliza la aplicación.

---

## API de Gutendex 🌐

Se utiliza la API de Gutendex para obtener información sobre libros y autores. Más información en
su [documentación oficial](https://gutendex.com).

---

## Pruebas 🧪

### **Ejecutar Pruebas**

El proyecto incluye pruebas unitarias para los servicios. Para ejecutarlas:

```bash
  mvn test
```

---

## Contribución 🤝

Si deseas contribuir al proyecto:

1. Haz un fork del repositorio.
2. Crea una rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commits (`git commit -m 'Añadida nueva funcionalidad'`).
4. Sube los cambios (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

---

## Licencia 📜

Este proyecto está bajo la Licencia MIT. Puedes consultar más detalles en el archivo [LICENSE](LICENSE).

---

## Autor 👨‍💻

Desarrollado por **[Tu Nombre](https://github.com/tu-usuario)**. Si tienes preguntas, no dudes en contactarme.

---

## ¿Qué incluye este README?**

- **Sección introductoria:** Resumen del proyecto y sus características principales.
- **Tecnologías utilizadas.**
- **Estructura del proyecto:** Breve descripción de la arquitectura.
- **Instrucciones para configurar y ejecutar el proyecto.**
- **Opciones del menú interactivo.**
- **Enlace a la documentación de la API utilizada.**

---
