# Sistema de Gestión de inventario y alquileres - EnEscena

## Descripción
Aplicación en Java para la empresa EnEscena que gestiona el inventario de equipos y los alquileres de los clientes. Permite administrar el inventario de equipos, cotizar alquileres, procesar alquileres, devoluciones y generar un reporte general.

## Conceptos aplicados
- Abstracción: Clase abstracta `Equipo` que contiene los métodos comunes a todos los equipos.
- Herencia: Clases `Proyector`, `CamaraVideo` y `EquipoSonido` que heredan de `Equipo`.
- Encapsulamiento: Propiedades privadas y métodos protegidos en `Equipo`.
- Polimorfismo: Métodos abstractos en `Equipo` que se sobrescriben en las clases concretas.

## Reglas de cobro:
- Proyectores: Q 50.00 por día, si es inalámbrico.
- Cámaras de video: Q 75.00 extra fijo si la resolución es superior a 1080p.
- Equipos de sonido: Q 100.00 por kW por la cantidad de días.

## Ejecución
Clone el repositorio o descargue los archivos '.java'. Compile los archivos desde la terminal con el siguiente comando:
```bash
javac *.java
java Main
```