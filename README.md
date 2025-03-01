📚 SISTEMA DE GESTION DE BIBLIOTECA 📚

DESCRIPCION

Este proyecto implementa un sistema de gestión de biblioteca en Java, permitiendo la administración de libros, usuarios y préstamos. El proyecto utiliza JUnit 5 para pruebas unitarias, Mockito para simular repositorios y Log4j2 para el registro de eventos.

FUNCIONALIDADES

GESTION DE LIBROS:

📖 Agregar un libro con ID, título y autor.

📖 Obtener un libro mediante su ID.

GESTION DE USUARIOS:

👤 Crear un usuario con ID y nombre.

GESTION DE PRESTAMOS:

📅 Prestar un libro a un usuario registrando la fecha del préstamo.

📅 Obtener todos los préstamos de un usuario específico.

PRUEBAS UNITARIAS

Se utilizan JUnit 5 y Mockito para validar las siguientes funcionalidades:

SERVICIO DE LIBROS:

📝 Adición de un libro.

🗑️ Eliminación de un libro.

🔍 Recuperación de un libro por ID.

❌ Prueba de libro no existente.

📚 Recorrido de la lista de libros.

SERVICIO DE PRESTAMOS:

📅 Agregar préstamo de un libro disponible.

🚫 Intentar agregar préstamo de un libro no disponible.

🔄 Devolver un préstamo existente.

❌ Intentar devolver un préstamo no existente.

SERVICIO DE USUARIOS:

🆕 Creación de un usuario con y sin fecha de registro.

🔍 Recuperación de un usuario por ID.

❌ Prueba de usuario no existente.

🗑️ Eliminación de un usuario.

❌ Intentar eliminar un usuario no existente.

📋 Recorrido de la lista de usuarios.

✉️ Cambio de correo electrónico de un usuario.

📞 Cambio de teléfono de un usuario.

MANEJO DE EXCEPCIONES

NotFoundException: Excepción personalizada lanzada si un recurso no se encuentra.

REGISTRO DE EVENTOS

Se utiliza Log4j2 para el registro de eventos importantes y errores en el sistema.

REQUISITOS

Java 11+

Maven

JUnit 5

Mockito

Log4j2
